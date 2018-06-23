package egovframework.MNG.SI.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.MNG.SI.service.SiteSvc;
import egovframework.MNG.util.RequestUtil;
import egovframework.MNG.util.paging.Page;

@Controller
@RequestMapping(value="/mng/site/")
public class SiteCtrl {
	
	@Resource(name="siteSvc")
	private SiteSvc siteSvc;
	
	/**
	 * 사이트 관리 리스트
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/siteList.do")
	public String SITE_R(ModelMap model, HttpServletRequest request)throws Exception{
		Map paramMap = RequestUtil.process(request);
		try {
			Map pageMap = Page.PageSet(paramMap, 
			siteSvc.SITE_CNT_R(paramMap), 10, 10);
			
			List list = siteSvc.SITE_R(pageMap);
			System.out.println(">>>>>>>>list = " + list);
			model.addAttribute("resultList", list);
			model.addAttribute("resultMap", pageMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/MNG/SI/siteList";
		
	}
	
	/**
	 * 사이트 등록 폼
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/siteForm.do")
	public String SITE_C(ModelMap model, HttpServletRequest request)throws Exception{
		return "/MNG/SI/siteForm";
	}
	
	

}
