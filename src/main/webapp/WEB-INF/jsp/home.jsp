<%@ include file="header.jsp" %>
<body>
	<h1>setup page</h1>
	<p>account can be configured here</p>
	<f:form method="POST" action="/store" modelAttribute="account">
		<div>
			<f:label path="username">username</f:label>
			<f:input path="username"/>
		</div>
		<div>
			<f:label path="password">password</f:label>
			<f:password path="password"/>
		</div>
		<div>
            <input type="submit" value="store"/>
        </div>
	</f:form>
</body>

</html>