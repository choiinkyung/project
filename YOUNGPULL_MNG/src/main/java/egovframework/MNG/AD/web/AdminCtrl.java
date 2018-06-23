package egovframework.MNG.AD.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.MNG.AD.service.AdminSvc;
import egovframework.MNG.util.RequestUtil;
import egovframework.MNG.util.paging.Page;

@Controller
@RequestMapping(value = "/mng/admin/")
public class AdminCtrl {

	
	@Resource(name = "adminSvc")
	private AdminSvc AdminSvc;
	
	/**
	 * 샘플 목록조회
	 * @param model
	 * @param request
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/adminList.do")
	public String ADMIN_R(ModelMap model, HttpServletRequest request) throws Exception {

		Map paramMap = RequestUtil.process(request);
		try {
			Map pageMap = Page.PageSet(paramMap, 
					AdminSvc.ADMIN_CNT_R(paramMap), 10, 10);
			
			List list = AdminSvc.ADMIN_R(pageMap);
			model.addAttribute("resultList", list);
			model.addAttribute("resultMap", pageMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "/MNG/SAMPLE/sampleList";
	}
	
	/**
	 * 샘플 등록폼
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/adminForm.do")
	public String ADMIN_DTL_R(ModelMap model, HttpServletRequest request) throws Exception {
		
		Map paramMap = RequestUtil.process(request);
		try {
			if ("U".equals(paramMap.get("dataStatus"))) {
				model.addAttribute("sample", AdminSvc.ADMIN_DTL_R(paramMap));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/MNG/AD/adminForm";
	}
	
	/**
	 * 샘플 등록/수정/삭제 처리
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ADProc.do")
	public JSONObject ADMIN_CU(ModelMap model, HttpServletRequest request) throws Exception {
		
		Map paramMap = RequestUtil.process(request);
		
		return AdminSvc.ADMIN_CUD(paramMap);
	}
	
	
	
	
	
}
