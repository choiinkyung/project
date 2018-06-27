package egovframework.MNG.ME.INME.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.MNG.ME.MemberVo;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper ("memberMapper")
public interface MemberMapper {

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
	int MEMBER_CNT_R(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 회원 상세조회
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	MemberVo MEMBER_DTL_R(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 회원 저장 및 수정
	 * @param paramMap
	 * @throws Exception
	 */
	void MEMBER_CU(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 회원 삭제
	 * @param paramMap
	 * @throws Exception
	 */
	void MEMBER_D(Map<String,Object> paramMap) throws Exception;
	
	
}
