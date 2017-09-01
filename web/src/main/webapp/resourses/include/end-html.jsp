<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<footer class="navbar-fixed-bottom row-fluid">
  <div class="container">
    <p>${jsp_error}</p>
    <p><span style="color: darkred; ">${jsp_message}  </span></p>
  </div>
</footer>

<!-- /container -->