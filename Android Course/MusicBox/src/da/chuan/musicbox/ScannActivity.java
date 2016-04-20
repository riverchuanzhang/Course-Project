package da.chuan.musicbox;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;
import da.chuan.musicbox.constants.Constants;
import da.chuan.musicbox.constants.HandlerFlag;
import da.chuan.musicbox.database.CatalogueDao;
import da.chuan.musicbox.database.MusicInfoDao;
import da.chuan.musicbox.pojo.Catalogue;
import da.chuan.musicbox.pojo.MusicInfo;
import da.chuan.musicbox.R;

/**
 * 音乐扫描页面
 *
 */
public class ScannActivity extends Activity {
	private ListView scannResultView;//音乐扫描结果列表
	private Button addBtn;//添加按钮
	private ViewGroup progressContainer;//进度条
	private AlertDialog dialog;//弹框
	private MusicInfoDao musicInfoDao;
	private CatalogueDao catalogueDao;
	private ScannHandler scannHandler;//页面更新操作由它处理（一般在线程中调用）
	private List<MusicInfo> musicInfos;//所有音乐
	private ScannAdapter scannAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_scann);
		init();
		startScann();
	}

	private void init() {
		progressContainer = (ViewGroup) findViewById(R.id.viewgroup_progress);
		addBtn = (Button) findViewById(R.id.btn_opt_all_add);
		scannResultView = (ListView) findViewById(R.id.listview_scann_result);
		musicInfoDao = MusicInfoDao.getInstance(this);
		catalogueDao = CatalogueDao.getInstance(this);
		scannHandler = new ScannHandler();
		initListener();
	}

	private void initListener() {
		scannResultView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

			}
		});
		addBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showCatalogueDailog();				
			}
		});

	}

	/**
	 * 显示进度条
	 */
	private void showProgreess() {
		progressContainer.setVisibility(View.VISIBLE);
	}

	/**
	 * 隐藏进度条
	 */
	private void hideProgress() {
		progressContainer.setVisibility(View.INVISIBLE);
	}

	private void showCatalogueDailog() {
		final List<Catalogue> catalogues = catalogueDao.queryList();
		int size = catalogues.size();
		String[] items = new String[size];//所有音乐目录名称
		for (int i = 0; i < size; i++) {
			items[i] = catalogues.get(i).getName();
		}
		//弹框出来
		AlertDialog.Builder builder = new Builder(this);
		builder.setItems(items, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				//把歌曲添加到选择的目录中
				List<MusicInfo> musicInfos = buildSelectedMusicInfos(catalogues
						.get(which).getId());
				new CatalogueSaveMusicTask(ScannActivity.this, musicInfos)
						.start();
			}
		});
		dialog = builder.create();
		dialog.show();
	}

	private void startScann() {
		showProgreess();
		new MusicScannTask(this).start();

	}
	
	/**
	 * 返回之前的页面
	 * @param v
	 */
	public void back(View v){
		this.finish();
	}

	/**
	 * 音乐搜索
	 *
	 */
	private class MusicScannTask extends Thread {

		private Context context;
		public MusicScannTask(Context context) {
			this.context = context;
		}

		@Override
		public void run() {
			super.run();
			List<MusicInfo> musicInfos = musicInfoDao.scannMusics();
			Message msg = Message.obtain();
			msg.obj = musicInfos;
			msg.what = HandlerFlag.SCANN_MUSIC_SUCCESS;
			scannHandler.sendMessage(msg);
		}
	}

	/**
	 * 音乐保存在某个目录
	 *
	 */
	private class CatalogueSaveMusicTask extends Thread {

		private Context context;
		private List<MusicInfo> musicInfos;

		public CatalogueSaveMusicTask(Context context,
				List<MusicInfo> musicInfos) {
			this.context = context;
			this.musicInfos = musicInfos;
		}

		@Override
		public void run() {
			super.run();
			musicInfoDao.save(musicInfos);
			Message msg = Message.obtain();
			msg.what = HandlerFlag.CATALOGUE_SAVE_MUSIC_SUCCESS;
			scannHandler.sendMessage(msg);
		}
	}

	/**
	 * 构造所选择歌曲的list
	 * @param catalogId
	 * @return
	 */
	private List<MusicInfo> buildSelectedMusicInfos(long catalogId) {
		List<MusicInfo> selectedMusicInfos = new ArrayList<MusicInfo>();
		MusicInfo musicInfo;
		for (int i = 0; i < musicInfos.size(); i++) {
			musicInfo = musicInfos.get(i);
			Boolean selectStatus = (Boolean) musicInfo.getExtras().get(
					Constants.SELECT_STATUS);
			if (selectStatus != null && selectStatus.booleanValue()) {
				musicInfo.setCatalogueId(catalogId);
				selectedMusicInfos.add(musicInfo);
			}
		}
		return selectedMusicInfos;
	}

	private class ScannAdapter extends BaseAdapter {

		private List<MusicInfo> musicInfos;

		public ScannAdapter(List<MusicInfo> musicInfos) {
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
			MusicInfo musicInfo = musicInfos.get(position);
			if (convertView == null) {
				LayoutInflater inflater = LayoutInflater.from(parent
						.getContext());
				convertView = inflater.inflate(
						R.layout.layout_scann_result_item, null);
			}
			TextView textView = (TextView) convertView
					.findViewById(R.id.txt_name);
			CheckBox checkBox = (CheckBox) convertView
					.findViewById(R.id.checkbox_selecte);
			textView.setText(musicInfo.getName());

			checkBox.setTag(R.id.tag_position, position);
			Boolean selectStatus = (Boolean) musicInfo.getExtras().get(
					Constants.SELECT_STATUS);
			if (selectStatus == null || !selectStatus.booleanValue()) {
				checkBox.setChecked(false);
			} else {
				checkBox.setChecked(true);
			}
			checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					int position = (Integer) buttonView
							.getTag(R.id.tag_position);
					musicInfos.get(position).getExtras()
							.put(Constants.SELECT_STATUS, isChecked);
				}
			});

			return convertView;
		}

	}

	private class ScannHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case HandlerFlag.SCANN_MUSIC_SUCCESS:
				hideProgress();
				musicInfos = (List<MusicInfo>) msg.obj;
				if (musicInfos != null) {
					scannAdapter = new ScannAdapter(musicInfos);
					scannResultView.setAdapter(scannAdapter);
				}
				break;
			case HandlerFlag.SCANN_MUSIC_FAILED:

				break;
			case HandlerFlag.CATALOGUE_SAVE_MUSIC_SUCCESS:
				if(dialog!=null&&dialog.isShowing()){
					dialog.dismiss();
				}
				break;
			default:
				break;
			}
		}
	}

}
