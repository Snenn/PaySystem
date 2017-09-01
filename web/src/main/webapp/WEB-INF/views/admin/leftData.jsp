<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <div STYLE="border-radius: 4px; background-color: f1f1f8; width: 360px; height: 230px">
        <br><p style="text-align: center; margin-top: 20px">Личные данные администратора</p><br>
        <p style="margin-left: 20px">Фамилия:   ${user.surname}</p>
        <p style="margin-left: 20px">Имя:   ${user.name}</p>
        <p style="margin-left: 20px">Логин: ${user.login}</p>
        <p style="margin-left: 20px">Пароль:    ${user.password}</p>

    </div>

    <div STYLE="border-radius: 4px; background-color: f1f1f8; margin-top: 30px; width: 360px; height: 270px">
        <br><p style="text-align: center; margin-top: 20px">Общие данные системы платежей</p><br>
        <p style="margin-left: 20px">Общее число клиентов: ${countUsers}  </p>
        <p style="margin-left: 20px">Общее число счетов:   ${countAccounts} </p>
        <p style="margin-left: 20px">Общее число кредитных карт:  ${countCards}  </p>
        <p style="margin-left: 20px">Общее число платежей:   ${countPayments} </p>
        <p style="margin-left: 20px">Общее число переводов:  ${countTransfers}  </p>
        <p style="margin-left: 20px">Всего денег на всех счетах:   ${sumBalance} </p>
    </div>
</head>
<body>

</body>
</html>
