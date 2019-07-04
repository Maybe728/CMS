layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        element = layui.element;
        $ = layui.jquery;

    $("body").on("click",".icons li",function(){
        var copyText = document.getElementById("copyText");
        copyText.innerText = $(this).text();
        copyText.select();
        document.execCommand("copy");
        layer.msg("复制成功",{anim: 2});
    })
})
