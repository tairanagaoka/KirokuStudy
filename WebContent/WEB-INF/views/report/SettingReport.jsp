<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../layout/app.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/report.css'/>">
    </c:param>
    <c:param name="content">
        <div id="report">
            <form method="POST"
                action="${pageContext.request.contextPath}/SettingReport">
                <c:if test="${illegal_operation != null || errors != null }">
                    <div class="flush_error">
                        <c:out value="${illegal_operation}"></c:out>
                        <c:if test="${errors != null }">
                            入力内容にエラーがあります。<br />
                            <c:forEach var="error" items="${errors}">
                                ・<c:out value="${error}" />
                                <br />
                            </c:forEach>
                        </c:if>
                    </div>
                </c:if>

                <div id="report_item">
                    <label for="goal">目標</label><br /> <select id="goal" name="goal">
                        <c:forEach var="goal" items="${goals}" varStatus="status">
                            <option value="${goal.id}"
                                <c:if test="${goal.id == report.goal.id}">selected</c:if>>
                                <c:out value="${goal.title}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div id="report_item">

                    <fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd"
                        var="reportDay" type="date" />
                    <label for="reportDate">日付</label><br /> <input type="date"
                        id="reportDate" name="reportDate"
                        value="<fmt:formatDate value='${reportDay}' pattern='yyyy-MM-dd' />" />
                    <br /> <br />
                </div>

                <div id="report_item">

                    <label for="hours">学習時間</label><br /> <input type="number"
                        name="hours" id="hours" value="${report.hours}" /> <br /> <br />
                </div>
                <input type="hidden" name="_token" value="${_token}" /> <input
                    type="hidden" name="weeklyGoalId" value="${report.weekly_goal.id}" />

                <div id="report_button">
                    <button type="submit"
                        <c:if test="${illegal_operation != null}">disabled</c:if>>今日の学習を記録する。</button>
                </div>
            </form>
            <p id="redirect">
                <a href="${pageContext.request.contextPath}/index">TOPへ戻る</a>
            </p>
        </div>
    </c:param>
</c:import>
