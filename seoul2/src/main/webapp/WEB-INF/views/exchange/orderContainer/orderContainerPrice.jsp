<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 


<%-- <%% %> 쓰는 대신 제이쿼리 cdn처럼 당겨올 수 있음 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="false" %>

<!-- css,js _ (입력)에서 상속받음. --> 
<script src="./resources/js/exchange/orderContainer/orderContainerPrice.js"></script>




<div id="orderContainerWrap" class="orderContainerWrap">
							<div class="orderContainerPrice">

								<!-- <div class="orderContainerPrice"> -->
									<table>
										<!-- up클래스는 갱신된 새 데이터가 상단에서부터 생기고
								down 클래스는 갱신된 새 데이터가 하단에서부터 생김
								그리고 마우스 오버시에 selectecArea라는 특정 class를 부여(relative)해서 
								평균가랑 누적가 누적액 팝업 selectBox div(absolute)를 띄운다.  -->
										<colgroup>
											<col width="42">
											<col width="120">
											<col width="*">
											<col width="120">
											<col width="42">
										</colgroup>
										<tbody>
											<tr class="down">
												<td></td>
												<td class="bar">													
													<!-- 체결수량 -->
													<a href="#">
														<div style=""><!-- 체결수량에 비례해서 div가 늘어남 --></div>
														<p>amout</p>
													</a>
												</td>
												<td class="upB">
													<!--positionR은 호가창에서 현제 시세를 마크업 해주는 html 요소임.
													가격이 전날 가격보다 상승이면 upclass를, 하락이면 down클래스를 주는듯 -->
													<!-- 체결가격 -->
													<a href="#">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>
												<td colspan="2" rowspan="15" class="inner01">
													<!-- 가격개요 -->
													<dl class="first">
														<dt>거래량</dt>
														<dd>
															num <i>btc</i>
														</dd>
														<dt>거래대금</dt>
														<dd>
															num <i>백만원</i> 
															<em>(최근24시간)</em>
														</dd>
													</dl>
													<dl>
														<dt>52주 최고</dt>
														<dd class="up">
															num 
															<em>
															0000.00.00
															</em>
														</dd>
														<dt>52주 최저</dt>
														<dd class="down">
															num 
															<em>
															0000.00.00
															</em>
														</dd>
													</dl>
													<dl>
														<dt>전일종가</dt>
														<dd>num</dd>
														<dt>당일고가</dt>
														<dd class="up">
														num
														</dd>
														<dt>당일저가</dt>
														<dd id="scrh" class="down">
														num
														</dd>
													</dl>											
												</td>
											</tr>
											
