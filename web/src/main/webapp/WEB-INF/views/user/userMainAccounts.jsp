<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib tagdir="/WEB-INF/tags/menu" prefix="menu" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
<e:url value="/j_spring_security_check" var="loginUrl"/>
<head>
    <meta http-equiv="Cache-Control" content="no-cache">
    <title><spring:message code="title.name"/></title>
    <link href="../../../resourses/css/bootstrap.css" rel="stylesheet"/>
    <link href="../../../resourses/css/spa.css" rel="stylesheet"/>
    <script src="../../../resourses/js/bootstrap.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <style>body {
        background: url("../../../resourses/images/homeBackground.jpg")
    }
    </style>
    <link rel="shortcut icon" href="../../../resourses/images/logo.png"/>

</head>

<body>
<script src="../../../resourses/js/react.js"></script>
<script src="../../../resourses/js/react-dom.js"></script>
<script src="../../../resourses/js/browser.min.js"></script>
<script src="../../../resourses/js/EventEmitter.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.2.0/lodash.js"></script>
<script src="../../../resourses/js/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="../../../resourses/js/sweetalert.css">

<div class="container">

    <div class="navbar-header" style=" margin-top: 7px">
        <img src="../../../resourses/images/logo.png" height="40" width="40">
    </div>

    <div class="navbar-header">
        <ul class="nav navbar-nav navbar-right">
            <c:set var="locale" value="${pageContext.response.locale}"/>
            <li><a href="?locale=en" style="color: white"><spring:message code="title.en"/> <img
                    src="../../../resourses/images/eng.png" style="width: 30px;height: 30px"></a></li>
            <li><a href="?locale=ru" style="color: white"><spring:message code="title.ru"/> <img
                    src="../../../resourses/images/rus.png" style="width: 30px;height: 30px"></a></li>
        </ul>
    </div>

    <div class="navbar-collapse collapse">

        <div class="navbar-header navbar-right" role="form">
            <sec:authorize access="isAuthenticated()">
                <a type="submit" style="margin-top: 10px" class="btn btn-default" href=/logout><spring:message
                        code="title.logout"/></a>
            </sec:authorize>
        </div>


    </div>
</div>

<div class="data_personalDate">
    <div class="data_personalDateTop">
        <div class="textInCaption"><spring:message code="user.personal"/></div>
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
</div>

<div class="exchangeRates">
    <div class="exchangeRatesTop">
        <div class="textInCaption"><spring:message code="user.exchangeRates"/></div>
        <iframe style="margin-top: 20px; margin-left: 20px" src="http://www.nbrb.by/publications/wmastersd.asp?
lan=en&datatype=0&fnt=Times&fntsize=16&fntcolor=005c7b
&lnkcolor=fc7f7f&bgcolor=f1f1f8&brdcolor=fdf2e0"
                width=190 height=95 frameborder=0 scrolling=no>
        </iframe>
    </div>
</div>

<div class="menu">
    <div class="menuTop">
        <div class="textInCaption"><spring:message code="user.menu"/></div>
        <div style="margin-top: 30px; margin-left: -10px; font-size: 120%">
            <ul class="menuTree">
                <li about="accounts"><spring:message code="accounts"/>
                    <ul hidden>
                        <li about="showAccounts">show accounts</li>
                        <li about="createAccounts"><spring:message code="user.createAccounts"/></li>
                        <li about="replenishAccount"><spring:message code="user.replenishAccount"/></li>
                    </ul>
                </li>
                <li about="creditCards"><spring:message code="creditCards"/>
                    <ul hidden>
                        <li about="showCards">show cards</li>
                        <li about="createCard"><spring:message code="user.createCard"/></li>
                    </ul>
                </li>
                <li about="payments"><spring:message code="payments"/>
                    <ul hidden>
                        <li about="showPayments">show payment</li>
                        <li about="createPayment"><spring:message code="user.createPayment"/></li>
                    </ul>
                </li>
                <li about="transfers"><spring:message code="transfers"/>
                    <ul hidden>
                        <li about="showTransfers">show transfer</li>
                        <li about="createTransfer"><spring:message code="user.createTransfer"/></li>
                    </ul>
                </li>
            </ul>

        </div>
    </div>
