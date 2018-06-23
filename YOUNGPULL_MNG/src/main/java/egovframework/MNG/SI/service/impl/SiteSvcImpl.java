package egovframework.MNG.SI.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.MNG.SI.service.SiteSvc;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service ("siteSvc")
public class SiteSvcImpl extends EgovAbstractServiceImpl implements SiteSvc{
	
	@Resource(name="siteMapper")
	private SiteMapper siteMapper;

	/**
	 * 사이트 관리 리스트
	 */
	@Override
	public List SITE_R(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return siteMapper.SITE_R(paramMap);
	}

	/**
	 * 사리트 관리 리스트 수
	 */
	@Override
	public int SITE_CNT_R(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return siteMapper.SITE_CNT_R(paramMap);
	}

	/**
	 * 사이트 삭제
	 */
	@Override
	public int SITE_D(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return siteMapper.SITE_D(paramMap);
	}

	/**
	 * 사이트 중복 체크
	 */
	@Override
	public int SITE_CK_R(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return siteMapper.SITE_CK_R(paramMap);
	}
	
	
}
