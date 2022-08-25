<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div id="show_goal">

            <p>あなたの目標</p>
            <div class="box2">
                <p>
                    <c:out value="${goal.title}" />
                </p>
            </div>

            <p>目標を達成するための行動</p>
            <div class="box2">
                <p>
                    <c:out value="${goal.action}" />
                </p>
            </div>

            <p>目標を達成したい理由</p>
            <div class="box2">
                <p>
                    <c:out value="${goal.reason}" />
                </p>
            </div>



        </div>
        <p>
            <a id="destroy" href="#" onclick="confirmDestroy();">この目標を削除する</a>
        </p>
         <form method="POST" action="${pageContext.request.contextPath}/DestroyGoal">
            <input type="hidden" name="_token" value="${_token}" />
        </form>
        <script>
            function confirmDestroy() {
                if (confirm("学習記録も消えてしまいますが、本当に削除してよろしいですか？")) {
                    document.forms[0].submit();
                }
            }
        </script>


        <p id="redirect">
            <a href="${pageContext.request.contextPath}/IndexGoal">一覧に戻る</a>
        </p>
    </c:param>
</c:import>
