package egovframework.MNG.ME.INME.web;

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
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.MNG.ME.INME.service.MemberSvc;
import egovframework.MNG.util.RequestUtil;
import egovframework.MNG.util.paging.Page;
import egovframework.common.SettingKey;

@Controller
@RequestMapping(value = "/mng/me/member/")
public class MemberCtrl {

	
	@Resource(name = "memberSvc")
	private MemberSvc memberSvc;
	
	final static Logger logger = LoggerFactory.getLogger(MemberCtrl.class);

	
	/**
	 * 회원 목록조회
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "memberList.do")
	public String MEMBER_R(ModelMap model, HttpServletRequest request, @RequestParam Map<String,Object> paramMap) throws Exception {
		System.out.println(">>>>>>>>>> = " + paramMap);
		try {
			Map pageMap = Page.PageSet(paramMap, 
					memberSvc.MEMBER_CNT_R(paramMap), 10, 10);
			
			model.addAttribute("resultList", memberSvc.MEMBER_R(pageMap));
			model.addAttribute("resultMap", pageMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "/MNG/ME/INME/memberList";
	}
	
	/**
	 * 회원 등록폼
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "memberForm.do")
	public String MEMBER_DTL_R(ModelMap model, HttpServletRequest request, @RequestParam Map<String,Object> paramMap) throws Exception {
		
		try {
			if ("U".equals(paramMap.get("dataStatus"))) {
				model.addAttribute("view", memberSvc.MEMBER_DTL_R(paramMap));
			}
			model.addAttribute("resultMap", paramMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/MNG/ME/INME/memberForm";
	}
	
	/**
	 * 회원 등록/수정/삭제 처리
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "memberProc.json")
	public String MEMBER_CU(ModelMap model, HttpServletRequest request, @RequestParam Map<String,Object> paramMap) throws Exception {
		JSONObject json = new JSONObject();
		try {
			json = memberSvc.MEMBER_CUD(paramMap);
			model.addAttribute("returnData", json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return SettingKey.JSON_VIEW;
	}
	
	
	
	
	
}
