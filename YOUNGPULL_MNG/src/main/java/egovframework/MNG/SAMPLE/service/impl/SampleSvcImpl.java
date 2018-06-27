package egovframework.MNG.SAMPLE.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.MNG.SAMPLE.service.SampleSvc;
import egovframework.MNG.SAMPLE.service.SampleVo;
import egovframework.MNG.util.CommonMessage;
import egovframework.MNG.util.fileUtil.FileUtils;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("sampleSvc")
public class SampleSvcImpl extends EgovAbstractServiceImpl implements SampleSvc{
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Resource(name="messageUtil")
	private CommonMessage messageUtil;
	
	@Resource(name="sampleMapper") 
	private SampleMapper sampleMapper; 
	
	@Resource(name = "sampleIndexService")
	private EgovIdGnrService sampleIndexService;

	@Override
	public List<?> SAMPLE_R(Map<String,Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		
		return sampleMapper.SAMPLE_R(paramMap);
	}

	@Override
	public int SAMPLE_CNT_R(Map<String,Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return sampleMapper.SAMPLE_CNT_R(paramMap);
	}

	@Override
	public SampleVo SAMPLE_DTL_R(Map<String,Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return sampleMapper.SAMPLE_DTL_R(paramMap);
	}

	@Override
	public JSONObject SAMPLE_CUD(Map<String,Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		String dataCode = "";
		String dataStatus = paramMap.get("dataStatus") != null || !"".equals(paramMap.get("dataStatus")) ? paramMap.get("dataStatus").toString() : "";
		try {
			//데이터 상태값
			if ("I".equals(dataStatus) || "U".equals(dataStatus)) {
				
				if ("I".equals(dataStatus)) {
					//등록일때만 신규 인덱스값 생성
					paramMap.put("seq_v", sampleIndexService.getNextStringId());
				}
				sampleMapper.SAMPLE_CU(paramMap);
			}else if ("D".equals(paramMap.get("dataStatus"))) {
				sampleMapper.SMAPLE_D(paramMap);
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
