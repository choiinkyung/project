package egovframework.MNG.COMM.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.MNG.COMM.service.CommonSvc;
import egovframework.MNG.util.CommonMessage;
import egovframework.common.SettingKey;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("commonSvc")
public class CommonSvcImpl extends EgovAbstractServiceImpl implements CommonSvc{
	
	@Resource(name="commonMapper")
	private CommonMapper commonMapper;
	
	@Resource(name="messageUtil")
	private CommonMessage messageUtil;

	@Resource(name = "codeIndexService")
	private EgovIdGnrService codeIndexService;
	
	
	@Override
	public Map<String,Object> selectFileView(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return commonMapper.selectFileView(paramMap);
	}
	
	@Override
	public JSONObject COMMON_SITECODE_R(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		JSONObject returnJson = new JSONObject();
		String dataStatus = "R";
		try {
			List<?> list = commonMapper.COMMON_SITECODE_R(paramMap);
			returnJson = messageUtil.returnMassage(messageUtil.setMsgCode(0), "", list.size(), list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return messageUtil.returnMassage(dataStatus, messageUtil.setMsgCode(1), e.getMessage());
		}
		return returnJson;
	}
	
	
	@Override
	public void ckeditorImageUpload(HttpServletRequest request,
			HttpServletResponse response, MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		OutputStream out = null;
		try{
			PrintWriter printWriter = null;	
			String fileName = file.getOriginalFilename();
	
			byte[] bytes = file.getBytes();
//			String uploadPath = SettingKey.EDITOR_FILE_PATH + "\\" + fileName;
			String uploadPath = SettingKey.EDITOR_FILE_PATH + "/" + new String(fileName.getBytes("UTF-8"));
			
			Runtime.getRuntime().exec("chmod 777 " + uploadPath);

			System.out.println(uploadPath);
			
			out = new FileOutputStream(new File(uploadPath));
			out.write(bytes);
			String callback = request.getParameter("CKEditorFuncNum");
			printWriter = response.getWriter();
			String fileUrl = "http://www.entostudy.com/upload/editor/" + new String(fileName.getBytes("UTF-8")); //url 경로
			printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
		               + callback
		               + ",'"
		               + fileUrl
		               + "','이미지를 업로드 하였습니다.'"
		               + ")</script>");
		       printWriter.flush();
		}catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}


	@Override
	public JSONObject COMMON_CODE_R(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject returnJson = new JSONObject();
		String dataStatus = "R";
		try {
			
			List<?> list = commonMapper.COMMON_CODE_R(paramMap);
			returnJson = messageUtil.returnMassage(messageUtil.setMsgCode(0), "", list.size(), list);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return messageUtil.returnMassage(dataStatus, messageUtil.setMsgCode(1), e.getMessage());
		}
		return returnJson;
	}


	@Override
	public JSONObject COMMON_CODE_CUD(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		String dataCode = "";
		String dataStatus = "I";
		try {
			//데이터 상태값
			//코드아이디
			List<Map<String,Object>> status =  (List<Map<String, Object>>) paramMap.get("dataStatus");
			List<Map<String,Object>> codeId =  (List<Map<String, Object>>) paramMap.get("code_id_v");
			List<Map<String,Object>> code_name_v =  (List<Map<String, Object>>) paramMap.get("code_name_v");
			List<Map<String,Object>> code_order_n =  (List<Map<String, Object>>) paramMap.get("code_order_n");
			List<Map<String,Object>> code_ref1_v =  (List<Map<String, Object>>) paramMap.get("code_ref1_v");
			List<Map<String,Object>> code_ref2_v =  (List<Map<String, Object>>) paramMap.get("code_ref2_v");
			List<Map<String,Object>> code_ref3_v =  (List<Map<String, Object>>) paramMap.get("code_ref3_v");
			List<Map<String,Object>> code_ref4_v =  (List<Map<String, Object>>) paramMap.get("code_ref4_v");
			List<Map<String,Object>> code_ref5_v =  (List<Map<String, Object>>) paramMap.get("code_ref5_v");
			
			String codeSeq = codeIndexService.getNextStringId();
			for (int i=0; i<codeId.size(); i++) {
				Map<String,Object> dataMap = new HashMap();
				
				if ("".equals(codeId.get(i))) {
					continue;
				}
				
				dataMap.put("code_upName_v", paramMap.get("code_upName_v"));
				
				dataMap.put("code_id_v", codeId.get(i));
				dataMap.put("code_name_v", code_name_v.get(i));
				dataMap.put("code_order_n", code_order_n.get(i));
				dataMap.put("code_ref1_v", code_ref1_v.get(i));
				dataMap.put("code_ref2_v", code_ref2_v.get(i));
				dataMap.put("code_ref3_v", code_ref3_v.get(i));
				dataMap.put("code_ref4_v", code_ref4_v.get(i));
				dataMap.put("code_ref5_v", code_ref5_v.get(i));
				
				System.out.println(">>>>>>>>> U PID = " + paramMap.get("code_upId_v"));
				if ("I".equals(status.get(i)) && (paramMap.get("code_upId_v") == null || "".equals(paramMap.get("code_upId_v")))) {
					dataMap.put("code_upId_v", codeSeq);
					commonMapper.COMMON_CODE_C(dataMap);
				}else if ("I".equals(status.get(i)) && (paramMap.get("code_upId_v") != null || !"".equals(paramMap.get("code_upId_v")))) {
					dataMap.put("code_upId_v", paramMap.get("code_upId_v"));
					commonMapper.COMMON_CODE_C(dataMap);
				}else if("U".equals(status.get(i))) {
					dataMap.put("code_upId_v", paramMap.get("code_upId_v"));
					commonMapper.COMMON_CODE_U(dataMap);
				}
				
			}
			
			 
			/*if ("I".equals(dataStatus) || "U".equals(dataStatus)) {
				commonMapper.COMMON_CODE_CU(paramMap);
			}else if ("D".equals(paramMap.get("dataStatus"))) {
				commonMapper.COMMON_CODE_D(paramMap);
			}else{
				//DB저장 상태값이 없을때 무조건 리턴시킴
				dataCode = "99";
				return messageUtil.returnMassage(dataStatus, messageUtil.setMsgCode(99), "데이터없음");
			}*/
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return messageUtil.returnMassage(dataStatus, messageUtil.setMsgCode(1), e.getMessage());
		}
		return messageUtil.returnMassage(dataStatus, messageUtil.setMsgCode(0), "");
	}

}
