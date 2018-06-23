package egovframework.MNG.AD.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.MNG.AD.service.AdministratorVo;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper ("adminMapper")
public interface AdministratorMapper {

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
	int SAMPLE_CNT_R(Map paramMap) throws Exception;
	
	/**
	 * 샘플 상세조회
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	AdministratorVo SAMPLE_DTL_R(Map paramMap) throws Exception;
	
	/**
	 * 샘플 저장 및 수정
	 * @param paramMap
	 * @throws Exception
	 */
	void SAMPLE_CU(Map paramMap) throws Exception;
	
	/**
	 * 샘플 삭제
	 * @param paramMap
	 * @throws Exception
	 */
	void SMAPLE_D(Map paramMap) throws Exception;
	
	
}
