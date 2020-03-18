package bit.com.a.dao;

import java.util.List;

import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;

public interface BbsDao {
	
	//public List<BbsDto> getBbsList();
	public List<BbsDto> getBbsList(BbsParam param);
	
	public int getBbsCount(BbsParam param);
	
	public BbsDto getBbs(int seq);
	
	public boolean write(BbsDto dto);

	public void readCount(int seq);

	public boolean setDelete(int seq);

	public boolean setUpdate(BbsDto dto);

	public boolean replyBbsInsert(BbsDto dto);

	public void replyBbsUpdate(BbsDto dto);


}
