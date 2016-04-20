package da.chuan.musicbox.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import da.chuan.musicbox.constants.DatabaseConstants;

public class BaseDao {

	private static final String TAG = "BaseDao";

	private Context context;

	public BaseDao(Context context) {
		this.context = context;
	}

	/**
	 * 获取数据库连接
	 * 
	 * @param dbname
	 *            数据库名字
	 * @return
	 */
	public SQLiteDatabase getSQliteDB(String dbname) {
		SQLiteDatabase sqliteDB = context.openOrCreateDatabase(
				DatabaseConstants.DB_NAME, Context.MODE_PRIVATE, null);
		return sqliteDB;
	}

	/**
	 * 默认获取数据库(musicbox.db)连接
	 * 
	 * @return
	 */
	public SQLiteDatabase getSQLiteDB() {
		return getSQliteDB(DatabaseConstants.DB_NAME);
	}
}
