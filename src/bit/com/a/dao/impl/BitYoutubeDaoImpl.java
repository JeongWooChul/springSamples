package bit.com.a.dao.impl;

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
	
	
	
}
