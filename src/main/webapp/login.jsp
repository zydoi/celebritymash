<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Celebrity Mash!</title>
</head>
<body>
	<p>Login:</p>
		<s:form action="login">
		<s:textfield name="userName" label="Name:" />
		<s:password name="password" label="Password:"/>
		<s:submit value="Submit" />
	</s:form>
</body>
</html>