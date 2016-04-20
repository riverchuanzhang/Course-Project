package da.chuan.musicbox.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import da.chuan.musicbox.utils.AssetsHelper;

/**
 * 数据库工具类
 * 
 */
public class DBUtil {

	private static final String TAG = "DBUtil";

	/**
	 * 释放数据库资源
	 * 
	 * @param cursor
	 *            游标
	 * @param sqliteDB
	 *            数据库连接
	 */
	public static void releaseResource(Cursor cursor, SQLiteDatabase sqliteDB) {
		if (sqliteDB != null && sqliteDB.isOpen()) {
			sqliteDB.close();
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}

	}

	/**
	 * 释放游标资源
	 * 
	 * @param cursor
	 */
	public static void releaseCursor(Cursor cursor) {
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
	}

	/**
	 * 释放连接资源
	 * 
	 * @param sqliteDB
	 */
	public static void releaseSQLiteDB(SQLiteDatabase sqliteDB) {
		if (sqliteDB != null && sqliteDB.isOpen()) {
			sqliteDB.close();
		}
	}

	/**
	 * 执行Assets资源中的sql文件
	 * 
	 * @param fileName
	 * @throws ZTException
	 */
	public static void executeAssetsSQLFile(Context context,
			SQLiteDatabase sqliteDB, String dbname, String fileName)
			throws Exception {
		AssetsHelper helper = new AssetsHelper(context);
		BufferedReader br = null;
		InputStream in = null;
		try {
			in = helper.openDB(fileName);
			br = new BufferedReader(new InputStreamReader(in));
			String sql = null;
			while ((sql = br.readLine()) != null) {
				sqliteDB.execSQL(sql);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new Exception(e);
			}
		}
	}

}
