package egovframework.MNG.BBS.service;

import egovframework.MNG.COMM.service.CommonVo;

public class M_BbsInfoVO extends CommonVo{

	/** 게시글번호 */
	private String bi_seq_n;
	/** 게시판코드 */
	private String bs_seq_n;
	/** 제목 */
	private String bi_title_v;
	/** 내용 */
	private String bi_content_t;
	/** 작성자 */
	private String bi_name_v;
	/** 작성자아이디 */
	private String bi_id_v;
	/** 핸드폰번호 */
	private String bi_phone_v;
	/** 첨부파일번호 */
	private String bi_fileSeq_n;
	/** 사용여부 */
	private String bi_useYn_c;
	/** 링크주소 */
	private String bi_linkUrl_v;
	/** 구분 */
	private String bi_gubun_v;
	/** 기타1 */
	private String bi_etc1_v;
	/** 기타2 */
	private String bi_etc2_v;
	/** 기타3 */
	private String bi_etc3_v;
	/** 기타4 */
	private String bi_etc4_v;
	/** 기타5 */
	private String bi_etc5_v;
	/** 조회수 */
	private String bi_hitCount_n;
	/** 공지글여부 */
	private String bi_noticeYn_c;
	/** 공개여부 */
	private String bi_openYn_c;
	/** 신규게시물 */
	private String new_data;
	/** 썸네일 */
	private String bi_thumbNail_v;
	/** 목록카운트 */
	private String bi_cnt_n;
	/** 시작일 */
	private String bi_startDtm_d;
	/** 종료일 */
	private String bi_endDtm_d;
	/** 파일일련번호 */
	private String bf_seq_n;
	/** 원본파일명 */
	private String bf_orgFileName_v;
	/** 저장파일명 */
	private String bf_storFileName_v;
	/** 파일경로 */
	private String bf_filePath_v;
	/** 파일사이즈 */
	private String bf_fileSize_n;
	/** 이벤트종료구분 */
	private String bi_end_event;
	
	
	/**
	 * @return the bi_end_event
	 */
	public String getBi_end_event() {
		return bi_end_event;
	}
	/**
	 * @param bi_end_event the bi_end_event to set
	 */
	public void setBi_end_event(String bi_end_event) {
		this.bi_end_event = bi_end_event;
	}
	/**
	 * @return the bi_endDtm_d
	 */
	public String getBi_endDtm_d() {
		return bi_endDtm_d;
	}
	/**
	 * @param bi_endDtm_d the bi_endDtm_d to set
	 */
	public void setBi_endDtm_d(String bi_endDtm_d) {
		this.bi_endDtm_d = bi_endDtm_d;
	}
	/**
	 * @return the bi_startDtm_d
	 */
	public String getBi_startDtm_d() {
		return bi_startDtm_d;
	}
	/**
	 * @param bi_startDtm_d the bi_startDtm_d to set
	 */
	public void setBi_startDtm_d(String bi_startDtm_d) {
		this.bi_startDtm_d = bi_startDtm_d;
	}
	/**
	 * @return the bi_cnt_n
	 */
	public String getBi_cnt_n() {
		return bi_cnt_n;
	}
	/**
	 * @param bi_cnt_n the bi_cnt_n to set
	 */
	public void setBi_cnt_n(String bi_cnt_n) {
		this.bi_cnt_n = bi_cnt_n;
	}
	/**
	 * @return the bi_thumbNail_v
	 */
	public String getBi_thumbNail_v() {
		return bi_thumbNail_v;
	}
	/**
	 * @param bi_thumbNail_v the bi_thumbNail_v to set
	 */
	public void setBi_thumbNail_v(String bi_thumbNail_v) {
		this.bi_thumbNail_v = bi_thumbNail_v;
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
	 * @return the bi_openYn_c
	 */
	public String getBi_openYn_c() {
		return bi_openYn_c;
	}
	/**
	 * @param bi_openYn_c the bi_openYn_c to set
	 */
	public void setBi_openYn_c(String bi_openYn_c) {
		this.bi_openYn_c = bi_openYn_c;
	}
	/**
	 * @return the bi_noticeYn_c
	 */
	public String getBi_noticeYn_c() {
		return bi_noticeYn_c;
	}
	/**
	 * @param bi_noticeYn_c the bi_noticeYn_c to set
	 */
	public void setBi_noticeYn_c(String bi_noticeYn_c) {
		this.bi_noticeYn_c = bi_noticeYn_c;
	}
	/**
	 * @return the bf_seq_n
	 */
	public String getBf_seq_n() {
		return bf_seq_n;
	}
	/**
	 * @param bf_seq_n the bf_seq_n to set
	 */
	public void setBf_seq_n(String bf_seq_n) {
		this.bf_seq_n = bf_seq_n;
	}
	/**
	 * @return the bf_orgFileName_v
	 */
	public String getBf_orgFileName_v() {
		return bf_orgFileName_v;
	}
	/**
	 * @param bf_orgFileName_v the bf_orgFileName_v to set
	 */
	public void setBf_orgFileName_v(String bf_orgFileName_v) {
		this.bf_orgFileName_v = bf_orgFileName_v;
	}
	/**
	 * @return the bf_storFileName_v
	 */
	public String getBf_storFileName_v() {
		return bf_storFileName_v;
	}
	/**
	 * @param bf_storFileName_v the bf_storFileName_v to set
	 */
	public void setBf_storFileName_v(String bf_storFileName_v) {
		this.bf_storFileName_v = bf_storFileName_v;
	}
	/**
	 * @return the bf_filePath_v
	 */
	public String getBf_filePath_v() {
		return bf_filePath_v;
	}
	/**
	 * @param bf_filePath_v the bf_filePath_v to set
	 */
	public void setBf_filePath_v(String bf_filePath_v) {
		this.bf_filePath_v = bf_filePath_v;
	}
	/**
	 * @return the bf_fileSize_n
	 */
	public String getBf_fileSize_n() {
		return bf_fileSize_n;
	}
	/**
	 * @param bf_fileSize_n the bf_fileSize_n to set
	 */
	public void setBf_fileSize_n(String bf_fileSize_n) {
		this.bf_fileSize_n = bf_fileSize_n;
	}
	/**
	 * @return the bi_hitCount_n
	 */
	public String getBi_hitCount_n() {
		return bi_hitCount_n;
	}
	/**
	 * @param bi_hitCount_n the bi_hitCount_n to set
	 */
	public void setBi_hitCount_n(String bi_hitCount_n) {
		this.bi_hitCount_n = bi_hitCount_n;
	}
	/**
	 * @return the bi_seq_n
	 */
	public String getBi_seq_n() {
		return bi_seq_n;
	}
	/**
	 * @param bi_seq_n the bi_seq_n to set
	 */
	public void setBi_seq_n(String bi_seq_n) {
		this.bi_seq_n = bi_seq_n;
	}
	/**
	 * @return the bs_seq_n
	 */
	public String getBs_seq_n() {
		return bs_seq_n;
	}
	/**
	 * @param bs_seq_n the bs_seq_n to set
	 */
	public void setBs_seq_n(String bs_seq_n) {
		this.bs_seq_n = bs_seq_n;
	}
	/**
	 * @return the bi_title_v
	 */
	public String getBi_title_v() {
		return bi_title_v;
	}
	/**
	 * @param bi_title_v the bi_title_v to set
	 */
	public void setBi_title_v(String bi_title_v) {
		this.bi_title_v = bi_title_v;
	}
	/**
	 * @return the bi_content_t
	 */
	public String getBi_content_t() {
		return bi_content_t;
	}
	/**
	 * @param bi_content_t the bi_content_t to set
	 */
	public void setBi_content_t(String bi_content_t) {
		this.bi_content_t = bi_content_t;
	}
	/**
	 * @return the bi_name_v
	 */
	public String getBi_name_v() {
		return bi_name_v;
	}
	/**
	 * @param bi_name_v the bi_name_v to set
	 */
	public void setBi_name_v(String bi_name_v) {
		this.bi_name_v = bi_name_v;
	}
	/**
	 * @return the bi_id_v
	 */
	public String getBi_id_v() {
		return bi_id_v;
	}
	/**
	 * @param bi_id_v the bi_id_v to set
	 */
	public void setBi_id_v(String bi_id_v) {
		this.bi_id_v = bi_id_v;
	}
	/**
	 * @return the bi_phone_v
	 */
	public String getBi_phone_v() {
		return bi_phone_v;
	}
	/**
	 * @param bi_phone_v the bi_phone_v to set
	 */
	public void setBi_phone_v(String bi_phone_v) {
		this.bi_phone_v = bi_phone_v;
	}
	/**
	 * @return the bi_fileSeq_n
	 */
	public String getBi_fileSeq_n() {
		return bi_fileSeq_n;
	}
	/**
	 * @param bi_fileSeq_n the bi_fileSeq_n to set
	 */
	public void setBi_fileSeq_n(String bi_fileSeq_n) {
		this.bi_fileSeq_n = bi_fileSeq_n;
	}
	/**
	 * @return the bi_useYn_c
	 */
	public String getBi_useYn_c() {
		return bi_useYn_c;
	}
	/**
	 * @param bi_useYn_c the bi_useYn_c to set
	 */
	public void setBi_useYn_c(String bi_useYn_c) {
		this.bi_useYn_c = bi_useYn_c;
	}
	/**
	 * @return the bi_linkUrl_v
	 */
	public String getBi_linkUrl_v() {
		return bi_linkUrl_v;
	}
	/**
	 * @param bi_linkUrl_v the bi_linkUrl_v to set
	 */
	public void setBi_linkUrl_v(String bi_linkUrl_v) {
		this.bi_linkUrl_v = bi_linkUrl_v;
	}
	/**
	 * @return the bi_gubun_v
	 */
	public String getBi_gubun_v() {
		return bi_gubun_v;
	}
	/**
	 * @param bi_gubun_v the bi_gubun_v to set
	 */
	public void setBi_gubun_v(String bi_gubun_v) {
		this.bi_gubun_v = bi_gubun_v;
	}
	/**
	 * @return the bi_etc1_v
	 */
	public String getBi_etc1_v() {
		return bi_etc1_v;
	}
	/**
	 * @param bi_etc1_v the bi_etc1_v to set
	 */
	public void setBi_etc1_v(String bi_etc1_v) {
		this.bi_etc1_v = bi_etc1_v;
	}
	/**
	 * @return the bi_etc2_v
	 */
	public String getBi_etc2_v() {
		return bi_etc2_v;
	}
	/**
	 * @param bi_etc2_v the bi_etc2_v to set
	 */
	public void setBi_etc2_v(String bi_etc2_v) {
		this.bi_etc2_v = bi_etc2_v;
	}
	/**
	 * @return the bi_etc3_v
	 */
	public String getBi_etc3_v() {
		return bi_etc3_v;
	}
	/**
	 * @param bi_etc3_v the bi_etc3_v to set
	 */
	public void setBi_etc3_v(String bi_etc3_v) {
		this.bi_etc3_v = bi_etc3_v;
	}
	/**
	 * @return the bi_etc4_v
	 */
	public String getBi_etc4_v() {
		return bi_etc4_v;
	}
	/**
	 * @param bi_etc4_v the bi_etc4_v to set
	 */
	public void setBi_etc4_v(String bi_etc4_v) {
		this.bi_etc4_v = bi_etc4_v;
	}
	/**
	 * @return the bi_etc5_v
	 */
	public String getBi_etc5_v() {
		return bi_etc5_v;
	}
	/**
	 * @param bi_etc5_v the bi_etc5_v to set
	 */
	public void setBi_etc5_v(String bi_etc5_v) {
		this.bi_etc5_v = bi_etc5_v;
	}
}
	