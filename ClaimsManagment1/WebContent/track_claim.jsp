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
<h3> Track Claims </h3>
<h1><b>List of Claim</b></h1>
<table id="t1">
<tr>
  <th>Claim Id</th>
  <th></th>
  <th></th>
  <th>Member Id</th>
  <th></th>
  <th></th>
  <th>Claim_Status</th>
</tr>
<c:forEach items="${tclaims}" var="list">
<tr>
<td>${list.claimid}</td>
<th></th>
  <th></th>
  <th></th>
  <th></th>
<td>${list.memid}</td>
 <th></th>
  <th></th>
  <th></th>
  <th></th>
 <td>${list.approve_status} </td>
 <th></th>
  <th></th>
  <th></th>
  <th></th>
  <td><a href="/Claim-2-master_ClaimsManagment1/CloseClaim?id=${list.claimid}">Close Claim </a> </td>
  <td><a href="/Claim-2-master_ClaimsManagment1/RaiseClaim?memid=${list.memid}">Resubmit Claim</a> </td>
 </tr> 
</c:forEach>
</table>
</div>  
  
 
</body>
</html>