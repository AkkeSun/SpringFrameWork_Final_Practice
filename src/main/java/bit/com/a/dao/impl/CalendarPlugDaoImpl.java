package bit.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.CalendarPlugDao;
import bit.com.a.dto.CalendarPlugDto;

@Repository
public class CalendarPlugDaoImpl implements CalendarPlugDao {

	@Autowired
	SqlSession session;
	
	String ns = "CalPlug.";

	@Override
	public List<CalendarPlugDto> getCalendarPlugList(CalendarPlugDto dto) {
		return session.selectList(ns + "getCalendarPlugList", dto);		
	}

	@Override
	public boolean writeCalAf(CalendarPlugDto dto) {
		int n = session.insert(ns+"writeCalAf", dto);
		return n>0?true:false;
	}

	@Override
	public boolean updateCal(CalendarPlugDto dto) {
		int n = session.update(ns+"updateCal", dto);
		return n>0?true:false;
	}

	@Override
	public boolean delCal(int seq) {
		int n = session.delete(ns+"delCal", seq);
		return n>0?true:false;
	}
	
	
}









