package egovframework.MNG.AD.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import egovframework.MNG.AD.service.AdminSvc;
import egovframework.MNG.AD.service.AdminVo;
import egovframework.MNG.util.fileUtil.FileUtils;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("adminSvc")
public class AdminSvcImpl extends EgovAbstractServiceImpl implements AdminSvc{
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Resource(name="adminMapper") 
	private AdminMapper AdminMapper; 
	

	@Override
	public List ADMIN_R(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		
		return AdminMapper.ADMIN_R(paramMap);
	}

	@Override
	public int ADMIN_CNT_R(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return AdminMapper.ADMIN_CNT_R(paramMap);
	}

	@Override
	public AdminVo ADMIN_DTL_R(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return AdminMapper.ADMIN_DTL_R(paramMap);
	}

	@Override
	public JSONObject ADMIN_CUD(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
