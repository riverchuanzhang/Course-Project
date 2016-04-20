package da.chuan.musicbox.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;

/**
 * <p>
 * 资源工具类，用于操作assets文件夹下的资源
 * </p>
 * 
 * 创建时间：2013-1-24
 * @author zdc
 *
 */
public class AssetsHelper {
	
	/**
	 * 数据库文件存储路径
	 */
	public static final String DB_UPDATE_PATH = "database";
	
	/**
	 * DEMO文件存储路径
	 */
	public static final String DEMO_PATH = "demo";
	
	private Context context;
	private AssetManager manager;
	
	public AssetsHelper(Context context) {
		this.context = context;
		this.manager = context.getAssets();
	}
	
	/**
	 * 获取数据库文件输入流
	 * 
	 * @param name
	 * @return
	 * @throws IOException 
	 */
	public InputStream openDB(String name) throws IOException{
		return open(DB_UPDATE_PATH, name);
	}
	
	/**
	 * 获取文件输入流
	 * @param modulePath
	 * @param name
	 * @return
	 * @throws IOException 
	 */
	public InputStream open(String modulePath, String name) throws IOException {
		InputStream in = manager.open(modulePath + File.separator + name);
		return in;
	}

}
