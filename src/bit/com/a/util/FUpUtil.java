package bit.com.a.util;

import java.util.Date;

public class FUpUtil {
	// myfile.txt => f.indexOf('.') = 6
	// 파일명과 확장자명 구분
	// f.substring(6) = .txt
	// f.substring(0, 6) = myfile
	
	
	
	// myfile.txt => 23423432.txt 시스템 시간을 가진 제목으로 변경
	
	public static String getNewFileName(String f) {
		String filename = "";
		String fpost = "";
		
		if(f.indexOf('.') >= 0) {	// 확장자명을 가진 파일
			fpost = f.substring( f.indexOf('.') );	// .txt
			filename = new Date().getTime() + fpost;  // 4324234.txt
		}
		else {	// 확장자명이 없는 파일
			filename = new Date().getTime() + ".back";
		}
		
		return filename;
	}
}
