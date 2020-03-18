package bit.com.a.service;

import java.util.List;

import bit.com.a.model.MemberDto;

public interface MemberService {

	public List<MemberDto> allMember();
	
	public int getId(String id);	
	public boolean addmember(MemberDto mem);
	
	public MemberDto login(MemberDto dto);

	
}
