package egovframework.MNG.SI.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper ("siteMapper")
public interface SiteMapper {
	
	/**
	 * 사리트 관리 목록 조회
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List SITE_R(Map paramMap) throws Exception;
	
	/**
	 * 사이트 관리 목록 수
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	int SITE_CNT_R(Map paramMap)throws Exception;

	/**
	 * 사아트 삭제
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	int SITE_D(Map paramMap)throws Exception;
	
	/**
	 * 사이트 중복 체크
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	int SITE_CK_R(Map paramMap)throws Exception;
}
