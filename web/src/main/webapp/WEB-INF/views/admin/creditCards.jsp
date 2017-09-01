<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../../../resourses/include/begin-html.jsp" %>
<%@ include file="../../../WEB-INF/views/admin/admin.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container" style="margin-top: 30px">

    <div STYLE="border-radius: 4px; background-color: #f1f1f8; width: 750px;  height: 530px; float: right">

<div>

    <div class="container-fluid">

        <br><div class="row" style="margin-top: 20px">
            <b>
                <div class=col-md-4 style="text-align: center">ID CreditCard</div>
                <div class=col-md-4 style="text-align: center">ID ACCOUNT</div>
                <div class=col-md-4 style="text-align: center">STATUS CreditCard</div>
            </b>
        </div>
        <br>
        <c:forEach var="creditCard" items="${creditCards}" >
            <div class="row">
                    <div class=col-md-4 style="text-align: center">${creditCard.getId()}</div>
                    <div class=col-md-4 style="text-align: center">${creditCard.getAccount().getId()}</div>
                    <div class=col-md-4 style="text-align: center">
                        <c:forEach items="${creditCardStatuses}" var="creditCardStatus">
                            <c:if test="${creditCardStatus.getId()==creditCard.getCreditCardStatus()}">
                                ${creditCardStatus.getStatus()}
                            </c:if>
                        </c:forEach>

                    </div>
            </div>
            <br>
        </c:forEach>
        <t:paginator step="8" count="${adCount}" urlprefix="?&startNumber="></t:paginator>
        <div style="text-align: center;">
            <form action="/admin/cr" method="POST"><div>
                <label for="idCard"></label>
                <input style="text-align: center" id="idCard"  pattern="[0-9]+" required title="Разрешены только цифры" placeholder="Enter Credit Card ID" name="idCard" type="text" required=""></div>
                <br>
            <jsp:useBean id="startIndex" scope="request" type="java.lang.Integer"/>
            <input type="hidden" id="startIndex" name="startIndex" value="${startIndex}"/>
            <button class="btn btn-danger" type="submit" name="block" >Block Credit Card</button>    <button class="btn btn-success" type="submit" name="unlock" >Unlock Credit Card</button>
            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}"><jsp:text/></input>
        </form></div>
    </div>

</div>

    </div>

    <%@ include file="../../../WEB-INF/views/admin/leftData.jsp" %>





</div>
<br>
<%@ include file="../../../resourses/include/end-html.jsp" %>
</html>