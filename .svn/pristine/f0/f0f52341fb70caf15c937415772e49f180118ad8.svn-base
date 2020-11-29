//JavaScript代码区域
layui.use(['jquery', 'layer', 'form', 'table', 'element', 'laydate'], function() {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var element = layui.element;
    var laydate = layui.laydate;
    var url;
    var mainIndex;
    var index = 0;
    var data;

    //渲染数据表格
    table.render({
        elem: '#userTable' //渲染的目标对象
        ,
        url: 'buscheck.do?opt=search' //数据接口
        ,
        title: '商机信息表' //数据导出来的标题
        /* ,toolbar:"<div>xxx</div>" */
        ,
        /*toolbar: "#userToolBar" //表格的工具条
            ,
        defaultToolbar: []
            /!* ,height:300 *!/
            ,
        height: 500
                ,*/
        cellMinWidth: 100 //设置列的最小默认宽度
        ,
        /*done: function(res, curr, count) {
            /!* alert(res);//后台url返回的json串
            alert(curr);//当前页
            alert(count);//数据总条数 *!/
        },
        totalRow: true //开启合并行
            ,*/
        page: true //是否启用分页
        /*   ,limit:20 //设置每页显示条数 默认为10
          ,limits:[20,40,60,80] */
        ,
        skin: 'line',
        text: {
            none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
        }, done: function (res, curr, count) {
            var date = res.data;
            if(date.check_condition == '1' | date.check_condition == '2'){
                $("#addSuggestion").css.style(disabled='disabled');
                $("#addSuggestion").addClass("layui-btn-disabled");
            }

        }
        ,
        cols: [
            [ //列表数据
                {
                    type: 'checkbox',
                    fixed: 'left'
                },{
                type:'numbers',
                title:'序号'

            }, {
                field: 'id',
                title: '审核编号',
                sort: true,
                align: 'center',
                hide:true
            }, {
                field: 'business_name',
                title: '商机名称',
                sort: true,
                align: 'center',
            }, {
            field: 'cost_id',
                    title: '申请编号',
                    sort: true,
                    align: 'center',
            },{
                field: 'userp',
                title: '审核人编号',
                sort: true,
                align: 'center',
                hide:true
            },{
                field: 'username',
                title: '审核人',
                sort: true,
                align: 'center'
            },
                {
                field: 'check_condition',
                title: '审核状态',
                align: 'center',
                    templet:function (data) {
                        if(data.check_condition == '0'){
                            return '<font color=#00bfff>待审核</font>';
                        }else if(data.check_condition == '1'){
                            return '<font color=green>审核通过</font>';
                        }else if(data.check_condition == '2'){
                            return '<font color=red>驳回</font>';

                        }
                    }
            }, {
                field: 'check_idea',
                title: '审核意见',
                align: 'center'
            },{
                field: 'check_date',
                title: '审核日期',
                align: 'center'
            },
                {
                    field: 'remarks',
                    title: '备注',
                    align: 'center'
                }, {
                field: 'operate',
                title: '操作',
                toolbar: '#checkedaa',
                width: 200,
                align: 'center'
                ,fixed: 'right'
            }
            ]
        ],
        page: {layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']}
        ,
        id: 'testReload'
        /*done: function(res, curr, count) {
            index++;
            if (index == 1)
                data = res.data;
        }*/
    });

    laydate.render({
        elem: '#check_date'
    });

    //模糊查询
    $("#searchSubmit").click(function () {
        var business_name = $("#business_name").val();
        var status = $("#status").val();
        var index = layer.msg('查询中,请稍后...', {icon: 16, time: true, shade: 0});
        //alert(search_name);
        setTimeout(function () {
            table.reload('testReload', {
                where: {business_name: business_name, status: status}
            });
        }, 500);
        layer.close(index);
    });
    //审批驳回
    form.on('submit(doSubmitOut)', function (data) {
      /*  alert(JSON.stringify(data.field));*/
        $.get(
            url,
            {
                "cost_id": data.cost_id,
                "check_condition": '2',
                "check_idea": $("#check_idea").val(),
                "check_date": $("#check_date").val(),
                "remarks": $("#remarks").val()
            },
            function (flag) {
                if (flag == 1) {
                    layer.msg("已成功驳回!", {
                        time: 1000,
                        end: function () {
                            window.location.href = "check.html";
                        }
                    });
                } else {
                    alert("操作失败!");
                    window.location.href = "check.html";
                }
            }, "json");
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
    //审批通过
    form.on('submit(doSubmit)', function (data) {
        $.get(
            url,
            {   "business":$("#business_id").val(),
                "userp":$("#user_name").val(),
                "check_condition": '1',
                "check_idea": $("#check_idea").val(),
                "check_date": $("#check_date").val(),
                "remarks": $("#remarks").val()
            },
            function (flag) {
                if (flag == 1) {
                    layer.msg("已通过哦!", {
                        time: 1000,
                        end: function () {

                            window.location.href = "check.html";
                        }
                    });
                } else {
                    alert("操作失败!");
                    window.location.href = "check.html";
                }
            }, "json");
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
    //客户意向下拉框事件
    form.on('select(searchName)', function (data) {
        // alert("是否选择");
        var bintention = data.value;
        layer.msg("恭喜您操作成功", {
            time: 800,
            end: function () {
                var index = layer.msg('查询中,请稍后...', {icon: 16, time: true, shade: 0});
                setTimeout(function () {
                    table.reload('testReload', {
                        where: {intention: bintention}
                    });
                }, 500);
                layer.close(index);
            }

        });
        return false;
    });

    //商机状态下拉框事件
    form.on('select(searchStatus)', function (data) {
        var Sstatus = data.value;
        layer.msg("恭喜您操作成功", {
            time: 800,
            end: function () {
                var index = layer.msg('查询中,请稍后...', {icon: 16, time: true, shade: 0});
                setTimeout(function () {
                    table.reload('testReload', {
                        where: {status: Sstatus}
                    });
                }, 500);
                layer.close(index);
            }

        });
        return false;
    });

    $('.demoTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


    //监听工具栏事件
    table.on('tool(userTable)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (obj.event === "view") {
            review(data);
        }
        if (layEvent === 'addSuggestion') {
            openUpdateUser(data);
        }
        if (layEvent === 'check') {
            openReport(data);
        }
    });
    function review(data){
        mainIndex = layer.open({
            type: 1,
            title: '查看商机费用',
            content: $("#reviewcost"),
            area: ['750px', '500px'],
            success: function (index) {
                $("#dataFrmC")[0].reset();
                $.getJSON("buscost.do?opt=queryAll",function (prices) {
                    $.each(prices,function (i,price) {
                        if(price.id == data.cost_id){
                            form.val("dataFrmC",price);
                            // //$("#dataFrmC").html(price);
                            // $("#business_tracking").html(price.business_tracking);
                        }
                        // $("#business_tracking").html(price.business_tracking);
                    })
                });
            }
        });
    }

    function openUpdateUser(data) {
        mainIndex = layer.open({
            type: 1,
            title: '审核',
            content: $("#saveOrUpdateDiv"),
            area: ['800px', '400px'],
            success: function (index) {
                form.val("dataFrm", data);
                // layer.msg(JSON.stringify(data.field));
                url = "buscheck.do?opt=update&id="+data.id+"&cost_id="+data.cost_id;
                //发起异步
                $.getJSON("buscheck.do?opt=checkUser", function (Bus_Cons) {
                    if(Bus_Cons == 0){
                        $("#person").css("display","none");
                    }else{
                        $("#user_name").empty();
                        $.each(Bus_Cons, function (i, bus_Con) {
                            var option = "<option value='"+bus_Con.id+ "'>" + bus_Con.username + "</option>";
                            // if(bus_Cons.size() == 1){
                            //     option = option + "'selected = 'selected'>" + bus_Con.username + "</option>";
                            // }else{
                            //     option = option + "'>" + bus_Con.username + "</option>";
                            // }
                            $("#user_name").append(option);
                            form.render();
                        });
                    }

                });
                //下拉框渲染

            }
        });
    }
});