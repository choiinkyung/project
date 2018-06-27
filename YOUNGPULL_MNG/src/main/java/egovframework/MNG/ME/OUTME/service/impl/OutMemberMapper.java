package egovframework.MNG.ME.OUTME.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.MNG.ME.MemberVo;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper ("outMemberMapper")
public interface OutMemberMapper {

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
	int OUTMEMBER_CNT_R(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 탈퇴회원 상세조회
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	MemberVo OUTMEMBER_DTL_R(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 탈퇴회원 저장 및 수정
	 * @param paramMap
	 * @throws Exception
	 */
	void OUTMEMBER_CU(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 탈퇴회원 삭제
	 * @param paramMap
	 * @throws Exception
	 */
	void OUTMEMBER_D(Map<String,Object> paramMap) throws Exception;
	
	
}
