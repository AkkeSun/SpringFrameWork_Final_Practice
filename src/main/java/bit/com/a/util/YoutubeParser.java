package bit.com.a.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Component;

@Component	// autowired를 사용하기 위함. 전역변수처럼 사용	
public class YoutubeParser {

	// 검색에 사용되는 기본 url
	String urls = "https://www.youtube.com/results?search_query=";
	
	public String search(String s) {		
		System.out.println("검색:" + s);
		
		BufferedReader br = null;
		String str = "";
		
		try {
			// 인코딩
			String ss = URLEncoder.encode(s, "utf-8");
			// url 완성			
			URL url = new URL(urls + ss);
			
			br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
			
			String msg = "";  // 전체 소스를 담는 그릇
			String text = ""; // json type부분만 저장할 그릇
			
			// null이 아닐때까지 읽어라
			while((msg = br.readLine())!= null) {
				text += msg.trim();
			}
			
			
			// 시작 위치
			int pos = text.trim().indexOf("\"responseContext\"");
			
			// 끝 위치 (pos 다음에 있는 };의 위치)
			int endpos = text.indexOf("};", pos);
			
			str = text.substring(pos - 1, endpos + 1);
			// https://jsonformatter.curiousconcept.com
			System.out.println(str); // 이거 복사해서 youtube폴더에 json파일 만들어 넣어두기
			
			//jsp에서 읽기 위해 따옴표 처리 수정
			str = str.replace("\"", "\\\"");	// " -> \"
			str = str.replace("\'", "\\\'");	// ' -> \'
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return str;
	}
}