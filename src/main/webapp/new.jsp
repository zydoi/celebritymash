<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
	<title>Celebrity Mash!</title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
	<s:form action="save" method="post">
		<s:textfield key="celebrity.name" /> 
		<s:textfield key="celebrity.foreignName" /> 
		<s:textfield key="celebrity.image" /> 
		<s:radio key="celebrity.gender" list="genders" />
		<s:submit key="submit" label="新增"/>
	</s:form>
</body>
</html>