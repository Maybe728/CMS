layui.use(['form','layer','table','laydate'],function(){
    var $ = layui.jquery,
        table = layui.table,
        laydate = layui.laydate;

    //日期范围
    laydate.render({
        elem: '#loginTime'
        ,range: true
    });

    //用户列表
    table.render({
        elem: '#loginLogList',
        url : '/loginLog/listData',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,20,30],
        limit : 10,
        id : "loginLogListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'userName', title: '用户名', minWidth:100, align:"center"},
            {field: 'ipAddress', title: 'IP地址', minWidth:100, align:'center'},
            {field: 'geographyLocation', title: '登录地点', minWidth:100, align:'center'},
            {field: 'createTime', title: '登录时间', minWidth:100, align:'center',
                templet: function(res) {
                    console.log(res);
                    if(res.createTime == null){
                        return "1900-01-01 00:00:00";
                    }else{
                        return res.createTime.substr(0,10)+" "+res.createTime.substr(11,8);
                    }
            }}
        ]]
    });

    //搜索
    $(".search_btn").on("click",function(){
        table.reload("loginLogListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                userName: $(".userName").val(),
                loginTime: $("#loginTime").val()
            }
        })
    });

})