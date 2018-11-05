<%--
  Created by IntelliJ IDEA.
  User: keen
  Date: 2018/10/23
  Time: 23:43
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String context = request.getContextPath();
%>
<html>
<head>
    <title>test</title>
    <script src="${context}/js/jquery-1.2.6.min.js"></script>
</head>
<body>
Test
<input type="button" id="btn" value="Button按钮"/>
<script type="application/javascript">
    $(function () {
        $("#btn").click(function () {
            $.ajax({
                url: 'http://localhost:8081/rest/services/itemCat/getItemCat',
                type: 'GET',
                success: function (resp) {
                    console.log(resp)
                },
                fail: function (err) {
                    console.log(err)
                }
            })
        })
    });
</script>
</body>
</html>
