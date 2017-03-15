<%@ include file="header.jsp" %>
<h1>projects page</h1>
<p>lists projects for username: <c:out value="${account.username}"/></p>

<table>
    <tr>
        <th>name</th>
        <th>status</th>
        <th>src. lang.</th>
        <th>target langs.</th>
    </tr>
	<c:forEach items="${projects}" var="project">
    <tr>
        <td>
            <c:out value="${project.name}" />
        </td>
        <td>
            <c:out value="${project.status}" />
        </td>
        <td>
            <c:out value="${project.source}" />
        </td>
        <td>
        	<c:forEach items="${project.targets}" var="target" varStatus="status">
	            <c:out value="${target}" />
	            <c:if test="${!status.last}">, </c:if>
			</c:forEach>        
        </td>
    </tr>
    </c:forEach>
</table>