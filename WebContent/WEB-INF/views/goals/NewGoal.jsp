<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">

        <link rel="stylesheet" href="<c:url value='/css/goal.css'/>">


        <form method="POST"
            action="${pageContext.request.contextPath}/CreateGoal">
            <p id="goal_title">目標を設定しよう</p>

            <ol id="goal_list">
                <div class="goal_content">
                    <div>
                        <li>あなたの目標を入力してください。</li>
                    </div>
                    <div>
                        <input type="text" name="title" required="required" id="name"
                            value="${goal.title}" />
                    </div>
                </div>

                <div class="goal_action">
                    <div>
                        <li>目標を達成するために必要な行動を考えてみましょう。</li>
                    </div>
                    <div>
                        <input type="text" name="action" required="required" id="name"
                            value="${goal.action}" />
                    </div>
                </div>

                <div class="goal_reason">

                    <div>
                        <li>なぜこの目標を達成したいのか考えてみましょう</li>
                    </div>

                    <div>
                        <input type="text" name="reason" required="required" id="name"
                            value="${goal.reason}" />

                    </div>
                </div>

            </ol>

            </div>
            <div id="caution">
                <p>目標設定前の注意点</p>
                <p>達成できる現実的な目標か考えてみましょう。（１年以上かかる長期目標は非推奨です。）</p>

                <p>目標が具体的なものか確認しましょう。 （勉強する等の曖昧な目標は非推奨です。）</p>

            </div>

            <input type="hidden" name="_token" value="${_token}" />


            <div class="button-panel">
                <input type="submit" class="button" title="作成" value="作成"></input>
            </div>
        </form>

        <p id="redirect">
            <a href="${pageContext.request.contextPath}/index">TOPへ戻る</a>
        </p>
    </c:param>
</c:import>