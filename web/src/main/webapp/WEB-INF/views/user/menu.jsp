<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<div STYLE="border-radius: 4px; background-color:#fffb7b; width: 200px; height: 30px">
<div style="font: 'Times New Roman'; font-size: 120%; margin-left: 20px;"><spring:message code="user.menu"/></div>
<div style="margin-top: 30px; margin-left: 20px; font-size: 120%">
    <div style="border-bottom: solid #314f80; width: 170px"> <a href="/user"><spring:message code="accounts"/></a></div>
        <a class="menu-downLevel" href="/user/createAccount"><spring:message code="user.createAccounts"/></a><br>
        <a class="menu-downLevel" href="/user/replenishAccount"><spring:message code="user.replenishAccount"/></a><br><br>
    <div style="border-bottom: solid #314f80; width: 170px"><a href="/user/cards" ><spring:message code="creditCards"/></a></div>
        <a class="menu-downLevel" href="/user/createCard"><spring:message code="user.createCard"/></a><br><br>
    <div style="border-bottom: solid #314f80; width: 170px"><a href="/user/payments" ><spring:message code="payments"/></a></div>
        <a class="menu-downLevel" href="/user/createPayment"><spring:message code="user.createPayment"/></a><br><br>
    <div style="border-bottom: solid #314f80; width: 170px"><a href="/user/transfers" ><spring:message code="transfers"/></a></div>
        <a class="menu-downLevel" href="/user/createTransfer"><spring:message code="user.createTransfer"/></a><br><br>

</div>
</div>