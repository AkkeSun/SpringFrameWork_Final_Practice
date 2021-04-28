package bit.com.a.poll;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import bit.com.a.util.UtilEx;

// 투표 작성자가 작성(JSP)해서 넘겨준 경우, 받은 데이터들 PollDto + PollSubDto 
public class PollBean implements Serializable {
	private String id;
	
	private int syear;
	private int smonth;
	private int sday;
	
	private int eyear;
	private int emonth;
	private int eday;
	
	private Date sdate;
	private Date edate;
	
	private String question;
	private int itemcount;
	
	private String poll1;
	private String poll2;
	private String poll3;
	private String poll4;
	private String poll5;
	private String poll6;
	private String poll7;
	private String poll8;
	private String poll9;
	private String poll10;
	private String poll11;
	private String poll12;
	private String poll13;
	private String poll14;
	private String poll15;
	private String poll16;
	private String poll17;
	private String poll18;
	private String poll19;
	private String poll20;
	
	private String pollnum[] = new String[20];
	
	// 보기묶음
	public String[] getPollnum() {
		pollnum[0] = poll1;
		pollnum[1] = poll2;
		pollnum[2] = poll3;
		pollnum[3] = poll4;
		pollnum[4] = poll5;
		pollnum[5] = poll6;
		pollnum[6] = poll7;
		pollnum[7] = poll8;
		pollnum[8] = poll9;
		pollnum[9] = poll10;
		pollnum[10] = poll11;
		pollnum[11] = poll12;
		pollnum[12] = poll13;
		pollnum[13] = poll14;
		pollnum[14] = poll15;
		pollnum[15] = poll16;
		pollnum[16] = poll17;
		pollnum[17] = poll18;
		pollnum[18] = poll19;
		pollnum[19] = poll20;
		
		return pollnum;
	}

