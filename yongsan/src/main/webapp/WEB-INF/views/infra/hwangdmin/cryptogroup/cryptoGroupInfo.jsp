<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>CryptoGroupInfo</title>
<%@ include file="../rsrcHwangdmin.jsp" %>
<link href="../resources/css/root/cryptogroup/cryptogroup.css" rel="stylesheet">
<!-- <script src="../resources/js/root/cryptoGroup/cryptogroupUpld.js"></script> -->
<script src="../resources/js/root/cryptoGroup/cryptogroupUpldprac2.js">
</script>
</head>
<body>
	<div class="wrapper">
		<nav class="navi">
			<%@ include file="../hwangdminComponent/rootNavi.jsp" %>
		</nav>
		<div class="mainDiv">
			<header class="navheader">
				<%@ include file="../hwangdminComponent/rootNaviHeader.jsp" %>	
			</header>
			
			<article class="mainArticle">
				<section class="CryptoGroupInfoDiv">
					<section class="InfoHeader">
						<h1>CryptoGroupInfo</h1>
					</section>
					
					<section class="InputCryptoInfo">
						<div class="infoInputGroup">
							<input type="text" id="cryptoSeq" class="cryptoSeq inputGroup" name="cryptoSeq" placeholder="cryptoSeq" readOnly>
						</div>
						<div class="infoInputGroup">
							<input type="text" id="cryptoName" class="cryptoName inputGroup" name="cryptoName" placeholder="cryptoName">
						</div>
						<div class="infoInputGroup">
							<select id="activeNy" class="activeNy inputGroup" name="activeNy">
								<option>activeNy</option>
								<option value="1">active</option>
								<option value="2">inactive</option>
							</select>
						</div>
						<div class="infoInputGroup">
							<input type="text" id="regDateBy" class="regDateBy inputGroup" name="regDateBy" placeholder="regDateBy">
						</div>
						<div class="infoInputGroup">
							<input type="text" id="modDateBy" class="modDateBy inputGroup" name="modDateBy" placeholder="modDateBy">
						</div>
						<div class="infoInputGroup">
							<select id="wodAvailable" class="wodAvailable inputGroup" name="wodAvailable">
								<option>wodAvailable</option>
								<option value="0">available</option>
								<option value="1">disAvailable</option>
							</select>
						</div>
					</section>
					<div class="uploadFileDiv">
						<!-- <div class="insert">
						    <form method="POST" onsubmit="return false;" enctype="multipart/form-data">
						        <input id="uploadImg" class="upload" type="file" onchange="addFile(this);" style="display:none;">
						        <label class="labelUploadImg" for="uploadImg">CryptoSymbol Upload</label>		
						        <div id="uploadImg" class="file-list"></div>
						    </form>
						</div> -->
						<input type="file" onchange="upload('img',1,3,6)" id="img" name="img" multiple="multiple"/>
						<div class="addScroll">
							<ul id="ulFlie2">
								
							</ul>
						</div>
					</div>
				</section>
			</article>
		</div>
	</div>
</div>
</body>
</html>