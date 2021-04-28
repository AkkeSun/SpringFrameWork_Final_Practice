package bit.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.BbsDao;
import bit.com.a.dto.BbsDto;
import bit.com.a.dto.BbsParam;

@Repository
public class BbsDaoImpl implements BbsDao{

	@Autowired
	SqlSession session;
	
	String ns = "Bbs.";

	@Override
	public List<BbsDto> getbbslist(BbsParam bbs) {
		return session.selectList(ns+"bbslist", bbs);
	}

	@Override
	public int getBbsCount(BbsParam bbs) {
		return session.selectOne(ns+"getBbsCount", bbs);
	}

	@Override
	public BbsDto getBbsDetail(int seq) {
		BbsDto dto =session.selectOne(ns+"bbsDetail", seq);
		return dto;
	}
	
	@Override
	public void countUp(int seq) {
		session.update(ns+"countUp", seq);
	}

	@Override
	public boolean bbsUpdate(BbsDto bbs) {
		int n = session.update(ns+"BbsUpdate", bbs);
		return n>0?true:false;
	}

	@Override
	public boolean bbsDelete(int seq) {
		int n = session.update(ns+"bbsDelete", seq);
		return n>0?true:false;
	}

	@Override
	public boolean bbsWriteAf(BbsDto bbs) {
		int n = session.insert(ns+"writeBbs", bbs);
		return n>0?true:false;
	}
	
	@Override
	public boolean answerUpdate(int seq) {
		int n = session.update(ns+"answerUpdate", seq);
		return n<0?true:false;
	}

	@Override
	public boolean answerInsert(BbsDto bbs) {
		int n = session.update(ns+"answerInsert", bbs);
		return n<0?true:false;
	}
	
}
