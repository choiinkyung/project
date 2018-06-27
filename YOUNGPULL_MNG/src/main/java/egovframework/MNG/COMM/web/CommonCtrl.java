package egovframework.MNG.COMM.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContextUtils;

import egovframework.MNG.COMM.service.CommonService;
import egovframework.MNG.util.RequestUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class CommonController {

	@Resource(name="commonService")
	private CommonService commonService;
	
	/**
	 * 파일 다운로드
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/cmmn/downloadFile.do")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Map paramMap = RequestUtil.process(request);
		
	    Map<String,Object> map = commonService.selectFileView(paramMap);

	    String storedFileName = (String)map.get("storedFileName");
	    String originalFileName = (String)map.get("originalFileName");
	    String storedFilePath = (String)map.get("storedFilePath");
	    
	    
	    try{
	    	
	    byte fileByte[] = FileUtils.readFileToByteArray(new File(storedFilePath+storedFileName));
        
        response.setContentType("application/octet-stream");
        response.setContentLength(fileByte.length);
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8")+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.getOutputStream().write(fileByte);
         
        response.getOutputStream().flush();
        response.getOutputStream().close();
	
	    } catch (Exception e) {
	        StringWriter sw = new StringWriter();
	        e.printStackTrace(new PrintWriter(sw));
	        String exceptionAsStrting = sw.toString();
	        e.printStackTrace();
	     }
	}
	
	/**
	 * 파일 업로드
	 * @param request
	 * @param response
	 * @param upload
	 * @throws Exception
	 */
	@RequestMapping(value="/cmmn/file/ckeditorImageUpload.do", method=RequestMethod.POST)

	public void ckeditorImageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset-utf-8");
		try {
			commonService.ckeditorImageUpload(request, response, upload);
		} catch (IOException e) {
			e.printStackTrace();
			e.getMessage();
		}
	}
}
