package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.impl.YoutubeDaoImpl;
import bit.com.a.dto.YoutubeDto;

@Service
public class YoutubeServiceImpl {

	@Autowired
	YoutubeDaoImpl dao;
	
	public boolean addYou(YoutubeDto you) {
		return dao.addYou(you);
	}
	
	public List<YoutubeDto> getYoutubeList(YoutubeDto you) {
		return dao.getYoutubeList(you);
	}
	
	public boolean youtubeDelete(YoutubeDto you) {
		return dao.youtubeDelete(you);

	}


}