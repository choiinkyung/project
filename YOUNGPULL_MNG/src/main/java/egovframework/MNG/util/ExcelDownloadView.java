package egovframework.MNG.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import net.sf.jxls.transformer.XLSTransformer;


/**
 * <p>
 * 각 Controller에서 excel파일 다운로드시 활용되는 view.
 * </p>
 *
 * <pre>
 *  <h3>-Example</h3>
 *  public ModelAndView test(HttpServletRequest request, HttpServletResponse response) throws Exception {
 *      ModelAndView mav = new ModelAndView(EXCEL_DOWN);
 *      //다운로드에 사용되어질 엑셀파일 템플릿
 *      mav.addObject(DaonExcelDownloadView.DOWN_EXCEL_TEMPLATE, getExcelTemplateName(request, this.getClass().getName()));
 *      //다운로드시 표시될 파일명 (확장자는 자동으로 xls로 지정된다.)
 *      mav.addObject(DaonExcelDownloadView.DOWN_FILE_NM, "파일명"); //확장자는 제외한다.
 *
 *      return mav;
 *  }
 * </pre>
 *
 * @author
 * @date 2009. 10. 7. $Id: $
 */

public class ExcelDownloadView extends AbstractView{

	
	public static final String EXCEL_DOWN = "excelDown";
	
	 private static final String FILE_CONTENT_TYPE = "application/vnd.ms-excel";
	 
	 	
	    public static final String DOWN_EXCEL_TEMPLATE = "downExcelTemplate";
	    public static final String DOWN_EXCEL_DATA = "downExcelData";
	    public static final String DOWN_FILE_NM = "downloadFileName";
	    public static final String DOWN_FILE_NM_ENC = "downloadFileNameEncoding";
	    private static final String EXCEL_EXTENSION = ".xls";
//	    private static final String COMMON_DATA_KEY = "data";
	    private static final String APPLICATION_PATH = "";
//	    private static final String APPLICATION_PATH = SettingKey.ROOT_PATH;

	    private static final String EXCEL_TEMPLATE_PATH = "";
//	    private static final String EXCEL_TEMPLATE_PATH = SettingKey.EXCEL_TEMPLATE_PATH;

	    
	    public ExcelDownloadView() {
	        setContentType(FILE_CONTENT_TYPE);
	    } 
	    
	    /*
	     * (non-Javadoc)
	     *
	     * @see
	     * org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel
	     * (java.util.Map, javax.servlet.http.HttpServletRequest,
	     * javax.servlet.http.HttpServletResponse)
	     */
	    protected void renderMergedOutputModel(Map model, HttpServletRequest request,
	                    HttpServletResponse response) throws Exception {
	    	System.out.println("!!!!!!!!!!!!!!!엑셀다운로드 시작!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	    	System.out.println("!!!!!!!!!!!!!!!엑셀다운로드 시작!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	    	System.out.println("!!!!!!!!!!!!!!!엑셀다운로드 시작!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	    	System.out.println("!!!!!!!!!!!!!!!엑셀다운로드 시작!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	    	System.out.println("!!!!!!!!!!!!!!!엑셀다운로드 시작!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	        //다운로드시 표시될 파일명
	        String downFileNm = model.get(DOWN_FILE_NM) == null ? "" : ((String) model.get(DOWN_FILE_NM)) + EXCEL_EXTENSION;
	        downFileNm = URLDecoder.decode(downFileNm, "UTF-8");
	        //다운로드 대상 엑셀 템플릿 파일
	        String appRoot = getServletContext().getRealPath("/");
	        String templateFile = model.get(DOWN_EXCEL_TEMPLATE) == null ? "" : (String) model.get(DOWN_EXCEL_TEMPLATE);
	        templateFile = appRoot + EXCEL_TEMPLATE_PATH + templateFile + EXCEL_EXTENSION;
	        templateFile = templateFile.replaceAll("/", "\\\\");
	        
	        //다운로드 대상 임시 엑셀파일
	        //String downloadTempFile = System.getProperty("java.io.tmpdir") + getRandomFileNm() + EXCEL_EXTENSION;
	        String downloadTempFile =  APPLICATION_PATH + "/" + EXCEL_TEMPLATE_PATH + getRandomFileNm() + EXCEL_EXTENSION;
	        downloadTempFile = downloadTempFile.replaceAll("/", "\\\\");
	        
	    	
	        XLSTransformer transformer = new XLSTransformer();
	        transformer.transformXLS(templateFile, model, downloadTempFile);
	        
	        File downFile = new File(downloadTempFile);
	        response.setContentLength((int) downFile.length());
	    
	        String strClient = request.getHeader("User-Agent");
	    	if ( strClient.indexOf("MSIE") != -1 ) {
	    		response.setHeader("Content-Disposition", "attachment; filename=\"" +  URLEncoder.encode(downFileNm, "UTF-8") + "\";");
	    	} else {
	    		response.setHeader("Content-Disposition", "attachment; filename=\"" +  URLEncoder.encode(downFileNm, "UTF-8") + "\";");
	    	}
	    	
	        response.setHeader("Content-Transfer-Encoding", "binary");
	        response.setContentType(getContentType());
	        OutputStream out = response.getOutputStream();
	        FileInputStream fis = null;

	        try {
	            fis = new FileInputStream(downFile);
	            FileCopyUtils.copy(fis, out);
	        }
	        finally {
	            if (fis != null) {
	                try {
	                    fis.close();
	                }
	                catch (IOException ex) {
	                }
	            }
	            out.flush();
	            //임시로 생성된 파일 삭제.
	            downFile.deleteOnExit();
	        }
	    }

	    /**
	     * 다운로드 대상 파일을 만들기 위한 임시파일명 생성.
	     *
	     * @return String
	     * @throws Exception
	     */
	    private synchronized String getRandomFileNm() throws Exception {
	        Calendar cal = Calendar.getInstance();
	        return String.valueOf(cal.getTimeInMillis());
	    } 
	    
	
}
