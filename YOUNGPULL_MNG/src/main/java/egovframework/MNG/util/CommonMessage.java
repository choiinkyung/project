package egovframework.MNG.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import egovframework.MNG.COMM.service.CommonVo;
import net.sf.json.JSONArray;

@Component("messageUtil")
public class CommonMessage {

	
	/**
	 * 리턴될 상태코드
	 * @param code 0 : 정상처리, code 1 : 처리실패, code 99 : 처리값이 없을때
	 * @return String
	 */
	public static String setMsgCode(int code) {
		String str = "";
		if ( code == 0 ) {
			str = "SUCCESS";
		}else if (code == 1) {
			str = "FAIL";
		}else if (code == 99) {
			str = "NULL";
		}
		return str;
	}
	
	/**
	 * 처리결과 메시지
	 * @param crud : 데이터저장상태
	 * @param code : 데이터 처리상태
	 * @param msg : 리턴될메시지 없으면 "" 값으로
	 * @return
	 * @throws IOException
	 */
	public static JSONObject returnMassage(String crud, String code, String msg) throws IOException{
		
		JSONObject jsonObj = new JSONObject();
		
		String crudTit = "저장";
		
		if ("U".equals(crud)) {
			crudTit = "수정";
		}else if ("R".equals(crud)) {
			crudTit = "조회";
		}else if ("D".equals(crud)) {
			crudTit = "삭제";
		}
		
		if (!"FAIL".equals(code) && !"".equals(msg)) {
			jsonObj.put("returnMsg", msg);
		}else if ("FAIL".equals(code)) {
			jsonObj.put("returnMsg", msg);
		}else{
			jsonObj.put("returnMsg", crudTit + "되었습니다.");
		}
		
		jsonObj.put("result", code);
		
		return jsonObj;
	}
	
	/**
	 * json 형식의 List 조회
	 * @param code : 데이터 처리상태
	 * @param msg : 리턴될메시지 없으면 "" 값으로
	 * @param listSize : 리스트사이즈
	 * @param list : 조회할 리스트
	 * @return
	 * @throws IOException
	 */
	public static JSONObject returnMassage(String code, String msg, int listSize, List<?> list) throws IOException{
		
		JSONObject jsonObj = new JSONObject();
		String crudTit = "저장";
		//리스트를 json 형태로 변환
		JSONArray jsonArray = JSONArray.fromObject(list);
		
		if ("SUCCESS".equals(code)) {
			jsonObj.put("returnMsg", listSize + "건이 조회 되었습니다.");
			jsonObj.put("returnList", jsonArray);
		}else if ("FAIL".equals(code)) {
			jsonObj.put("returnMsg", msg);
		}
		jsonObj.put("result", code);
		
		return jsonObj;
	}
	
}
