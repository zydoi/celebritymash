<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Error Page</title>
</head>
<body>
<h4>The CelebrityMash has malfunctioned.</h4>
 
<p>  Please contact technical support with the following information:</p> 
 
<h4>Exception Name: <s:property value="exception" /> </h4>
 
<h4>Exception Details: <s:property value="exceptionStack" /></h4> 
</body>
</html>