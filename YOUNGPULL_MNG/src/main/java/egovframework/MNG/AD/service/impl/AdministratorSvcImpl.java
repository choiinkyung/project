package egovframework.MNG.AD.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import egovframework.MNG.AD.service.AdministratorSvc;
import egovframework.MNG.AD.service.AdministratorVo;
import egovframework.MNG.AD.service.administratorVo;
import egovframework.MNG.util.fileUtil.FileUtils;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("administratorSvc")
public class AdministratorSvcImpl extends EgovAbstractServiceImpl implements AdministratorSvc{
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Resource(name="sampleMapper") 
	private AdministratorMapper administratorMapper; 
	
	@Resource(name = "sampleIndexService")
	private EgovIdGnrService sampleIndexService;

	@Override
	public List SAMPLE_R(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		
		return administratorMapper.SAMPLE_R(paramMap);
	}

	@Override
	public int SAMPLE_CNT_R(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return administratorMapper.SAMPLE_CNT_R(paramMap);
	}

	@Override
	public AdministratorVo SAMPLE_DTL_R(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return administratorMapper.SAMPLE_DTL_R(paramMap);
	}

	@Override
	public JSONObject SAMPLE_CUD(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
