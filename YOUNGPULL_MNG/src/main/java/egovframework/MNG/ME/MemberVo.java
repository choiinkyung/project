package egovframework.MNG.ME;

import com.sun.xml.internal.ws.developer.Serialization;

import egovframework.MNG.COMM.service.CommonVo;

@Serialization
public class MemberVo extends CommonVo{
	
	/*일련번호*/
	private String seq_v;
	/*이름*/
	private String name_v;
	/*아이디*/
	private String id_v;
	/*비밀번호*/
	private String pwd_v;
	/*첨부파일 일련번호*/
	private String file_seq_v;
	/*등록일*/
	private String reg_dt_d;
	/*등록아이디*/
	private String REG_ID_V;
	public String getSeq_v() {
		return seq_v;
	}
	public void setSeq_v(String seq_v) {
		this.seq_v = seq_v;
	}
	public String getName_v() {
		return name_v;
	}
	public void setName_v(String name_v) {
		this.name_v = name_v;
	}
	public String getId_v() {
		return id_v;
	}
	public void setId_v(String id_v) {
		this.id_v = id_v;
	}
	public String getPwd_v() {
		return pwd_v;
	}
	public void setPwd_v(String pwd_v) {
		this.pwd_v = pwd_v;
	}
	public String getFile_seq_v() {
		return file_seq_v;
	}
	public void setFile_seq_v(String file_seq_v) {
		this.file_seq_v = file_seq_v;
	}
	public String getReg_dt_d() {
		return reg_dt_d;
	}
	public void setReg_dt_d(String reg_dt_d) {
		this.reg_dt_d = reg_dt_d;
	}
	public String getREG_ID_V() {
		return REG_ID_V;
	}
	public void setREG_ID_V(String rEG_ID_V) {
		REG_ID_V = rEG_ID_V;
	}
	
	

}
