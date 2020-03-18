package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.CalendarDao;
import bit.com.a.model.CalendarDto;
import bit.com.a.model.CalendarParam;
import bit.com.a.service.CalendarService;


@Service
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	CalendarDao calendarDao;

	@Override
	public List<CalendarDto> getCalendarList(CalendarDto cal) {
		return calendarDao.getCalendarList(cal);
	}

	@Override
	public boolean addCalendar(CalendarDto dto) {
		return calendarDao.addCalendar(dto);
	}

	@Override
	public CalendarDto getDetail(CalendarDto fcal) {
		return calendarDao.getDetail(fcal);
	}

	@Override
	public List<CalendarDto> getDayList(CalendarDto fcal) {
		return calendarDao.getDayList(fcal);
	}

	@Override
	public List<CalendarDto> getCallistmonth(CalendarDto caldto) {
		return calendarDao.getCallistmonth(caldto);	
	}
	
	
}
