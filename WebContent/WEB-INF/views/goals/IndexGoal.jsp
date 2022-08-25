<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush !=null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <div id="goal_index">
            <p>あなたの学習目標</p>

            <c:forEach var="goal" items="${goals}">
                <a href="${pageContext.request.contextPath}/ShowGoal?id=${goal.id}">
                    <div class="box2">
                        <p>
                            <c:out value="${goal.title}" />
                        </p>
                    </div>
                </a>


            </c:forEach>
        </div>

        <div id="pagination">
            （全 ${goals_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((goals_count - 1) / 5) + 1}"
                step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                            </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/IndexGoal?page=${i}"><c:out
                                value="${i}" /></a>&nbsp;
                            </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>


        <p id="redirect">
            <a href="${pageContext.request.contextPath}/index">TOPへ戻る</a>
        </p>
    </c:param>
</c:import>
