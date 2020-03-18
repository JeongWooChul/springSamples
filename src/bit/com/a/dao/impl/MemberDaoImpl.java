package bit.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.MemberDao;
import bit.com.a.model.MemberDto;

@Repository // == 저장소
public class MemberDaoImpl implements MemberDao {

	// DB와 통신
	@Autowired		// <- 객체 생성 (의존성)
	SqlSession sqlSession;
	
	String namespace = "Member.";

	@Override
	public List<MemberDto> allMember() {
		List<MemberDto> list = sqlSession.selectList(namespace + "allMember");		
		return list;
	}
	
	@Override
	public int getId(String id) {		
		return sqlSession.selectOne(namespace + "getId", id);
	}
	
	@Override
	public boolean addmember(MemberDto mem) {		
		int n = sqlSession.insert(namespace + "addmember", mem);		
		return n>0?true:false;
	}

	@Override
	public MemberDto login(MemberDto dto) {
		MemberDto mem = sqlSession.selectOne(namespace + "login", dto);
		return mem;
	}


	
}









