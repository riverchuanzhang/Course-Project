package da.chuan.musicbox.constants;

/**
 * <p>
 * 数据库常量
 * </p>
 * 
 * 创建时间：2013-2-1
 * 
 * @author zdc
 * 
 */
public class DatabaseConstants {

	public static final String DB_NAME = "musicbox";

	/**
	 * 目录表
	 * 
	 */
	public class CatalogueTable {
		public static final String TABLE_NAME = "catalogue";

		public class Column {
			public static final String ID = "_id";
			public static final String NAME = "name";
		}

	}

	/**
	 * 音乐表
	 */
	public class MusicInfoTable {

		public static final String TABLE_NAME = "musicinfo";

		public class Column {
			public static final String ID = "_id";
			public static final String CATALOGUE_ID = "catalogue_id";
			public static final String PATH = "path";
			public static final String NAME = "name";
		}

	}

	/**
	 * 目录音乐关联表
	 * 
	 */
	public class CatalogMusicTable {

		public static final String TABLE_NAME = "catalog_musicinfo";

		public class Column {
			public static final String CATALOG_ID = "catalog_id";
			public static final String MUSIC_ID = "music_id";
		}

	}

}