<!-- ---------------------------------------------------- bidtest start ---------------------------------------------------- -->										
											<tr class="down downtest"> <!-- .downtest start -->
												<td></td>
												<td class="bar">													
													<!-- 체결수량 -->													
													<a href="#">
														<div style="width: 34.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="upB">
													<!--positionR은 호가창에서 현제 시세를 마크업 해주는 html 요소임.
													가격이 전날 가격보다 상승이면 upclass를, 하락이면 down클래스를 주는듯 -->
													<!-- 체결가격 -->
													<a href="#">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>											
											</tr> <!-- .downtest start -->
											
											
											<tr class="down downtest"> <!-- .downtest start -->
												<td></td>
												<td class="bar">													
													<!-- 체결수량 -->													
													<a href="#">
														<div style="width: 84.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="upB">
													<!--positionR은 호가창에서 현제 시세를 마크업 해주는 html 요소임.
													가격이 전날 가격보다 상승이면 upclass를, 하락이면 down클래스를 주는듯 -->
													<!-- 체결가격 -->
													<a href="#">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>											
											</tr> <!-- .downtest start -->
											
											<tr class="down downtest"> <!-- .downtest start -->
												<td></td>
												<td class="bar">													
													<!-- 체결수량 -->													
													<a href="#">
														<div style="width: 34.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="upB">
													<!--positionR은 호가창에서 현제 시세를 마크업 해주는 html 요소임.
													가격이 전날 가격보다 상승이면 upclass를, 하락이면 down클래스를 주는듯 -->
													<!-- 체결가격 -->
													<a href="#">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>											
											</tr> <!-- .downtest start -->
											
											<tr class="down downtest"> <!-- .downtest start -->
												<td></td>
												<td class="bar">													
													<!-- 체결수량 -->													
													<a href="#">
														<div style="width: 34.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="upB">
													<!--positionR은 호가창에서 현제 시세를 마크업 해주는 html 요소임.
													가격이 전날 가격보다 상승이면 upclass를, 하락이면 down클래스를 주는듯 -->
													<!-- 체결가격 -->
													<a href="#">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>											
											</tr> <!-- .downtest start -->
											
											<tr class="down downtest"> <!-- .downtest start -->
												<td></td>
												<td class="bar">													
													<!-- 체결수량 -->													
													<a href="#">
														<div style="width: 34.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="upB">
													<!--positionR은 호가창에서 현제 시세를 마크업 해주는 html 요소임.
													가격이 전날 가격보다 상승이면 upclass를, 하락이면 down클래스를 주는듯 -->
													<!-- 체결가격 -->
													<a href="#">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>											
											</tr> <!-- .downtest start -->
											
											<tr class="down downtest"> <!-- .downtest start -->
												<td></td>
												<td class="bar">													
													<!-- 체결수량 -->													
													<a href="#">
														<div style="width: 34.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="upB">
													<!--positionR은 호가창에서 현제 시세를 마크업 해주는 html 요소임.
													가격이 전날 가격보다 상승이면 upclass를, 하락이면 down클래스를 주는듯 -->
													<!-- 체결가격 -->
													<a href="#">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>											
											</tr> <!-- .downtest start -->
											
											<tr class="down downtest"> <!-- .downtest start -->
												<td></td>
												<td class="bar">													
													<!-- 체결수량 -->													
													<a href="#">
														<div style="width: 34.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="upB">
													<!--positionR은 호가창에서 현제 시세를 마크업 해주는 html 요소임.
													가격이 전날 가격보다 상승이면 upclass를, 하락이면 down클래스를 주는듯 -->
													<!-- 체결가격 -->
													<a href="#">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>											
											</tr> <!-- .downtest start -->
											
											<tr class="down downtest"> <!-- .downtest start -->
												<td></td>
												<td class="bar">													
													<!-- 체결수량 -->													
													<a href="#">
														<div style="width: 34.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="upB">
													<!--positionR은 호가창에서 현제 시세를 마크업 해주는 html 요소임.
													가격이 전날 가격보다 상승이면 upclass를, 하락이면 down클래스를 주는듯 -->
													<!-- 체결가격 -->
													<a href="#">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>											
											</tr> <!-- .downtest start -->
											
											<tr class="down downtest"> <!-- .downtest start -->
												<td></td>
												<td class="bar">													
													<!-- 체결수량 -->													
													<a href="#">
														<div style="width: 11.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="upB">
													<!--positionR은 호가창에서 현제 시세를 마크업 해주는 html 요소임.
													가격이 전날 가격보다 상승이면 upclass를, 하락이면 down클래스를 주는듯 -->
													<!-- 체결가격 -->
													<a href="#">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>											
											</tr> <!-- .downtest start -->
											
											<tr class="down downtest"> <!-- .downtest start -->
												<td></td>
												<td class="bar">													
													<!-- 체결수량 -->													
													<a href="#">
														<div style="width: 20.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="upB">
													<!--positionR은 호가창에서 현제 시세를 마크업 해주는 html 요소임.
													가격이 전날 가격보다 상승이면 upclass를, 하락이면 down클래스를 주는듯 -->
													<!-- 체결가격 -->
													<a href="#">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>											
											</tr> <!-- .downtest start -->
											
											<tr class="down downtest"> <!-- .downtest start -->
												<td></td>
												<td class="bar">													
													<!-- 체결수량 -->													
													<a href="#">
														<div style="width: 2.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="upB">
													<!--positionR은 호가창에서 현제 시세를 마크업 해주는 html 요소임.
													가격이 전날 가격보다 상승이면 upclass를, 하락이면 down클래스를 주는듯 -->
													<!-- 체결가격 -->
													<a href="#">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>											
											</tr> <!-- .downtest start -->
											
											<tr class="down downtest"> <!-- .downtest start -->
												<td></td>
												<td class="bar">													
													<!-- 체결수량 -->													
													<a href="#">
														<div style="width: 12.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="upB">
													<!--positionR은 호가창에서 현제 시세를 마크업 해주는 html 요소임.
													가격이 전날 가격보다 상승이면 upclass를, 하락이면 down클래스를 주는듯 -->
													<!-- 체결가격 -->
													<a href="#">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>											
											</tr> <!-- .downtest start -->
											
											<tr class="down downtest"> <!-- .downtest start -->
												<td></td>
												<td class="bar">													
													<!-- 체결수량 -->													
													<a href="#">
														<div style="width: 74.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="upB">
													<!--positionR은 호가창에서 현제 시세를 마크업 해주는 html 요소임.
													가격이 전날 가격보다 상승이면 upclass를, 하락이면 down클래스를 주는듯 -->
													<!-- 체결가격 -->
													<a href="#">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>											
											</tr> <!-- .downtest start -->
											
											<tr class="down downtest"> <!-- .downtest start -->
												<td></td>
												<td class="bar">													
													<!-- 체결수량 -->													
													<a href="#">
														<div style="width: 4.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="upB">
													<!--positionR은 호가창에서 현제 시세를 마크업 해주는 html 요소임.
													가격이 전날 가격보다 상승이면 upclass를, 하락이면 down클래스를 주는듯 -->
													<!-- 체결가격 -->
													<a href="#">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>											
											</tr> <!-- .downtest start -->
											
