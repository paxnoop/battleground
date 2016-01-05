<%--
  Created by IntelliJ IDEA.
  User: sugin
  Date: 15. 7. 2.
  Time: 오후 6:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    table.dataTable.stripe tbody tr.odd{
        background-color: #fff4f3;
    }
</style>
<div class="row">
    <div class="col-xs-2" style="padding: 0px">
        <ul class="list-group">
            <li class="list-group-item"><span class="badge">14</span><a href="#">한적한 들판</a></li>
            <li class="list-group-item"><span class="badge">14</span><a href="#">오크 야영지</a></li>
            <li class="list-group-item"><span class="badge">14</span><a href="#">어두운 동굴</a></li>
            <li class="list-group-item"><span class="badge">14</span><a href="#">화염의 대지</a></li>
            <li class="list-group-item"><span class="badge">14</span><a href="#">쓸쓸한 뒷골목</a></li>
        </ul>
    </div>
    <div class="col-xs-10" style="padding-left: 10px">
        <div class="panel panel-danger" style="text-align: center">
            <!-- Default panel contents -->
            <div class="panel-heading" style="text-align: left">던전 > 한적한 들판</div>

            <!-- Table -->
            <table id="square_board" class="stripe" cellspacing="0" width="100%">
            </table>

            <div class="row" style="margin:20px 0px 20px 0px;text-align: center">
                <div class="col-xs-4 col-xs-offset-2">
                    <ul class="pagination" style="margin: 0px;">
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li>
                            <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="col-xs-4">
                    <div class="input-group">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">제목 <span class="caret"></span></button>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#">제목</a></li>
                                <li><a href="#">작성자</a></li>
                            </ul>
                        </div>
                        <input type="text" class="form-control" placeholder="Search for..."/>
                        <span class="input-group-btn">
                         <button class="btn btn-default" type="button">검색</button>
                        </span>
                    </div>
                </div>
            </nav>
        </div>


    </div>


</div>

<script type="text/javascript">
    $(document).ready(function() {
        var row =[1,"test","tester","07.06.10:13",10];
        var data = [];
        for(var i =0;i<10;i++){
            data.push(row);
        }
        var tableColumnDef = [
            {
                "sTitle": "no",
                "sClass": "square_no",
                "mRender": function (data, type, full) {
                    return data;
                }
            },
            {
                "sTitle": "제목",
                "sClass": "square_title",
                "mRender": function (data, type, full) {
                    return data;
                }
            },
            {
                "sTitle": "작성자",
                "sClass": "square_writer",
                "mRender": function (data, type, full) {
                    return data;
                }
            },
            {
                "sTitle": "작성일",
                "sClass": "square_regdate",
                "mRender": function (data, type, full) {
                    return data;
                }
            },
            {
                "sTitle": "조회수",
                "sClass": "square_click",
                "mRender": function (data, type, full) {
                    return data;
                }
            }
        ];

        $('#square_board').dataTable( {
            "aaData":   data,
            "aoColumns": tableColumnDef,
            "bScrollCollapse": true,
            "bLengthChange": false,     //length change select box
            "bFilter": false,           //search bar
            "bPaginate": false,         //paging
            "bDeferRender": true,
            "bInfo": false,
            "bSort" : false
        } );
    } );
</script>