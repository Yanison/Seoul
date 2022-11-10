package com.seoul.infra.common.basic;

import java.sql.Date;

public class commonVO extends PaginationVO   {
	private String shValue;
	private Integer shValueSeq;
	private Date shValueDate;
	private Integer ActiveNy;
	private Integer shOption;
	
	
	
	public Integer getShOption() {
		return shOption;
	}
	public void setShOption(Integer shOption) {
		this.shOption = shOption;
	}
	public Integer getActiveNy() {
		return ActiveNy;
	}
	public void setActiveNy(Integer activeNy) {
		ActiveNy = activeNy;
	}
	public String getShValue() {
		return shValue;
	}
	public void setShValue(String shValue) {
		this.shValue = shValue;
	}
	public Integer getShValueSeq() {
		return shValueSeq;
	}
	public void setShValueSeq(Integer shValueSeq) {
		this.shValueSeq = shValueSeq;
	}
	public Date getShValueDate() {
		return shValueDate;
	}
	public void setShValueDate(Date shValueDate) {
		this.shValueDate = shValueDate;
	}
	
	public void setParamsPaging(int totalRows) {
//		
//		setThisPage(4); // 현재페이지

		setTotalRows(totalRows);

		if (getTotalRows() == 0) {
			setTotalPages(1);
		} else {
			setTotalPages(getTotalRows() / getRowNumToShow());
		}
		/*
		 * 전체 데이터 갯수가 0이면 전체 페이지는 1이
		 * 아니면 전체 페이지 갯수는 얻은 전체 데이터 갯수에서 
		 * 화면에 보여질 갯수로 나눈다. 
		 * RowNumToShow 는 항상 10이다. 
		 */

		if (getTotalRows() % getRowNumToShow() > 0) {
			setTotalPages(getTotalPages() + 1);
		}
		
		/*
		 * 전체 데이터 갯수에서 화면에 보여질 갯수 로 나눈 나머지가 0보다 크면
		 * 전체 페이지 갯수는 getTotalPages + 1 로 설정
		 */

		if (getTotalPages() < getThisPage()) {
			setThisPage(getTotalPages());
		}
		/*
		 * 전체 페이지 갯수보다 현제 페이지 갯수바다 작을경우 
		 * setThisPage = getTotalPages() 로 설정
		 */
		
		setStartPage(((getThisPage() - 1) / getPageNumToShow()) * getPageNumToShow() + 1);
		
		/*
		 * 시작페이지 번호는 현재 페이지에서 1을 뺀 숫자에서 
		 * '화면에 보여질 페이지 수' 와 '그 수에 +1 한 만큼의 수' 곱으로 나눈다. 
		 */
		
		setEndPage(getStartPage() + getPageNumToShow() - 1);
		/*
		 * 마지막 페이지는 시작페이지에서(현재 보여지는 페이지수 - 1) 로 한다. 
		 */
		
		
		//전체 페이지수 구현
//		setPageNumToShow( (getTotalRows() / getRowNumToShow())+1);
		

		if (getEndPage() > getTotalPages()) {
			setEndPage(getTotalPages());
		}
		/*
		 * 마지막페이지 숫자가 전체페이지 숫자보다 클 경우에 
		 * 마지막페이지는 현재 전체페이지 숫자로 설정한다. 
		 * 
		 */
		
		setEndRnumForOracle((getRowNumToShow() * getThisPage()));
		setStartRnumForOracle((getEndRnumForOracle() - getRowNumToShow()) + 1);
		if (getStartRnumForOracle() < 1) setStartRnumForOracle(1);
		
		/*
		 * setEndRnumForOracle 쿼리 row
		 * setStartRnumForOracle 쿼리 시작 row
		 * 
		 * 보
		 */
		if (getThisPage() == 1) {
			setStartRnumForMysql(0);
		} else {
			setStartRnumForMysql((getRowNumToShow() * (getThisPage()-1)));
		}
		
//		System.out.println("getThisPage():" + getThisPage());
//		System.out.println("getTotalRows():" + getTotalRows());
//		System.out.println("getRowNumToShow():" + getRowNumToShow());
//		System.out.println("getTotalPages():" + getTotalPages());
//		System.out.println("getStartPage():" + getStartPage());
//		System.out.println("getEndPage():" + getEndPage());		
//		System.out.println("getStartRnumForOracle():" + getStartRnumForOracle());
//		System.out.println("getEndRnumForOracle():" + getEndRnumForOracle());
//		System.out.println("getStartRnumForMysql(): " + getStartRnumForMysql());
	}
	
	
}