<!-- ---------------------------------------------------- bidtest end ---------------------------------------------------- -->
											
											
											<tr class="up"> <!-- class up start -->
												<td colspan="2" rowspan="15" class="inner02">
													<!-- 체결가/체결량 -->
													<dl>
														<dt>체결강도</dt>
														<dd>ratio%</dd>
													</dl>
													<div class="overflow">
														<table>
															<colgroup>
																<col width="50%">
																<col width="*">
															</colgroup>
															<thead>
																<tr>
																	<th>체결가</th>													
																	<th>체결물량</th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td>price</td>
																	<td class="up">amount</td>
																<tr>
																
																<tr>
																	<td>price</td>
																	<td class="up">amount</td>
																<tr>
																
																<tr>
																	<td>price</td>
																	<td class="down">amount</td>
																<tr>
																
																<tr>
																	<td>price</td>
																	<td class="up">amount</td>
																<tr>
																
																<tr>
																	<td>price</td>
																	<td class="down">amount</td>
																<tr>
																
																
															</tbody>
														</table>
													</div>
												</td>
												<td class="downB">
													<!-- 체결가격 -->
													<a href="">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>
												<td class="bar">
													<a>
														<div style="width: 53.4%;"></div>
														<p>amout</p>
													</a>
													<!-- 체결수 -->													
												</td>
												<td class="last">
													
												</td>
											</tr><!-- class up end -->
