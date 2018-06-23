package egovframework.MNG.COMM.service;

import com.sun.xml.internal.ws.developer.Serialization;

/**
 * 공통VO
 * 공통으로 사용되는 것만 입력할 것.
 * @author study
 * 
 */
/**
 * @author admin
 *
 */
@Serialization
public class CommonVO {

	/** 등록일 */
	private String reg_dt_d;
	/** 등록아이디 */
	private String reg_id_v;
	/** 수정일 */
	private String up_dt_d;
	/** 수정아이디 */
	private String up_id_v;
	/** 삭제여부 */
	private String del_yn_c;
	/** 검색 조건 */
	private String search_key;
	/** 검색 값 */
	private String search_val;
	/** 코드명 */
	private String code_name;
	/** 코드번호 */
	private String code_no;
	/** 코드명 */
	private String code_name_v;
	/** 코드번호 */
	private String code_mcode_v;
	/** 이전글 번호 */
	private String prev_seq_n;
	/** 다음글 번호 */
	private String next_seq_n;
	/** 신규게시물여부 */
	private String new_data;
	/** 페이지번호 */
	private String page_number;
	/** sms수신여부 */
	private String mem_getNews_c;
	
	
	
	/**
	 * @return the mem_getnews_c
	 */
	public String getMem_getNews_c() {
		return mem_getNews_c;
	}
	/**
	 * @param mem_getnews_c the mem_getnews_c to set
	 */
	public void setMem_getNews_c(String mem_getNews_c) {
		this.mem_getNews_c = mem_getNews_c;
	}
	/**
	 * @return the page_number
	 */
	public String getPage_number() {
		return page_number;
	}
	/**
	 * @param page_number the page_number to set
	 */
	public void setPage_number(String page_number) {
		this.page_number = page_number;
	}
	/**
	 * @return the new_data
	 */
	public String getNew_data() {
		return new_data;
	}
	/**
	 * @param new_data the new_data to set
	 */
	public void setNew_data(String new_data) {
		this.new_data = new_data;
	}
	/**
	 * @return the man_id_v
	 */
	/**
	 * @return the code_mcode_v
	 */
	public String getCode_mcode_v() {
		return code_mcode_v;
	}
	/**
	 * @param code_mcode_v the code_mcode_v to set
	 */
	public void setCode_mcode_v(String code_mcode_v) {
		this.code_mcode_v = code_mcode_v;
	}
	/**
	 * @return the code_name_v
	 */
	public String getCode_name_v() {
		return code_name_v;
	}
	/**
	 * @param code_name_v the code_name_v to set
	 */
	public void setCode_name_v(String code_name_v) {
		this.code_name_v = code_name_v;
	}
	/**
	 * @return the search_key
	 */
	public String getSearch_key() {
		return search_key;
	}
	/**
	 * @param search_key the search_key to set
	 */
	public void setSearch_key(String search_key) {
		this.search_key = search_key;
	}
	/**
	 * @return the search_val
	 */
	public String getSearch_val() {
		return search_val;
	}
	/**
	 * @param search_val the search_val to set
	 */
	public void setSearch_val(String search_val) {
		this.search_val = search_val;
	}

	
	/**
	 * @return the reg_id_v
	 */
	public String getReg_id_v() {
		return reg_id_v;
	}
	/**
	 * @param reg_id_v the reg_id_v to set
	 */
	public void setReg_id_v(String reg_id_v) {
		this.reg_id_v = reg_id_v;
	}


	/**
	 * @return the up_id_v
	 */
	public String getUp_id_v() {
		return up_id_v;
	}
	/**
	 * @param up_id_v the up_id_v to set
	 */
	public void setUp_id_v(String up_id_v) {
		this.up_id_v = up_id_v;
	}
	/**
	 * @return the del_yn_c
	 */
	public String getDel_yn_c() {
		return del_yn_c;
	}
	/**
	 * @param del_yn_c the del_yn_c to set
	 */
	public void setDel_yn_c(String del_yn_c) {
		this.del_yn_c = del_yn_c;
	}
	/**
	 * @return the code_name
	 */
	public String getCode_name() {
		return code_name;
	}
	/**
	 * @param code_name the code_name to set
	 */
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	/**
	 * @return the code_no
	 */
	public String getCode_no() {
		return code_no;
	}
	/**
	 * @param code_no the code_no to set
	 */
	public void setCode_no(String code_no) {
		this.code_no = code_no;
	}
	/**
	 * @return the prev_seq_n
	 */
	public String getPrev_seq_n() {
		return prev_seq_n;
	}
	/**
	 * @param prev_seq_n the prev_seq_n to set
	 */
	public void setPrev_seq_n(String prev_seq_n) {
		this.prev_seq_n = prev_seq_n;
	}
	/**
	 * @return the next_seq_n
	 */
	public String getNext_seq_n() {
		return next_seq_n;
	}
	/**
	 * @param next_seq_n the next_seq_n to set
	 */
	public void setNext_seq_n(String next_seq_n) {
		this.next_seq_n = next_seq_n;
	}
	public String getReg_dt_d() {
		return reg_dt_d;
	}
	public void setReg_dt_d(String reg_dt_d) {
		this.reg_dt_d = reg_dt_d;
	}
	public String getUp_dt_d() {
		return up_dt_d;
	}
	public void setUp_dt_d(String up_dt_d) {
		this.up_dt_d = up_dt_d;
	}
	
	
}
