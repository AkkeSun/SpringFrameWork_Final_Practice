package bit.com.a.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.dto.BbsDto;
import bit.com.a.dto.BbsParam;
import bit.com.a.service.BbsService;

@Controller
public class BbsController {

	@Autowired
	BbsService service;
	
	
//TODO 일단 이동
	@RequestMapping(value = "bbslist.do", method = RequestMethod.GET)
	public String bbslist(Model model) {
		
		model.addAttribute("doc_title", "글목록");
		return "bbslist.tiles";
	}
	
	
//TODO 이동후에 Ajax로 처리하기 
	@ResponseBody 
	@RequestMapping(value = "bbslistData.do", method = RequestMethod.GET)
	public List<BbsDto> bbslist(Model model, BbsParam param) {
		
		//값 내보내기
		model.addAttribute("doc_title", "글목록");
		
		//start, end값 셋팅
		int start = param.getPage() * 10 + 1;
		int end = (param.getPage()+1) * 10 ;
		
		//확인
		System.out.println("search : "+param.getSearch());
		System.out.println("choice : "+param.getChoice());
		System.out.println("page : "+param.getPage());
				
		param.setStart(start);
		param.setEnd(end);
		List<BbsDto> list = service.getbbslist(param);
		
		return list;
	}
	
//TODO 페이징 처리를 위한 글의 총 수 구하기
	@ResponseBody 
	@RequestMapping(value = "bbslistCount.do", method = RequestMethod.GET)
	public int bbslistcount(BbsParam param) {
		int count = service.getBbsCount(param);
		System.out.println("글의 총수 : " + count);
		return count;
	}
	
	
	
//TODO Detail
	@RequestMapping(value = "bbsdetail.do", method = RequestMethod.GET)
	public String bbsdetail(int seq, Model model) {
		BbsDto bbs = service.getBbsDetail(seq);
		
		//K : V
		model.addAttribute("bbs", bbs);
		//count UP
		service.countUp(seq);
		return "bbsdetail.tiles";
	}

	
//TODO Update
	@RequestMapping(value = "bbsupdate.do", method = RequestMethod.GET)
	public String bbsupdate(int seq, Model model) {
		BbsDto bbs = service.getBbsDetail(seq);
		model.addAttribute("bbs",bbs);
		return "bbsupdate.tiles";
	}
	
	@RequestMapping(value = "bbsupdateAf.do", method = RequestMethod.POST)
	public String bbsupdatAf(BbsDto bbs) {
		boolean b = service.bbsUpdate(bbs);
		if(b)
			System.out.println("BbsUpdate Success");
		else
			System.out.println("BbsUpdate Success");
		
		return "redirect:/bbslist.do";
	}
	
	
//TODO Delete
		@RequestMapping(value = "bbsDelete.do", method = RequestMethod.GET)
		public String bbsDelete(int seq) {
			boolean b = service.bbsDelete(seq);
			
			if(b)
				System.out.println("bbsDelete Success");
			else
				System.out.println("bbsDelete Fail");
			
			return "redirect:/bbslist.do";
		}
		
	
//TODO Write
		@RequestMapping(value = "bbswrite.do", method = RequestMethod.GET)
		public String bbswrite() {
			return "bbswrite.tiles";
		}
		
		@RequestMapping(value = "bbsWriteAf.do", method = RequestMethod.POST)
		public String bbsWriteAf(BbsDto bbs) {
			boolean b = service.bbsWriteAf(bbs);
			
			if(b)
				System.out.println("bbsWriteAf Success");
			else
				System.out.println("bbsWriteAf Fail");
			
			return "redirect:/bbslist.do";
		}
		
//TODO Answer	
		@RequestMapping(value = "answer.do", method = RequestMethod.GET)
		public String answerbbs(int seq, Model model) {
			BbsDto bbs = service.getBbsDetail(seq);
			
			model.addAttribute("bbs", bbs);
			return "answer.tiles";
		}	
		
		@RequestMapping(value = "answerAf.do", method = RequestMethod.POST)
		public String answerAf(BbsDto bbs, Model model) {
			
			service.answerUpdate(bbs.getSeq());  // 재 정렬(부모글의 seq)
			boolean b=service.answerInsert(bbs); // 값 넣기

			if(b) {
				System.out.println("answer success");
				return "redirect:/bbslist.do";
			} else {
				System.out.println("answer fail");
				return "redirect:/bbslist.do";
			}
		}
		

}
