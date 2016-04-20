package da.chuan.musicbox;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import da.chuan.musicbox.constants.DatabaseConstants;
import da.chuan.musicbox.constants.HandlerFlag;
import da.chuan.musicbox.database.CatalogueDao;
import da.chuan.musicbox.database.MusicInfoDao;
import da.chuan.musicbox.database.MusicOpenHelper;
import da.chuan.musicbox.pojo.Catalogue;
import da.chuan.musicbox.pojo.MusicInfo;
import da.chuan.musicbox.service.MusicService;
import da.chuan.musicbox.R;

/**
 * 主页面
 * 
 */
public class MainActivity extends Activity {
	private GridView categoryView;
	private Button scannBtn;// 搜索按钮
	private Button prevBtn;// 上一首歌按钮
	private Button nextBtn;// 下一首歌按钮
	private Button playBtn;// 播放按钮
	private Button pauseBtn;// 暂停按钮
	private FrameLayout frameContainer;// 底部栏上面的View容器，用于在所有目录和目录内容之间切换显示

	private ListView categoryMusicView;// 目录歌曲页面的歌曲列表View
	private Button categoryMusicBackBtn;// 目录歌曲页面的返回按钮
	private ViewGroup progrossView;// 进度条

	private TextView musicNameText;// 底部栏的当前播放的音乐名

	private List<Catalogue> catalogues;// 所有音乐目录
	private CatalogAdapter catalogAdapter;// 用于显示音乐目录的适配器

	private ArrayList<MusicInfo> musicInfos;// 某目录的所有音乐信息
	private CatalogMusicAdapter catalogMusicAdapter;// 用于显示某目录的所有音乐信息的适配器

	private MusicHandler musicHandler;// 页面更新操作由它处理（一般在线程中调用）

	private CatalogueDao catalogueDao;// 目录数据库操作
	private MusicInfoDao musicInfoDao;// 音乐信息数据库操作

	private Catalogue curCatalogue;// 目录歌曲页面的当前目录

	BroadcastReceiver receiver;// 用于更新页面（音乐播放的状态）

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * 初始化
	 */
	private void init() {
		categoryView = (GridView) findViewById(R.id.view_category);
		frameContainer = (FrameLayout) findViewById(R.id.viewgroup_frame_container);
		scannBtn = (Button) findViewById(R.id.btn_scann);
		prevBtn = (Button) findViewById(R.id.btn_opt_prev);
		nextBtn = (Button) findViewById(R.id.btn_opt_next);
		playBtn = (Button) findViewById(R.id.btn_opt_play);
		pauseBtn = (Button) findViewById(R.id.btn_opt_pause);

		musicNameText = (TextView) findViewById(R.id.txt_music_name);

		categoryMusicView = (ListView) findViewById(R.id.listview_music);
		categoryMusicBackBtn = (Button) findViewById(R.id.btn_music_back);
		progrossView = (ViewGroup) findViewById(R.id.viewgroup_progress);

		musicHandler = new MusicHandler();

		catalogueDao = CatalogueDao.getInstance(this);
		musicInfoDao = MusicInfoDao.getInstance(this);
		receiver = new MusicRecevier();

		initListner();
		initAfter();
	}

	/**
	 * 初始化监听器（处理点击事件）
	 */
	private void initListner() {
		scannBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 调整到搜索页面
				Intent intent = new Intent(MainActivity.this,
						ScannActivity.class);
				startActivity(intent);

			}
		});

		categoryView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 显示进度条
