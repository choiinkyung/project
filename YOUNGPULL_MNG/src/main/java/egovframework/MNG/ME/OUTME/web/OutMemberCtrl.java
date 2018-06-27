package egovframework.MNG.ME.OUTME.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.MNG.ME.OUTME.service.OutMemberSvc;
import egovframework.MNG.util.RequestUtil;
import egovframework.MNG.util.paging.Page;
import egovframework.common.SettingKey;

@Controller
@RequestMapping(value = "/mng/me/outMember/")
public class OutMemberCtrl {

	
	@Resource(name = "outMemberSvc")
	private OutMemberSvc outMemberSvc;
	
	final static Logger logger = LoggerFactory.getLogger(OutMemberCtrl.class);

	
	/**
	 * 탈퇴회원 목록조회
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "outMemberList.do")
	public String OUTMEMBER_R(ModelMap model, HttpServletRequest request) throws Exception {

		Map paramMap = RequestUtil.process(request);
		
		try {
			Map pageMap = Page.PageSet(paramMap, 
					outMemberSvc.OUTMEMBER_CNT_R(paramMap), 10, 10);
			
			model.addAttribute("resultList", outMemberSvc.OUTMEMBER_R(pageMap));
			model.addAttribute("resultMap", pageMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "/MNG/ME/OUTME/outMemberList";
	}
	
	/**
	 * 탈퇴회원 등록폼
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "outMemberForm.do")
	public String OUTMEMBER_DTL_R(ModelMap model, HttpServletRequest request) throws Exception {
		
		Map paramMap = RequestUtil.process(request);
		try {
			if ("U".equals(paramMap.get("dataStatus"))) {
				model.addAttribute("outMember", outMemberSvc.OUTMEMBER_DTL_R(paramMap));
			}
			model.addAttribute("resultMap", paramMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/MNG/ME/OUTME/outMemberForm";
	}
	
	/**
	 * 탈퇴회원 등록/수정/삭제 처리
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "outMemberProc.json")
	public String OUTMEMBER_CU(ModelMap model, HttpServletRequest request) throws Exception {
		
		Map paramMap = RequestUtil.process(request);
		JSONObject json = new JSONObject();
		try {
			json = outMemberSvc.OUTMEMBER_CUD(paramMap);
			model.addAttribute("returnData", json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return SettingKey.JSON_VIEW;
	}
	
	
	
	
	
}
