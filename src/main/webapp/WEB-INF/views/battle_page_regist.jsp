<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: sugin
  Date: 15. 7. 2.
  Time: 오후 6:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="<c:url value="/resources/smarteditor/js/HuskyEZCreator.js"/>" charset="utf-8"></script>
<div class="row">
    <div class="col-md-2" style="padding: 0px">
        <ul class="list-group" id="leftmenu">
        </ul>
    </div>
    <div class="col-md-10" style="padding-left: 10px">
        <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading" style="text-align: left">${location1Kor} > ${location2Kor} > 글쓰기</div>
            <div style="padding: 10px">
                <form id='myForm' method='post' action="/regist_article">
                    <input type="hidden" name="location1" id="location1" value="${location1}">
                    <input type="hidden" name="location2" id="location2" value="${location2}">
                    <input type="hidden" name="writer" id="${user_name}" value="${user_name}">
                    <input type="hidden" name="writer_id" id="${user_id}" value="${user_id}">
                    <table class="table" style=" font-size: 14px;">
                        <tr>
                            <td style="width: 10%;border:none">제목</td>
                            <td style="width: 90%;border:none"><input type="text" style="width:70%" name="title" id="title"/></td>
                        </tr>
                        <tr>
                            <td style="width: 10%;border:none">작성자</td>
                            <td style="width: 90%;border:none">${user_name}</td>
                        </tr>
                        <tr>
                            <td colspan="2" class="view_text">
                                <textarea name="body" id="body" rows="10" style="width: 100%"></textarea>
                            </td>
                        </tr>
                        <tr>

                        </tr>
                    </table>
                    <div style="text-align: right; margin-right: 15px">
                        <a class="btn btn-default" id="write" onclick="fn_openBoardList()">목록</a>
                        <a class="btn btn-default" id="list" onclick="fn_insertBoard()">등록</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var oEditors = [];
    $(document).ready(function() {
        nhn.husky.EZCreator.createInIFrame({
            oAppRef: oEditors,
            elPlaceHolder: "body",
            sSkinURI: '<c:url value="/resources/smarteditor/SmartEditor2Skin.html"/>',
            fCreator: "createSEditor2"
        });
        getLeftMenu(location1);

    });

    function fn_openBoardList(){
        location.href="/${location1}/${location2}";
    }

    function fn_insertBoard(){
        oEditors.getById["body"].exec("UPDATE_CONTENTS_FIELD", []);
        $("#myForm").submit();
    }
</script>