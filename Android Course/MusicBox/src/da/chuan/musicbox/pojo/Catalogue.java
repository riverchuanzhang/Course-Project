package da.chuan.musicbox.pojo;

import java.util.List;

/**
 * 音乐目录
 * 
 */
public class Catalogue {
	private long id;
	private String name;

	private List<MusicInfo> musics;

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

	public List<MusicInfo> getMusics() {
		return musics;
	}

	public void setMusics(List<MusicInfo> musics) {
		this.musics = musics;
	}

}