//				progrossView.setVisibility(View.VISIBLE);
				// 隐藏所有目录页面
				frameContainer.getChildAt(1).setVisibility(View.GONE);
				// 加载所选择目录的所有音乐信息
				curCatalogue = catalogues.get(position);
				new CatalogueMusicLoadTask(parent.getContext(), catalogues.get(
						position).getId()).start();
			}
		});

		categoryMusicBackBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 清空所有音乐信息
				musicInfos.clear();
				// 更新页面
				catalogMusicAdapter.notifyDataSetChanged();
				// 显示所有目录页面
				frameContainer.getChildAt(1).setVisibility(View.VISIBLE);
			}
		});

		categoryMusicView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 发送数据（播放操作，第几首，要播放的所有音乐）给音乐播放服务
				Intent intent = new Intent(parent.getContext(),
						MusicService.class);
				intent.putExtra(MusicService.OPT, MusicService.OPT_PLAY);
				intent.putExtra(MusicService.CUR_PLAY_INDEX, position);
				intent.putExtra(MusicService.ENTRY, musicInfos);
				startService(intent);
				// 播放按钮隐藏（则暂停按钮显示）
				playBtn.setVisibility(View.GONE);
				// 设置当初播放的歌曲名
				musicNameText.setText(musicInfos.get(position).getName());
			}
		});

		categoryMusicView
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, final int position, long id) {
						// 弹框出现，处理删除操作
						AlertDialog.Builder builder = new AlertDialog.Builder(
								parent.getContext());
						builder.setTitle("操作");
						builder.setMessage("是否要删除");
						builder.setPositiveButton("确定",
								new AlertDialog.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// 删除歌曲
										MusicInfo musicInfo = musicInfos
												.get(position);
										musicInfoDao.delete(musicInfo.getId(),
												musicInfo.getCatalogueId());
										// 弹框消失
										dialog.dismiss();
										// 进度条出现
										progrossView
												.setVisibility(View.VISIBLE);
										// 重新加载当前目录下所有歌曲信息
										new CatalogueMusicLoadTask(
												MainActivity.this, curCatalogue
														.getId()).start();
									}
								});
						builder.setNegativeButton("取消",
								new AlertDialog.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								});
						builder.create().show();
						return true;
					}
				});
	}

	/**
	 * 初始化最后的步骤
	 */
	private void initAfter() {
		registerBroatcast();
		// 检查数据库并加载所有目录
		new DatabaseLoadTask(this).start();
	}

	/**
	 * 创建目录操作
	 * 
	 * @param v
	 */
	public void createCatalog(View v) {
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.layout_catalogue_create, null);
		final EditText nameEdit = (EditText) view
				.findViewById(R.id.edit_catalogue_name);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("创建目录");
		builder.setPositiveButton("确定", new AlertDialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				String name = nameEdit.getEditableText().toString();
				// 验证输入的名称是否为空
				if (name == null || name.trim().equals("")) {
					Toast.makeText(MainActivity.this, "请输入目录名",
							Toast.LENGTH_SHORT).show();
				} else {
					// 添加目录
					Catalogue catalogue = new Catalogue();
					catalogue.setName(name);
					catalogueDao.save(catalogue);

					new DatabaseLoadTask(MainActivity.this).start();

				}
			}
		});
		builder.setNegativeButton("取消", new AlertDialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.setView(view);
		builder.create().show();

	}

	/**
	 * 音乐继续播放操作
	 * 
	 * @param v
	 */
	public void resume(View v) {
		// playBtn.setVisibility(View.GONE);
		startService(MusicService.OPT_RESUME);
	}

	/**
	 * 音乐暂停操作
	 * 
	 * @param v
	 */
	public void pause(View v) {
		// playBtn.setVisibility(View.VISIBLE);
		startService(MusicService.OPT_PAUSE);
	}

	/**
	 * 播放上一首的操作
	 * 
	 * @param v
	 */
	public void previous(View v) {
		startService(MusicService.OPT_PREVIOUS);
	}

	/**
	 * 播放下一首的操作
	 * 
	 * @param v
	 */
	public void next(View v) {
		startService(MusicService.OPT_NEXT);
	}

	private void stop() {
		startService(MusicService.OPT_STOP);
	}

	/**
	 * 根据类型，让音乐服务进行不同处理（播放，暂停，上，下一首）
	 * 
	 * @param optType
	 */
	private void startService(int optType) {
		Intent intent = new Intent(MainActivity.this, MusicService.class);
		intent.putExtra(MusicService.OPT, optType);
		intent.putExtra(MusicService.ENTRY, musicInfos);
		startService(intent);
	}

	/**
	 * 目录适配器
	 * 
	 * 
	 */
	private class CatalogAdapter extends BaseAdapter {
		private List<Catalogue> catalogues;

		public CatalogAdapter(List<Catalogue> catalogues) {
			this.catalogues = catalogues;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return catalogues.size();
		}

		@Override
		public Object getItem(int position) {
			return catalogues.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater inflater = LayoutInflater.from(parent
						.getContext());
				convertView = inflater.inflate(R.layout.layout_catalogue_item,
						null);
			}
			TextView nameTxt = (TextView) convertView
					.findViewById(R.id.txt_catalogue_name);
			nameTxt.setText(catalogues.get(position).getName());
			return convertView;
		}

	}

	/**
	 * 目录适配器
	 * 
	 * @author zdc
	 * 
	 */
	private class CatalogMusicAdapter extends BaseAdapter {
		private List<MusicInfo> musicInfos;

		public CatalogMusicAdapter(List<MusicInfo> musicInfos) {
			this.musicInfos = musicInfos;
		}

		@Override
		public int getCount() {
			return musicInfos.size();
		}

		@Override
		public Object getItem(int position) {
			return musicInfos.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = new TextView(parent.getContext());
			}
			((TextView) convertView)
					.setText(musicInfos.get(position).getName());
			return convertView;
		}

	}

	/**
	 * 检查数据库
	 * 
	 * @author zdc
	 * 
	 * 
	 */
	private class DatabaseLoadTask extends Thread {

		private Context context;

		public DatabaseLoadTask(Context context) {
			this.context = context;
		}

		@Override
		public void run() {
			super.run();
			// 获取当前数据库版本，如不存在数据库会自动创建
			int currentVersion = getResources().getInteger(
					R.integer.db_current_version);
			MusicOpenHelper musicOpenHelper = new MusicOpenHelper(context,
					DatabaseConstants.DB_NAME, null, currentVersion);
			SQLiteDatabase db = musicOpenHelper.getWritableDatabase();
			if (db != null) {
				db.close();
			}

			// 加载目录
			new CatalogueLoadTask(MainActivity.this).start();
		}
	}

	/**
	 * 目录加载任务
	 * 
	 * @author zdc
	 * 
	 */
	private class CatalogueLoadTask extends Thread {
		private Context context;

		public CatalogueLoadTask(Context context) {
			this.context = context;
		}

		@Override
		public void run() {
			super.run();
			Message msg = Message.obtain();
			List<Catalogue> catalogues = catalogueDao.queryList();
			msg.obj = catalogues;
			msg.what = HandlerFlag.LOAD_CATALOGUE_SUCCESS;
			musicHandler.sendMessage(msg);
		}
	}

	/**
	 * 目录中的音乐加载任务
	 * 
	 * @author zdc
	 * 
	 */
	private class CatalogueMusicLoadTask extends Thread {
		private Context context;
		private long catalogueId;

		public CatalogueMusicLoadTask(Context context, long catalogueId) {
			this.context = context;
			this.catalogueId = catalogueId;
		}

		@Override
		public void run() {
			super.run();
			Message msg = Message.obtain();
			List<MusicInfo> musicInfos = musicInfoDao.qureyAll(catalogueId);
			msg.obj = musicInfos;
			msg.what = HandlerFlag.LOAD_CATALOGUE_MUSIC_SUCCESS;
			musicHandler.sendMessage(msg);
		}
	}

	private class MusicHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case HandlerFlag.LOAD_CATALOGUE_SUCCESS:// 目录加载成功
				if (msg.obj != null) {
					catalogues = (List<Catalogue>) msg.obj;
					catalogAdapter = new CatalogAdapter(catalogues);
					categoryView.setAdapter(catalogAdapter);
				}
				break;
			case HandlerFlag.LOAD_CATALOGUE_FAILED:// 目录加载失败
				Toast.makeText(MainActivity.this, "LOAD_CATALOGUE_FAILED",
						Toast.LENGTH_SHORT).show();
				break;
			case HandlerFlag.LOAD_CATALOGUE_MUSIC_SUCCESS:// 音乐信息加载成功
				if (msg.obj != null) {
					musicInfos = (ArrayList<MusicInfo>) msg.obj;
					catalogMusicAdapter = new CatalogMusicAdapter(musicInfos);
					categoryMusicView.setAdapter(catalogMusicAdapter);
					progrossView.setVisibility(View.GONE);
				}
				break;
			default:
				break;
			}
		}
	}

	/* 
	 * 返回键处理
	 */
	@Override
	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("操作");
		builder.setMessage("退出后，是否后台播放？");
		builder.setPositiveButton("确定", new AlertDialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				MainActivity.this.finish();
			}
		});
		builder.setNegativeButton("取消", new AlertDialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(MainActivity.this,
						MusicService.class);
				MainActivity.this.stopService(intent);

				dialog.dismiss();
				MainActivity.this.finish();
			}
		});
		builder.create().show();
	}

	/**
	 * 注册广播
	 */
	public void registerBroatcast() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(MusicRecevier.MUSIC_ACTION);
		registerReceiver(receiver, filter);
	}

	/**
	 * 音乐广播接受器
	 * 
	 * @author zdc
	 * 
	 */
	public class MusicRecevier extends BroadcastReceiver {
		public static final String MUSIC_ACTION = "MUSIC_ACTION";
		public static final String MUSIC_NAME = "music_name";

		public static final String MUSIC_OPT_TYPE = "music_opt_type";
		public static final int MUSIC_RESUME = 0;
		public static final int MUSIC_PAUSE = 1;
		public static final int MUSIC_PLAY = 2;

		@Override
		public void onReceive(Context context, Intent intent) {
			int type = intent.getIntExtra(MUSIC_OPT_TYPE, -1);
			switch (type) {
			case MUSIC_RESUME://继续播放
				playBtn.setVisibility(View.GONE);
				pauseBtn.setVisibility(View.VISIBLE);
				break;
			case MUSIC_PAUSE://暂停
				playBtn.setVisibility(View.VISIBLE);
				pauseBtn.setVisibility(View.GONE);
				break;
			case MUSIC_PLAY://播放
				String musicName = intent.getStringExtra(MUSIC_NAME);
				musicNameText.setText(musicName);
				playBtn.setVisibility(View.GONE);
				pauseBtn.setVisibility(View.VISIBLE);
				break;
			default:
				break;
			}
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}

}
