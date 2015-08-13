<%--
  Created by IntelliJ IDEA.
  User: sugin
  Date: 15. 8. 12.
  Time: 오후 5:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-md-4 col-md-offset-4" style="margin-top: 30px">
        <form id="loginForm" action="/session" method="post">
            <input type="hidden" name="location1" id="location1" value="${location1}">
            <input type="hidden" name="location2" id="location2" value="${location2}">
            <input type="text" class="form-control" name="id" id="id" placeholder="아이디" style="border-radius: 4px 0px 0px 0px" >
            <input type="password" class="form-control" name="pw" id="pw" placeholder="패스워드" style="border-radius: 0px 0px 0px 4px" onkeypress="if(event.keyCode==13) {fn_login(); return false;}">
        </form>
        <div class="btn btn-default" onclick="fn_login()" style="width: 100%">로그인</div>
        <div style="margin-top: 10px; text-align: center;">
            <a style="text-decoration: none;cursor: pointer;color: grey">아이디 찾기</a> |
            <a style="text-decoration: none;cursor: pointer;color: grey">비밀번호 찾기</a> |
            <a style="text-decoration: none;cursor: pointer;color: grey">회원가입</a>
        </div>
        <div style="margin-top: 50px; text-align: center;">
            <div style="color:red">${message}</div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function fn_login(){
        $("#loginForm").submit();
    }
</script>
