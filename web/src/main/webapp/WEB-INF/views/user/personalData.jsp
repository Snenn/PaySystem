<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div STYLE="border-radius: 4px; background-color:#fffb7b; width: 900px; height: 30px">
    <div style="font: 'Times New Roman'; font-size: 120%; margin-left: 20px;"><spring:message code="user.personal"/></div>
    <div>
        <div style="float: left; width: 50%; margin-top: 20px; margin-left: 20px ">
            <spring:message code="user.surname"/>: ${user.surname}<br><br>
            <spring:message code="user.name"/>: ${user.name}<br><br>
            <spring:message code="user.login"/>: ${user.login}<br><br>
        </div>
        <div style="float: left; width: -10%; margin-top: 20px; margin-left: 10px ">
            <spring:message code="user.totalAccounts"/>: ${countAccounts}<br><br>
            <spring:message code="user.totalCards"/>: ${countCards}<br><br>
            <spring:message code="user.totalBalance"/>: ${sumBalance} byn<br><br>
        </div>

    </div>
</div>
