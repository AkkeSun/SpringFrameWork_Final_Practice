package bit.com.a.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.MemberDao;
import bit.com.a.dto.MemberDto;
import bit.com.a.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberdao;

	@Override
	public int getId(String id) {
		System.out.println("in MemberService getId()");
		return memberdao.getId(id);
	}

	@Override
	public boolean addmember(MemberDto dto) {
		return 	memberdao.addmember(dto);

	}

	@Override
	public MemberDto login(MemberDto dto) {
		return memberdao.login(dto);
	}
	
}
