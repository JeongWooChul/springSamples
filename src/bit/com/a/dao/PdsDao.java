package bit.com.a.dao;

import java.util.List;

import bit.com.a.model.PdsDto;

public interface PdsDao {
	
	public List<PdsDto> getPdsList(); 
	public boolean uploadPds(PdsDto dto);
	public PdsDto getPds(int seq);
	public boolean updatePds(PdsDto dto);
}
