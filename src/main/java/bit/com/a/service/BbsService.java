package bit.com.a.service;

import java.util.List;

import bit.com.a.dto.BbsDto;
import bit.com.a.dto.BbsParam;

public interface BbsService {
	List<BbsDto> getbbslist(BbsParam bbs);
	int getBbsCount(BbsParam bbs);
	BbsDto getBbsDetail (int seq);
	void countUp(int seq);

	boolean bbsUpdate(BbsDto bbs);
	boolean bbsDelete(int seq);
	boolean bbsWriteAf(BbsDto bbs);
	boolean answerUpdate(int seq);
	boolean answerInsert(BbsDto bbs); 
}
