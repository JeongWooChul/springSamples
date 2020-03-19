package bit.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.BitYoutubeDao;
import bit.com.a.model.YoutubeSave;

@Repository
public class BitYoutubeDaoImpl implements BitYoutubeDao {

	@Autowired
	SqlSession sqlSession;
	
	String ns = "Youtube.";

	@Override
	public boolean writeYoutube(YoutubeSave ys) {
		int n = sqlSession.insert(ns+"writeYoutube", ys);
		return n>0?true:false;
	}

	@Override
	public YoutubeSave getYoutube(YoutubeSave ys) {
		return sqlSession.selectOne(ns+"getYoutube", ys);
	}

	@Override
	public List<YoutubeSave> getYoutubeList(YoutubeSave ys) {		
		return sqlSession.selectList(ns + "getYoutubeList", ys);
	}
	
	@Override
	public boolean youtubeDel(int seq) {
		int n = sqlSession.delete(ns + "youtubedel", seq);
		return n>0?true:false;
	}

}
