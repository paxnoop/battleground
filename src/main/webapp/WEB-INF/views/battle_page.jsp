<%--
  Created by IntelliJ IDEA.
  User: sugin
  Date: 15. 7. 2.
  Time: 오후 6:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-md-2" style="padding: 0px;">
        <ul class="list-group" id="leftmenu">
        </ul>
    </div>
    <div class="col-md-10" style="padding-left: 10px">
        <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading" style="text-align: left">광장 > 모두의 광장</div>
            <div style="padding: 10px">
                <div >
                    <span style="width: 100px">제목</span>
                    <span id="title"></span>
                </div>
                <div style="border-bottom-style:dashed;border-bottom-width: 1px;opacity: 0.3;margin: 10px 0px 10px 0px;"></div>
                <div style="margin-bottom: 10px">
                    <span style="width: 100px">작성자</span>
                    <span id="writer"></span>
                </div>
                <div id="body" style="min-height: 200px;border: 1px solid lightgrey;padding: 10px;border-radius: 4px 4px 0px 0px;border-bottom: 0px">
                    본문
                </div>
                <div style="border: 1px solid lightgrey;padding: 10px;border-radius: 0px 0px 4px 4px;border-top: 0px">
                    <table id="comments" class="" cellspacing="0" style="width: 100%;">
                    </table>
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
        });

        var row =[1,"test","tester",10,"07.06.10:13"];
        var data = [];
        for(var i =0;i<10;i++){
            data.push(row);
        }
        var tableColumnDef = [
            {
                "sClass": "square_comment_writer",
                "mRender": function (data, type, full) {
                    return data;
                }
            },
            {
                "sClass": "square_comment_copy",
                "mRender": function (data, type, full) {
                    return data;
                }
            },
            {
                "sClass": "square_comment_updown",
                "mRender": function (data, type, full) {
                    return data;
                }
            },
            {
                "sClass": "square_comment_functions",
                "mRender": function (data, type, full) {
                    return data;
                }
            },
            {
                "sClass": "square_comment_regdate",
                "mRender": function (data, type, full) {
                    return data;
                }
            }
        ];

        $('#comments').dataTable( {
            "aaData":   data,
            "aoColumns": tableColumnDef,
            "bScrollCollapse": true,
            "bLengthChange": false,     //length change select box
            "bFilter": false,           //search bar
            "bPaginate": false,         //paging
            "bDeferRender": true,
            "bInfo": false,
            "bSort" : false,
            "sDom": 'lfrtip'
        } );
    } );
</script>