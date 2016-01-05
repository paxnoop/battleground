<%--
  Created by IntelliJ IDEA.
  User: sugin
  Date: 15. 7. 2.
  Time: 오후 6:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
    <div class="col-md-2" style="padding: 0px;">
        <ul class="list-group" id="leftmenu">
        </ul>
    </div>
    <div class="col-md-10" style="padding-left: 10px">
        <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading" style="text-align: left">${location1} > ${location2}</div>
            <div style="padding: 10px">
                <div >
                    <span style="width: 100px">제목 | </span>
                    <span id="title"></span>
                </div>
                <div style="border-bottom-style:dashed;border-bottom-width: 1px;opacity: 0.3;margin: 10px 0px 10px 0px;"></div>
                <div style="margin-bottom: 10px">
                    <span style="width: 100px;font-size: 12px;color: grey">작성자</span>
                    <span id="writer"></span>
                    <span style="width: 100px;font-size: 12px;color: grey">| 조회수</span>
                    <span id="click_count" style="font-size: 12px"></span>
                    <span style="width: 100px;font-size: 12px;color: grey">| 좋아요</span>
                    <span id="like_count" style="font-size: 12px;color: #0000ff"></span>
                    <span style="width: 100px;font-size: 12px;color: grey">| 등록시간</span>
                    <span id="reg_date" style="font-size: 12px;color: grey"></span>
                </div>
                <div id="body" style="min-height: 200px;border: 1px solid lightgrey;padding: 10px;border-radius: 4px 4px 0px 0px;border-bottom: 0px">
                    본문
                </div>
                <div style="border: 1px solid lightgrey;padding: 10px;border-radius: 0px 0px 4px 4px;border-top: 0px">
                    <table id="comments" class="" cellspacing="0" style="width: 100%;">
                    </table>
                    <div style="background: #f5f5f5">
                        <div class="input-group" style="margin-bottom: 10px;width: 100%;padding: 20px">
                            <form id="regist_comment" action="/regist_comment" method="post">
                                <input type="hidden" name="a_id" id="a_id" value="${a_id}">
                                <input type="hidden" name="writer" id="${user_name}" value="${user_name}">
                                <input type="hidden" name="writer_id" id="${user_id}" value="${user_id}">
                                <input type="hidden" name="location1" id="location1" value="${location1}">
                                <input type="hidden" name="location2" id="location2" value="${location2}">
                                <input type="text" class="form-control" name="copy" id="copy" style="border-radius: 0px 0px 0px 4px" onkeypress="if(event.keyCode==13) {fn_login(); return false;}">
                            </form>
                            <span class="input-group-addon btn" id="basic-addon1" style="width: 50px" onclick="fn_insertComment()">댓글달기</span>
                        </div>
                    </div>
                </div>
                <div style="text-align: right;margin-top: 20px">
                    <c:if test="${user_id != null}">
                        <a class="btn btn-default" href="/${location1}/${location2}/regist">글쓰기</a>
                    </c:if>
                    <a class="btn btn-default" id="list" href="/${location1}/${location2}">목록</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        getLeftMenu(location1);
        $.ajax({
            url:"/api/article",
            dataType:"json",
            data:{
                "a_id":${a_id}
            }
        }).done(function(data){
            $("#title").html(data.title);
            $("#body").html(data.body);
            $("#writer").html(data.writer);
            $("#click_count").html(data.click_count);
            $("#like_count").html(data.like_count);
            $("#reg_date").html(moment(data.reg_date).format("YYYY.MM.DD HH시mm분"));
        });
        getComments(${a_id});
    });

    function fn_insertComment(){
        if(!checkLogin()){
            alert("로그인 후 댓글달기가 가능합니다.")
        }
        else{
            $("#regist_comment").submit();
        }

    }
</script>