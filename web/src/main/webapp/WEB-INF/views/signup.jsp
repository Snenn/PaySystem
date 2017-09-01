<%@ include file="/resourses/include/begin-html.jsp" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<s:form id="registrationForm" class="form-horizontal" action="reg/signUp" modelAttribute="userForm" method="post">
<fieldset>

    <div style="width: 500px; height: 400px;     background-color: rgba(255, 255, 255, 0.6); margin-left: 500px; margin-top: 100px; border-radius: 5px">

        <div align="center" ><br>Регистрация пользователя</div>

        <div class="form-group" style="margin-left: 50px; margin-top: 20px; width: 400px">
            <input id="surname" name="surname" type="text" placeholder="Введите фамилию" class="form-control input-md" required=""><br>
            <input id="name" name="name" type="text" placeholder="Введите имя" class="form-control input-md" required=""><br>
            <input id="login" name="login" type="text" placeholder="Введите логин" class="form-control input-md" required=""><br>
            <input id="password" name="password" type="text" placeholder="Введите пароль" class="form-control input-md" required=""><br>
            <button id="OKreg" name="OKreg" class="btn btn-success">Зарегестрироваться</button>
        </div>


    </div>

</fieldset>
</s:form>

<%@ include file="/resourses/include/end-html.jsp" %>


