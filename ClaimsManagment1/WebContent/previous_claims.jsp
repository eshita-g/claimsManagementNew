<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
<header  id="p1">
<br>
<p id="a1">Claims Management System 
<br><br>
</p>
</header>
<h4> Processed  Claims </h4>
<h1><b>List of Claim</b></h1>
<table id="t1">
<tr>
  <th>Claim Id</th>
  <th></th>
  <th></th>
  <th></th>
  <th></th>
  <th>Claim Status</th>
 
  
</tr>
<c:forEach items="${prevclaims}" var="list">
<tr>
<td>${list.getClaimid()}</td>
<th></th>
  <th></th>
  <th></th>
  <th></th>
 <th></th>
  <th></th>
  <th></th>
  <th></th>
 <td>${list.getApprove_status()} </td>
 <th></th>
  <th></th>
  <th></th>
  <th></th>
 </tr> 
</c:forEach>
</table>
</div>
</body>
</html>