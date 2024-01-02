<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Bizplus会社管理システム</title>
<link
	href="${pageContext.request.contextPath}/resources/css/authority/basic_layout.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/resources/css/authority/common_style.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<table class="table" cellspacing="0" cellpadding="5" width="100%"
			align="center" border="0">
			<tr>
				<th colspan="2" align="center">システムバージョン情報</th>
			</tr>
			<tr>
				<td width="120" height="30" align="right">最新バージョン:<span
					class="TableRow2"></span></td>
				<td style="text-align: left">管理システム 1.0</td>
			</tr>
			<tr>
				<td width="120" height="30" align="right">著作権表示:</td>
				<td style="text-align: left">1、このソフトウェアは、サードパーティのシステムへのソフトウェアの書面による許可なしに、商用ソフトウェアです。
					<br> 2、このソフトウェアは、関連する法律とコンピュータソフトウェア保護条例と会社を保護するために、<br>
					すべての権利を保有します。
				</td>
			</tr>
		</table>
		<table class="table" cellspacing="0" cellpadding="5" width="100%"
			align="center" border="0">
			<tr>
				<th colspan="2" align="center">システム開発</th>
			</tr>
			<tr>
				<td width="120" height="30" align="right">プロジェクト作成:</td>
				<td style="text-align: left">ABC株式会社</td>
			</tr>
			<tr>
				<td width="120" height="30" align="right">給料明細書のアクセス(会社番号：xx):</td>
				<td style="text-align: left"><a target="_blank"
					href="https://login.php">https://login.php</a>
				</td>
			</tr>
			<tr>
				<td width="120" height="30" align="right">連絡先:</td>
				<td style="text-align: left"><a
					href="mailto:info@abc.co.jp">info@abc.co.jp</a></td>
			</tr>
			<tr>
				<td width="100" height="30" align="right">ホーム:<span
					class="TableRow2"></span></td>
				<td style="text-align: left"><a href="http://abc.co.jp/"
					target="_blank">http://abc.tokyo/</a></td>
			</tr>
		</table>
	</div>
</body>
</html>