	// 날짜 묶음
	public Date getSdate() {
		return UtilEx.toDate(syear, smonth, sday);		
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getEdate() {
		return UtilEx.toDate(eyear, emonth, eday);		
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}	
	
	public PollBean() {
	}
	
	public PollBean(String id, int syear, int smonth, int sday, int eyear, int emonth, int eday, Date sdate, Date edate,
			String question, int itemcount, String poll1, String poll2, String poll3, String poll4, String poll5,
			String poll6, String poll7, String poll8, String poll9, String poll10, String poll11, String poll12,
			String poll13, String poll14, String poll15, String poll16, String poll17, String poll18, String poll19,
			String poll20, String[] pollnum) {
		super();
		this.id = id;
		this.syear = syear;
		this.smonth = smonth;
		this.sday = sday;
		this.eyear = eyear;
		this.emonth = emonth;
		this.eday = eday;
		this.sdate = sdate;
		this.edate = edate;
		this.question = question;
		this.itemcount = itemcount;
		this.poll1 = poll1;
		this.poll2 = poll2;
		this.poll3 = poll3;
		this.poll4 = poll4;
		this.poll5 = poll5;
		this.poll6 = poll6;
		this.poll7 = poll7;
		this.poll8 = poll8;
		this.poll9 = poll9;
		this.poll10 = poll10;
		this.poll11 = poll11;
		this.poll12 = poll12;
		this.poll13 = poll13;
		this.poll14 = poll14;
		this.poll15 = poll15;
		this.poll16 = poll16;
		this.poll17 = poll17;
		this.poll18 = poll18;
		this.poll19 = poll19;
		this.poll20 = poll20;
		this.pollnum = pollnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSyear() {
		return syear;
	}

	public void setSyear(int syear) {
		this.syear = syear;
	}

	public int getSmonth() {
		return smonth;
	}

	public void setSmonth(int smonth) {
		this.smonth = smonth;
	}

	public int getSday() {
		return sday;
	}

	public void setSday(int sday) {
		this.sday = sday;
	}

	public int getEyear() {
		return eyear;
	}

	public void setEyear(int eyear) {
		this.eyear = eyear;
	}

	public int getEmonth() {
		return emonth;
	}

	public void setEmonth(int emonth) {
		this.emonth = emonth;
	}

	public int getEday() {
		return eday;
	}

	public void setEday(int eday) {
		this.eday = eday;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getItemcount() {
		return itemcount;
	}

	public void setItemcount(int itemcount) {
		this.itemcount = itemcount;
	}

	public String getPoll1() {
		return poll1;
	}

	public void setPoll1(String poll1) {
		this.poll1 = poll1;
	}

	public String getPoll2() {
		return poll2;
	}

	public void setPoll2(String poll2) {
		this.poll2 = poll2;
	}

	public String getPoll3() {
		return poll3;
	}

	public void setPoll3(String poll3) {
		this.poll3 = poll3;
	}

	public String getPoll4() {
		return poll4;
	}

	public void setPoll4(String poll4) {
		this.poll4 = poll4;
	}

	public String getPoll5() {
		return poll5;
	}

	public void setPoll5(String poll5) {
		this.poll5 = poll5;
	}

	public String getPoll6() {
		return poll6;
	}

	public void setPoll6(String poll6) {
		this.poll6 = poll6;
	}

	public String getPoll7() {
		return poll7;
	}

	public void setPoll7(String poll7) {
		this.poll7 = poll7;
	}

	public String getPoll8() {
		return poll8;
	}

	public void setPoll8(String poll8) {
		this.poll8 = poll8;
	}

	public String getPoll9() {
		return poll9;
	}

	public void setPoll9(String poll9) {
		this.poll9 = poll9;
	}

	public String getPoll10() {
		return poll10;
	}

	public void setPoll10(String poll10) {
		this.poll10 = poll10;
	}

	public String getPoll11() {
		return poll11;
	}

	public void setPoll11(String poll11) {
		this.poll11 = poll11;
	}

	public String getPoll12() {
		return poll12;
	}

	public void setPoll12(String poll12) {
		this.poll12 = poll12;
	}

	public String getPoll13() {
		return poll13;
	}

	public void setPoll13(String poll13) {
		this.poll13 = poll13;
	}

	public String getPoll14() {
		return poll14;
	}

	public void setPoll14(String poll14) {
		this.poll14 = poll14;
	}

	public String getPoll15() {
		return poll15;
	}

	public void setPoll15(String poll15) {
		this.poll15 = poll15;
	}

	public String getPoll16() {
		return poll16;
	}

	public void setPoll16(String poll16) {
		this.poll16 = poll16;
	}

	public String getPoll17() {
		return poll17;
	}

	public void setPoll17(String poll17) {
		this.poll17 = poll17;
	}

	public String getPoll18() {
		return poll18;
	}

	public void setPoll18(String poll18) {
		this.poll18 = poll18;
	}

	public String getPoll19() {
		return poll19;
	}

	public void setPoll19(String poll19) {
		this.poll19 = poll19;
	}

	public String getPoll20() {
		return poll20;
	}

	public void setPoll20(String poll20) {
		this.poll20 = poll20;
	}

	public void setPollnum(String[] pollnum) {
		this.pollnum = pollnum;
	}

	@Override
	public String toString() {
		return "PollBean [id=" + id + ", syear=" + syear + ", smonth=" + smonth + ", sday=" + sday + ", eyear=" + eyear
				+ ", emonth=" + emonth + ", eday=" + eday + ", sdate=" + sdate + ", edate=" + edate + ", question="
				+ question + ", itemcount=" + itemcount + ", poll1=" + poll1 + ", poll2=" + poll2 + ", poll3=" + poll3
				+ ", poll4=" + poll4 + ", poll5=" + poll5 + ", poll6=" + poll6 + ", poll7=" + poll7 + ", poll8=" + poll8
				+ ", poll9=" + poll9 + ", poll10=" + poll10 + ", poll11=" + poll11 + ", poll12=" + poll12 + ", poll13="
				+ poll13 + ", poll14=" + poll14 + ", poll15=" + poll15 + ", poll16=" + poll16 + ", poll17=" + poll17
				+ ", poll18=" + poll18 + ", poll19=" + poll19 + ", poll20=" + poll20 + ", pollnum="
				+ Arrays.toString(pollnum) + "]";
	}
	
	
	
}














