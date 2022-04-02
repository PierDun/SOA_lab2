<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 31.03.2022
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Add New Dragon</title>
</head>
<body>
<jsp:include page="menu.jsp">
    <jsp:param name="new" value="active" />
</jsp:include>
<div class="dragon-form" align="center">
    <caption>
        <h2>Add New Dragon</h2>
    </caption>
    <div class="error-msg">
    </div>
    <form:form method="post" action="/dragons" modelAttribute="dragon">
        <p>Name:
            <form:input path="name" type="text" name="name" class="form-control"/></p>
        <p>X:
            <form:input path="x" type="text" name="x" class="form-control"/></p>
        <p>Y:
            <form:input path="y" type="text" name="y" class="form-control"/></p>
        <p>Age:
            <form:input path="age" type="text" name="age" class="form-control"/></p>
        <p>Description:
            <form:input path="desription" type="text" name="description" class="form-control"/></p>
        <p>Color:
            <form:select path="color" name="color" class="form-control">
                <option value="RED">RED</option>
                <option value="BLACK">BLACK</option>
                <option value="YELLOW">YELLOW</option>
                <option value="ORANGE">ORANGE</option>
                <option value="WHITE">WHITE</option>
            </form:select></p>
        <p>Type:
            <form:select path="type" name="type" class="form-control">
                <option value="WATER">WATER</option>
                <option value="UNDERGROUND">UNDERGROUND</option>
                <option value="AIR">AIR</option>
                <option value="FIRE">FIRE</option>
            </form:select></p>
        <p>Cave: </p>
        <p>Depth: <form:input path="depth" type="text" name="depth" class="form-control"/></p>
        <input type="submit" value="Add">
    </form:form>
</div>
<script>
    <%@ include file="/WEB-INF/js/dragonForm.js" %>
</script>
</body>
</html>
