package egovframework.MNG.AD.service;

import java.util.Date;

import com.sun.xml.internal.ws.developer.Serialization;

import egovframework.MNG.COMM.service.CommonVO;

@Serialization
public class AdministratorVo {
	
	/*일련번호*/
	private String ad_id_v;
	/*이름*/
	private String ad_pwd_v;
	/*아이디*/
	private String ad_name_v;
	/*비밀번호*/
	private String ad_useyn_v;
	/*첨부파일 일련번호*/
	private String ad_rating_v;
	private String ad_crsite_v;
	private Date ad_lstlogin_d;
	private int ad_logincnt_n;
	/*등록일*/
	private Date  reg_dt_d;
	/*등록아이디*/
	private String reg_id_v;
	private Date upt_dt_d;
	private String upt_id_v;
	
	public AdministratorVo() {}

	public String getAd_id_v() {
		return ad_id_v;
	}

	public void setAd_id_v(String ad_id_v) {
		this.ad_id_v = ad_id_v;
	}

	public String getAd_pwd_v() {
		return ad_pwd_v;
	}

	public void setAd_pwd_v(String ad_pwd_v) {
		this.ad_pwd_v = ad_pwd_v;
	}

	public String getAd_name_v() {
		return ad_name_v;
	}

	public void setAd_name_v(String ad_name_v) {
		this.ad_name_v = ad_name_v;
	}

	public String getAd_useyn_v() {
		return ad_useyn_v;
	}

	public void setAd_useyn_v(String ad_useyn_v) {
		this.ad_useyn_v = ad_useyn_v;
	}

	public String getAd_rating_v() {
		return ad_rating_v;
	}

	public void setAd_rating_v(String ad_rating_v) {
		this.ad_rating_v = ad_rating_v;
	}

	public String getAd_crsite_v() {
		return ad_crsite_v;
	}

	public void setAd_crsite_v(String ad_crsite_v) {
		this.ad_crsite_v = ad_crsite_v;
	}

	public Date getAd_lstlogin_d() {
		return ad_lstlogin_d;
	}

	public void setAd_lstlogin_d(Date ad_lstlogin_d) {
		this.ad_lstlogin_d = ad_lstlogin_d;
	}

	public int getAd_logincnt_n() {
		return ad_logincnt_n;
	}

	public void setAd_logincnt_n(int ad_logincnt_n) {
		this.ad_logincnt_n = ad_logincnt_n;
	}

	public Date getReg_dt_d() {
		return reg_dt_d;
	}

	public void setReg_dt_d(Date reg_dt_d) {
		this.reg_dt_d = reg_dt_d;
	}

	public String getReg_id_v() {
		return reg_id_v;
	}

	public void setReg_id_v(String reg_id_v) {
		this.reg_id_v = reg_id_v;
	}

	public Date getUpt_dt_d() {
		return upt_dt_d;
	}

	public void setUpt_dt_d(Date upt_dt_d) {
		this.upt_dt_d = upt_dt_d;
	}

	public String getUpt_id_v() {
		return upt_id_v;
	}

	public void setUpt_id_v(String upt_id_v) {
		this.upt_id_v = upt_id_v;
	};

	
	

}
