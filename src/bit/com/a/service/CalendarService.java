package bit.com.a.service;

import java.util.List;

import bit.com.a.model.CalendarDto;
import bit.com.a.model.CalendarParam;

public interface CalendarService {

	public List<CalendarDto> getCalendarList(CalendarDto cal);

	public boolean addCalendar(CalendarDto dto);

	public CalendarDto getDetail(CalendarDto fcal);

	public List<CalendarDto> getDayList(CalendarDto fcal);

	public List<CalendarDto> getCallistmonth(CalendarDto caldto);
}
