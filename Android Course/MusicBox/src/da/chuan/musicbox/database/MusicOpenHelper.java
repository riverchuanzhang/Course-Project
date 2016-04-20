package da.chuan.musicbox.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import da.chuan.musicbox.constants.DatabaseConstants;
import da.chuan.musicbox.R;

public class MusicOpenHelper extends SQLiteOpenHelper {

	private Context mContext;
	private DBCreateListner mCreateListner;

	public DBCreateListner getCreateListner() {
		return mCreateListner;
	}

	public void setCreateListner(DBCreateListner createListner) {
		this.mCreateListner = createListner;
	}

	public MusicOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Integer currentVersion = mContext.getResources().getInteger(
				R.integer.db_current_version);
		String currentVersionFile = mContext.getResources().getString(
				R.string.db_current_file);
		boolean success = true;
		try {
			DBUtil.executeAssetsSQLFile(mContext, db,
					DatabaseConstants.DB_NAME, currentVersionFile);
			db.setVersion(currentVersion);
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		if (mCreateListner != null) {
			if (success) {
				mCreateListner.success();
			} else {
				mCreateListner.error();
			}
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public static interface DBCreateListner {
		public void error();

		public void success();
	}

}
