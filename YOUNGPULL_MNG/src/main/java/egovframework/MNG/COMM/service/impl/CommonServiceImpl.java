package egovframework.MNG.COMM.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.common.SettingKey;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.MNG.COMM.service.CommonService;

@Service("commonService")
public class CommonServiceImpl extends EgovAbstractServiceImpl implements CommonService{
	
	@Resource(name="commonMapper")
	private CommonMapper commonMapper;

	@Override
	public Map<String,Object> selectFileView(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return commonMapper.selectFileView(paramMap);
	}
	
	
	@Override
	public void ckeditorImageUpload(HttpServletRequest request,
			HttpServletResponse response, MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		OutputStream out = null;
		try{
			PrintWriter printWriter = null;	
			String fileName = file.getOriginalFilename();
	
			byte[] bytes = file.getBytes();
//			String uploadPath = SettingKey.EDITOR_FILE_PATH + "\\" + fileName;
			String uploadPath = SettingKey.EDITOR_FILE_PATH + "/" + new String(fileName.getBytes("UTF-8"));
			
			Runtime.getRuntime().exec("chmod 777 " + uploadPath);

			System.out.println(uploadPath);
			
			out = new FileOutputStream(new File(uploadPath));
			out.write(bytes);
			String callback = request.getParameter("CKEditorFuncNum");
			printWriter = response.getWriter();
			String fileUrl = "http://www.entostudy.com/upload/editor/" + new String(fileName.getBytes("UTF-8")); //url 경로
			printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
		               + callback
		               + ",'"
		               + fileUrl
		               + "','이미지를 업로드 하였습니다.'"
		               + ")</script>");
		       printWriter.flush();
		}catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}
	
	

}
