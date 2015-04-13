<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>

<%
  final String titleText = "Data Server Error";
%>

<!DOCTYPE html>
<html lang="en">

<head>
	<title><%= titleText %></title>
	<meta charset="UTF-8" />
</head>

<body>
	<p><strong>An <em>error</em> has occurred</strong>:</p>                
    <p>${pageContext.exception.message}</p>        
    <p>For further assistance, please contact:
    	<a href="mailto:tech_support@LTERnet.edu?Subject=NIS%20Data%20Portal%20error" target="_top">
        LTER Technical Support</a>. Please copy the error message shown 
        above into your email message, along with any other information 
        that might help us to assist you more promptly.</p>
</body>

</html>