</div>


<%--Here is rendering react action windows--%>
<div id="root"></div>

<div class="news">
    <div class="newsTop">
        <div class="textInCaption"><spring:message code="user.news"/></div>
        <div style="margin-top: 10px">
            <script type="text/javascript" src="https://feed.mikle.com/js/fw-loader.js" data-fw-param="22680/"></script>
        </div>
    </div>
</div>


<footer class="navbar-fixed-bottom row-fluid">
    <div class="container">
        <p>${jsp_error}</p>
        <p><span style="color: darkred; ">${jsp_message} </span></p>
    </div>
</footer>


<script type="text/babel">
    "use strict"
    window.ee = new EventEmitter();

    var tree = document.getElementsByClassName("menuTree");
    var treeLis = tree[0].getElementsByTagName("li");

    /* wrap all textNodes into spans */
    for (var i = 0; i < treeLis.length; i++) {
        var li = treeLis[i];

        var span = document.createElement('span');
        li.insertBefore(span, li.firstChild);
        span.appendChild(span.nextSibling);

    }

    /* catch clicks on whole tree */
    tree[0].onclick = function (event) {
        var target = event.target;
        var action = event.target.parentElement.getAttribute('about').trim();
        window.ee.emit('Action', action);
        if (target.tagName != 'SPAN') {
            return;
        }

        /* now we know the SPAN is clicked */
        var childrenContainer = target.parentNode.getElementsByTagName('ul')[0];
        if (!childrenContainer) return; // no children

        childrenContainer.hidden = !childrenContainer.hidden;
    }

    var Accounts = React.createClass({

        getInitialState: function () {
            return this.state = {
                list: []
            }
        },

        loadData () {
            var self = this;
            var req = new XMLHttpRequest();
            req.responseType = 'json';
            var url = 'http://localhost:8080/accounts'
            req.open('GET', url, true);
            req.send();
            var json;
            req.onreadystatechange = function () {
                //document.write( req.readyState + " - req.readyState");
                //document.write( req.status + " - req.status");

                if (req.readyState === 4 && req.status === 200) {
                    json = req.response;
                    self.setState({list: json});
                }
            }
        },

        componentDidMount() {
            this.loadData();
        },


        render() {
            const items = this.state.list.map((item) => {
                return (<tr key={item.id}>
                    <td>{item.id}</td>
                    <td>{item.balance} </td>
                    <td >{new Date(item.date).toUTCString()}</td>
                </tr>);
            });
            return (
                <div className='centring'>
                    <div>
                        <table className='table table-striped'>
                            <tbody>
                            <tr >
                                <td>id</td>
                                <td>balance</td>
                                <td>date of creation</td>
                            </tr>
                            {items}
                            </tbody>
                        </table>
                    </div>
                </div>
            );
        }


    });



    var CreateAccounts = React.createClass({

        loadData () {
            var self = this;
            var req = new XMLHttpRequest();
            req.responseType = 'json';
            var url = 'http://localhost:8080/createAccount'
            req.open('GET', url, true);
            req.send();
            var json;
            req.onreadystatechange = function () {
                //document.write( req.readyState + " - req.readyState");
                //document.write( req.status + " - req.status");

                if (req.readyState === 4 && req.status === 200) {
                    json = req.response;
                    swal(json);
                }
            }
        },

        onBtnClickHandler: function(e) {
            e.preventDefault();
            //this.state.messages = "";
            this.loadData();
        },

        render: function () {

            return (

            <div className='centring'>
            <spring:message code="user.createAccounts.messages1"/>${countAccounts} <spring:message code="user.createAccounts.messages2"/>

<div>
            <button className='btn btn-success' onClick={this.onBtnClickHandler} id='createAccount' ><spring:message code="user.createAccounts"/></button>
            </div>

</div>


            );
        }
    });

    var ReplenishAccount = React.createClass({


        loadData () {
            var self = this;
            var sum = document.getElementById("sum");
            var aid = document.getElementById("aid");
            var req = new XMLHttpRequest();
            req.responseType = 'json';
            var url = 'http://localhost:8080/replenishAccount?id='+aid.value+'&sum='+sum.value;
            req.open('GET', url, true);
            req.send();
            var json;
            req.onreadystatechange = function () {
                //document.write( req.readyState + " - req.readyState");
                //document.write( req.status + " - req.status");

                if (req.readyState === 4 && req.status === 200) {
                    json = req.response;
                    swal(json);
                }
            }
        },

        onBtnClickHandler: function(e) {
            e.preventDefault();
            this.loadData();
        },

        render: function () {

            return (
<div className='centring'>
            <c:if test="${countAccounts>0}">
                    <div >
                        <form method="GET" >
                            <spring:message code="user.replenishAccount.message1"/>
                            <input id="sum" name="sum" type="text" pattern='[0-9]+' required title="Разрешены только цифры" placeholder="input sum" className='form-control input-md' required=""/>
                            <br/>
                            <spring:message code="user.replenishAccount.message2"/>
                            <select id="aid" className='form-control' name="selectAccount">
                                <c:forEach var="account" items="${accounts}">
                                    <option >ID ${account.getId()} balance ${account.getBalance()} byn </option>
                                </c:forEach>
                            </select>
                            <br/>
                            <spring:message code="user.replenishAccount.message3"/>
                            <br/>
                            <button className='btn btn-success' onClick={this.onBtnClickHandler} id="replenishAccount" ><spring:message code="user.replenishAccount"/></button>
                        </form>
                    </div>
            </c:if>

            <c:if test="${countAccounts==0}">
            <div className='centring'>
                У вас нет актуального счета.<br/><br/>
                Для пополнения счета необходимо завести счет в меню "создать счет".
            </div>
            </c:if>
</div>

            );
        }
    });

    var CreditCards = React.createClass({

        getInitialState: function () {
            return this.state = {
                list: []
            }
        },

        loadData () {
            var self = this;
            var req = new XMLHttpRequest();
            req.responseType = 'json';
            var url = 'http://localhost:8080/cards'
            req.open('GET', url, true);
            req.send();
            var json;
            req.onreadystatechange = function () {
                //document.write( req.readyState + " - req.readyState");
                //document.write( req.status + " - req.status");

                if (req.readyState === 4 && req.status === 200) {
                    json = req.response;
                    self.setState({list: json});
                }
            }
        },

        componentDidMount() {
            this.loadData();
        },


        render() {
            const items = this.state.list.map((item) => {
                return (<tr key={item.id}>
                    <td >{item.id}</td>
                    <td >{item.account.balance}</td>
                    <td >{new Date(item.date).toUTCString()}</td>
                    <td >{item.creditCardStatus}</td>
                </tr>);
            });
            return (
                    <div className='centring'>
                        <div>
                            <table className='table table-striped'>
                                <tbody>
                                <tr>
                                    <td >id</td>
                                    <td>balance</td>
                                    <td>date of creation</td>
                                    <td>status</td>
                                </tr>
                                {items}
                                </tbody>
                            </table>
                        </div>
                    </div>
            );
        }
    });

    var CreateCard = React.createClass({

        loadData () {
            var self = this;
            var aid = document.getElementById("aid");
            var req = new XMLHttpRequest();
            req.responseType = 'json';
            var url = 'http://localhost:8080/createCard?id='+aid.value;
            req.open('GET', url, true);
            req.send();
            var json;
            req.onreadystatechange = function () {
                //document.write( req.readyState + " - req.readyState");
                //document.write( req.status + " - req.status");

                if (req.readyState === 4 && req.status === 200) {
                    json = req.response;
                    swal(json);
                }
            }
        },

        onBtnClickHandler: function(e) {
            e.preventDefault();
            //this.state.messages = "";
            this.loadData();
        },

        render: function () {

            return (

                    <div className='centring'>

                        <c:if test="${countAccounts==0}">
                            <div>
                            У вас нет актуального счета.<br/><br/>
                            Для создания карты необходимо завести счет в меню "создать счет".
                            </div>
                        </c:if>
                        <c:if test="${countAccounts>0}">
                            <div className="centring">
                                <spring:message code="user.createCard.message1"/>

                                <form action="/user/createCard" method="post">
                                    <select id="aid" className='form-control' name="selectCard">
                                        <c:forEach var="account" items="${accounts}">
                                            <option >ID ${account.getId()} balance ${account.getBalance()} byn </option>
                                        </c:forEach>
                                    </select>

                                    <br/>

                                    <spring:message code="user.createCard.message2"/>

                                    <button className='btn btn-success' type="submit" name="createCard" onClick={this.onBtnClickHandler} ><spring:message code="user.createCard"/></button>
                                </form>

                            </div>
                        </c:if>

                    </div>

            );
        }
    });

    var Payments = React.createClass({

        getInitialState: function () {
            return this.state = {
                list: []
            }
        },

        loadData () {
            var self = this;
            var req = new XMLHttpRequest();
            req.responseType = 'json';
            var url = 'http://localhost:8080/payments'
            req.open('GET', url, true);
            req.send();
            var json;
            req.onreadystatechange = function () {
                //document.write( req.readyState + " - req.readyState");
                //document.write( req.status + " - req.status");

                if (req.readyState === 4 && req.status === 200) {
                    json = req.response;
                    self.setState({list: json});
                }
            }
        },

        componentDidMount() {
            this.loadData();
        },


        render() {
            const items = this.state.list.map((item) => {
                return (<tr key={item.id}>
                    <td >{item.id}</td>
                    <td >{item.sum}</td>
                    <td >{new Date(item.data).toUTCString()}</td>
                    <td >{item.creditCardPayment}</td>
                </tr>);
            });
            return (
                <div className='centring'>
                    <div>
                        <table className='table table-striped'>
                            <tbody>
                            <tr>
                                <td >id</td>
                                <td >summa</td>
                                <td >date of creation</td>
                                <td >id card</td>
                            </tr>
                            {items}
                            </tbody>
                        </table>
                    </div>
                </div>
            );
        }
    });

    var CreatePayment = React.createClass({

        loadData () {
            var self = this;
            var aid = document.getElementById("pay_cid");
            var sum = document.getElementById("pay_sum");
            var req = new XMLHttpRequest();
            req.responseType = 'json';
            var url = 'http://localhost:8080/createPayment?id='+aid.value+'&sum='+sum.value;
            req.open('GET', url, true);
            req.send();
            var json;
            req.onreadystatechange = function () {
                //document.write( req.readyState + " - req.readyState");
                //document.write( req.status + " - req.status");

                if (req.readyState === 4 && req.status === 200) {
                    json = req.response;
                    swal(json);
                }
            }
        },

        onBtnClickHandler: function(e) {
            e.preventDefault();
            //this.state.messages = "";
            this.loadData();
        },

        render: function () {

            return (

                    <div className='centring'>
                        <c:if test="${countCards==0}">
                            <div className='centring'>
                                У вас нет карт.<br/><br/>
                                Для создания платежа необходимо завести карту в меню "создать карту".
                            </div>
                        </c:if>
                        <c:if test="${countCards>0}">
                            <div className='centring'>

                                <spring:message code="user.createPayment.message1"/>
                                <form  method="GET">
                                    <select className='form-control'  id="pay_cid" name="selectCard">
                                        <c:forEach  var="card" items="${cards}">
                                            <option >ID ${card.getId()} balance ${card.account.getBalance()} byn
                                                <c:forEach items="${creditCardStatuses}" var="creditCardStatus">
                                                    <c:if test="${creditCardStatus.getId()==card.getCreditCardStatus()}">
                                                        ${creditCardStatus.getStatus()}
                                                    </c:if>
                                                </c:forEach>
                                            </option>
                                        </c:forEach>
                                    </select><br/>
                                    <spring:message code="user.createPayment.message2"/>

                                    <input id="pay_sum" name="sum" type="text" pattern="[0-9]+" required title="Разрешены только цифры" placeholder="input sum" className='form-control input-md' required=""/>
                                    <br/>

                                    <spring:message code="user.createPayment.message3"/>

                                    <button onClick={this.onBtnClickHandler} className='btn btn-success'  name="createPayment" ><spring:message code="user.createPayment"/></button>
                                </form>
                            </div>
                        </c:if>
                    </div>
            );
        }
    });

    var Transfers = React.createClass({

        getInitialState: function () {
            return this.state = {
                list: []
            }
        },

        loadData () {
            var self = this;
            var req = new XMLHttpRequest();
            req.responseType = 'json';
            var url = 'http://localhost:8080/transfers'
            req.open('GET', url, true);
            req.send();
            var json;
            req.onreadystatechange = function () {
                //document.write( req.readyState + " - req.readyState");
                //document.write( req.status + " - req.status");

                if (req.readyState === 4 && req.status === 200) {
                    json = req.response;
                    self.setState({list: json});
                }
            }
        },

        componentDidMount() {
            this.loadData();
        },


        render() {
            const items = this.state.list.map((item) => {
                return (<tr key={item.id}>
                    <td >{item.id}</td>
                    <td >{item.sum}</td>
                    <td >{item.cardSender}</td>
                    <td >{item.cardRecipient}</td>
                    <td >{new Date(item.data).toUTCString()}</td>
                </tr>);
            });
            return (
                <div className='centring'>
                    <div>
                        <table className='table table-striped'>
                            <tbody>
                            <tr>
                                <td >id </td>
                                <td >summa </td>
                                <td >id card sender </td>
                                <td >id card recipient </td>
                                <td >date of creation</td>
                            </tr>
                            {items}
                            </tbody>
                        </table>
                    </div>
                </div>
            );
        }
    });

    var CreateTransfer = React.createClass({

        loadData () {
            var self = this;
            var cid = document.getElementById("cid");
            var sum = document.getElementById("sum");
            var idRecipient = document.getElementById("idRecipient");
            var req = new XMLHttpRequest();
            req.responseType = 'json';
            var url = 'http://localhost:8080/createTransfer?id='+cid.value+'&sum='+sum.value+'&idRecipient='+idRecipient.value;
            req.open('GET', url, true);
            req.send();
            var json;
            req.onreadystatechange = function () {
                //document.write( req.readyState + " - req.readyState");
                //document.write( req.status + " - req.status");

                if (req.readyState === 4 && req.status === 200) {
                    json = req.response;
                    swal(json);
                }
            }
        },

        onBtnClickHandler: function(e) {
            e.preventDefault();
            //this.state.messages = "";
            this.loadData();
        },

        render: function () {


            return (

                    <div className='centring'>
                        <c:if test="${countCards==0}">
                            <div className='centring'>
                                У вас нет карт.<br/><br/>
                                Для создания перевода необходимо завести карту в меню "создать карту".
                            </div>
                        </c:if>
                        <c:if test="${countCards>0}">
                                <div className='centring'>

                                    <spring:message code="user.createTransfer.message1"/>
                                    <form>
                                        <select id="cid" className='form-control' name="selectCard">
                                            <c:forEach var="card" items="${cards}">
                                                <option>ID ${card.getId()} balance ${card.account.getBalance()} byn
                                                <c:forEach items="${creditCardStatuses}" var="creditCardStatus">
                                                    <c:if test="${creditCardStatus.getId()==card.getCreditCardStatus()}">
                                                        ${creditCardStatus.getStatus()}
                                                    </c:if>
                                            </c:forEach>
                                                </option>
                                            </c:forEach>
                                                </select><br/>
                                        <spring:message code="user.createTransfer.message2"/>
                                        <input id="sum" name="sum" type="text" pattern="[0-9]+" required title="Разрешены только цифры" placeholder="input sum" className='form-control input-md' required=""/>
                                        <br/>
                                                <spring:message code="user.createTransfer.message3"/>
                                        <input id="idRecipient" name="idRecipient" type="text" pattern="[0-9]+" required title="Разрешены только цифры" placeholder="input id card Recipient" className='form-control input-md' required=""/>
                                        <br/>

                                        <button className='btn btn-success' onClick={this.onBtnClickHandler} name="createTransfer" ><spring:message code="user.createTransfer"/></button>
                                    </form>
                                </div>
                        </c:if>
                    </div>
            );
        }
    });








    var text = "accounts";

    var App = React.createClass({
        getInitialState: function () {
            return {
                state: text
            };
        },
        componentDidMount: function () {
            var self = this;
            window.ee.addListener('Action', function (item) {

                if (item == "showAccounts") text = "accounts";
                if (item == "createAccounts") text = "createAccounts";
                if (item == "replenishAccount") text = "replenishAccount";
                if (item == "showCards") text = "creditCards";
                if (item == "createCard") text = "createCard";
                if (item == "showPayments") text = "payments";
                if (item == "createPayment") text = "createPayment";
                if (item == "showTransfers") text = "transfers";
                if (item == "createTransfer") text = "createTransfer";
                var newText = text;
                self.setState({state: newText});
            });
        },
        componentWillUnmount: function () {
            window.ee.removeListener('Action');
        },

        render: function () {
            if (text.trim() == "accounts") {
                return (
                        <div className='actionWindow'>
                            <div className='actionWindowTop'>
                                <div className='textInCaption'><spring:message code="accounts"/></div>
                                <div className='top'/>
                            </div>
                            <div className='scroll'>
                                <Accounts />
                            </div>
                        </div>
                )

            }
            if (text.trim() == "createAccounts") {
                return (
                        <div className='actionWindow'>
                            <div className='actionWindowTop'>
                                <div className='textInCaption'><spring:message code="user.createAccounts"/></div>
                                <div className='top'/>
                                <CreateAccounts />
                            </div>
                        </div>
                );
            }
            if (text.trim() == "replenishAccount") {
                return (
                        <div className='actionWindow'>
                            <div className='actionWindowTop'>
                                <div className='textInCaption'><spring:message code="user.replenishAccount"/></div>
                                <div className='top'/>
                                <ReplenishAccount />
                            </div>
                        </div>
                );
            }
            if (text.trim() == "creditCards") {
                return (
                        <div className='actionWindow'>
                            <div className='actionWindowTop'>
                                <div className='textInCaption'><spring:message code="creditCards"/></div>
                                <div className='top'/>
                            </div>
                            <div className='scroll'>
                            <CreditCards />
                            </div>
                        </div>
                );
            }
            if (text.trim() == "createCard") {
                return (
                        <div className='actionWindow'>
                            <div className='actionWindowTop'>
                                <div className='textInCaption'><spring:message code="user.createCard"/></div>
                                <div className='top'/>
                                <CreateCard />
                            </div>
                        </div>
                );
            }
            if (text.trim() == "payments") {
                return (
                        <div className='actionWindow'>
                            <div className='actionWindowTop'>
                                <div className='textInCaption'><spring:message code="payments"/></div>
                                <div className='top'/>
                            </div>
                            <div className='scroll'>
                                <Payments />
                            </div>
                        </div>
                );
            }
            if (text.trim() == "createPayment") {
                return (
                        <div className='actionWindow'>
                            <div className='actionWindowTop'>
                                <div className='textInCaption'><spring:message code="user.createPayment"/></div>
                                <div className='top'/>
                                <CreatePayment />
                            </div>
                        </div>
                );
            }
            if (text.trim() == "transfers") {
                return (
                        <div className='actionWindow'>
                            <div className='actionWindowTop'>
                                <div className='textInCaption'><spring:message code="transfers"/></div>
                                <div className='top'/>
                            </div>
                            <div className='scroll'>
                                <Transfers />
                            </div>
                        </div>
                );
            }
            if (text.trim() == "createTransfer") {
                return (
                        <div className='actionWindow'>
                            <div className='actionWindowTop'>
                                <div className='textInCaption'><spring:message code="user.createTransfer"/></div>
                                <div className='top'/>
                                <CreateTransfer />
                            </div>
                        </div>
                );
            }


        }

    });
    ReactDOM.render(
            <App />
        ,
        document.getElementById('root')
    );


</script>




</body>