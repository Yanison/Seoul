package com.seoul.infra.modules.codegroup;

import java.util.ArrayList;
import java.util.List;

import com.seoul.infra.common.basic.commonVO;

public class CommonCodeGroup extends CodeGroup{


	//ccg
	
	
	private Integer infrCcgSeq;
	private String infrCcgName;
	private String infrCcgNameEng;
	private Integer infrCcgUseNy;
	private Integer infrCcgDelNy;
	private Integer infrCgSeqFk;
	private Integer infrCcgTypeNum;
	
	






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








	public Integer getInfrCgSeqFk() {
		return infrCgSeqFk;
	}








	public void setInfrCgSeqFk(Integer infrCgSeqFk) {
		this.infrCgSeqFk = infrCgSeqFk;
	}








	public Integer getInfrCcgTypeNum() {
		return infrCcgTypeNum;
	}








	public void setInfrCcgTypeNum(Integer infrCcgTypeNum) {
		this.infrCcgTypeNum = infrCcgTypeNum;
	}








	public static List<CommonCodeGroup> getCachedCodeArrayList() {
		return cachedCodeArrayList;
	}








	public static void setCachedCodeArrayList(List<CommonCodeGroup> cachedCodeArrayList) {
		CommonCodeGroup.cachedCodeArrayList = cachedCodeArrayList;
	}








	//	for cache
	public static List<CommonCodeGroup> cachedCodeArrayList = new ArrayList<CommonCodeGroup>();
	
}
