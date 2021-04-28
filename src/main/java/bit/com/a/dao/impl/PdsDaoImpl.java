package bit.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.PdsDao;
import bit.com.a.dto.PdsDto;
import bit.com.a.dto.PdsParam;

@Repository
public class PdsDaoImpl implements PdsDao {

	@Autowired
	SqlSession session;
	
	String ns = "Pds.";

	@Override
	public List<PdsDto> getPdsList(PdsParam pds) {		
		return session.selectList(ns + "getPdslist", pds);	
	}

	@Override
	public boolean uploadPds(PdsDto dto) {
		int i = session.insert(ns + "uploadPds", dto);
		return i>0?true:false;
	}

	@Override
	public boolean readCountUp(int seq) {
		int n = session.update(ns+"readCountUp", seq);
		return n>0?true:false;
	}

	@Override
	public PdsDto getPdsDetail(int seq) {
		return session.selectOne(ns+"getPdsDetail", seq);
	}

	@Override
	public boolean updatePds(PdsDto dto) {
		int n =session.update(ns+"updatePds", dto); 
		return n>0?true:false;
	}

	@Override
	public boolean pdsdelete(int seq) {
		int n =session.delete(ns+"pdsdelete", seq); 
		return false;
	}

	@Override
	public int psdlistCount(PdsParam param) {
		return session.selectOne(ns+"psdlistCount", param);
	}
	
}








