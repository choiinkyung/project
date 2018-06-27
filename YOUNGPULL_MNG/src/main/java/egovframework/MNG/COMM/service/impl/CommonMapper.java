package egovframework.MNG.COMM.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("commonMapper")
public interface CommonMapper {

	/**
	 * 파일 상세조회
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> selectFileView(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 공통코드 조회
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<?> COMMON_CODE_R(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 공통코드, 사이트 조회
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<?> COMMON_SITECODE_R(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 공통코드 등록
	 * @param paramMap
	 * @throws Exception
	 */
	void COMMON_CODE_C (Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 공통코드 수정
	 * @param paramMap
	 * @throws Exception
	 */
	
	void COMMON_CODE_U (Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 공통코드 삭제 
	 * @param paramMap
	 * @throws Exception
	 */
	void COMMON_CODE_D (Map<String,Object> paramMap) throws Exception;
	
	
	
}
