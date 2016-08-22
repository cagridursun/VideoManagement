<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Video Management Console</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/css/app.css' />" rel="stylesheet">
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body ng-app="myApp" class="ng-cloak">
<div class="generic-container" ng-controller="VideoController as ctrl">

    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Video Management Page </span></div>

    </div>

    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Videos </span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID.</th>
                    <th>Video Name</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="video in ctrl.videos track by $index">
                    <td><a ng-href="vid/still/{{video.id}}" ng-bind="video.id"></a></td>
                    <td><span ng-bind="video.title"></span></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="<c:url value='/js/angular.js' />"></script>
<script src="<c:url value='/js/ui-bootstrap-tpls-0.10.0.js' />"></script>
<script src="<c:url value='/js/app.js' />"></script>
<script src="<c:url value='/js/service/video_service.js' />"></script>
<script src="<c:url value='/js/controller/video_controller.js' />"></script>

</body>
</html>