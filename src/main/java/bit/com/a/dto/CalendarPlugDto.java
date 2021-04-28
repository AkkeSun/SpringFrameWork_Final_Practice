package bit.com.a.dto;

import java.io.Serializable;

/*
DROP TABLE CALENDARPLUG
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_CALPLUG;

CREATE TABLE CALENDARPLUG(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	SLEVEL NUMBER(8),
	TITLE VARCHAR2(200) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	STARTDATE VARCHAR2(20) NOT NULL,
	ENDDATE VARCHAR2(20),
	WDATE DATE NOT NULL
);

CREATE SEQUENCE SEQ_CALPLUG
START WITH 1
INCREMENT BY 1;

ALTER TABLE CALENDARPLUG
ADD CONSTRAINT FK_CALPLUG_ID FOREIGN KEY(ID)
REFERENCES MEMBER(ID);

INSERT INTO CALENDARPLUG(SEQ, ID, SLEVEL, TITLE, CONTENT, STARTDATE, ENDDATE, WDATE)
VALUES(SEQ_CALPLUG.NEXTVAL, 'aaa', 1, '사업미팅', '제품계약서건', '2021-04-22T12:30:00', '', SYSDATE)

INSERT INTO CALENDARPLUG(SEQ, ID, SLEVEL, TITLE, CONTENT, STARTDATE, ENDDATE, WDATE)
VALUES(SEQ_CALPLUG.NEXTVAL, 'aaa', 1, '국내여행', '대포항으로', '2021-04-26T08:30:00', '2021-04-30T16:00:00', SYSDATE)

INSERT INTO CALENDARPLUG(SEQ, ID, SLEVEL, TITLE, CONTENT, STARTDATE, ENDDATE, WDATE)
VALUES(SEQ_CALPLUG.NEXTVAL, 'aaa', 1, '연수', '위크샵', '2021-04-19', '', SYSDATE)

*/
public class CalendarPlugDto implements Serializable {

	private int seq;
	private String id;
	private int slevel;		    // 중요도: 1, 2, 3
	private String title;
	private String content;
	private String startdate;	// 시작일 또는 지정일
	private String enddate;		// 종료일(생략가능)
	private String wdate;
	
	public CalendarPlugDto() {
	}

	public CalendarPlugDto(int seq, String id, int slevel, String title, String content, String startdate,
			String enddate, String wdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.slevel = slevel;
		this.title = title;
		this.content = content;
		this.startdate = startdate;
		this.enddate = enddate;
		this.wdate = wdate;
	}

	// 업로드용 생성자
	public CalendarPlugDto(String id, int slevel, String title, String content, String startdate, String enddate) {
		super();
		this.id = id;
		this.slevel = slevel;
		this.title = title;
		this.content = content;
		this.startdate = startdate;
		this.enddate = enddate;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSlevel() {
		return slevel;
	}

	public void setSlevel(int slevel) {
		this.slevel = slevel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	@Override
	public String toString() {
		return "CalendarPlugDto [seq=" + seq + ", id=" + id + ", slevel=" + slevel + ", title=" + title + ", content="
				+ content + ", startdate=" + startdate + ", enddate=" + enddate + ", wdate=" + wdate + "]";
	}
	
	
}






