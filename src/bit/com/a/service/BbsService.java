package bit.com.a.service;

import java.util.List;

import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;

public interface BbsService {

	//public List<BbsDto> getBbsList();
	public List<BbsDto> getBbsList(BbsParam param);
	public BbsDto getBbs(int seq);
	public int getBbsCount(BbsParam param);
	
	public boolean write(BbsDto dto);

	public void readCount(int seq);

	public boolean setDelete(int seq);

	public boolean setUpdate(BbsDto dto);

	public boolean replyBbsInsert(BbsDto dto);
	
}
