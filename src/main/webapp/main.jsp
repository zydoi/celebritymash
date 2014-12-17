<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title>Celebrity Mash!</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
	<s:url action="login" var="login" />
	<s:url action="register" var="register" />
	<div id="header">
		<a href="/" class="logo">Celebrity Mash</a>
		<a href="" class="register">Sign up</a>
		<a href="" class="login">Login</a>
	</div>
	<div id="secondary">
		<div class="nav">
			<a href="" class="big_text">Hollywood</a>
			<a href="" class="big_text">Greater China</a>
			<a href="" class="big_text">Global Rank</a>
			<a href="" class="big_text">My Favorites</a>
		</div>
	</div>
<center>
<table>
	<s:url action="select" var="selectL">
	  	<s:param name="winner.id">${celebrityL.id}</s:param>
	  	<s:param name="loser.id">${celebrityR.id}</s:param>
	</s:url>
	<s:url action="select" var="selectR">
		<s:param name="winner.id">${celebrityR.id}</s:param>
		<s:param name="loser.id">${celebrityL.id}</s:param>
	</s:url>

	<tbody><tr>
		<!-- 
		<td valign="top" class="image"><a href="select.do?winner=${p1.id}&wscore=${p1.score}&loser=${p2.id}&lscore=${p2.score}"><img class="main_img" src="${p1.image}"></a></td>
		<td valign="top" class="image"><a href="select.do?winner=${p2.id}&wscore=${p2.score}&loser=${p1.id}&lscore=${p1.score}"><img class="main_img" src="${p2.image}"></a></td>
		 -->
		<td valign="top" class="image"><a href="${selectL}"><img class="main_img" src="${celebrityL.image}"></a></td>
		<td valign="top" class="image"><a href="${selectR}"><img class="main_img" src="${celebrityR.image}"></a></td>
		
	</tr>
	<tr>
		<td><div id="name">${celebrityL.name}</div></td>
		<td><div id="name">${celebrityR.name}</div></td>
	</tr>
	<tr>
		<td><div id="rank">Won: <s:property value="celebrityL.won" />, Lost: <s:property value="celebrityL.lost" /></div></td>
		<td><div id="rank">Won: <s:property value="celebrityR.won" />, Lost: <s:property value="celebrityR.lost" /></div></td>
	</tr>
</tbody></table>
<div id="vs"><img src="img/vs-icon.png" alt="vs-icon"></div>

<table>
	<tr>
	<c:forEach var="celebrity" items="${topRated}" >
		<td class="top10"><img class="top_img" src="${celebrity.image}"></td>
	</c:forEach>
	</tr>
	<tr>
	<c:forEach var="celebrity" items="${topRated}" >
		<td class="top10"><div class="top_name">${celebrity.name}</div></td>
	</c:forEach>
	</tr>
</table>

<div id="footer">Copyright © 2014 Ethan Zhang All rights reserved.</div>
</center>
</body>
</html>