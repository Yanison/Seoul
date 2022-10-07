package com.seoul.infra.dto;

import java.util.Date;

public class CryptoUpload {
	
	
	private Integer cryptoUploadSeq;
	private Integer type;
	private Integer defaultNy;
	private Integer sort;
	private String originalName;
	private String uudiName;
	private String ext;
	private Integer size;
	private Integer delNy;
	private Integer pseq;
	private Date regDateAt;
	private String regDateBy;
	private Date ModDateAt;
	private String ModDateBy;
	private Integer cryptoSeq;
	
	
	public Integer getCryptoUploadSeq() {
		return cryptoUploadSeq;
	}
	public void setCryptoUploadSeq(Integer cryptoUploadSeq) {
		this.cryptoUploadSeq = cryptoUploadSeq;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getDefaultNy() {
		return defaultNy;
	}
	public void setDefaultNy(Integer defaultNy) {
		this.defaultNy = defaultNy;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getUudiName() {
		return uudiName;
	}
	public void setUudiName(String uudiName) {
		this.uudiName = uudiName;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getDelNy() {
		return delNy;
	}
	public void setDelNy(Integer delNy) {
		this.delNy = delNy;
	}
	public Integer getPseq() {
		return pseq;
	}
	public void setPseq(Integer pseq) {
		this.pseq = pseq;
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
	public Integer getCryptoSeq() {
		return cryptoSeq;
	}
	public void setCryptoSeq(Integer cryptoSeq) {
		this.cryptoSeq = cryptoSeq;
	}
	
	

}
