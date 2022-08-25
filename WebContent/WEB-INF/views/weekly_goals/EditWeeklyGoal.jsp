<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div id="weekly_goal">
            <form method="POST"
                action="${pageContext.request.contextPath}/UpdateWeeklyGoal">
                <div id="weekly_goal_item">

                    <fmt:parseDate value="${weekly_goal.study_startdate}"
                        pattern="yyyy-MM-dd" var="study_startdateDay" type="date" />
                    <label for="study_startdate">学習開始日</label><br /> <input
                        type="date" name="study_startdate" id="study_startdate"
                        value="<fmt:formatDate value='${study_startdateDay}' pattern='yyyy-MM-dd' />" />
                    <br /> <br />
                </div>
                <div id="weekly_goal_item">

                    <fmt:parseDate value="${weekly_goal.study_enddate}"
                        pattern="yyyy-MM-dd" var="study_enddateDay" type="date" />
                    <label for="study_enddate">学習終了日</label><br /> <input type="date"
                        name="study_enddate" id="study_enddate"
                        value="<fmt:formatDate value='${study_enddateDay}' pattern='yyyy-MM-dd' />" />
                    <br /> <br />
                </div>

                <div id="weekly_goal_item">

                    <label for="study_time">学習目標時間</label><br /> <input type="number"
                        name="hours" id="" value="${weekly_goal.hours}" /> <br /> <br />
                </div>
                <input type="hidden" name="_token" value="${_token}" />

                <div id="weekly_goal_button">
                    <button type="submit">目標を設定する。</button>
                </div>

            </form>
        </div>

        <p id="redirect">
            <a href="${pageContext.request.contextPath}/index">TOPへ戻る</a>
        </p>
    </c:param>
</c:import>
