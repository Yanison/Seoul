package com.seoul.infra.modules.helpcenter;




import java.util.Date;

import com.seoul.infra.common.basic.commonVO;

public class HelpcenterDto extends commonVO {
	
	private int bdSeq;
	private String bdTitle;
	private String bdContent;
	private String bdDiv;
	private int delNy;
	private int memberSeq;
	private String memberNickname;
	
	
	
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getBdDiv() {
		return bdDiv;
	}
	public void setBdDiv(String bdDiv) {
		this.bdDiv = bdDiv;
	}
	public String getBdTitle() {
		return bdTitle;
	}
	public void setBdTitle(String bdTitle) {
		this.bdTitle = bdTitle;
	}
	public int getBdSeq() {
		return bdSeq;
	}
	public void setBdSeq(int bdSeq) {
		this.bdSeq = bdSeq;
	}
	public String getBdContent() {
		return bdContent;
	}
	public void setBdContent(String bdContent) {
		this.bdContent = bdContent;
	}
	public int getDelNy() {
		return delNy;
	}
	public void setDelNy(int delNy) {
		this.delNy = delNy;
	}
	public int getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}
	
	private int cmtSeq;
	private String text;
	public int getCmtSeq() {
		return cmtSeq;
	}
	public void setCmtSeq(int cmtSeq) {
		this.cmtSeq = cmtSeq;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	private Date bdRegDateTime;
	private Date bdUptDateTime;
	private Date cmtRegDateTime;
	private Date cmtUptDateTime;
	
	
	
	
	public Date getBdRegDateTime() {
		return bdRegDateTime;
	}
	public void setBdRegDateTime(Date bdRegDateTime) {
		this.bdRegDateTime = bdRegDateTime;
	}
	public Date getBdUptDateTime() {
		return bdUptDateTime;
	}
	public void setBdUptDateTime(Date bdUptDateTime) {
		this.bdUptDateTime = bdUptDateTime;
	}
	public Date getCmtRegDateTime() {
		return cmtRegDateTime;
	}
	public void setCmtRegDateTime(Date cmtRegDateTime) {
		this.cmtRegDateTime = cmtRegDateTime;
	}
	public Date getCmtUptDateTime() {
		return cmtUptDateTime;
	}
	public void setCmtUptDateTime(Date cmtUptDateTime) {
		this.cmtUptDateTime = cmtUptDateTime;
	}

	private int view;
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	

}
