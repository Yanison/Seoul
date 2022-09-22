package com.seoul.infra.modules.codegroup;

import java.util.ArrayList;
import java.util.List;

import com.seoul.infra.common.basic.commonVO;

public class CommonCodeGroup extends CodeGroup{


	//ccg
	public Integer infrCcgSeq;
	public Integer infrCcgUseNy;
	public Integer infrCcgDelNy;
	public Integer infrCgSeqFk;
	public String infrCcgName;
	public String infrCcgNameEng;
	
	
	
	
	
	
	public Integer getInfrCcgSeq() {
		return infrCcgSeq;
	}






	public void setInfrCcgSeq(Integer infrCcgSeq) {
		this.infrCcgSeq = infrCcgSeq;
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






	//	for cache
	public static List<CommonCodeGroup> cachedCodeArrayList = new ArrayList<CommonCodeGroup>();
	
}
