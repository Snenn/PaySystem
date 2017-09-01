<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib tagdir="/WEB-INF/tags/menu" prefix="menu" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<script src="http://code.jquery.com/jquery-1.10.2.js"
        type="text/javascript"></script>
<script src="js/app-ajax.js" type="text/javascript"></script>
<e:url value="/j_spring_security_check" var="loginUrl" />
<head >
    <meta http-equiv="Cache-Control" content="no-cache">
    <title><spring:message code="title.name"/></title>
    <link href="/resourses/css/bootstrap.css" rel="stylesheet" />
    <script src="/resourses/js/bootstrap.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <style>body { background:url("../../resourses/images/homeBackground.jpg")}
    </style>
    <link rel="shortcut icon" href="/resourses/images/logo.png"/>

</head>

<body>

<div class="container">

        <div class="navbar-header" style=" margin-top: 7px" >
                    <img src="../../resourses/images/logo.png" height="40" width="40" >
        </div>
        
        <div class="navbar-header">
            <ul class="nav navbar-nav navbar-right">
                <c:set var="locale" value="${pageContext.response.locale}"/>
                    <li><a href="?locale=en" style="color: white"><spring:message code="title.en"/>  <img src="../../resourses/images/eng.png" style="width: 30px;height: 30px"></a></li>
                    <li><a href="?locale=ru" style="color: white"><spring:message code="title.ru"/>  <img src="../../resourses/images/rus.png" style="width: 30px;height: 30px"></a></li>
            </ul>
        </div>
        
    <div class="navbar-collapse collapse">

        <div class="navbar-header navbar-right" role="form">
            <sec:authorize access="isAuthenticated()">
                <a type="submit" style="margin-top: 10px" class="btn btn-default" href=/logout><spring:message code="title.logout"/></a>
            </sec:authorize>
        </div>



    </div>
</div>


</div>
</body>