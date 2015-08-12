<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"  %>
<script type="text/javascript" src="<c:url value="/resources/js/main.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/moment-v2.10.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/numeral-v1.5.3.min.js"/>"></script>

<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/jquery.dataTables.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/custom.css"/>" />

<link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/resources/images/favicon.ico"/>">
<script type="text/javascript">
    var location1 = "${location1}";
    var location2 = "${location2}";

    function getLeftMenu(location1){
        $.ajax({
            url:"/api/leftmenu",
            dataType:"json",
            data:{
                "location1":location1
            }
        }).done(function(data){
            if(data != null){
                for(var i=0;i<data.length;i++){

                    var active = (data[i].location2 === location2) ? "active" : "";
                    var textColor = (data[i].location2 === location2) ? 'style="color: #ffffff"' : '';
                    var url = "/"+data[i].location1+"/"+data[i].location2
                    var item = '<li class="list-group-item '+active+'"><a '+textColor+' href="'+url+'">'+data[i].name+'</a></li>';

                    $('#leftmenu').append(item);
                }
            }
        });
    }
    function makeTableItems(tableItems){
        var items=[];
        for(var i=0;i<tableItems.length;i++){
            var item=[];
            item.push(tableItems[i].id);
            var title={};
            title.id=tableItems[i].id;
            title.title=tableItems[i].title;
            item.push(title);
            item.push(tableItems[i].writer);
            item.push(tableItems[i].reg_date);
            item.push(tableItems[i].click_count);
            items.push(item)
        }
        return items;
    }

    function getArticles(location1, location2){
        $.ajax({
            url:"/api/articles",
            dataType:"json",
            data:{
                "location1":location1,
                "location2":location2
            }
        }).done(function(data){
            var tableData = makeTableItems(data);
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
                        return "<a href='/${location1}/${location2}/battle_page?a_id="+data.id+"'>"+data.title+"</a>";
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
                        if(moment("2015-08-11 17:36:57.0").format("D")!==moment().format("D")){
                            return moment(data).format("YYYY.MM.DD");
                        }
                        return moment(data).format("HH:mm");
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
            $('#board').dataTable( {
                "aaData":   tableData,
                "aoColumns": tableColumnDef,
                "bScrollCollapse": true,
                "bLengthChange": false,     //length change select box
                "bFilter": false,           //search bar
                "bPaginate": false,         //paging
                "bDeferRender": true,
                "bInfo": false,
                "bSort" : false,
                "language" : {
                    "emptyTable": "등록된 게시물이 없습니다."
                }
            } );
        });
    }
</script>