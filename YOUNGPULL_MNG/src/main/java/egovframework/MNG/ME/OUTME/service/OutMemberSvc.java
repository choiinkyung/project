package egovframework.MNG.ME.OUTME.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import egovframework.MNG.ME.MemberVo;

public interface OutMemberSvc {

	/**
	 * 탈퇴회원 목록 조회
	 * @param paramMap
	 * @return 탈퇴회원 리스트 조회 결과값
	 * @throws Exception
	 */
	List<?> OUTMEMBER_R(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 탈퇴회원 목록 카운트조회
	 * @param paramMap
	 * @return 탈퇴회원 카운트조획 결과값
	 * @throws Exception
	 */
	int OUTMEMBER_CNT_R  (Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 탈퇴회원 상세조회
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	MemberVo OUTMEMBER_DTL_R(Map<String,Object> paramMap) throws Exception;
	
	
	/**
	 * 탈퇴회원 저장/수정/삭제 로직 처리
	 * @param paramMap
	 * @throws Exception
	 */
	JSONObject OUTMEMBER_CUD (Map<String,Object> paramMap) throws Exception;
	
	
}
