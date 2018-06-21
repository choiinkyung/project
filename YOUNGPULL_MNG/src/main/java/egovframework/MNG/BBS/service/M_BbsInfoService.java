package egovframework.MNG.BBS.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface M_BbsInfoService {

	
	List bbsInfoList (Map paramMap) throws Exception;
	
	int bbsInfoListCount (Map paramMap) throws Exception;
	
	void bbsInfoInsert (Map paramMap, M_BbsInfoVO bbsInfoVo, HttpServletRequest request) throws Exception;
	
	void bbsInfoUpdate (Map paramMap, M_BbsInfoVO bbsInfoVo, HttpServletRequest request) throws Exception;
	
	void bbsInfoDelete(Map paramMap) throws Exception;

	M_BbsInfoVO bbsInfoView (Map paramMap) throws Exception;
	
	List bbsInfoFileList (Map paramMap) throws Exception;
	
	void bbsInfoFileUpdate (Map paramMap) throws Exception;
	
	void bbsInfoOpenYnUpdate (Map paramMap) throws Exception;
	
}