<!-- ---------------------------------------------------- bidtest start ---------------------------------------------------- -->											
											<tr class="up uptest"> <!-- .uptest start -->
												<td class="downB">
													<!-- 체결가격 -->
													<a href="">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>
												<td class="bar">
													<!-- 체결수 -->
													<a>
														<div style="width: 3.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="last">
													
												</td>
											</tr> <!-- .uptest start -->
											
											<tr class="up uptest"> <!-- .uptest start -->
												<td class="downB">
													<!-- 체결가격 -->
													<a href="">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>
												<td class="bar">
													<!-- 체결수 -->
													<a>
														<div style="width: 7.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="last">
													
												</td>
											</tr> <!-- .uptest start -->
											
											<tr class="up uptest"> <!-- .uptest start -->
												<td class="downB">
													<!-- 체결가격 -->
													<a href="">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>
												<td class="bar">
													<!-- 체결수 -->
													<a>
														<div style="width: 18.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="last">
													
												</td>
											</tr> <!-- .uptest start -->
											
											<tr class="up uptest"> <!-- .uptest start -->
												<td class="downB">
													<!-- 체결가격 -->
													<a href="">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>
												<td class="bar">
													<!-- 체결수 -->
													<a>
														<div style="width: 12.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="last">
													
												</td>
											</tr> <!-- .uptest start -->
											
											<tr class="up uptest"> <!-- .uptest start -->
												<td class="downB">
													<!-- 체결가격 -->
													<a href="">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>
												<td class="bar">
													<!-- 체결수 -->
													<a>
														<div style="width: 27.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="last">
													
												</td>
											</tr> <!-- .uptest start -->
											
											<tr class="up uptest"> <!-- .uptest start -->
												<td class="downB">
													<!-- 체결가격 -->
													<a href="">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>
												<td class="bar">
													<!-- 체결수 -->
													<a>
														<div style="width: 40.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="last">
													
												</td>
											</tr> <!-- .uptest start -->
											
											<tr class="up uptest"> <!-- .uptest start -->
												<td class="downB">
													<!-- 체결가격 -->
													<a href="">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>
												<td class="bar">
													<!-- 체결수 -->
													<a>
														<div style="width: 27.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="last">
													
												</td>
											</tr> <!-- .uptest start -->
											
											<tr class="up uptest"> <!-- .uptest start -->
												<td class="downB">
													<!-- 체결가격 -->
													<a href="">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>
												<td class="bar">
													<!-- 체결수 -->
													<a>
														<div style="width: 27.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="last">
													
												</td>
											</tr> <!-- .uptest start -->
											
											<tr class="up uptest"> <!-- .uptest start -->
												<td class="downB">
													<!-- 체결가격 -->
													<a href="">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>
												<td class="bar">
													<!-- 체결수 -->
													<a>
														<div style="width: 27.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="last">
													
												</td>
											</tr> <!-- .uptest start -->
											
											<tr class="up uptest"> <!-- .uptest start -->
												<td class="downB">
													<!-- 체결가격 -->
													<a href="">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>
												<td class="bar">
													<!-- 체결수 -->
													<a>
														<div style="width: 27.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="last">
													
												</td>
											</tr> <!-- .uptest start -->
											
											<tr class="up uptest"> <!-- .uptest start -->
												<td class="downB">
													<!-- 체결가격 -->
													<a href="">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>
												<td class="bar">
													<!-- 체결수 -->
													<a>
														<div style="width: 27.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="last">
													
												</td>
											</tr> <!-- .uptest start -->
											
											<tr class="up uptest"> <!-- .uptest start -->
												<td class="downB">
													<!-- 체결가격 -->
													<a href="">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>
												<td class="bar">
													<!-- 체결수 -->
													<a>
														<div style="width: 27.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="last">
													
												</td>
											</tr> <!-- .uptest start -->
											
											<tr class="up uptest"> <!-- .uptest start -->
												<td class="downB">
													<!-- 체결가격 -->
													<a href="">
														<div class="ty03">
															<strong>price</strong>
														</div>
														<div class="ty02">ratio%</div>
													</a>
												</td>
												<td class="bar">
													<!-- 체결수 -->
													<a>
														<div style="width: 27.4%;"></div>
														<p>amout</p>
													</a>
												</td>
												<td class="last">
													
												</td>
											</tr> <!-- .uptest start -->
											
										
<!-- ---------------------------------------------------- bidtest end ---------------------------------------------------- -->											
										</tbody>
									</table>
								</div>							
							</div>
								