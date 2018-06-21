package egovframework.MNG.BBS.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.MNG.BBS.service.M_BbsInfoService;
import egovframework.MNG.BBS.service.M_BbsInfoVO;
import egovframework.MNG.util.fileUtil.FileUtils;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("mBbsInfoService")
public class M_BbsInfoServiceImpl extends EgovAbstractServiceImpl implements M_BbsInfoService{

	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Resource(name="mBbsInfoMapper") 
	private M_BbsInfoMapper mBbsInfoMapper; 
 
	@Override
	public List bbsInfoList(Map paramMap) throws Exception { 
		// TODO Auto-generated method stub  
		return mBbsInfoMapper.bbsInfoList(paramMap);
	} 

	@Override
	public int bbsInfoListCount(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return mBbsInfoMapper.bbsInfoListCount(paramMap);
	}

	
	@Override
	public void bbsInfoInsert(Map paramMap, M_BbsInfoVO bbsInfoVo, HttpServletRequest request) throws Exception {
		
		mBbsInfoMapper.bbsInfoInsert(paramMap);
		paramMap.put("bi_seq_n", mBbsInfoMapper.selectBbsInfoSeq(paramMap));
		
		List<Map<String,Object>> fileList = fileUtils.parseInsertFileInfo(paramMap, request);
		
		for(int i=0, size=fileList.size(); i<size; i++){
			bbsInfoVo.setBi_seq_n(fileList.get(i).get("BI_SEQ_N").toString());
			bbsInfoVo.setBf_orgFileName_v(fileList.get(i).get("ORIGINAL_FILE_NAME").toString());
			bbsInfoVo.setBf_storFileName_v(fileList.get(i).get("STORED_FILE_NAME").toString());
			bbsInfoVo.setBf_fileSize_n(fileList.get(i).get("FILE_SIZE").toString());
			bbsInfoVo.setBf_filePath_v(fileList.get(i).get("FILE_PATH").toString());
			mBbsInfoMapper.bbsInfoFileInsert(bbsInfoVo);
		}
	}
	
	@Override
	public void bbsInfoUpdate(Map paramMap, M_BbsInfoVO bbsInfoVo,
			HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		mBbsInfoMapper.bbsInfoUpdate(paramMap);
		
		//mBbsInfoMapper.bbsInfoFileListDelete(paramMap);
		List<Map<String,Object>> fileList = fileUtils.parseUpdateFileInfo(paramMap, request);
		Map<String,Object> tempMap = null;
		for(int i=0, size=fileList.size(); i<size; i++){
			tempMap = fileList.get(i);
			if(tempMap.get("IS_NEW").equals("Y")){
				bbsInfoVo.setBf_orgFileName_v(fileList.get(i).get("ORIGINAL_FILE_NAME").toString());
				bbsInfoVo.setBf_storFileName_v(fileList.get(i).get("STORED_FILE_NAME").toString());
				bbsInfoVo.setBf_fileSize_n(fileList.get(i).get("FILE_SIZE").toString());
				bbsInfoVo.setBf_filePath_v(fileList.get(i).get("FILE_PATH").toString());
				mBbsInfoMapper.bbsInfoFileInsert(bbsInfoVo);
			}
			else{
				mBbsInfoMapper.bbsInfoFileUpdate(tempMap);
			}
		}
		
	}

	@Override
	public List bbsInfoFileList(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return mBbsInfoMapper.bbsInfoFileList(paramMap);
	}

	@Override
	public void bbsInfoFileUpdate(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		mBbsInfoMapper.bbsInfoFileDelete(paramMap);
	}

	@Override
	public M_BbsInfoVO bbsInfoView(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		return mBbsInfoMapper.bbsInfoView(paramMap);
	}

	@Override
	public void bbsInfoDelete(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		mBbsInfoMapper.bbsInfoDelete(paramMap);
	}

	@Override
	public void bbsInfoOpenYnUpdate(Map paramMap) throws Exception {
		// TODO Auto-generated method stub
		String bi_seq_n = paramMap.get("bi_seq_n") != null ? paramMap.get("bi_seq_n").toString() : "";
		String[] array_bi_seq_n = bi_seq_n.split(",");
		for(int i=0; i<array_bi_seq_n.length; i++) {
			paramMap.put("bi_seq_n", array_bi_seq_n[i]);
			mBbsInfoMapper.bbsInfoOpenYnUpdate(paramMap);
		}
		
	}
	
}