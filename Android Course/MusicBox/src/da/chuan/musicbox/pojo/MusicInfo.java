package da.chuan.musicbox.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 音乐信息
 *
 */
public class MusicInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String path;
	private long catalogueId;

	public long getCatalogueId() {
		return catalogueId;
	}

	public void setCatalogueId(long catalogueId) {
		this.catalogueId = catalogueId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	private Map<String, Object> extras = new HashMap<String, Object>();

	public Map<String, Object> getExtras() {
		return extras;
	}

}
