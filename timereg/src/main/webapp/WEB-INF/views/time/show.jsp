<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show hours</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.7.1.js"></script>
<script type="text/javascript">
	function edit(id) {
		comment=$("#comment-"+id);
		editField=$("#edit-"+id);
		comment.hide();
		editField.show();
		editField.focus();
		return true;
	}
	
	function save(id){
		comment=$("#comment-"+id);
		editField=$("#edit-"+id);
		comment.show();
		editField.hide();
		var entry = new Object();
		entry.id=id;
		entry.comment=comment.val();
		$.postJSON("account", entry, function(data) {
	        $("#assignedId").val(data.id);
	        showPopup();
	    });
		return true;
	}
</script>
</head>
<body>
	<table>
		<caption>List of hours registered</caption>
		<tfoot>
			<tr>
				<td><c:if test="${hours.lastLinkedPage > hours.page }">
						<a href="?page=${hours.page+1 }">Next Page</a>
					</c:if></td>
				<td><c:if test="${hours.firstLinkedPage < hours.page }">
						<a href="?page=${hours.page-1 }">Previous Page</a>
					</c:if></td>
			</tr>
		</tfoot>
		<thead>
			<tr>
				<th>ID</th>
				<th>Comment</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${hours.pageList }" var="hour">
				<tr>
					<td>${hour.id }</td>
					<td><div id="comment-${hour.id }">
							${hour.comment }<a onclick="edit(${hour.id });"><img
								src="../icons/pencil.png" alt="Edit"></a>
						</div> <input type="text" value="${hour.comment }" id="edit-${hour.id }"
						style="display: none;" onblur="save(${hour.id})"></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</body>
</html>