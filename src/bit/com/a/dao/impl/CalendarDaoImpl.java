package bit.com.a.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.CalendarDao;
import bit.com.a.model.CalendarDto;
import bit.com.a.model.CalendarParam;

@Repository
public class CalendarDaoImpl implements CalendarDao {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	private String ns = "Calendar.";
	
	@Override
	public List<CalendarDto> getCalendarList(CalendarDto cal) {
		return sqlSession.selectList(ns + "getCalendar", cal);
	}

	@Override
	public boolean addCalendar(CalendarDto dto) {
		int n = sqlSession.insert(ns + "addCalendar", dto);
		return n>0?true:false;
	}

	@Override
	public CalendarDto getDetail(CalendarDto fcal) {
		CalendarDto dto = sqlSession.selectOne(ns + "getDetail", fcal);
		return dto;
	}

	@Override
	public List<CalendarDto> getDayList(CalendarDto fcal) {
		List<CalendarDto> list = sqlSession.selectList(ns + "getDayList", fcal);
		return list;
	}

	@Override
	public List<CalendarDto> getCallistmonth(CalendarDto caldto) {
		return sqlSession.selectList(ns+"getCallistmonth", caldto);
	}

	
}
