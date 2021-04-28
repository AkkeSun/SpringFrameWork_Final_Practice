package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.CalendarDao;
import bit.com.a.dto.CalendarDto;
import bit.com.a.dto.CalendarParam;
import bit.com.a.service.CalendarService;

@Service
public class CalendarServiceImpl implements CalendarService {
	@Autowired
	CalendarDao cal;



	@Override
	public List<CalendarDto> getCalendarList(CalendarDto dto) {
		return cal.getCalendarList(dto);
	}



	
}
