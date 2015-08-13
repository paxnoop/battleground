<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page session="false" pageEncoding="UTF-8"  %>
<html>
<head>
    <title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
    <tiles:insertAttribute name="header"></tiles:insertAttribute>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar" aria-expanded="true" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">BattleGround</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse in" aria-expanded="true">
            <ul class="nav navbar-nav">
                <li <c:if test="${location1 eq 'cityhall'}">class="active"</c:if>><a href="/cityhall/everyone"><i class="fa fa-university"></i> 마을회관</a></li>
                <li <c:if test="${location1 eq 'square'}">class="active"</c:if>><a href="/square/everyone"><i class="fa fa-cloud"></i> 광장</a></li>
                <li <c:if test="${location1 eq 'dungeon'}">class="active"</c:if>><a href="/dungeon/all"><i class="fa fa-gavel"></i> 던전</a></li>
                <li <c:if test="${location1 eq 'market'}">class="active"</c:if>><a href="/market/all"><i class="fa fa-shopping-cart"></i> 시장</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-user"></i> 캐릭터<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/status"><i class="fa fa-bar-chart"></i> 스테이터스</a></li>
                        <li><a href="/inventory"><i class="fa fa-suitcase"></i> 인벤토리</a></li>
                        <li><a href="/quest"><i class="fa fa-exclamation" style="margin-left: 5px;margin-right: 5px"></i> 퀘스트</a></li>
                        <li><a href="/guild"><i class="fa fa-users"></i> 길드</a></li>

                    </ul>
                </li>

            </ul>
            <c:if test="${user_id != null}">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-cog"></i> ${user_id}<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/status"><i class="fa fa-wrench"></i> 내 정보변경</a></li>
                            <li><a href="/logout"><i class="fa fa-sign-out"></i> 로그아웃</a></li>
                        </ul>
                    </li>
                </ul>
            </c:if>
            <c:if test="${user_id ==null && location1 != 'cityhall'}">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/login"><i class="fa fa-sign-in"></i> 로그인</a></li>
            </ul>
            </c:if>


        </div>
    </div>
</nav>
<div class="container">
    <tiles:insertAttribute name="content"></tiles:insertAttribute>
</div>

</body>
</html>
