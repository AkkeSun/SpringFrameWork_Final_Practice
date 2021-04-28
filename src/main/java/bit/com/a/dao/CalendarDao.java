package bit.com.a.dao;

import java.util.List;

import bit.com.a.dto.CalendarDto;
import bit.com.a.dto.CalendarParam;

public interface CalendarDao {

	List<CalendarDto> getCalendarList(CalendarDto cal);

	boolean calWrite(CalendarDto dto);
}
