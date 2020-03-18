package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.MemberDao;
import bit.com.a.model.MemberDto;
import bit.com.a.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberDao;

	@Override
	public List<MemberDto> allMember() {		
		return memberDao.allMember();
	}
	
	@Override
	public int getId(String id) {		
		return memberDao.getId(id);		
	}
	
	@Override
	public boolean addmember(MemberDto mem) {		
		return memberDao.addmember(mem);		
	}

	@Override
	public MemberDto login(MemberDto dto) {		
		return memberDao.login(dto);		
	}


	
}





