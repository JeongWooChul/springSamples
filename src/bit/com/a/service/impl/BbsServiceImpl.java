package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.BbsDao;
import bit.com.a.dao.impl.BbsDaoImpl;
import bit.com.a.model.BbsDto;
import bit.com.a.model.BbsParam;
import bit.com.a.service.BbsService;

@Service
public class BbsServiceImpl implements BbsService {

	@Autowired
	BbsDao bbsDao; // = new BbsDaoImpl();
	/*
	@Override
	public List<BbsDto> getBbsList() {		
		return bbsDao.getBbsList();
	}
	*/
	
	@Override
	public List<BbsDto> getBbsList(BbsParam param) {
		return bbsDao.getBbsList(param);
	}
	
	
	@Override
	public int getBbsCount(BbsParam param) {
		return bbsDao.getBbsCount(param);
	}


	@Override
	public BbsDto getBbs(int seq) {
		return bbsDao.getBbs(seq);
	}


	@Override
	public boolean write(BbsDto dto) {
		return bbsDao.write(dto);
	}

	@Override
	public void readCount(int seq) {
		bbsDao.readCount(seq);		
	}

	@Override
	public boolean setDelete(int seq) {
		return bbsDao.setDelete(seq);
	}

	@Override
	public boolean setUpdate(BbsDto dto) {
		return bbsDao.setUpdate(dto);
	}

	@Override
	public boolean replyBbsInsert(BbsDto dto) {
		bbsDao.replyBbsUpdate(dto);
		return bbsDao.replyBbsInsert(dto);
	}
	
	
}








