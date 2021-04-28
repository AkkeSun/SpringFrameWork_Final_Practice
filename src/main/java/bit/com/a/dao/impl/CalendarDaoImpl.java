package bit.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.CalendarDao;
import bit.com.a.dto.CalendarDto;

@Repository
public class CalendarDaoImpl implements CalendarDao {

	@Autowired
	SqlSession session;
	
	String ns = "Cal.";
	
	@Override
	public List<CalendarDto> getCalendarList(CalendarDto cal) {		
		return session.selectList(ns + "getCalendar", cal);		
	}

	
	  @Override public boolean calWrite(CalendarDto dto) { int n =
	  session.insert(ns+"calWrite", dto); return n>0?true:false; }
	 
}
