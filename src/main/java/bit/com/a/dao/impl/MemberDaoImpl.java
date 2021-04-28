package bit.com.a.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.MemberDao;
import bit.com.a.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	SqlSession sqlsessin;
		
	String namespace="Member.";
	
	@Override
	public int getId(String id) {
		System.out.println("in MemberDaoImpl getId()");
		return sqlsessin.selectOne(namespace+"getId", id);
	}

	@Override
	public boolean addmember(MemberDto dto) {
		System.out.println("in MemberDaoImpl getId()");
		int n = sqlsessin.insert(namespace+"addmember", dto);
		return n>0?true:false;
	}

	@Override
	public MemberDto login(MemberDto dto) {
		return sqlsessin.selectOne(namespace+"login", dto);
	}

}
