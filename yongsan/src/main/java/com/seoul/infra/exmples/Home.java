package com.seoul.infra.exmples;

public class Home {
	public Object header;
	public String resultCode;
	public String resultMsg;

	public Object body;
	public int pageNo;
	public int totalCount;
	public int numOfRows;

	public Object[] items;
	public String YYYY;
	public String MM;
	public String KIT_PROD_QTY;
	public String KIT_EXPRT_QTY;
	public String KIT_STOCK_QTY;
	public Object getHeader() {
		return header;
	}
	public void setHeader(Object header) {
		this.header = header;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getNumOfRows() {
		return numOfRows;
	}
	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}
	public Object[] getItems() {
		return items;
	}
	public void setItems(Object[] items) {
		this.items = items;
	}
	public String getYYYY() {
		return YYYY;
	}
	public void setYYYY(String yYYY) {
		YYYY = yYYY;
	}
	public String getMM() {
		return MM;
	}
	public void setMM(String mM) {
		MM = mM;
	}
	public String getKIT_PROD_QTY() {
		return KIT_PROD_QTY;
	}
	public void setKIT_PROD_QTY(String kIT_PROD_QTY) {
		KIT_PROD_QTY = kIT_PROD_QTY;
	}
	public String getKIT_EXPRT_QTY() {
		return KIT_EXPRT_QTY;
	}
	public void setKIT_EXPRT_QTY(String kIT_EXPRT_QTY) {
		KIT_EXPRT_QTY = kIT_EXPRT_QTY;
	}
	public String getKIT_STOCK_QTY() {
		return KIT_STOCK_QTY;
	}
	public void setKIT_STOCK_QTY(String kIT_STOCK_QTY) {
		KIT_STOCK_QTY = kIT_STOCK_QTY;
	}
	
	

}
