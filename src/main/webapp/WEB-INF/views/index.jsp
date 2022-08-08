<%--
  Created by IntelliJ IDEA.
  User: KuzAS
  Date: 25.07.2022
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="accidents" scope="request" type="java.util.List"/>
<jsp:useBean id="user" scope="request" type="java.lang.Object"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>Accident</title>
</head>
<body>

<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Spring MVC</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <a class="nav-item nav-link">
                <span>
                    <a href="<c:url value='/logout'/>"> <c:out value="${user.username}"/> | Выйти</a>
                </span>
            </a>
        </div>
    </nav>
    <div class="row pt-3">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Правонарушение</th>
                <th scope="col">Тип</th>
                <th scope="col">Статья</th>
                <th scope="col">Описание</th>
                <th scope="col">Адрес</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="accident" items="${accidents}">
                <tr>
                    <td>
                        <c:out value="${accident.id}"/>
                    </td>
                    <td>
                        <c:out value="${accident.name}"/>
                        <span>
                            <a href="<c:url value='/edit?id=${accident.id}'/>">Ред.</a>
                        </span>
                    </td>
                    <td>
                        <c:out value="${accident.type.name}"/>
                    </td>
                    <td>
                        <c:forEach var="rule" items="${accident.rules}">
                            <c:out value="${rule.name}"/>
                        </c:forEach>
                    </td>
                    <td>
                        <c:out value="${accident.text}"/>
                    </td>
                    <td>
                        <c:out value="${accident.address}"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="row pt-3">
        <a class="btn btn-primary" href="<c:url value='/create'/>" role="button">Добавить инцидент</a>
    </div>
</div>
</body>
</html>
