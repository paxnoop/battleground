<%--
  Created by IntelliJ IDEA.
  User: sugin
  Date: 15. 7. 2.
  Time: 오후 6:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-md-2" style="padding: 0px">
        <ul class="list-group" id="leftmenu">
        </ul>
    </div>
    <div class="col-md-10" style="padding-left: 10px">
        <div class="panel panel-default" style="text-align: center">
            <!-- Default panel contents -->
            <div class="panel-heading" style="text-align: left">${location1Kor} > ${location2Kor}</div>

            <!-- Table -->
            <table id="board" class="stripe" cellspacing="0" width="100%">
            </table>

            <div class="row" style="margin:20px 0px 20px 0px;text-align: center">

                <div style="text-align: left;margin-bottom: 20px;margin-left:100px">
                    <ul class="pagination" style="margin: 0px">
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
                        <li><a href="#">6</a></li>
                        <li><a href="#">7</a></li>
                        <li><a href="#">8</a></li>
                        <li><a href="#">9</a></li>
                        <li><a href="#">10</a></li>

                        <li>
                            <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>

                    </ul>
                    <a class="btn btn-default" href="/${location1}/${location2}/regist" style="float: right;margin-right:50px">글쓰기</a>
                </div>


                <div style="margin-left: 100px;width: 40%">
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
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var test;
    $(document).ready(function() {
        getLeftMenu(location1);
        getArticles(location1, location2);
        $.ajax({
            url:"/api/articles",
            dataType:"json",
            data:{
                "location1":location1,
                "location2":location2
            }
        }).done(function(data){
            test=data;
        });
    });


</script>