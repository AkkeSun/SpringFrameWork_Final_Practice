package bit.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dto.YoutubeDto;


@Repository
public class YoutubeDaoImpl {
	@Autowired
	SqlSession session;
	
	String ns="Youtube.";
	
	public boolean addYou(YoutubeDto you) {
		int n = session.insert(ns+"addYou", you);
		return n>0?true:false;
	}
	
	public List<YoutubeDto> getYoutubeList(YoutubeDto you) {
		return session.selectList(ns+"getYoutubeList", you);
	}
	
	public boolean youtubeDelete(YoutubeDto you) {
		int n = session.delete(ns+"youtubeDelete", you);
		return n>0?true:false;
	}
	
	
	
	
	
}
