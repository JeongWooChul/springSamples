package bit.com.a.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("DownloadView renderMergedOutputModel");

		File file = (File)model.get("downloadFile");	// == getAttribute
		
		response.setContentType(this.getContentType());		// 응답받은 컨텐츠 타입
		response.setContentLength((int)(file.length()));	// 응답받은 길이값
		
		// IE/chrom인지 분간
		String userAgent = request.getHeader("user-Agent"); 
		boolean ie = userAgent.indexOf("MSIE") > -1;	// true면 익스플로어
		
		String filename = null;
		if(ie) {
			filename = URLEncoder.encode(file.getName(), "utf-8"); //IE일때 인코딩 
		} else {
			filename = new String( file.getName().getBytes("utf-8"), "iso-8859-1" );	//크롬일때 인코딩
		}
		
		// 다운로드 창
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Length", "" + file.length());
		response.setHeader("Pragma", "no-cache;"); 
		response.setHeader("Expires", "-1;");
		
		OutputStream out = response.getOutputStream();
		FileInputStream fi = null;
		
		fi = new FileInputStream(file);
		FileCopyUtils.copy(fi, out);	// 넘어온 파일 포인트를 복사해서 밖으로 내보냄 실제 파일 다운
		
		// db에서 다운로드 카운트 증가
		
		if(fi != null) {
			fi.close();
		}
		
		
	}

}
