<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head >
    <meta http-equiv="Cache-Control" content="no-cache">
    <link href="../../../resourses/css/bootstrap.css" rel="stylesheet" />
    <script src="../../../resourses/js/bootstrap.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</head>

<div class="container" style="margin-top: 30px; size: 1000px ">
    <div class="row">
            <div class=col-md-2><a href=/admin class="btn btn-success" style="width: 165px" ><spring:message code="admin.main"/></a></div>
            <div class=col-md-2><a href=/admin/users class="btn btn-success" style="width: 165px" ><spring:message code="users"/></a></div>
            <div class=col-md-2><a href=/admin/accounts class="btn btn-success" style="width: 165px" ><spring:message code="accounts"/></a></div>
            <div class=col-md-2><a href=/admin/cr type="submit" class="btn btn-success" style="width: 165px" ><spring:message code="creditCards"/></a></div>
            <div class=col-md-2><a href=/admin/payments class="btn btn-success" style="width: 165px" ><spring:message code="payments"/></a></div>
            <div class=col-md-2><a href=/admin/transfers class="btn btn-success" style="width: 165px" ><spring:message code="transfers"/></a></div>
    </div>
</div>

</html>