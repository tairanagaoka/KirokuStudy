<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <link rel="stylesheet" href="<c:url value='/css/login.css'/>">


        <form method="POST"
            action="${pageContext.request.contextPath}/CreateUser">
            <c:import url="_form.jsp" />
        </form>

    </c:param>
</c:import>