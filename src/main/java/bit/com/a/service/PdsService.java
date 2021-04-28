package bit.com.a.service;

import java.util.List;

import bit.com.a.dto.PdsDto;
import bit.com.a.dto.PdsParam;

public interface PdsService {
	List<PdsDto> getPdsList(PdsParam pds);
	boolean uploadPds(PdsDto dto);
	boolean readCountUp(int seq);
	PdsDto getPdsDetail(int seq);
	boolean updatePds(PdsDto dto);
	boolean pdsdelete(int seq);
	int psdlistCount(PdsParam param);
	
}
