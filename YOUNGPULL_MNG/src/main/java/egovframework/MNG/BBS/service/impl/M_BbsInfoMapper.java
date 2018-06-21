package egovframework.MNG.BBS.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.MNG.BBS.service.M_BbsInfoVO;

@Mapper ("mBbsInfoMapper")
public interface M_BbsInfoMapper {

	
	List bbsInfoList (Map paramMap) throws Exception;
	
	int bbsInfoListCount (Map paramMap) throws Exception;
	
	void bbsInfoInsert (Map paramMap) throws Exception;
	
	void bbsInfoUpdate (Map paramMap) throws Exception;
	
	M_BbsInfoVO bbsInfoView (Map paramMap) throws Exception;
	
	String selectBbsInfoSeq (Map paramMap) throws Exception;
	
	void bbsInfoFileInsert (M_BbsInfoVO bbsInfoVo) throws Exception;
	
	List bbsInfoFileList (Map paramMap) throws Exception;
	
	void bbsInfoFileUpdate (Map paramMap) throws Exception;
	
	void bbsInfoFileDelete (Map paramMap) throws Exception;
	
	void bbsInfoFileListDelete (Map paramMap) throws Exception;
	
	void bbsInfoDelete (Map paramMap) throws Exception;
	
	void bbsInfoOpenYnUpdate (Map paramMap) throws Exception;
	

}
