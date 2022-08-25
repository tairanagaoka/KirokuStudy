<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div class="form-wrapper">
    <h1 class="login_title">Create an account</h1>

    <form>
        <div class="form-item">
            <label for="name"></label><br /> <input type="text" name="name"
                required="required" placeholder="name" id="name"
                value="${user.name}" />
        </div>
        <div class="form-item">
            <label for="code"></label> <input type="text" name="code"
                required="required" placeholder="user code" id="code"
                value="${user.code}" />
        </div>
        <div class="form-item">
            <label for="password"></label> <input type="password" name="password"
                required="required" placeholder="Password" id="password"
                value="${user.password}">
        </div>
        <input type="hidden" name="_token" value="${_token}" />

        <div class="button-panel">
            <input type="submit" class="button" title="Create" value="Create"></input>
        </div>

    </form>
    <div class="form-footer">
        <p>
            <a href="${pageContext.request.contextPath}/login">Sign In</a>
        </p>
    </div>
</div>
