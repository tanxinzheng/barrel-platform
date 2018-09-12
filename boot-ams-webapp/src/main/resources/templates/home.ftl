<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>应用服务中心</title>
    <link rel="stylesheet" href="${ctx.contextPath}/vendor/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx.contextPath}/app.css">
</head>
<body>
<div class="layui-header layui-bg-black">
    <div class="layui-main layui-fluid">
        <ul class="layui-nav" lay-filter="">
            <li class="layui-nav-item">应用平台列表</li>
            <#--<li class="layui-nav-item"><a href="">产品分类</a></li>-->
            <#--<li class="layui-nav-item"><a href="">文   档</a></li>-->
            <#--<li class="layui-nav-item">-->
                <#--<a href="">登录</a>-->
            <#--</li>-->
            <#--<li class="layui-nav-item">-->
                <#--<a href=""><img src="//t.cn/RCzsdCq" class="layui-nav-img">我</a>-->
                <#--<dl class="layui-nav-child">-->
                    <#--<dd><a href="javascript:;">修改信息</a></dd>-->
                    <#--<dd><a href="javascript:;">安全管理</a></dd>-->
                    <#--<dd><a href="javascript:;">退了</a></dd>-->
                <#--</dl>-->
            <#--</li>-->
        </ul>
    </div>
</div>
<div class="layui-fluid data-list">
    <div class="layui-row">
        <form action="" id="searchForm">
        <input type="text" id="keyword" name="keyword" value="${RequestParameters["keyword"]!}" placeholder="请输入应用名称，应用代码或描述" autocomplete="off" class="layui-input"/>
        </form>
    </div>
    <br>
    <div class="layui-row layui-col-space10 data-list-body">
    <br>
    <#list appList as app>
        <div class="layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-header layui-bg-blue">
                    <a style="color: #fff;" target="_blank" href="${app.url}">${app.appName}<span>（${app.appCode}）</span></a>
                </div>
                <div class="layui-card-body">
                    ${app.description}
                </div>
            </div>
        </div>
    </#list>
    </div>
</div>

<script src="${ctx.contextPath}/vendor/layui/layui.js"></script>
</body>
</html>
