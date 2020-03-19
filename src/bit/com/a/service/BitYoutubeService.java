package bit.com.a.service;

import bit.com.a.model.YoutubeSave;

public interface BitYoutubeService {

	public boolean writeYoutube(YoutubeSave ys);

	public YoutubeSave getYoutube(YoutubeSave ys);
}
