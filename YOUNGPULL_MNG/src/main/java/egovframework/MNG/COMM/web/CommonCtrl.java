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
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import egovframework.MNG.COMM.service.CommonSvc;
import egovframework.MNG.util.RequestUtil;
import egovframework.common.SettingKey;

@Controller
public class CommonCtrl {

	@Resource(name="commonSvc")
	private CommonSvc commonSvc;
	
	/**
	 * 파일 다운로드
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/cmmn/downloadFile.do")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Map paramMap = RequestUtil.process(request);
		
	    Map<String,Object> map = commonSvc.selectFileView(paramMap);

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
			commonSvc.ckeditorImageUpload(request, response, upload);
		} catch (IOException e) {
			e.printStackTrace();
			e.getMessage();
		}
	}
	
	@RequestMapping(value = "/mng/common/code/codeList.do")
	public String COMMON_CODE_R(ModelMap model, HttpServletRequest request) throws Exception {
		Map paramMap = RequestUtil.process(request);
		model.addAttribute("resultMap", paramMap);
		return "/common/CODE/codeList";
	}
	
	/**
	 * 공통코드 조회
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/code/codeList.json")
	public String COMMON_CODEJSON_R(ModelMap model, HttpServletRequest request) throws Exception {
		Map paramMap = RequestUtil.process(request);
		try {
			model.addAttribute("returnData", commonSvc.COMMON_CODE_R(paramMap));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SettingKey.JSON_VIEW;
	}
	
	/**
	 * 코드 등록/수정/삭제 처리
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/code/codeProc.json")
	public String SAMPLE_CU(ModelMap model, HttpServletRequest request) throws Exception {
		
		Map paramMap = RequestUtil.process(request);
		JSONObject json = new JSONObject();
		try {
			json = commonSvc.COMMON_CODE_CUD(paramMap);
			model.addAttribute("returnData", json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return SettingKey.JSON_VIEW;
	}
	
	/**
	 * 공통코드 및 사이트 조회
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/code/COMMON_SITECODE_R.json")
	public String COMMON_SITECODE_R(ModelMap model, HttpServletRequest request) throws Exception {
		Map paramMap = RequestUtil.process(request);
		try {
			model.addAttribute("returnData", commonSvc.COMMON_SITECODE_R(paramMap));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SettingKey.JSON_VIEW;
	}
	
	
}
