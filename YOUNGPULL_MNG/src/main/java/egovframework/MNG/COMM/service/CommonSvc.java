package egovframework.MNG.COMM.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public interface CommonSvc {

	/**
	 * 첨부파일 조회
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> selectFileView(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * CK에디터 썸네일 이미지 업로드
	 * @param request
	 * @param response
	 * @param file
	 * @throws Exception
	 */
	void ckeditorImageUpload (HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws Exception ;
	
	/**
	 * 공통코드 목록조회
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	JSONObject COMMON_CODE_R (Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 공통코드 저장/수정/삭제 로직 처리
	 * @param paramMap
	 * @throws Exception
	 */
	JSONObject COMMON_CODE_CUD (Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 공통 코드 사이트 조회
	 * @param paramMap
	 * @throws Exception
	 */
	JSONObject COMMON_SITECODE_R (Map<String,Object> paramMap) throws Exception;
	
	
}
