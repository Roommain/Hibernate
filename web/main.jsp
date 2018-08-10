<%@ page import="java.util.List" %>
<%@ page import="com.ltf.dao.Reg" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
    <style>
        html{
            width: 100%;
            height: 100%;
        }
        body{
            overflow: hidden;
            position: fixed;
            width: 100%;
            height: 100%;
            background: url("bggif.gif");
            background-size: cover;
        }
        table{
            text-align: center;
            margin: 10px auto;
            color: white;
        }
        table tr td a{
            color: white;
        }
        table tr:hover{
            background-color: #cccccc;
        }
    </style>
</head>
<body>
<table border="10" width="80%">
<tr>
    <th>昵称</th>
    <th>账号</th>
    <th>密码</th>
    <th>邮箱</th>
    <th colspan="2">操作</th>
</tr>
<%
    List<Reg> list = (List<Reg>)request.getAttribute("reglist");
%>
<%
    for(Reg reg:list){
        %>
    <tr>
        <td><%=reg.getNickname()%></td>
        <td><%=reg.getTpl()%></td>
        <td><%=reg.getPassword()%></td>
        <td><%=reg.getEmail()%></td>
        <td><a href="change?id=<%=reg.getId()%>">修改</a></td>
        <td><a href="delete?id=<%=reg.getId()%>">删除</a></td>
    </tr>
        <%
    }
%>
</table>
</body>
</html>
