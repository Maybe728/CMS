layui.use(['form', 'layer', 'table'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#orderList',
        url: '/order/listData',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 20, 30],
        limit: 10,
        id: "orderListTable",
        cols: [[
            {type: "checkbox", fixed: "left"},
            {field: 'id', hide: true},
            {field: 'orderType', title: '订单类别', align: "center"},
            {field: 'orderPrice', title: '订单价格', align: 'center'},
            // {field: 'roleId', title: '角色名称', align:'center',templet:function(d){
            //     var name = "";
            //     roleList.forEach(function(e){
            //         if (e.id === d.roleId){
            //             name = e.roleName;
            //         }
            //     });
            //     return name;
            // }},
            {field: 'address', title: '地址', align: 'center'},
            {
                field: 'orderStatus', title: '订单状态状态', align: 'center', templet: function (d) {
                    if (d.state === 1) {
                        return '<span class="layui-badge layui-bg-green">待处理</span>';
                    } else if (d.state === 0) {
                        return '<span class="layui-badge layui-bg-cyan">已完成</span>';
                    } else {
                        return '<span class="layui-badge layui-bg-orange">处理中</span>';
                    }
                }
            },
            {
                field: 'gmtCreate', title: '修改时间', align: 'center', templet: function (data) {
                    if (data.gmtCreate != null) {
                        var date = data.gmtCreate.split("T")[0];
                        var time = data.gmtCreate.split(".")[0].split("T")[1];
                        return date+" "+time;
                    }else{
                        return " ";
                    }

                }
            },
            {
                field: 'gmtModified', title: '修改时间', align: 'center', templet: function (data) {
                    if (data.gmtModified != null) {
                        var date = data.gmtModified.split("T")[0];
                        var time = data.gmtModified.split(".")[0].split("T")[1];
                        return date+" "+time;
                    }else{
                        return " ";
                    }
                }
            }
        ]]
    });

    //搜索
    $(".search_btn").on("click", function () {
        table.reload("orderListTable", {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                state: $(".state").val()
            }
        })
    });

    //批量删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('orderListTable'),
            data = checkStatus.data,
            idArr = [];
        if (data.length > 0) {
            for (var i in data) {
                idArr.push(data[i].id);
            }
            layer.confirm('确定删除选中的订单？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/order/delBatch", {
                    idArr: idArr.toString()
                }, function (data) {
                    layer.close(index);
                    tableIns.reload();
                    if (data.data) {
                        layer.msg("删除成功！");
                    } else {
                        layer.msg(data.msg);
                    }
                })
            })
        } else {
            layer.msg("请选择需要删除的订单");
        }
    })

})
