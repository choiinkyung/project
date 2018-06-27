package egovframework.MNG.SAMPLE.web;

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

import egovframework.MNG.SAMPLE.service.SampleSvc;
import egovframework.MNG.util.RequestUtil;
import egovframework.MNG.util.paging.Page;
import egovframework.common.SettingKey;

@Controller
@RequestMapping(value = "/mng/sample/")
public class SampleCtrl {

	
	@Resource(name = "sampleSvc")
	private SampleSvc sampleSvc;
	
	final static Logger logger = LoggerFactory.getLogger(SampleCtrl.class);

	
	/**
	 * 샘플 목록조회
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "sampleList.do")
	public String SAMPLE_R(ModelMap model, HttpServletRequest request) throws Exception {

		Map paramMap = RequestUtil.process(request);
		
		try {
			Map pageMap = Page.PageSet(paramMap, 
					sampleSvc.SAMPLE_CNT_R(paramMap), 10, 10);
			
			List list = sampleSvc.SAMPLE_R(pageMap);
			System.out.println(">>>>>>>>list = " + list);
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
	@RequestMapping(value = "sampleForm.do")
	public String SAMPLE_DTL_R(ModelMap model, HttpServletRequest request) throws Exception {
		
		Map paramMap = RequestUtil.process(request);
		try {
			if ("U".equals(paramMap.get("dataStatus"))) {
				model.addAttribute("sample", sampleSvc.SAMPLE_DTL_R(paramMap));
			}
			model.addAttribute("resultMap", paramMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/MNG/SAMPLE/sampleForm";
	}
	
	/**
	 * 샘플 등록/수정/삭제 처리
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "sampleProc.json")
	public String SAMPLE_CU(ModelMap model, HttpServletRequest request) throws Exception {
		
		Map paramMap = RequestUtil.process(request);
		JSONObject json = new JSONObject();
		try {
			json = sampleSvc.SAMPLE_CUD(paramMap);
			model.addAttribute("returnData", json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return SettingKey.JSON_VIEW;
	}
	
	
	
	
	
}
