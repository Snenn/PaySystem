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
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <div STYLE="border-radius: 4px; background-color:#fffb7b; width: 690px; height: 30px">
        <div style="font: 'Times New Roman'; font-size: 120%; margin-left: 20px;"><spring:message code="user.replenishAccount"/></div>
<c:if test="${countAccounts==0}">
    <div style="margin-left: 20px; margin-top: 50px">
        У вас нет актуального счета.<br><br>
        Для пополнения счета необходимо завести счет в меню "создать счет".
    </div>
</c:if>
<c:if test="${countAccounts>0}">
        <div style="margin-left: 20px; margin-top: 20px">
            <form action="/user/replenishAccount" method="POST">

                <spring:message code="user.replenishAccount.message1"/>
                <input style="width: 400px" id="sum" name="sum" type="text" pattern="[0-9]+" required title="Разрешены только цифры" placeholder="input sum" class="form-control input-md" >
                <br>
                <spring:message code="user.replenishAccount.message2"/>
                <select class="form-control" style="width: 400px" name="selectAccount">
                    <c:forEach var="account" items="${accounts}">
                        <option>ID ${account.getId()} balance ${account.getBalance()} byn </option>
                    </c:forEach>
                </select>
                <br>
                <spring:message code="user.replenishAccount.message3"/>
                <button class="btn btn-success" type="submit" name="replenishAccount" ><spring:message code="user.replenishAccount"/></button>
                <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}"><jsp:text/></input>
            </form>
        </div>
</c:if>
    </div>


</div>
<div STYLE="border-radius: 4px; background-color: #f1f1f8; width: 230px; height: 500px; margin-left: 10px; float: left; margin-top: 10px">
    <%@ include file="news.jsp" %>
</div>
<%@ include file="/resourses/include/end-html.jsp" %>
