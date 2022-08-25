<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:import url="../layout/app.jsp">
    <c:param name="content">

        <link rel="stylesheet" href="<c:url value='/css/login.css'/>">


        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <form method="POST" action="${pageContext.request.contextPath}/login">
            <c:import url="login_form.jsp" />
        </form>

    </c:param>
</c:import>