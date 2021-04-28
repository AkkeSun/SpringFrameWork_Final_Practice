package bit.com.a.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import bit.com.a.service.PdsService;

public class DownloadView extends AbstractView{

	@Autowired
	PdsService service;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("DownloadView renderMergedOutputModel");
		
		//getAttribute
		File file = (File)model.get("downloadFile");	
		String originalFileName = (String)model.get("originalFileName");
		int seq = (Integer)model.get("seq");

		
		response.setContentType(this.getContentType());
		response.setContentLength((int)file.length());
		
		//Internet Explore & chrome -> 타입 맞춰주기
		String userAgent = request.getHeader("user-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1;
		String filename = null;
		
		//Internet Explore
		if(ie) 
			filename = URLEncoder.encode(file.getName(), "utf-8");
		//chrome
		else 
			filename = new String( file.getName().getBytes("utf-8"), "iso-8859-1" );
		
		// 파일네임 한글명 설정
		originalFileName = URLEncoder.encode(originalFileName, "utf-8");
		
		//다운로드창 설정
		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFileName + "\";"); // originalFileName으로 다운
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Length", "" + file.length());
		response.setHeader("Pragma", "no-cache;"); 
		response.setHeader("Expires", "-1;");
		
		
	    //파일 만들기
		OutputStream out = response.getOutputStream();
		FileInputStream fi = new FileInputStream(file);
		
		//다운로드
		FileCopyUtils.copy(fi, out);
		
		//다운로드횟수 증가 (만들기)
		boolean b = service.readCountUp(seq);
		if(b) 
			System.out.println("readCountUp Success");
		else  
			System.out.println("readCountUp Fail");
			
		//닫아주기
		if(fi != null) {
			fi.close();
		}
		
	}

}
