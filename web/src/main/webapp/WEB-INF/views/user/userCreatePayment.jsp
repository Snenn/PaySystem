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
        <div style="font: 'Times New Roman'; font-size: 120%; margin-left: 20px;"><spring:message code="user.createPayment"/></div>
<c:if test="${countCards==0}">
    <div style="margin-left: 20px; margin-top: 50px">
        У вас нет карт.<br><br>
        Для создания платежа необходимо завести карту в меню "создать карту".
    </div>
</c:if>
<c:if test="${countCards>0}">
    <div style="margin-left: 20px; margin-top: 20px">

        <spring:message code="user.createPayment.message1"/>
        <form action="/user/createPayment" method="post">
            <select class="form-control" style="width: 400px" name="selectCard">
                <c:forEach var="card" items="${cards}">
                    <option>ID ${card.getId()} balance ${card.account.getBalance()} byn
                        <c:forEach items="${creditCardStatuses}" var="creditCardStatus">
                            <c:if test="${creditCardStatus.getId()==card.getCreditCardStatus()}">
                                ${creditCardStatus.getStatus()}
                            </c:if>
                        </c:forEach>
                    </option>
                </c:forEach>
            </select><br>
            <spring:message code="user.createPayment.message2"/>

            <input style="width: 400px" id="sum" name="sum" type="text" pattern="[0-9]+" required title="Разрешены только цифры" placeholder="input sum" class="form-control input-md" required="">
            <br>

            <spring:message code="user.createPayment.message3"/>

            <button class="btn btn-success" type="submit" name="createPayment" ><spring:message code="user.createPayment"/></button>
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

