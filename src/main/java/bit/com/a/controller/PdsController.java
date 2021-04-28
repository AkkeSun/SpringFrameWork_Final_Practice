package bit.com.a.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import bit.com.a.dto.BbsDto;
import bit.com.a.dto.PdsDto;
import bit.com.a.dto.PdsParam;
import bit.com.a.service.PdsService;
import bit.com.a.util.PdsUtil;

@Controller
public class PdsController {

	@Autowired
	PdsService service;

	
//TODO 일단 이동
		@RequestMapping(value = "pdslist.do", method = RequestMethod.GET)
		public String pdslist(Model model) {
			
			model.addAttribute("doc_title", "자료실 목록");
			return "pdslist.tiles";
	}	
	
	
//TODO 이동후에 Ajax로 처리하기 
	@ResponseBody 
	@RequestMapping(value = "pdslistData.do", method = {RequestMethod.GET, RequestMethod.POST})
	public List<PdsDto> pdslist(Model model, PdsParam param) {

		//값 내보내기
		model.addAttribute("doc_title", "자료실 목록");
		
		//start, end값 셋팅
		int start = param.getPage() * 10 + 1;
		int end = (param.getPage()+1) * 10 ;
		
		//확인
		System.out.println("search : "+param.getSearch());
		System.out.println("choice : "+param.getChoice());
		System.out.println("page : "+param.getPage());
		
		param.setStart(start);
		param.setEnd(end);
		List<PdsDto> list = service.getPdsList(param);
		
		return list;
	}
	
//TODO 페이징 처리를 위한 글의 총 수 구하기
		@ResponseBody 
		@RequestMapping(value = "psdlistCount.do", method = RequestMethod.GET)
		public int bbslistcount(PdsParam param) {
			int count = service.psdlistCount(param);
			System.out.println("글의 총수 : " + count);
			return count;
		}	
	
//TODO 자료 올리기	
	@RequestMapping(value = "pdswrite.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String pdswrite(Model model) {
		model.addAttribute("doc_title", "자료 올리기");
		
		return "pdswrite.tiles";
	}
	
	@RequestMapping(value = "pdsupload.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String pdsupload(PdsDto pdsdto, HttpServletRequest req,
							//폼필드가 아니라면 여기로 넘어온다. (value=jsp에서 file name 값)
							@RequestParam(value = "fileload", required = false)
							MultipartFile fileload) {

		// upload 경로 설정
		// 1. server(tomcat)
		String fupload = req.getServletContext().getRealPath("/upload");
		
		// 2. folder
		// String fupload = "d:\\tmp";
		System.out.println("파일경로: " + fupload);
		
		// filename 취득
		String filename = fileload.getOriginalFilename();
		// new filename 생성 (Pdsutil 활용)
		String newfilename = PdsUtil.getNewFilename(filename);
		
		// filename, newfilename dto에 넣어주기
		pdsdto.setFilename(filename);
		pdsdto.setNewfilename(newfilename);
		
		// 파일명 + 확장자 
		File file = new File(fupload + "/" + newfilename);
		
		try {			
			//파일 업로드
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());
			//db저장
			service.uploadPds(pdsdto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/pdslist.do";
	}
	
	
//TODO 파일 다운로드	
	@RequestMapping(value = "fileDownload.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String fileDownload (PdsDto dto, 
			                    HttpServletRequest req, Model model) {
		
		// 경로
		//1. server
		String fupload = req.getServletContext().getRealPath("/upload");
		
		// 2. folder
		// String fupload = "d:\\tmp";
		System.out.println("파일경로: " + fupload);
		
		// 다운로드 받을 파일명 + 확장자
		File downloadFile = new File(fupload + "/" + dto.getNewfilename());
		
		model.addAttribute("downloadFile", downloadFile);  
		model.addAttribute("originalFileName", dto.getFilename()); //오리지날파일네임  
		model.addAttribute("seq", dto.getSeq());
		
		// downloadView.xml 에서 생성된 객채에 접근 (downloadview.java) -> 이동하는게 아니야. 그냥 다운받는거지
		return "downloadView";
	}
	
	
	
//TODO 디테일
	@RequestMapping(value = "pdsdetail.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String pdsdetail (Model model, int seq) {

		PdsDto pds = service.getPdsDetail(seq);
		service.readCountUp(seq);
		
		model.addAttribute("doc_title", "자료 Detail");
		model.addAttribute("pds", pds);
		
		return "pdsdetail.tiles";
	}
	

	
//TODO 삭제
		@RequestMapping(value = "pdsdelete.do", method = {RequestMethod.GET, RequestMethod.POST})
		public String pdsdelete (int seq) {
		
			boolean b = service.pdsdelete(seq);
			if(b)
				System.out.println("pdsdelete success");
			else
				System.out.println("pdsdelete success");
			return "redirect:/pdslist.do";

		}
		


//TODO 수정
	@RequestMapping(value = "pdsupdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String pdsupdate(PdsDto pdsdto, HttpServletRequest req,
							//폼필드가 아니라면 여기로 넘어온다. (value=jsp에서 file name 값)
							@RequestParam(value = "fileload", required = false)
							MultipartFile fileload) {
		
		// upload 경로 설정
		String fupload = req.getServletContext().getRealPath("/upload");
		System.out.println("파일경로: " + fupload);
		
		// 새로 업로드한 filename 취득
		String filename = fileload.getOriginalFilename();
		
		// 파일을 업로드 했다면 (업로드 안했으면 null이 아니라 빈 문자열이 들어옴)
		if(!filename.equals("")) {

			// new filename 생성 (Pdsutil 활용)
			String newfilename = PdsUtil.getNewFilename(filename);
			
			// filename, newfilename dto에 넣어주기
			pdsdto.setFilename(filename);
			pdsdto.setNewfilename(newfilename);
			
			// 파일명 + 확장자 
			File file = new File(fupload + "/" + newfilename);
			
			try {			
				//파일 업로드
				FileUtils.writeByteArrayToFile(file, fileload.getBytes());
				//db저장
				service.updatePds(pdsdto);
				System.out.println("updatePds Success (new File ver)");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("updatePds Faile (new File ver)");
			}
		}
		
		//새로운 파일을 업로드하지 않았다면
		else {
			//db저장 : filename, newfilename 변경사항 없음
			service.updatePds(pdsdto);
			System.out.println("updatePds Faile");
		}
		
		return "redirect:/pdslist.do";
	}
	
}
