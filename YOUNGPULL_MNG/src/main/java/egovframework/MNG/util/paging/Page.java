package egovframework.MNG.util.paging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class Page {

	/**
	 * 
	 * @param params
	 * @param 게시물 전체 건수
	 * @param 한 페이지에 게시되는 게시물 건수
	 * @param 페이징 리스트 사이즈
	 * @return
	 */
	public static Map PageSet (Map params,int totalCount,int recordCountPerPage, int pageSize){
		
		Map<String,Object> map = (Map<String,Object>)params;
	    PaginationInfo paginationInfo = null;
	     
	    if(map.containsKey("pageNo") == false || StringUtils.isEmpty(map.get("pageNo")) == true)
	        map.put("pageNo","1");
	     
	    paginationInfo = new PaginationInfo();
	    paginationInfo.setCurrentPageNo(Integer.parseInt(map.get("pageNo").toString())); //현재 페이지 번호
	    
	    paginationInfo.setRecordCountPerPage(recordCountPerPage);		//한 페이지에 게시되는 게시물 건수
	    paginationInfo.setPageSize(pageSize);							//페이징 리스트의 사이즈
	    
	    
	    int start = paginationInfo.getFirstRecordIndex();
	    int end = paginationInfo.getRecordCountPerPage();
	    
	    map.put("STARTPAGE",start);
	    map.put("ENDPAGE",end);
	     
	    params = map;
	    
	    Map<String,Object> returnMap = new HashMap<String,Object>();
	    returnMap = params;
	    if(totalCount == 0){
	        map = new HashMap<String,Object>();
	        map.put("TOTAL_COUNT",0);  
	        if(paginationInfo != null){
	            paginationInfo.setTotalRecordCount(0);
	            returnMap.put("paginationInfo", paginationInfo); 
	        }
	    }
	    else{
	        if(paginationInfo != null){
	            paginationInfo.setTotalRecordCount(totalCount); //전체 게시물 건 수
	            returnMap.put("paginationInfo", paginationInfo);
	        }
	    }
	    return returnMap;
		
	}
	
}
