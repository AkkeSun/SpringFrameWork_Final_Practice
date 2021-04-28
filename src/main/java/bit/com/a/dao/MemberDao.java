package bit.com.a.dao;

import bit.com.a.dto.MemberDto;

public interface MemberDao {
	int getId(String id);
	boolean addmember(MemberDto dto);
	MemberDto login(MemberDto dto);
}