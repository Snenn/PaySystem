<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles"   uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"     uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="e"       uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ include file="/resourses/include/begin-html.jsp" %>
<e:url value="/j_spring_security_check" var="loginUrl" />

<div style="width: 250px; height: 400px; float: left;     background-color: rgba(255,255,255,0.9); margin-left: 150px; margin-top: 150px; border-radius: 5px">

    <div style="margin-left: 50px; width: 200px">
        <br><div style="color: blue"><img style="width: 40px; height: 40px;" src="../../resourses/images/logo.png">&nbsp;<spring:message code="title.name"/></div>
        <form class="form-group"   method="post" action="${loginUrl}">
            <br>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input id="j_username" name="j_username" type="text" placeholder="<spring:message code="title.login"/>" class="form-control input-md" style="width: 150px"><br>
            <input id="j_password" name="j_password" type="password" placeholder="<spring:message code="title.password"/>" class="form-control input-md" style="width: 150px"><br>
            <button type="submit" class="btn btn-success" style="width: 100px"><spring:message code="title.signIn"/></button><br>
        </form>
    </div>
    <button style="margin-left: 50px; width: auto" type="submit" class="btn btn-default" href=/reg><spring:message code="title.signUp"/></button><br>
    <button style="margin-left: 50px; margin-top: 10px; width: auto" type="submit" class="btn btn-default" href=/login><spring:message code="return"/></button>

</div>

<div style="width: 600px; height: 200px;     background-color: rgba(255, 255, 255, 0.6); margin-left: 600px; margin-top: 200px; border-radius: 5px">
    <div style=" margin-left: 15px;margin-right: 15px"><p style="text-align: center; font: 'Times New Roman'; font-size: large" ><br><spring:message code="title.welcome"/></p></div>
</div>


<%@ include file="/resourses/include/end-html.jsp" %>
