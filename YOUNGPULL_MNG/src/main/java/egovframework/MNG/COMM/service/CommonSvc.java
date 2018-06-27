package egovframework.MNG.COMM.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface CommonService {

	Map<String,Object> selectFileView(Map paramMap) throws Exception;
	
	void ckeditorImageUpload (HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws Exception ;
	
}
