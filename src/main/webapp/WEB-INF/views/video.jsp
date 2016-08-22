<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



Video Id :${video.id}
<br>
Video Title : ${video.title}
<br>
Video Description : ${video.description}
<br>
Video Duration Time (sec) : ${(video.length - (video.length %100))/1000}
<br>
<p>
<c:choose>
    <c:when test="${empty err}">
        <form action="" method="post">
            <input type="number" name="timeStamp" min="0" max="${(video.length - (video.length %100))/1000}" value="0.1" step="0.1">
            <input type="hidden" name="downloadedVideoUrl" value="${downloadedUrl}">
            <input type="submit" value="Create Still Image">
        </form>
    </c:when>
    <c:otherwise>
        ${err}
        <br>
        <a href="../">Back</a>
    </c:otherwise>
</c:choose>
</p>

