<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<style>
		<%@include file="../assets/css/enLang.css"%>
	    <%@include file="../assets/css/body.css"%>
	    <%@include file="../assets/css/button.css"%>
	    <%@include file="../assets/css/input.css"%>	    
	    <%@include file="../assets/css/table.css"%>
	    <%@include file="../assets/css/link.css"%>
	    <%@include file="../assets/css/icon.css"%>
	</style>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<title>Teams</title>
</head>
<body>
	<div id = "upperline">
		<c:choose>
			<c:when test="${sessionScope.user == null}">
				<c:import url="..\WEB-INF\jspf\authorize_en_EN.jsp" />
			</c:when>
			<c:otherwise>
				<c:import url="..\WEB-INF\jspf\upper_en_EN.jsp" />
			</c:otherwise>
		</c:choose>
		<form action = "Controller" method = "get">
		  	<input type = "hidden" name = 'page' value = "path.teams"></input>
		  	<c:import url="..\WEB-INF\jspf\header.jsp" />
		</form>
  	</div>
	<c:import url="..\WEB-INF\jspf\sidenav_en_EN.jsp" />
	<div class = "content">
		<c:if test="${sessionScope.user.role == 'Administrator'}">
		 	<form action="Controller" method="get">
			  	<input type="hidden" name="command" value="add">
			  	<input type="hidden" name="type" value="Team">
			  	<button type="submit">Add new team<i class="material-icons">add</i></button>
			</form>
		</c:if>
		<div class = "scroll">
			<table cellspacing="0">
		  	<tr>
		  		<th>Sport</th>
				<th>Team name</th>
				<th>Wins quantity</th>
				<th>Loses quantity</th>
			</tr>
		  	<c:forEach var="elem" items="${list}" varStatus="status">
				<tr>
					<td><c:out value="${ elem.sport }" /></td>
					<td><c:out value="${ elem.name }" /></td>
					<td><c:out value="${ elem.wins}"/></td>
					<td><c:out value="${ elem.loses}"/></td>
				</tr>
			</c:forEach>
		  </table>
		</div>
    </div>
</body>
</html>