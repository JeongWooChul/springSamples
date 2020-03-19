package bit.com.a.dao;

import bit.com.a.model.YoutubeSave;

public interface BitYoutubeDao {

	public boolean writeYoutube(YoutubeSave ys);
	
	public YoutubeSave getYoutube(YoutubeSave ys);
}
