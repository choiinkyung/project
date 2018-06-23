package egovframework.MNG.AD.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.MNG.AD.service.AdministratorSvc;
import egovframework.MNG.util.RequestUtil;
import egovframework.MNG.util.paging.Page;

@Controller
@RequestMapping(value = "/mng/admin/")
public class AdministratorCtrl {

	
	@Resource(name = "adminSvc")
	private AdministratorSvc adminSvc;
	
	/**
	 * 샘플 목록조회
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/adminList.do")
	public String SAMPLE_R(ModelMap model, HttpServletRequest request) throws Exception {

		Map paramMap = RequestUtil.process(request);
		System.out.println(">>>>>>>>paramMap = " + paramMap);
		try {
			Map pageMap = Page.PageSet(paramMap, 
					adminSvc.SAMPLE_CNT_R(paramMap), 10, 10);
			
			List list = adminSvc.SAMPLE_R(pageMap);
			System.out.println(">>>>>>>>list = " + list);
			model.addAttribute("resultList", list);
			model.addAttribute("resultMap", pageMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "/MNG/AD/adminList";
	}
	
	/**
	 * 샘플 등록폼
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/adminForm.do")
	public String SAMPLE_DTL_R(ModelMap model, HttpServletRequest request) throws Exception {
		
		Map paramMap = RequestUtil.process(request);
		try {
			if ("U".equals(paramMap.get("dataStatus"))) {
				model.addAttribute("sample", adminSvc.SAMPLE_DTL_R(paramMap));
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
	public JSONObject SAMPLE_CU(ModelMap model, HttpServletRequest request) throws Exception {
		
		Map paramMap = RequestUtil.process(request);
		
		return adminSvc.SAMPLE_CUD(paramMap);
	}
	
	
	
	
	
}
