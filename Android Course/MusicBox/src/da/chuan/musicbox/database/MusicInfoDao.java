package da.chuan.musicbox.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;
import da.chuan.musicbox.constants.DatabaseConstants;
import da.chuan.musicbox.pojo.MusicInfo;

public class MusicInfoDao extends BaseDao {

	private Context mContext;

	private static MusicInfoDao instance;

	private MusicInfoDao(Context context) {
		super(context);
		mContext = context;
	}

	public static MusicInfoDao getInstance(Context context) {
		if (instance == null) {
			instance = new MusicInfoDao(context);
		}
		return instance;
	}

	/**
	 * 扫描媒体库获取音乐信息
	 * 
	 * @return
	 */
	public List<MusicInfo> scannMusics() {
		ContentResolver mResolver = mContext.getContentResolver();
		Cursor cursor = mResolver.query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
				null);
		String tilte;
		String path;
		List<MusicInfo> musicInfos = new ArrayList<MusicInfo>();
		MusicInfo musicInfo;
		while (cursor != null && cursor.moveToNext()) {
			tilte = cursor.getString(cursor
					.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
			path = cursor.getString(cursor
					.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
			musicInfo = new MusicInfo();
			musicInfo.setName(tilte);
			musicInfo.setPath(path);
			musicInfos.add(musicInfo);
		}
		if (cursor != null) {
			cursor.close();
		}
		return musicInfos;
	}

	/**
	 * 保存音乐
	 * 
	 * @param musicInfo
	 * @return
	 */
	public long save(MusicInfo musicInfo) {
		SQLiteDatabase database = getSQLiteDB();
		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseConstants.MusicInfoTable.Column.CATALOGUE_ID,
				musicInfo.getCatalogueId());
		contentValues.put(DatabaseConstants.MusicInfoTable.Column.NAME,
				musicInfo.getName());
		contentValues.put(DatabaseConstants.MusicInfoTable.Column.PATH,
				musicInfo.getPath());
		long id = database.insert(DatabaseConstants.MusicInfoTable.TABLE_NAME,
				null, contentValues);
		database.close();
		return id;
	}

	public void save(List<MusicInfo> musicInfos) {
		if (musicInfos == null) {
			return;
		}
		long musicId;
		for (int i = 0; i < musicInfos.size(); i++) {
			musicId = save(musicInfos.get(i));
			musicInfos.get(i).setId(musicId);
		}
	}

	/*
	 * public List<MusicInfo> qureyAll(int catalogueId) { SQLiteDatabase
	 * database = getSQLiteDB(); String selection =
	 * DatabaseConstants.CatalogMusicTable.Column.CATALOG_ID + "=?"; String[]
	 * selectionArgs = new String[] { "" + catalogueId }; List<MusicInfo>
	 * musicInfos = new ArrayList<MusicInfo>(); List<Integer> ids = new
	 * ArrayList<Integer>(); int musicIdIndex; int musicId; Cursor cursor =
	 * database.query( DatabaseConstants.CatalogMusicTable.TABLE_NAME, null,
	 * selection, selectionArgs, null, null, null); while (cursor != null &&
	 * cursor.moveToNext()) { musicIdIndex = cursor
	 * .getColumnIndex(DatabaseConstants.CatalogMusicTable.Column.MUSIC_ID);
	 * musicId = cursor.getInt(musicIdIndex); ids.add(musicId); } if (cursor !=
	 * null) { cursor.close(); database.close(); }
	 * 
	 * MusicInfo musicInfo; for (int i = 0; i < ids.size(); i++) { musicInfo =
	 * query(ids.get(i)); if (musicInfo != null) { musicInfos.add(musicInfo); }
	 * } return musicInfos; }
	 */

	public List<MusicInfo> qureyAll(long catalogueId) {
		SQLiteDatabase database = getSQLiteDB();
		String selection = DatabaseConstants.MusicInfoTable.Column.CATALOGUE_ID
				+ "=?";
		String[] selectionArgs = new String[] { "" + catalogueId };

		List<MusicInfo> musicInfos = new ArrayList<MusicInfo>();
		MusicInfo musicInfo;
		int musicIdIndex;
		int nameIndex;
		int pathIndex;
		int catalogueIdIndex;

		Cursor cursor = database.query(
				DatabaseConstants.MusicInfoTable.TABLE_NAME, null, selection,
				selectionArgs, null, null, null);
		while (cursor != null && cursor.moveToNext()) {
			musicInfo = new MusicInfo();

			musicIdIndex = cursor
					.getColumnIndex(DatabaseConstants.MusicInfoTable.Column.ID);
			nameIndex = cursor
					.getColumnIndex(DatabaseConstants.MusicInfoTable.Column.NAME);
			pathIndex = cursor
					.getColumnIndex(DatabaseConstants.MusicInfoTable.Column.PATH);
			catalogueIdIndex = cursor
					.getColumnIndex(DatabaseConstants.MusicInfoTable.Column.CATALOGUE_ID);
			musicInfo.setCatalogueId(cursor.getInt(catalogueIdIndex));
			musicInfo.setId(cursor.getInt(musicIdIndex));
			musicInfo.setName(cursor.getString(nameIndex));
			musicInfo.setPath(cursor.getString(pathIndex));
			musicInfos.add(musicInfo);
		}
		if (cursor != null) {
			cursor.close();
			database.close();
		}
		return musicInfos;
	}

	public MusicInfo query(int musicId) {
		MusicInfo musicInfo = null;
		SQLiteDatabase database = getSQLiteDB();
		String selection = DatabaseConstants.MusicInfoTable.Column.ID + "=?";
		String[] selectionArgs = new String[] { "" + musicId };
		Cursor musicCursor = database.query(
				DatabaseConstants.MusicInfoTable.TABLE_NAME, null, selection,
				selectionArgs, null, null, null);
		if (musicCursor != null && musicCursor.moveToNext()) {
			musicInfo = new MusicInfo();
			int nameIndex = musicCursor
					.getColumnIndex(DatabaseConstants.MusicInfoTable.Column.NAME);
			int pathIndex = musicCursor
					.getColumnIndex(DatabaseConstants.MusicInfoTable.Column.PATH);
			musicInfo.setName(musicCursor.getString(nameIndex));
			musicInfo.setPath(musicCursor.getString(pathIndex));
		}
		musicCursor.close();
		database.close();
		return musicInfo;
	}

	public void delete(long musicId, long catalogueId) {
		SQLiteDatabase database = getSQLiteDB();
		String whereClause = DatabaseConstants.MusicInfoTable.Column.ID
				+ "=? and "
				+ DatabaseConstants.MusicInfoTable.Column.CATALOGUE_ID + "=?";
		String[] whereArgs = new String[] { "" + musicId, "" + catalogueId };
		database.delete(DatabaseConstants.MusicInfoTable.TABLE_NAME,
				whereClause, whereArgs);
		database.close();
	}

}
