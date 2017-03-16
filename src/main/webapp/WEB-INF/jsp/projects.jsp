<%@ include file="header.jsp" %>
<h1>projects page</h1>
<p>lists projects for username: <c:out value="${account.username}"/></p>

<table border="1">
    <tr>
        <th>name</th>
        <th>status</th>
        <th>src. lang.</th>
        <th>target langs.</th>
    </tr>
	<tr>
		<td colspan="4">no projects were found</td>
	</tr>
</table>
<script type="text/javascript">
    $(document).ready(function() {
    	$.ajax({
    	  type: 'GET',
    	  url: '/projects' ,
    	  dataType: 'json',
    	  contentType: "application/json",
    	  success: function (response) {
    	    var table = '';
    	    $.each(response, function(i, item) {
    	      table += '<tr><td>' + item.name;
    	      table += '</td><td>'+ item.status;
    	      table += '</td><td>'+ item.source;
    	      table += '</td><td><ul>';
    	      $.each(item.targets, function(j, target){
    	    	  table += '<li>' + target + '</li>';
    	      });
    	      table += '</ul></td></tr>';
    	    });
    	    $("tr:has(td)").replaceWith(table);
    	  }
    	});
    });
</script>

</body>
</html>