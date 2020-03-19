package bit.com.a.service;

import java.util.List;

import bit.com.a.model.YoutubeSave;

public interface BitYoutubeService {

	public boolean writeYoutube(YoutubeSave ys);

	public YoutubeSave getYoutube(YoutubeSave ys);

	public boolean youtubeDel(int seq);

	public List<YoutubeSave> getYoutubeList(YoutubeSave you);
}
