<%--
  Created by IntelliJ IDEA.
  User: sugin
  Date: 15. 7. 2.
  Time: 오후 6:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-xs-9" style="padding: 0px;border: solid 1px darkgrey;height: 120px" >
        <img src="/resources/images/banner_top.jpg" style="width: 100%;height: 100%">
    </div>
    <div class="col-xs-3" style="border: solid 1px #dadada;height: 120px;padding: 0px">
        <div style="padding: 10px;float: left">
            <c:if test="${user_name == null}">
            <div class="input-group" style="margin-bottom: 10px;width: 100%">
                <form id="loginForm" action="/session" method="post">
                    <input type="hidden" name="location1" id="location1" value="${location1}">
                    <input type="hidden" name="location2" id="location2" value="${location2}">
                    <input type="text" class="form-control" name="id" id="id" placeholder="아이디" style="border-radius: 4px 0px 0px 0px" >
                    <input type="password" class="form-control" name="pw" id="pw" placeholder="패스워드" style="border-radius: 0px 0px 0px 4px" onkeypress="if(event.keyCode==13) {fn_login(); return false;}">
                </form>
                <span class="input-group-addon btn" id="basic-addon1" style="width: 50px" onclick="fn_login()">로그인</span>
            </div>
            <div style="text-align: center">
                <a href="#">회원가입</a> | <a href="#">아이디/비밀번호 찾기</a>
            </div>
            </c:if>
            <c:if test="${user_name != null}">
                어서오세요! ${user_name}님
            </c:if>


            <%--<div class="btn-group" role="group" style="width: 100%">--%>
                <%--<button type="button" class="btn btn-default" style="width: 33%">로그인</button>--%>
                <%--<button type="button" class="btn btn-default" style="width: 34%">회원가입</button>--%>
                <%--<button type="button" class="btn btn-default" style="width: 33%">PW찾기</button>--%>
            <%--</div>--%>
            <div>
                <a href="#"></a>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12" style="border: solid 1px darkgrey;height: 300px;padding: 0px">
        아직 안잡힌 몬스터 생성 글 리스트
    </div>
</div>
<script type="text/javascript">
    function fn_login(){
        $("#loginForm").submit();
    }
</script>