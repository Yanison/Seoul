package com.seoul.infra.modules.membergroup;

import com.seoul.infra.common.basic.commonVO;

public class MemberGroupVO extends commonVO {
	
	private int mmSess;
	private int shValuOption;
	private String shStrOption;
	public int getMmSess() {
		return mmSess;
	}
	public void setMmSess(int mmSess) {
		this.mmSess = mmSess;
	}
	public int getShValuOption() {
		return shValuOption;
	}
	public void setShValuOption(int shValuOption) {
		this.shValuOption = shValuOption;
	}
	public String getShStrOption() {
		return shStrOption;
	}
	public void setShStrOption(String shStrOption) {
		this.shStrOption = shStrOption;
	}
}
