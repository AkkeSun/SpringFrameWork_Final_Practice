package bit.com.a.service;

import bit.com.a.dto.MemberDto;

public interface MemberService {
	int getId(String id);
	boolean addmember(MemberDto dto);
	MemberDto login(MemberDto dto);

}
