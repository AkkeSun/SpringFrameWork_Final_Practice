package bit.com.a.service;

import java.util.List;

import bit.com.a.dto.CalendarPlugDto;

public interface CalendarPlugService {

	List<CalendarPlugDto> getCalendarPlugList(CalendarPlugDto dto);
	  boolean writeCalAf(CalendarPlugDto dto);
	  boolean updateCal(CalendarPlugDto dto);
	  boolean delCal(int seq);
}
