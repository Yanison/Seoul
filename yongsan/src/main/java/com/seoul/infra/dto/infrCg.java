package com.seoul.infra.dto;

import java.util.Date;

public class infrCg {
	
	private Integer infrCgSeq;
	private String infrCgName;
	private String infrCgNameEng;
	private Integer infrCgUseNy;
	private Integer infrCgDelNy;
	private Date regDateAt;
	private String regDateBy;
	private Date ModDateAt;
	private String ModDateBy;
	
	public Integer getInfrCgSeq() {
		return infrCgSeq;
	}
	public void setInfrCgSeq(Integer infrCgSeq) {
		this.infrCgSeq = infrCgSeq;
	}
	public String getInfrCgName() {
		return infrCgName;
	}
	public void setInfrCgName(String infrCgName) {
		this.infrCgName = infrCgName;
	}
	public String getInfrCgNameEng() {
		return infrCgNameEng;
	}
	public void setInfrCgNameEng(String infrCgNameEng) {
		this.infrCgNameEng = infrCgNameEng;
	}
	public Integer getInfrCgUseNy() {
		return infrCgUseNy;
	}
	public void setInfrCgUseNy(Integer infrCgUseNy) {
		this.infrCgUseNy = infrCgUseNy;
	}
	public Integer getInfrCgSeqFk() {
		return infrCgSeqFk;
	}
	public void setInfrCgSeqFk(Integer infrCgSeqFk) {
		this.infrCgSeqFk = infrCgSeqFk;
	}
	
	
	private Integer infrCcgSeq;
	private String infrCcgName;
	private String infrCcgNameEng;
	private Integer infrCcgUseNy;
	private Integer infrCcgDelNy;
	private Integer infrCgSeqFk;
	private Integer infrCcgTypeNum;

	public Integer getInfrCgDelNy() {
		return infrCgDelNy;
	}
	public void setInfrCgDelNy(Integer infrCgDelNy) {
		this.infrCgDelNy = infrCgDelNy;
	}
	public Date getRegDateAt() {
		return regDateAt;
	}
	public void setRegDateAt(Date regDateAt) {
		this.regDateAt = regDateAt;
	}
	public String getRegDateBy() {
		return regDateBy;
	}
	public void setRegDateBy(String regDateBy) {
		this.regDateBy = regDateBy;
	}
	public Date getModDateAt() {
		return ModDateAt;
	}
	public void setModDateAt(Date modDateAt) {
		ModDateAt = modDateAt;
	}
	public String getModDateBy() {
		return ModDateBy;
	}
	public void setModDateBy(String modDateBy) {
		ModDateBy = modDateBy;
	}
	public Integer getInfrCcgSeq() {
		return infrCcgSeq;
	}
	public void setInfrCcgSeq(Integer infrCcgSeq) {
		this.infrCcgSeq = infrCcgSeq;
	}
	public String getInfrCcgName() {
		return infrCcgName;
	}
	public void setInfrCcgName(String infrCcgName) {
		this.infrCcgName = infrCcgName;
	}
	public String getInfrCcgNameEng() {
		return infrCcgNameEng;
	}
	public void setInfrCcgNameEng(String infrCcgNameEng) {
		this.infrCcgNameEng = infrCcgNameEng;
	}
	public Integer getInfrCcgUseNy() {
		return infrCcgUseNy;
	}
	public void setInfrCcgUseNy(Integer infrCcgUseNy) {
		this.infrCcgUseNy = infrCcgUseNy;
	}
	public Integer getInfrCcgDelNy() {
		return infrCcgDelNy;
	}
	public void setInfrCcgDelNy(Integer infrCcgDelNy) {
		this.infrCcgDelNy = infrCcgDelNy;
	}
	public Integer getInfrCcgTypeNum() {
		return infrCcgTypeNum;
	}
	public void setInfrCcgTypeNum(Integer infrCcgTypeNum) {
		this.infrCcgTypeNum = infrCcgTypeNum;
	}
}
