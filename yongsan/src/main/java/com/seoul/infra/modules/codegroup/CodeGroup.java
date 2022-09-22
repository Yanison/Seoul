package com.seoul.infra.modules.codegroup;

import java.util.ArrayList;
import java.util.List;

import com.seoul.infra.common.basic.commonVO;

public class CodeGroup extends commonVO{

	
//cg
	public Integer infrCgSeq;
	public Integer infrCgUseNy;
	public Integer infrCgDelNy;
	public String infrCgName;
	public String infrCgNameEng;
	
	
	
	
	public Integer getInfrCgSeq() {
		return infrCgSeq;
	}






	public void setInfrCgSeq(Integer infrCgSeq) {
		this.infrCgSeq = infrCgSeq;
	}






	public Integer getInfrCgUseNy() {
		return infrCgUseNy;
	}






	public void setInfrCgUseNy(Integer infrCgUseNy) {
		this.infrCgUseNy = infrCgUseNy;
	}






	public Integer getInfrCgDelNy() {
		return infrCgDelNy;
	}






	public void setInfrCgDelNy(Integer infrCgDelNy) {
		this.infrCgDelNy = infrCgDelNy;
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





	public Integer infrCgSeqFk;
	
	public Integer getInfrCgSeqFk() {
		return infrCgSeqFk;
	}
	public void setInfrCgSeqFk(Integer infrCgSeqFk) {
		this.infrCgSeqFk = infrCgSeqFk;
	}



	//	for cache
	public static List<CodeGroup> cachedCodeArrayList = new ArrayList<CodeGroup>();
	
}
