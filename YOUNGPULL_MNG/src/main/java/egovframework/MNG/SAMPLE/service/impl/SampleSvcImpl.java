package egovframework.MNG.SAMPLE.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import egovframework.MNG.SAMPLE.service.SampleSvc;
import egovframework.MNG.SAMPLE.service.SampleVo;
import egovframework.MNG.util.fileUtil.FileUtils;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("sampleService")
public class SampleSvcImpl extends EgovAbstractServiceImpl implements SampleSvc{
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Resource(name="sampleMapper") 
	private SampleMapper sampleMapper; 
	
	@Resource(name = "sampleIndexService")
	private EgovIdGnrService sampleIndexService;

	@Override
	public List SAMPLE_R(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return sampleMapper.SAMPLE_R(paramMap);
	}

	@Override
	public int SAMPLE_CNT_R(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return sampleMapper.SAMPLE_CNT_R(paramMap);
	}

	@Override
	public SampleVo SAMPLE_DTL_R(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return sampleMapper.SAMPLE_DTL_R(paramMap);
	}

	@Override
	public JSONObject SAMPLE_CUD(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		JSONObject returnData = new JSONObject();
		try {

			//데이터 상태값
			String dataStatus = paramMap.get("dataStatus") != null || !"".equals(paramMap.get("dataStatus")) ? paramMap.get("dataStatus").toString() : "";
			if ("I".equals(dataStatus) || "U".equals(dataStatus)) {
				
				if ("I".equals(dataStatus)) {
					//등록일때만 신규 인덱스값 생성
					paramMap.put("sampleSeq", sampleIndexService.getNextStringId());
				}
				sampleMapper.SAMPLE_CU(paramMap);
			}else if ("D".equals(paramMap.get("dataStatus"))) {
				sampleMapper.SMAPLE_D(paramMap);
			}else{
				//DB저장 상태값이 없을때 무조건 리턴시킴
				returnData.put("result", "NULL");
				returnData.put("returnMsg", "NULL");
				return returnData;
			}
			returnData.put("result", "OK");
			returnData.put("returnMsg", "OK");
			returnData.put("returnPage", "OK");
			
		} catch (Exception e) {
			// TODO: handle exception
			returnData.put("result", "FAIL");
			returnData.put("returnMsg", e.getMessage());
		}
		return returnData;
	}
	


}
