package bit.com.a.dto;

import java.io.Serializable;

public class CalendarParam{
	
	//값이 받아졌는지 확인하기 위한 변수셋팅
	private int year = -1;
	private int month = -1;
	private int day = -1;
	private int lastDay; // 28 29 30 31
	private int dayOfWeek;	// 요일
	
	private int hour = 0;
	private int min = 0;
	
	public CalendarParam() {
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getLastDay() {
		return lastDay;
	}

	public void setLastDay(int lastDay) {
		this.lastDay = lastDay;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	@Override
	public String toString() {
		return "CalendarParam [year=" + year + ", month=" + month + ", day=" + day + ", lastDay=" + lastDay
				+ ", dayOfWeek=" + dayOfWeek + ", hour=" + hour + ", min=" + min + "]";
	}
	
	
	
}
