<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:import url="../layout/app.jsp">

    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>


        <c:choose>
            <c:when test="${infos ==null || weekly_goal ==null}">

                <h1 id="first_message">
                    学習目標が設定されていません。学習目標から設定してください。
                </h1>

            </c:when>
            <c:otherwise>
                <c:if test="${weekly_goal !=null}">
                    <p>学習期間</p>
                    <table>
                        <tr>
                            <td>学習開始日</td>
                            <td>学習終了日</td>
                            <td>目標学習時間</td>
                        </tr>

                        <tr>
                            <td><c:out value="${weekly_goal.study_startdate}" /></td>
                            <td><c:out value="${weekly_goal.study_enddate}" /></td>
                            <td><c:out value="${weekly_goal.hours}">時間</c:out></td>
                        </tr>

                    </table>
                </c:if>
                <c:if test="${infos != null}">
                    <p>学習時間</p>
                    <table>
                        <tr>
                            <td>学習中の目標</td>
                            <td>今日の学習時間</td>
                            <td>総学習時間</td>
                        </tr>
                        <c:forEach var="info" items="${infos}">
                            <tr>
                                <td><c:out value="${info.goalTitle}" /></td>
                                <td><c:out value="${info.todayStudyHours}" /></td>
                                <td><c:out value="${info.totalStudyHours}" /></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>