package egovframework.MNG.ME.INME.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import egovframework.MNG.ME.MemberVo;
import egovframework.MNG.ME.INME.service.MemberSvc;
import egovframework.MNG.util.CommonMessage;
import egovframework.MNG.util.fileUtil.FileUtils;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("memberSvc")
public class MemberSvcImpl extends EgovAbstractServiceImpl implements MemberSvc{
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Resource(name="messageUtil")
	private CommonMessage messageUtil;
	
	@Resource(name="memberMapper") 
	private MemberMapper memberMapper; 
	

	@Override
	public List<?> MEMBER_R(Map<String,Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		
		return memberMapper.MEMBER_R(paramMap);
	}

	@Override
	public int MEMBER_CNT_R(Map<String,Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return memberMapper.MEMBER_CNT_R(paramMap);
	}

	@Override
	public MemberVo MEMBER_DTL_R(Map<String,Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return memberMapper.MEMBER_DTL_R(paramMap);
	}

	@Override
	public JSONObject MEMBER_CUD(Map<String,Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		String dataCode = "";
		String dataStatus = paramMap.get("dataStatus") != null || !"".equals(paramMap.get("dataStatus")) ? paramMap.get("dataStatus").toString() : "";
		try {
			//데이터 상태값
			if ("I".equals(dataStatus) || "U".equals(dataStatus)) {
				
				memberMapper.MEMBER_CU(paramMap);
			}else if ("D".equals(paramMap.get("dataStatus"))) {
				memberMapper.MEMBER_D(paramMap);
			}else{
				//DB저장 상태값이 없을때 무조건 리턴시킴
				dataCode = "99";
				return messageUtil.returnMassage(dataStatus, messageUtil.setMsgCode(99), "데이터없음");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return messageUtil.returnMassage(dataStatus, messageUtil.setMsgCode(1), e.getMessage());
		}
		return messageUtil.returnMassage(dataStatus, messageUtil.setMsgCode(0), "");
	}

}
