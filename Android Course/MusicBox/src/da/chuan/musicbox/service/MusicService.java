package da.chuan.musicbox.service;

import java.io.IOException;
import java.util.List;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.IBinder;
import da.chuan.musicbox.MainActivity;
import da.chuan.musicbox.pojo.MusicInfo;

/**
 * 音乐服务
 *
 */
public class MusicService extends Service {

	private MediaPlayer mediaPlayer;
	private List<MusicInfo> musicInfos;// 播放列表
	private int curPlayIndex;

	public static final String ENTRY = "da.chuan.musicbox.entry";
	public static final String CUR_PLAY_INDEX = "cur_play_index";

	public static final String OPT = "operiation";
	public static final int OPT_PLAY = 1;
	public static final int OPT_PAUSE = 2;
	public static final int OPT_RESUME = 3;
	public static final int OPT_STOP = 4;
	public static final int OPT_PREVIOUS = 5;
	public static final int OPT_NEXT = 6;

	public int playerState=-1;
	public static final int UNKOWN = 1;
	public static final int STARTED = 1;
	public static final int STOPPED = 2;
	public static final int PAUSED = 3;
	public static final int PLAYBACK_COMPLETED = 4;
	public static final int PREPARED = 5;

	@Override
	public void onCreate() {
		super.onCreate();
		init();

	}

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent != null && intent.getExtras() != null) {
			int optType = intent.getIntExtra(OPT, -1);
			switch (optType) {
			case OPT_PLAY:
				musicInfos = (List<MusicInfo>) intent.getExtras()
						.getSerializable(ENTRY);
				curPlayIndex = intent.getExtras().getInt(CUR_PLAY_INDEX);
				play();
				break;
			case OPT_PAUSE:
				pause();
				break;
			case OPT_RESUME:
				resume();
				break;
			case OPT_STOP:
				stop();
				break;
			case OPT_PREVIOUS:
				previous();
				break;
			case OPT_NEXT:
				next();
				break;

			default:
				break;
			}
		}

		return super.onStartCommand(intent, flags, startId);

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}

	private void init() {
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				playerState=PLAYBACK_COMPLETED;
				
				Bundle bundle = new Bundle();
				bundle.putInt(MainActivity.MusicRecevier.MUSIC_OPT_TYPE,
						MainActivity.MusicRecevier.MUSIC_PAUSE);
				sendMusicBroadcast(bundle);

			}
		});
	}

	private void play() {
		if (musicInfos != null && curPlayIndex < musicInfos.size()) {
			final MusicInfo musicInfo = musicInfos.get(curPlayIndex);
			if (musicInfo.getPath() != null) {
				mediaPlayer.reset();
				try {
					mediaPlayer.setDataSource(musicInfo.getPath());
					mediaPlayer.prepare();
					mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

						@Override
						public void onPrepared(MediaPlayer mp) {
							mediaPlayer.start();
							playerState=STARTED;

							Bundle bundle = new Bundle();
							bundle.putInt(
									MainActivity.MusicRecevier.MUSIC_OPT_TYPE,
									MainActivity.MusicRecevier.MUSIC_PLAY);
							bundle.putString(
									MainActivity.MusicRecevier.MUSIC_NAME,
									musicInfo.getName());
							sendMusicBroadcast(bundle);
						}
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void pause() {
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
			playerState=PAUSED;
			Bundle bundle = new Bundle();
			bundle.putInt(MainActivity.MusicRecevier.MUSIC_OPT_TYPE,
					MainActivity.MusicRecevier.MUSIC_PAUSE);
			sendMusicBroadcast(bundle);
		}
	}

	private void resume() {
		if (mediaPlayer != null & !mediaPlayer.isPlaying()) {
			if (musicInfos != null && curPlayIndex != -1) {
				mediaPlayer.start();
				playerState=STARTED;
				Bundle bundle = new Bundle();
				bundle.putInt(MainActivity.MusicRecevier.MUSIC_OPT_TYPE,
						MainActivity.MusicRecevier.MUSIC_RESUME);
				sendMusicBroadcast(bundle);
			}
		}
	}

	private void previous() {
		if (musicInfos != null) {
			int size = musicInfos.size();
			curPlayIndex = (curPlayIndex + size - 1) % size;
			play();
		}

	}

	private void next() {
		if (musicInfos != null) {
			int size = musicInfos.size();
			curPlayIndex = (curPlayIndex + 1) % size;
			play();
		}
	}

	private void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}
	}

	private void sendMusicBroadcast(Bundle bundle) {
		Intent intent = new Intent();
		intent.putExtras(bundle);
		intent.setAction(MainActivity.MusicRecevier.MUSIC_ACTION);
		sendBroadcast(intent);
	}
}
