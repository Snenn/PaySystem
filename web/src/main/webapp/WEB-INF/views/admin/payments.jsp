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
                    <div class=col-md-3 style="text-align: center">ID Payment</div>
                    <div class=col-md-3 style="text-align: center">Sum</div>
                    <div class=col-md-3 style="text-align: center">ID Card</div>
                    <div class=col-md-3 style="text-align: center">Data</div>

                </b>
            </div>
                <br>
                <c:forEach var="payment" items="${payments}" >
                    <div class="row">
                        <div class=col-md-3 style="text-align: center">${payment.getId()}</div>
                        <div class=col-md-3 style="text-align: center">${payment.getSum()}</div>
                        <div class=col-md-3 style="text-align: center">${payment.creditCard.getId()}</div>
                        <div class=col-md-3 style="text-align: center">${payment.getData()}</div>

                    </div>
                    <br>
                </c:forEach>
                <t:paginator step="8" count="${adCount}" urlprefix="?&startNumber="></t:paginator>

            </div>

        </div>

    </div>

    <%@ include file="../../../WEB-INF/views/admin/leftData.jsp" %>





</div>
<br>
<%@ include file="../../../resourses/include/end-html.jsp" %>
</html>