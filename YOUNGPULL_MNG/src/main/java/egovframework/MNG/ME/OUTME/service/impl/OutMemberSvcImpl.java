package egovframework.MNG.ME.OUTME.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import egovframework.MNG.ME.MemberVo;
import egovframework.MNG.ME.OUTME.service.OutMemberSvc;
import egovframework.MNG.util.CommonMessage;
import egovframework.MNG.util.fileUtil.FileUtils;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("outMemberSvc")
public class OutMemberSvcImpl extends EgovAbstractServiceImpl implements OutMemberSvc{
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Resource(name="messageUtil")
	private CommonMessage messageUtil;
	
	@Resource(name="outMemberMapper") 
	private OutMemberMapper outMemberMapper; 
	

	@Override
	public List<?> OUTMEMBER_R(Map<String,Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		
		return outMemberMapper.OUTMEMBER_R(paramMap);
	}

	@Override
	public int OUTMEMBER_CNT_R(Map<String,Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return outMemberMapper.OUTMEMBER_CNT_R(paramMap);
	}

	@Override
	public MemberVo OUTMEMBER_DTL_R(Map<String,Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return outMemberMapper.OUTMEMBER_DTL_R(paramMap);
	}

	@Override
	public JSONObject OUTMEMBER_CUD(Map<String,Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		String dataCode = "";
		String dataStatus = paramMap.get("dataStatus") != null || !"".equals(paramMap.get("dataStatus")) ? paramMap.get("dataStatus").toString() : "";
		try {
			//데이터 상태값
			if ("I".equals(dataStatus) || "U".equals(dataStatus)) {
				outMemberMapper.OUTMEMBER_CU(paramMap);
			}else if ("D".equals(paramMap.get("dataStatus"))) {
				outMemberMapper.OUTMEMBER_D(paramMap);
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
