var $, tab, dataStr, layer;
layui.config({
    base: "/static/js/modules/"
}).extend({
    "bodyTab": "bodyTab"
})
layui.use(['bodyTab', 'form', 'element', 'layer', 'jquery'], function () {
    var form = layui.form,
        element = layui.element;
    $ = layui.$;
    layer = parent.layer === undefined ? layui.layer : top.layer;
    tab = layui.bodyTab({
        openTabNum: "8"  //最大可打开窗口数量
    });

    //通过顶部菜单获取左侧二三级菜单
    function getData(code) {
        $.post("/menu/left", {
            code: code
        }, function (res) {
            dataStr = res.data;
            //重新渲染左侧菜单
            tab.render();
        })
    }

    //页面加载时判断左侧菜单是否显示
    //通过顶部菜单获取左侧菜单
    $(".topLevelMenus li,.mobileTopLevelMenus dd").click(function () {
        if ($(this).parents(".mobileTopLevelMenus").length != "0") {
            $(".topLevelMenus li").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
        } else {
            $(".mobileTopLevelMenus dd").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
        }
        $(".layui-layout-admin").removeClass("showMenu");
        $("body").addClass("site-mobile");
        getData($(this).data("menu"));
        //渲染顶部窗口
        tab.tabMove();
    })

    //隐藏左侧导航
    $(".hideMenu").click(function () {
        if ($(".topLevelMenus li.layui-this a").data("url")) {
            layer.msg("此栏目状态下左侧菜单不可展开");  //主要为了避免左侧显示的内容与顶部菜单不匹配
            return false;
        }
        $(".layui-layout-admin").toggleClass("showMenu");
        //渲染顶部窗口
        tab.tabMove();
    })

    //通过顶部菜单获取左侧二三级菜单（默认）
    $(".topLevelMenus li:first").click();

    //手机设备的简单适配
    $('.site-tree-mobile').on('click', function () {
        $('body').addClass('site-mobile');
    });
    $('.site-mobile-shade').on('click', function () {
        $('body').removeClass('site-mobile');
    });

    // 添加新窗口
    $("body").on("click", ".layui-nav .layui-nav-item a:not('.mobileTopLevelMenus .layui-nav-item a')", function () {
        //如果不存在子级
        if ($(this).siblings().length == 0) {
            addTab($(this));
            $('body').removeClass('site-mobile');  //移动端点击菜单关闭菜单层
        }
        $(this).parent("li").siblings().removeClass("layui-nav-itemed");
    })

    //刷新后还原打开的窗口
    if (cacheStr == "true") {
        if (window.sessionStorage.getItem("menu") != null) {
            menu = JSON.parse(window.sessionStorage.getItem("menu"));
            curmenu = window.sessionStorage.getItem("curmenu");
            var openTitle = '';
            for (var i = 0; i < menu.length; i++) {
                openTitle = '';
                if (menu[i].icon) {
                    if (menu[i].icon.split("-")[0] == 'icon') {
                        openTitle += '<i class="seraph ' + menu[i].icon + '"></i>';
                    } else {
                        openTitle += '<i class="layui-icon">' + menu[i].icon + '</i>';
                    }
                }
                openTitle += '<cite>' + menu[i].title + '</cite>';
                openTitle += '<i class="layui-icon layui-unselect" data-id="' + menu[i].layId + '"></i>';
                element.tabAdd("bodyTab", {
                    title: openTitle,
                    content: "<iframe src='" + menu[i].href + "' data-id='" + menu[i].layId + "'></frame>",
                    id: menu[i].layId
                })
                //定位到刷新前的窗口
                if (curmenu != "undefined") {
                    if (curmenu == '' || curmenu == "null") {  //定位到后台首页
                        element.tabChange("bodyTab", '');
                    } else if (JSON.parse(curmenu).title == menu[i].title) {  //定位到刷新前的页面
                        element.tabChange("bodyTab", menu[i].layId);
                    }
                } else {
                    element.tabChange("bodyTab", menu[menu.length - 1].layId);
                }
            }
            //渲染顶部窗口
            tab.tabMove();
        }
    } else {
        window.sessionStorage.removeItem("menu");
        window.sessionStorage.removeItem("curmenu");
    }
    var i = 0;
    $("#img").click(function () {
        if (i == 0) {
            $("#img").removeClass("layui-icon-shrink-right");
            $("#img").addClass("layui-icon-spread-left");
            i = 1;
        }else{
            $("#img").removeClass("layui-icon-spread-left");
            $("#img").addClass("layui-icon-shrink-right");
            i = 0;
        }
    })
})

//打开新窗口
function addTab(_this) {
    tab.tabAdd(_this);
}

//每一分钟轮询一遍是否有新的订单
function showNewOrder(){
    $.get("/order/getNewOrders", function (res) {
        if (res.data>0) {
            layer.open({
                type: 1
                ,title:"新订单提醒"
                , offset: 'rb'
                , content: '<audio autoplay="autoplay"><source src="/static/images/tips.mp3" type="audio/ogg" /></audio><div style="padding: 20px 100px;">' + "<b>您有新的订单啦！！！</b>" + '</div>'
                , btn: '确定'
                , btnAlign: 'r' //按钮居中
                , shade: 0 //不显示遮罩
                , time:299000
                , skin:"layui-layer-molv"
                , yes: function () {
                    layer.closeAll();
                    //跳转页面
                }
            });
        }
    })
}

setInterval("showNewOrder()","300000");


