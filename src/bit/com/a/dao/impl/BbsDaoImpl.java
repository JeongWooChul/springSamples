package bit.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.BbsDao;
import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;

@Repository
public class BbsDaoImpl implements BbsDao {
	
	@Autowired
	SqlSession sqlSession;			
	String ns = "Bbs.";
	/*
	@Override
	public List<BbsDto> getBbsList() {
		List<BbsDto> list = sqlSession.selectList(ns + "getBbsList");
		return list;
	}
	*/
	@Override
	public List<BbsDto> getBbsList(BbsParam param) {
		List<BbsDto> list = sqlSession.selectList(ns + "getBbsList", param);
		return list;
	}

	@Override
	public int getBbsCount(BbsParam param) {
		return sqlSession.selectOne(ns + "getBbsCount", param);
	}

	@Override
	public BbsDto getBbs(int seq) {
		BbsDto dto = sqlSession.selectOne(ns + "getBbs", seq);
		return dto;
	}

	@Override
	public boolean write(BbsDto dto) {
		int n = sqlSession.insert(ns+"writeBbs", dto);		
		return n>0?true:false;
	}

	@Override
	public void readCount(int seq) {
		sqlSession.update(ns+"readCount",seq);	
	}

	@Override
	public boolean setDelete(int seq) {
		int n = sqlSession.update(ns+"setDelete",seq );
		return n>0?true:false;
	}

	@Override
	public boolean setUpdate(BbsDto dto) {
		int n = sqlSession.update(ns+"setUpdate", dto);
		return n>0?true:false;
	}

	@Override
	public void replyBbsUpdate(BbsDto dto) {
		sqlSession.update(ns+"replyBbsUpdate", dto);		
	}

	@Override
	public boolean replyBbsInsert(BbsDto dto) {
		
		int n = sqlSession.insert(ns+"replyBbsInsert", dto);
		return n>0?true:false;
	}


	
}






