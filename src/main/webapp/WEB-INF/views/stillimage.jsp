<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cagri.dursun
  Date: 18.8.2016
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${empty err}">
        <img src="<c:url value='/images/${stillImagePath}' />" />
    </c:when>
    <c:otherwise>
        ${err}
    </c:otherwise>
</c:choose>
<br>
<a href="./">Back</a>