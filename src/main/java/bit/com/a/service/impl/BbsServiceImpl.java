package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.BbsDao;
import bit.com.a.dto.BbsDto;
import bit.com.a.dto.BbsParam;
import bit.com.a.service.BbsService;

@Service
public class BbsServiceImpl implements BbsService {
	@Autowired
	BbsDao bbsdao;

	@Override
	public List<BbsDto> getbbslist(BbsParam bbs) {
		return bbsdao.getbbslist(bbs);
	}

	@Override
	public int getBbsCount(BbsParam bbs) {
		return bbsdao.getBbsCount(bbs);
	}

	@Override
	public BbsDto getBbsDetail(int seq) {			
		return bbsdao.getBbsDetail(seq);
	}

	@Override
	public boolean bbsUpdate(BbsDto bbs) {
		return bbsdao.bbsUpdate(bbs);
	}

	@Override
	public boolean bbsDelete(int seq) {
		return bbsdao.bbsDelete(seq);
	}

	@Override
	public boolean bbsWriteAf(BbsDto bbs) {
		return bbsdao.bbsWriteAf(bbs);
	}

	@Override
	public void countUp(int seq) {
		bbsdao.countUp(seq);
	}
	
	@Override
	public boolean answerUpdate(int seq) {
		return bbsdao.answerUpdate(seq);
	}

	@Override
	public boolean answerInsert(BbsDto bbs) {
		return bbsdao.answerInsert(bbs);
	}
}
