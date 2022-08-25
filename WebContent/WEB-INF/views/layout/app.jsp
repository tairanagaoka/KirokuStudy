<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title><c:out value="KirokuStudy" /></title>
<link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <div id="header_menu">

                    <a href="${pageContext.request.contextPath}/index">KirokuStudy</a>


                <div id="header_list">
                    <c:if test="${sessionScope.login_user != null}">
                        <a href="${pageContext.request.contextPath}/IndexGoal">目標一覧</a>
                    </c:if>

                    <c:if test="${sessionScope.login_user != null}">
                        <a href="${pageContext.request.contextPath}/SettingWeeklyGoal">学習期間</a>
                    </c:if>

                    <c:if test="${sessionScope.login_user != null}">
                        <a href="${pageContext.request.contextPath}/NewGoal">学習目標</a>
                    </c:if>

                    <c:if test="${sessionScope.login_user != null}">
                        <a href="${pageContext.request.contextPath}/SettingReport">学習記録</a>
                    </c:if>


                    <c:if test="${sessionScope.login_user != null}">
                        <a href="${pageContext.request.contextPath}/logout">ログアウト</a>
                    </c:if>
                </div>
            </div>

        </div>
        <div id="content">${param.content}</div>
        <div id="footer">KirokuStudy</div>
    </div>
</body>
</html>