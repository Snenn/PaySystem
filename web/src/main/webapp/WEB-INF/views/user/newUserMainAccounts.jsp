<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/resourses/include/begin-html.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags/menu" prefix="menu" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<div STYLE="border-radius: 4px; background-color: #f1f1f8; width: 900px; height: 150px; margin-left: 200px; float: left">
    <%@ include file="personalData.jsp" %>
</div>
<div STYLE="border-radius: 4px; background-color: #f1f1f8; width: 230px; height: 150px; margin-left: 1110px">
    <%@ include file="exchangeRates.jsp" %>
</div>
<div STYLE="border-radius: 4px; background-color: #f1f1f8; width: 200px; height: 500px; margin-left: 200px; float: left; margin-top: 10px">
        <%@ include file="menu.jsp" %>
</div>
<div STYLE="border-radius: 4px; background-color: #f1f1f8; width: 690px; height: 500px; margin-left: 10px; float: left; margin-top: 10px">
    <div STYLE="border-radius: 4px; background-color:#fffb7b; width: 690px; height: 30px">
        <div style="font: 'Times New Roman'; font-size: 120%; margin-left: 20px;"><spring:message code="accounts"/></div>

        <div style="margin-top: 20px" >

            <div style="float: left; margin-left: 10%"><spring:message code="IDAccount"/></div>
            <div style="float: left; margin-left: 25%"><spring:message code="balance"/></div>

            <br>
            <c:forEach var="account" items="${accounts}" >
                <div class="row">
                    <div class=col-md-4 style="text-align: center">${account.getId()}</div>
                    <div class=col-md-4 style="text-align: center">${account.getBalance()}</div>
                </div>
                <br>
            </c:forEach>
            </b>
            <div style="margin-top: 400px; margin-left: 20px">
                <t:paginator step="8" count="6" urlprefix="?&startNumber="></t:paginator>
            </div>

        </div>

    </div>
</div>
<div STYLE="border-radius: 4px; background-color: #f1f1f8; width: 230px; height: 500px; margin-left: 10px; float: left; margin-top: 10px">
    <%@ include file="news.jsp" %>
</div>
<%@ include file="/resourses/include/end-html.jsp" %>
