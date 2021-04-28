package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.PdsDao;
import bit.com.a.dto.PdsDto;
import bit.com.a.dto.PdsParam;
import bit.com.a.service.PdsService;
@Service
public class PdsServiceImpl implements PdsService {

	@Autowired
	PdsDao dao;

	@Override
	public List<PdsDto> getPdsList(PdsParam pds) {
		return dao.getPdsList(pds);
	}

	@Override
	public boolean uploadPds(PdsDto dto) {
		return dao.uploadPds(dto);
	}

	@Override
	public boolean readCountUp(int seq) {
		return dao.readCountUp(seq);
	}

	@Override
	public PdsDto getPdsDetail(int seq) {
		return dao.getPdsDetail(seq);
	}

	@Override
	public boolean updatePds(PdsDto dto) {
		return dao.updatePds(dto);
	}

	@Override
	public boolean pdsdelete(int seq) {
		return dao.pdsdelete(seq);
	}

	@Override
	public int psdlistCount(PdsParam param) {
		return dao.psdlistCount(param);
	}
	
}
