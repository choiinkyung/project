package egovframework.MNG.AD.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public interface AdministratorSvc {

	/**
	 * 샘플 목록 조회
	 * @param paramMap
	 * @return 샘플 리스트 조회 결과값
	 * @throws Exception
	 */
	List SAMPLE_R(Map paramMap) throws Exception;
	
	/**
	 * 샘플 목록 카운트조회
	 * @param paramMap
	 * @return 샘플 카운트조획 결과값
	 * @throws Exception
	 */
	int SAMPLE_CNT_R  (Map paramMap) throws Exception;
	
	/**
	 * 샘플 상세조회
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	AdministratorVo SAMPLE_DTL_R(Map paramMap) throws Exception;
	
	
	/**
	 * 샘플 저장/수정/삭제 로직 처리
	 * @param paramMap
	 * @throws Exception
	 */
	JSONObject SAMPLE_CUD (Map paramMap) throws Exception;
	
	
}
