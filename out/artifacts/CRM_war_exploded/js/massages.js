//javascript代码去

layui.use(['layer','jquery', 'form', 'table', 'element', 'laydate'], function() {
    var $ = layui.jquery;
     var layer = layui.layer;
     var form = layui.form;
     var table = layui.table;
     var element = layui.element,
     laydate = layui.element;
     //获取页面id
    var dat ;
    layer.ready(function () {
        var url = window.location.href;
        var data = url.split("?");
        if(data.length == 2){
            dat= decodeURIComponent(data[1].split("=")[1]);
        }
    });
        /**
         * 模块1页面信息
         */
        layer.ready(function () {
        $.getJSON(
            "track.do?opt=findById&id="+dat+"&limit=10&page=1", function (tracks) {
                $.each(tracks, function (i, track) {
                    var option="<div class=\"layuimini-notice\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<div class=\"layuimini-notice-title\">【"+track.name+"】 "+track.message+" </div>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<div class=\"layuimini-notice-extra\">"+track.createdate+"</div>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<div class=\"layuimini-notice-content layui-hide\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t界面足够简洁清爽。<br>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t一个接口几行代码而已直接初始化整个框架，无需复杂操作。<br>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t支持多tab，可以打开多窗口。<br>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t支持无限级菜单和对font-awesome图标库的完美支持。<br>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t失效以及报错菜单无法直接打开，并给出弹出层提示完美的线上用户体验。<br>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\turl地址hash定位，可以清楚看到当前tab的地址信息。<br>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t刷新页面会保留当前的窗口，并且会定位当前窗口对应左侧菜单栏。<br>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t移动端的友好支持。<br>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t\t\t\t</div>";
                    $("#track").append(option);
                })
            }
        );
        var app = {};
            $.getJSON(
                "bus.do?opt=findById&id="+dat, function (tracks) {
                    $.each(tracks,function (i,track) {
                        var option1 = "<form class=\"layui-form \" action=\"\" lay-filter=\"dataFrm\" id=\"dataFrm\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t<table class=\"layui-table\" style=\"height: 365px;\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t<colgroup>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<col width=\"100\">\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<col>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t</colgroup>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td>商机编号</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td id=\"id\">" +track.id+"\n" +
                            "\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td>商机名称</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td id=\"name\">"+track.name+"</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td>商机状态</td>\n";
                        if(track.status == 1){
                            option1 = option1 + "<td id=\"status\">跟进中</td>"
                        }else if(track.status == 2){
                            option1 = option1 + "<td id=\"status\">已报备</td>"
                        }else if(track.status == 3){
                            option1 = option1 + "<td id=\"status\">已流失</td>"
                        }
                            option1 = option1 +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td>客户编号</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td id=\"customers_id\">"+track.customer_name+"\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td>类型编号</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td id=\"type_name\">"+track.type_name+"\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n"+
                                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td>客户意向</td>\n";
                        if(track.intention == 3){
                            option1 = option1 + "<td id=\"intention\">强</td>"
                        }else if(track.intention == 2){
                            option1 = option1 + "<td id=\"intention\">中</td>"
                        }else if(track.intention == 1){
                            option1 = option1 + "<td id=\"intention\">弱</td>"
                        };
                        option1 = option1 +

                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td>备注</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td id=\"remarks\">"+track.remarks+"\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t</form>";
                        $("#saveOrUpdateDiv").append(option1);
                    })

                }
            );
        });
    /**
     * 查看公告信息
     **/
    $('body').on('click', '.layuimini-notice', function() {
        var title = $(this).children('.layuimini-notice-title').text(),
            noticeTime = $(this).children('.layuimini-notice-extra').text(),
            content = $(this).children('.layuimini-notice-content').html();
        var html =
            '<div style="padding:15px 20px; text-align:justify; line-height: 22px;border-bottom:1px solid #e2e2e2;background-color: #2f4056;color: #ffffff">\n' +
            '<div style="text-align: center;margin-bottom: 20px;font-weight: bold;border-bottom:1px solid #718fb5;padding-bottom: 5px"><h4 class="text-danger">' +
            title + '</h4></div>\n' +
            '<div style="font-size: 12px">' + content + '</div>\n' +
            '</div>\n';
        parent.layer.open({
            type: 1,
            title: '系统公告' + '<span style="float: right;right: 1px;font-size: 12px;color: #b1b3b9;margin-top: 1px">' +
                noticeTime + '</span>',
            area: '300px;',
            shade: 0.8,
            id: 'layuimini-notice',
            btn: ['查看', '取消'],
            btnAlign: 'c',
            moveType: 1,
            content: html,
            success: function(layero) {
                var btn = layero.find('.layui-layer-btn');
                btn.find('.layui-layer-btn0').attr({
                    href: 'https://gitee.com/zhongshaofa/layuimini',
                    target: '_blank'
                });
            }
        });
    });
});