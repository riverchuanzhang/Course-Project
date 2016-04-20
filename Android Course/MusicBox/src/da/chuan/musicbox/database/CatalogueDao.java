package da.chuan.musicbox.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import da.chuan.musicbox.constants.DatabaseConstants;
import da.chuan.musicbox.pojo.Catalogue;

/**
 * 与目录相关的数据库操作
 *
 */
public class CatalogueDao extends BaseDao {

	private Context mContext;
	private static CatalogueDao instance;

	private CatalogueDao(Context context) {
		super(context);
		mContext = context;
	}

	public static CatalogueDao getInstance(Context context) {
		if (instance == null) {
			instance = new CatalogueDao(context);
		}
		return instance;
	}
	

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<Catalogue> queryList() {
		List<Catalogue> catalogues = new ArrayList<Catalogue>();
		SQLiteDatabase db = getSQLiteDB();
		//String[] columns = new String[] { DatabaseConstants.CatalogueTable.Column.NAME };
		if (db != null) {
			Cursor cursor = db.query(
					DatabaseConstants.CatalogueTable.TABLE_NAME, null, null,
					null, null, null, null);
			int nameIndex = -1;
			int idIndex;
			String name;
			Catalogue catalogue;
			while (cursor != null && cursor.moveToNext()) {
				nameIndex = cursor
						.getColumnIndex(DatabaseConstants.CatalogueTable.Column.NAME);
				name = cursor.getString(nameIndex);
				idIndex=cursor.getColumnIndex(DatabaseConstants.CatalogueTable.Column.ID);
				catalogue = new Catalogue();
				catalogue.setId(cursor.getLong(idIndex));
				catalogue.setName(name);
				catalogues.add(catalogue);
			}
			if (cursor != null) {
				cursor.close();
			}
			db.close();

		}
		return catalogues;
	}

	/**
	 * 删除目录
	 * @param catalogueId
	 */
	public void delete(long catalogueId){
		SQLiteDatabase db = getSQLiteDB();
		String whereClause=DatabaseConstants.CatalogueTable.Column.ID+"=?";
		String[]whereArgs=new String[]{""+catalogueId};
		db.delete(DatabaseConstants.CatalogueTable.TABLE_NAME, whereClause, whereArgs);
		db.close();
	}
	
	/**
	 * 保存添加目录
	 * @param catalogue
	 */
	public void save(Catalogue catalogue){
		SQLiteDatabase db = getSQLiteDB();
		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseConstants.CatalogueTable.Column.NAME,
				catalogue.getName());
		long id = db.insert(DatabaseConstants.CatalogueTable.TABLE_NAME,
				null, contentValues);
		db.close();
	}
}
