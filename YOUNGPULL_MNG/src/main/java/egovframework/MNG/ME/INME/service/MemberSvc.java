package egovframework.MNG.ME.INME.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import egovframework.MNG.ME.MemberVo;

public interface MemberSvc {

	/**
	 * 회원 목록 조회
	 * @param paramMap
	 * @return 회원 리스트 조회 결과값
	 * @throws Exception
	 */
	List<?> MEMBER_R(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 회원 목록 카운트조회
	 * @param paramMap
	 * @return 회원 카운트조획 결과값
	 * @throws Exception
	 */
	int MEMBER_CNT_R  (Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 회원 상세조회
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	MemberVo MEMBER_DTL_R(Map<String,Object> paramMap) throws Exception;
	
	
	/**
	 * 회원 저장/수정/삭제 로직 처리
	 * @param paramMap
	 * @throws Exception
	 */
	JSONObject MEMBER_CUD (Map<String,Object> paramMap) throws Exception;
	
	
}
