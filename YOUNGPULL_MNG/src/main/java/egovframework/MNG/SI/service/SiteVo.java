package egovframework.MNG.SI.service;

import com.sun.xml.internal.ws.developer.Serialization;

import egovframework.MNG.COMM.service.CommonVo;

@Serialization
public class SiteVo extends CommonVo{
	/*도메인주소*/
	private String SI_DADDR_V;
	/*도메인명*/
	private String SI_DNAME_V;
	/*도메인관리사이트*/
	private String SI_DSITE_V;
	/*도메인만료일*/
	private String SI_DENDDT_V;
	/*도메인계정아이디*/
	private String SI_DID_V;
	/*도메인계정비밀번호*/
	private String SI_DPWD_V;
	/*호스팅주소*/
	private String SI_HADDR_V;
	/*호스팅아이디*/
	private String SI_HID_V;
	/*호스팅비밀번호*/
	private String SI_HPWD_V;
	/*호스팅종료일*/
	private String SI_HENDDT_V;
	/*상세내용*/
	private String SI_TEXT_T;
	/*남은 일수 */
	private String USE_DAYS;
	
	public String getSI_DADDR_V() {
		return SI_DADDR_V;
	}
	public void setSI_DADDR_V(String sI_DADDR_V) {
		SI_DADDR_V = sI_DADDR_V;
	}
	public String getSI_DNAME_V() {
		return SI_DNAME_V;
	}
	public void setSI_DNAME_V(String sI_DNAME_V) {
		SI_DNAME_V = sI_DNAME_V;
	}
	public String getSI_DSITE_V() {
		return SI_DSITE_V;
	}
	public void setSI_DSITE_V(String sI_DSITE_V) {
		SI_DSITE_V = sI_DSITE_V;
	}
	public String getSI_DENDDT_V() {
		return SI_DENDDT_V;
	}
	public void setSI_DENDDT_V(String sI_DENDDT_V) {
		SI_DENDDT_V = sI_DENDDT_V;
	}
	public String getSI_DID_V() {
		return SI_DID_V;
	}
	public void setSI_DID_V(String sI_DID_V) {
		SI_DID_V = sI_DID_V;
	}
	public String getSI_DPWD_V() {
		return SI_DPWD_V;
	}
	public void setSI_DPWD_V(String sI_DPWD_V) {
		SI_DPWD_V = sI_DPWD_V;
	}
	public String getSI_HADDR_V() {
		return SI_HADDR_V;
	}
	public void setSI_HADDR_V(String sI_HADDR_V) {
		SI_HADDR_V = sI_HADDR_V;
	}
	public String getSI_HID_V() {
		return SI_HID_V;
	}
	public void setSI_HID_V(String sI_HID_V) {
		SI_HID_V = sI_HID_V;
	}
	public String getSI_HPWD_V() {
		return SI_HPWD_V;
	}
	public void setSI_HPWD_V(String sI_HPWD_V) {
		SI_HPWD_V = sI_HPWD_V;
	}
	public String getSI_HENDDT_V() {
		return SI_HENDDT_V;
	}
	public void setSI_HENDDT_V(String sI_HENDDT_V) {
		SI_HENDDT_V = sI_HENDDT_V;
	}
	public String getSI_TEXT_T() {
		return SI_TEXT_T;
	}
	public void setSI_TEXT_T(String sI_TEXT_T) {
		SI_TEXT_T = sI_TEXT_T;
	}
	public String getUSE_DAYS() {
		return USE_DAYS;
	}
	public void setUSE_DAYS(String uSE_DAYS) {
		USE_DAYS = uSE_DAYS;
	}
	
	
}
