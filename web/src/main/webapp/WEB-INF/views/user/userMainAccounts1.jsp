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
                    <ul>
                        <li about="createAccounts"><spring:message code="user.createAccounts"/></li>
                        <li about="replenishAccount"><spring:message code="user.replenishAccount"/></li>
                    </ul>
                </li>
                <li about="creditCards"><spring:message code="creditCards"/>
                    <ul>
                        <li about="createCard"><spring:message code="user.createCard"/></li>
                    </ul>
                </li>
                <li about="payments"><spring:message code="payments"/>
                    <ul>
                        <li about="createPayment"><spring:message code="user.createPayment"/></li>
                    </ul>
                </li>
                <li about="transfers"><spring:message code="transfers"/>
                    <ul>
                        <li about="createTransfer"><spring:message code="user.createTransfer"/></li>
                    </ul>
                </li>
            </ul>

        </div>
    </div>
</div>


<div STYLE="border-radius: 4px; background-color: #f1f1f8; width: 690px; height: 500px; margin-left: 10px; float: left; margin-top: 10px">
    <div STYLE="border-radius: 4px; background-color:#fffb7b; width: 690px; height: 30px">
        <div class="textInCaption"><spring:message code="accounts"/></div>

        <div style="margin-top: 20px">

            <div style="float: left; margin-left: 10%"><spring:message code="IDAccount"/></div>
            <div style="float: left; margin-left: 25%"><spring:message code="balance"/></div>

            <br>
            <c:forEach var="account" items="${accounts}">
                <div class="row">
                    <div class=col-md-4 style="text-align: center">${account.getId()}</div>
                    <div class=col-md-4 style="text-align: center">${account.getBalance()}</div>
                </div>
                <br>
            </c:forEach>
            <%--<div style="margin-top: 400px; margin-left: 20px">--%>
            <%--<t:paginator step="8" count="6" urlprefix="?&startNumber="></t:paginator>--%>
            <%--</div>--%>
            <div id="root"></div>


        </div>

    </div>
</div>


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

        render: function () {
            var accounts =
                this.props.data;
            alert("Accounts: " + accounts);

            return (
                    <div>
                        {accounts}
                    </div>
            )

        }
    });

    var CreateAccounts = React.createClass({

        render: function () {

            return (
                    <div >
                        <strong> createAccounts </strong>
                    </div>
            );
        }
    });

    var ReplenishAccount = React.createClass({

        render: function () {

            return (
                    <div >
                        <strong> replenishAccount </strong>
                    </div>
            );
        }
    });

    var CreditCards = React.createClass({

        render: function () {

            return (
                    <div >
                        <strong> creditCards </strong>
                    </div>
            );
        }
    });

    var CreateCard = React.createClass({

        render: function () {

            return (
                    <div >
                        <strong> createCard </strong>
                    </div>
            );
        }
    });

    var Payments = React.createClass({

        render: function () {

            return (
                    <div >
                        <strong> payments </strong>
                    </div>
            );
        }
    });

    var CreatePayment = React.createClass({

        render: function () {

            return (
                    <div >
                        <strong> createPayment </strong>
                    </div>
            );
        }
    });

    var Transfers = React.createClass({

        render: function () {

            return (
                    <div >
                        <strong> transfers </strong>
                    </div>
            );
        }
    });

    var CreateTransfer = React.createClass({

        render: function () {

            return (
                    <div >
                        <strong> createTransfer </strong>
                    </div>
            );
        }
    });





    // Write render main window


    var text = "accounts";
    var json = null;

    var App = React.createClass({
        getInitialState: function () {
            return {
                state: text
            };
        },
        componentDidMount: function () {
            var self = this;
            window.ee.addListener('Action', function (item) {

                if (item == "accounts") {
                    text = "accounts";
                    (function f() {
                        var req = new XMLHttpRequest();
                        req.responseType = 'json';
                        var url = 'http://localhost:8080/rest'
                        req.open('GET', url, true);
                        req.send();
                        req.onreadystatechange = function () {
                            //document.write( req.readyState + " - req.readyState");
                            //document.write( req.status + " - req.status");

                            if (req.readyState === 4 && req.status === 200) {
                                json = req.response;
                                alert("res" + json);
                            }
                        }

                    })();

                }
                ;


//                if (item == "createAccounts") text = "createAccounts";
//                if (item == "replenishAccount") text = "replenishAccount";
//                if (item == "creditCards") text = "creditCards";
//                if (item == "createCard") text = "createCard";
//                if (item == "payments") text = "payments";
//                if (item == "createPayment") text = "createPayment";
//                if (item == "transfers") text = "transfers";
//                if (item == "createTransfer") text = "createTransfer";
                var newText = text;
                self.setState({state: newText});
            });
        },
        componentWillUnmount: function () {
            window.ee.removeListener('Action');
        },

        render: function () {
            if (text.trim() == "accounts") {
                alert(json);
                return (
                        <Accounts data={json}/>
                )

            }
            if (text.trim() == "createAccounts") {
                return (
                        <CreateAccounts />
                );
            }
            if (text.trim() == "replenishAccount") {
                return (
                        <ReplenishAccount />
                );
            }
            if (text.trim() == "creditCards") {
                return (
                        <CreditCards />
                );
            }
            if (text.trim() == "createCard") {
                return (
                        <CreateCard />
                );
            }
            if (text.trim() == "payments") {
                return (
                        <Payments />
                );
            }
            if (text.trim() == "createPayment") {
                return (
                        <CreatePayment />
                );
            }
            if (text.trim() == "transfers") {
                return (
                        <Transfers />
                );
            }
            if (text.trim() == "createTransfer") {
                return (
                        <CreateTransfer />
                );
            }


        }

    });
    ReactDOM.render(
            <App />,
        document.getElementById('root')
    );


</script>
</body>