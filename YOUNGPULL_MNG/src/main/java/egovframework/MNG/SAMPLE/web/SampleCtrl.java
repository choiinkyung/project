package egovframework.MNG.SAMPLE.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.MNG.SAMPLE.service.SampleSvc;
import egovframework.MNG.util.RequestUtil;
import egovframework.MNG.util.paging.Page;

@Controller
@RequestMapping(value = "/sample/")
public class SampleCtrl {

	
	@Resource(name = "SampleService")
	private SampleSvc sampleSvc;
	
	/**
	 * 샘플 목록조회
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sampleList.do")
	public String SAMPLE_R(ModelMap model, HttpServletRequest request) throws Exception {

		Map paramMap = RequestUtil.process(request);
		
		Map pageMap = Page.PageSet(paramMap, 
				sampleSvc.SAMPLE_CNT_R(paramMap), 10, 10);
		
		List list = sampleSvc.SAMPLE_R(pageMap);
		model.addAttribute("resultList", list);
		model.addAttribute("resultMap", pageMap);
		
		return "/sample/sampleList";
	}
	
	/**
	 * 샘플 등록폼
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sampleForm.do")
	public String SAMPLE_DTL_R(ModelMap model, HttpServletRequest request) throws Exception {
		
		Map paramMap = RequestUtil.process(request);
		if ("U".equals(paramMap.get("dataStatus"))) {
			model.addAttribute("sample", sampleSvc.SAMPLE_DTL_R(paramMap));
		}
		return "/sample/sampleForm";
	}
	
	/**
	 * 샘플 등록/수정/삭제 처리
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sampleProc.do")
	public JSONObject SAMPLE_CU(ModelMap model, HttpServletRequest request) throws Exception {
		
		Map paramMap = RequestUtil.process(request);
		
		return sampleSvc.SAMPLE_CUD(paramMap);
	}
	
	
	
	
	
}
