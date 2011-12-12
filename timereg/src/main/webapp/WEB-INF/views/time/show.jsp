<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show hours</title>
</head>
<body>
	<table>
		<caption>List of hours registered</caption>
		<tfoot>
			<tr>
				<td><c:if test="${hours.lastLinkedPage > hours.page }">
						<a href="?page=${hours.page+1 }">Next Page</a>
					</c:if></td><td><c:if test="${hours.firstLinkedPage < hours.page }">
						<a href="?page=${hours.page-1 }">Previous Page</a>
					</c:if></td>
			</tr>
		</tfoot>
		<tbody>
			<c:forEach items="${hours.pageList }" var="hour">
				<tr>
					<td>${hour.id }</td>
					<td>${hour.comment }</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</body>
</html>