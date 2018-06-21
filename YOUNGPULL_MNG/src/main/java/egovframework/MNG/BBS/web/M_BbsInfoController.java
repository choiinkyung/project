package egovframework.MNG.BBS.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.MNG.BBS.service.M_BbsInfoService;
import egovframework.MNG.BBS.service.M_BbsInfoVO;
import egovframework.MNG.util.CommUtil;
import egovframework.MNG.util.RequestUtil;
import egovframework.MNG.util.paging.Page;

@Controller
@RequestMapping(value = "/management/bbs")
public class M_BbsInfoController {

	@Resource(name = "mBbsInfoService")
	private M_BbsInfoService mBbsInfoService;
	
	/**
	 * 리스트
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bbsList.do")
	public String bbsList(ModelMap model, HttpServletRequest request) throws Exception {

		Map paramMap = RequestUtil.process(request);
		
		Map pageMap = Page.PageSet(paramMap, 
				mBbsInfoService.bbsInfoListCount(paramMap), 10, 10);
		
		List list = mBbsInfoService.bbsInfoList(pageMap);
		model.addAttribute("resultList", list);
		model.addAttribute("resultMap", pageMap);
		
		return boardFolder(paramMap.get("bs_seq_n").toString())+"/list";
	}
	
	/**
	 * 상세페이지
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bbsView.do")
	public String bbsView(ModelMap model, HttpServletRequest request) throws Exception {
		
		Map paramMap = RequestUtil.process(request);
		
		model.addAttribute("view", mBbsInfoService.bbsInfoView(paramMap));
		model.addAttribute("fileList", mBbsInfoService.bbsInfoFileList(paramMap));
		model.addAttribute("resultMap", paramMap);
		
		return boardFolder(paramMap.get("bs_seq_n").toString())+"/view";
	}
	
	/**
	 * 등록폼
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bbsInsertForm.do")
	public String bbsInsertForm(ModelMap model, HttpServletRequest request) throws Exception {
		
		Map paramMap = RequestUtil.process(request);
		model.addAttribute("resultMap", paramMap);
		
		return boardFolder(paramMap.get("bs_seq_n").toString())+"/insertForm";
	}
	
	/**
	 * 수정폼
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bbsUpdateForm.do")
	public String bbsUpdateForm(ModelMap model, HttpServletRequest request) throws Exception {
		
		Map paramMap = RequestUtil.process(request);
		
		model.addAttribute("view", mBbsInfoService.bbsInfoView(paramMap));
		model.addAttribute("fileList", mBbsInfoService.bbsInfoFileList(paramMap));
		model.addAttribute("resultMap", paramMap);
		
		return boardFolder(paramMap.get("bs_seq_n").toString())+"/updateForm";
	}
	
	
	/**
	 * 썸네일 이미지 등록
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/thumbnailUpdate.do", produces = "application/json; charset=utf8")
	@ResponseBody
	public String thumbnailUpdate(ModelMap model, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map paramMap = RequestUtil.process(request);
		String thumbnailFile = "";
		try{
			thumbnailFile = CommUtil.thumbnail(paramMap, request, response);
		}catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		return thumbnailFile;
	}
	
	/**
	 * 등록/수정/삭제처리
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bbsRegist.do")
	public String bbsRegist(ModelMap model,M_BbsInfoVO bbsInfoVo, HttpServletRequest request) throws Exception {
		
		Map paramMap = RequestUtil.process(request, bbsInfoVo);
		
		String mode = paramMap.get("mode") != null ? paramMap.get("mode").toString() : "";
		
		paramMap.put("folderName", "bbsFile");
		
		try {
			if ("update".equals(mode)) {
				mBbsInfoService.bbsInfoUpdate(paramMap, bbsInfoVo, request);
				paramMap.put("returnUrl", "/management/bbs/bbsList.do");
			}else if("fileDelete".equals(mode)) {
				mBbsInfoService.bbsInfoFileUpdate(paramMap);
				paramMap.put("returnUrl", "/management/bbs/bbsUpdateForm.do");
			}else if("delete".equals(mode)) {
				mBbsInfoService.bbsInfoDelete(paramMap);
				paramMap.put("returnUrl", "/management/bbs/bbsList.do");
			}else if("bbsDel".equals(mode)) {
				String[] bbs_del = paramMap.get("bbsDel").toString().split(",");
				for (int i = 0; i < bbs_del.length; i++) {
					paramMap.put("bi_seq_n", bbs_del[i]);
					mBbsInfoService.bbsInfoDelete(paramMap);
				}
				paramMap.put("returnUrl", "/management/bbs/bbsList.do");
			}else if("openYnUpdate".equals(mode)) {
				mBbsInfoService.bbsInfoOpenYnUpdate(paramMap);
				paramMap.put("returnUrl", "/management/bbs/bbsList.do");
			}else{
				mBbsInfoService.bbsInfoInsert(paramMap, bbsInfoVo, request);
				paramMap.put("returnUrl", "/management/bbs/bbsList.do");
			}
			
		}catch (Exception e) {
			paramMap.put("registError", "오류가 발생하였습니다.");
			paramMap.put("errorMsg", e.getMessage());
		}
		 
		model.addAttribute("resultMap", paramMap);
		return boardFolder(paramMap.get("bs_seq_n").toString())+"/success";
		
	}
	
	/**
	 * 처리결과 페이지
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bbsSendEmail.do")
	public String bbsSendEmail(ModelMap model, HttpServletRequest request) throws Exception {
		
		Map paramMap = RequestUtil.process(request);
		model.addAttribute("resultMap", paramMap);
		return boardFolder(paramMap.get("bs_seq_n").toString())+"/success";
	}
	
	/**
	 * 처리결과 페이지
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bbsSuccess.do")
	public String bbsSuccess(ModelMap model, HttpServletRequest request) throws Exception {
		
		Map paramMap = RequestUtil.process(request);
		model.addAttribute("resultMap", paramMap);
		
		return boardFolder(paramMap.get("bs_seq_n").toString())+"/success";
	}
	
	
	/**
	 * 게시판 폴더
	 * @param bs_seq_n
	 * @return
	 */
	private String boardFolder(String bs_seq_n) {
		String boardFolder = "";
		//공지사항
		if ("1".equals(bs_seq_n)) {
			boardFolder = "/management/bbs/notice";
		//qna
		}else if("2".equals(bs_seq_n)){
			boardFolder = "/management/bbs/qna";
		//E.fit
		}else if("3".equals(bs_seq_n)){
			boardFolder = "/management/bbs/efit";
		//event
		}else if("4".equals(bs_seq_n)){
			boardFolder = "/management/bbs/event";
		//입시정보
		}else if("5".equals(bs_seq_n)){
			boardFolder = "/management/bbs/info";
		}
		
		return boardFolder;
	}
	
}
