package bit.com.a.util;

import java.util.Date;

public class PdsUtil {	
	// 파일명, 확장자명 분리하기
	// myfile.txt -> f.indexOf('.') -> 6
	// f.subString(6)     -> .txt 
	// f.subString(0, 6)  -> myfile
	
	// newfilename 만들기
	public static String getNewFilename(String f) {
		String filename = "";
		String fpost = "";
		
		//확장자명이 있다면
		if(f.indexOf('.') >= 0) {
			fpost = f.substring( f.indexOf('.') );	    // 확장자 
			filename = new Date().getTime() + fpost;    // newfilename 
		}
		//확장자명이 없다면
		else {
			filename = new Date().getTime() + ".back";  // newfilename 
		}
		
		return filename;
	}
	
}
