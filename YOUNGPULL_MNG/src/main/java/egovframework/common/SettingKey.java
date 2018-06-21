package egovframework.common;
//sadsa

public class SettingKey {
	/** 기본 경로 */
	//개발서버
//	public static final String ROOT_PATH = "/usr/local/tomcat/webapps/entostudy";
	//노트북작업
	public static final String ROOT_PATH = "D:/IKSOFT/workspace/entoStudy/src/main/webapp";
	//로컬경로
//	public static final String ROOT_PATH = "C:/java_project/workspace/entoStudy/src/main/webapp";

	//public static final String ROOT_PATH = "/tomcat/webapps/ROOT";
	/** 첨부파일 경로 */
	public static final String FILE_PATH = ROOT_PATH + "/upload";
	
	public static final String EDITOR_FILE_PATH = ROOT_PATH + "/upload/editor";
	/** 썸네일 파일 경로 */
	public static final String THUMBNAIL_FILE_PATH = ROOT_PATH + "/upload/thumbnail";
	/** 썸네일 파일 기본 경로 */
	public static final String THUMBNAIL_FILE_SIMPLE_PATH = "/upload/thumbnail";
	/** 관리자 세션 명*/
	public static final String MANAGER_SESSION = "MNG_SESSION"; 
	/** 엑셀 다운로드 템플릿 경로 */
	public static final String EXCEL_TEMPLATE_PATH = "WEB-INF/template/xls/";
	/** 공통 처리 결과 페이지 */
	public static final String RESULT_PAGE = "/common/commonSuccess";

	public static final String PAGE_URL = "http://www.youngpullgong.com";
	
}
