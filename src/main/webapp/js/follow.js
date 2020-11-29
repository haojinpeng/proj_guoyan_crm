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
    //下拉框渲染
    layer.ready(function () {
        //下拉框渲染
        setTimeout(function () {
            //发起异步
            $.getJSON("bus.do?opt=queryType", function (Bus_types) {
                $("#businesstype").empty();
                $("#businesstype").append("<option value='' >--请选择--</option>");
                form.render();
                $.each(Bus_types, function (i, bus_type) {
                    $("#businesstype").append("<option value='" + bus_type.id + "'>" + bus_type.name + "</option>");
                    form.render();
                });

            });
        }, 100);

    });
    //渲染表格2
    table.render({
        elem: '#userTable' //渲染的目标对象
        ,
        url: 'track.do?opt=search' //数据接口
        ,
        title: '商机跟进表' //数据导出来的标题
        /* ,toolbar:"<div>xxx</div>" */
        ,
        // toolbar: "#userToolBar1" //表格的工具条
        // 	,
        // defaultToolbar: []
        // 	/* ,height:300 */
        // 	,
        // height: 500
        // 		,
        cellMinWidth: 90 //设置列的最小默认宽度
        ,
        // done: function(res, curr, count) {
        // 	/* alert(res);//后台url返回的json串
        // 	alert(curr);//当前页
        // 	alert(count);//数据总条数 */
        // },
        // totalRow: true //开启合并行
        // 	,
        page: true //是否启用分页
        /*   ,limit:20 //设置每页显示条数 默认为10
          ,limits:[20,40,60,80] */
        ,
        skin: 'line',
        text: {
            none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
        },
        cols: [
            [ //列表数据
                {
                    type: 'checkbox',
                    fixed: 'left'
                },{
                type: 'numbers',
                title: '序号'
            }, {
                field: 'id',
                title: '商机编号',
                width: 110,
                sort: true,
                align: 'center',
                hide:true
            }, {
                field: 'business',
                title: '商机名称',
                sort: true,
                edit: true,
                align: 'center',
                hide:true
            },{
                field: 'name',
                title: '商机名称',
                sort: true,
                edit: true,
                align: 'center'
            },
                {
                field: 'type',
                title: '记录类型',
                width: 90,
                align: 'center',
                    hide:true
            }, {
                field: 'type_name',
                title: '记录类型',
                width: 90,
                align: 'center',
            }, {
                field: 'createdate',
                title: '记录日期',
                width: 110,
                sort: true,
                align: 'center'
            }, {
                field: 'recorder',
                title: '记录人',
                width: 80,
                align: 'center',
                hide:true
            },
                {
                    field: 'username',
                    title: '记录人',
                    width: 80,
                    align: 'center'
                },{
                field: 'message',
                title: '记录信息',
                width: 150,
                align: 'center'
            }, {
                field: 'feedback_result',
                title: '反馈信息',
                width: 90,
                align: 'center'
            }, {
                field: 'remarks',
                title: '备注',
                width: 100,
                align: 'center'
            }, {
                field: 'operate',
                title: '操作',
                width:300,
                toolbar: '#userBar',
                align: 'center'
            }
            ]
        ],
        page: {layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']}
        ,
        id: 'testReload',
        /*done: function(res, curr, count) {
            index++;
            if (index == 1)
                data = res.data;
        }*/
    });
    //日期时间范围
    laydate.render({
        elem: '#date'
        ,type: 'date'
        ,range: '~'
    });
    laydate.render({
        elem: '#createdate'
    });
    //添加
    $("#data-add-btn").on("click", function () {
        adddata();
    });

    function adddata() {
        mainIndex = layer.open({
            type: 1,
            title: '添加商机信息',
            content: $("#saveOrUpdateDiv"),
            area: ['750px', '450px'],
            success: function (index) {
                //清空表单数据
                $("#dataFrm")[0].reset();
                url = "track.do?opt=add" ;
                setTimeout(function () {
                    //发起异步
                    $.getJSON("bus.do?opt=query", function (Bus_Cons) {
                        $("#business").empty();
                        $.each(Bus_Cons, function (i, bus_Con) {
                            $("#business").append("<option value='" + bus_Con.id + "'>" + bus_Con.name + "</option>");
                            form.render();
                        });
                    });
                },100);
                //下拉框渲染
                    //发起异步
                    $.getJSON("bus.do?opt=queryType", function (Bus_types) {
                        $("#type").empty();
                        $.each(Bus_types, function (i, bus_type) {
                            $("#type").append("<option value='" + bus_type.id + "'>" + bus_type.name + "</option>");
                            form.render();
                        });

                    });
            }
        });
    }

    //模糊查询
    $("#searchSubmit").click(function () {
        var businesstype = $("#businesstype").val();
        var busrecorder = $("#busrecorder").val();
        var date = $("#date").val();
        var index = layer.msg('查询中,请稍后...', {icon: 16, time: 800, shade: 1});
        //alert(search_name);
        setTimeout(function () {
            table.reload('testReload', {
                where: {type: businesstype, busrecorder: busrecorder, date: date}
            });
        }, 500);
        layer.close(index);
    });
    // 监听删除操作
    $(".data-delete-btn").on("click", function () {
        delAll();
    });
    //批量删除
    function delAll(){
        //获取选中数据
        var checkStatus = table.checkStatus('testReload'),
            data = checkStatus.data;
        if (data == "") {
            layer.msg('至少也得选一个吧', {icon: 2});
            return;
        }

        var delArray=[];
        //获取单元格选中的行
        if (data.length > 0) {
            for(var i=0;i<data.length;i++){
                delArray.push(data[i].id);

            }
        }
        var ids =delArray.join(",");


        layer.confirm('确认要删除嘛？' + delArray, function (index) {
            //发起异步请求
            $.ajax({
                type: 'POST',
                url: 'track.do?opt=deletes',
                data: {'delArray': ids},

            });
            layer.msg('删除成功！', {icon: 1});
            location.reload();
        });
    }
    //删除操作
    function delbus(id){
        layer.confirm('确认要删除吗?',function (index) {
            $.post("track.do", { opt: "delete", id: id },
                function(flag){
                    if(flag == 1){
                        layer.msg("删除成功", {icon: 6});
                        location.reload();
                        layer.close(index);
                    }else{
                        layer.msg("删除失败", {icon: 5});
                        location.reload();
                    }
                });
        });
    }
    //添加与修改弹窗提交
    form.on('submit(doSubmit)', function (data) {
        alert(JSON.stringify(data.field));
        // alert($("#feedback_result").val());
        $.get(
            url,
            {

                "business": $("#business").val(),
                "type": $("#type").val(),
                "createdate": $("#createdate").val(),
                "recorder": $("#recorder").val(),
                "message": $("#message").val(),
                "feedback":$("#feedback_result").val(),
                "remarks": $("#remarks").val()
            },
            function (flag) {
                if (flag == 1) {
                    layer.msg("恭喜您操作成功!", {
                        time: 1000,
                        end: function () {
                            window.location.href = "follow.html";
                        }
                    });
                } else {
                    alert("操作失败!");
                    window.location.href = "follow.html";
                }
            }, "json");
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
    //客户意向下拉框事件
    form.on('select(searchName)', function (data) {
        // alert("是否选择");
        var bintention = data.value;
        layer.msg(bintention);
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
        layer.msg(Sstatus);
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

        if (obj.event === "del") {
            delbus(data.id)
        }
        if (layEvent === 'edit') {
            openUpdateUser(data);
        }
        if (layEvent === 'addPrice') {
            addPrice();
        }
    });
    function addPrice() {
        mainIndex = layer.open({
            type: 1,
            title: '添加费用',
            content: $("#openPrice"),
            area: ['650px', '500px'],
            success: function (index) {
                //清空表单数据
                $("#dataFrmP")[0].reset();
                url = "buscost.do?opt=add" ;
                //下拉框渲染
                //发起异步
                $.getJSON("bus.do?opt=query", function (Bus_Cons) {
                    $("#business_id").empty();
                    $.each(Bus_Cons, function (i, bus_Con) {
                        $("#business_id").append("<option value='" + bus_Con.id + "'>" + bus_Con.name + "</option>");
                        form.render();
                    });
                });
                //发起异步
                $.getJSON("bus.do?opt=queryType", function (Bus_types) {
                    $("#consume_type").empty();
                    $.each(Bus_types, function (i, bus_type) {
                        $("#consume_type").append("<option value='" + bus_type.id + "'>" + bus_type.name + "</option>");
                        form.render();
                    });

                });

                setTimeout(function () {
                    //发起异步
                    $.getJSON("bus.do?opt=queryUser", function (Bus_types) {
                        $("#cost_declarant").empty();
                        $.each(Bus_types, function (i, bus_type) {
                            $("#cost_declarant").append("<option value='" + bus_type.id + "'>" + bus_type.username + "</option>");
                            form.render();
                        });

                    });
                },200);
                setTimeout(function () {
                    //发起异步
                    $.getJSON("bus.do?opt=queryFid", function (Bus_types) {
                        $("#business_tracking").empty();
                        $.each(Bus_types, function (i, bus_type) {
                            $("#business_tracking").append("<option value='" + bus_type.id + "'>" + bus_type.id + "</option>");
                            form.render();
                        });

                    });
                },400);
            }
        });
    }
    //添加与修改弹窗提交
    form.on('submit(doSubmitP)', function (data) {
        alert(JSON.stringify(data.field));
        alert($("#perdict_consume").val());
        alert($("#consume_type").val());
        $.get(
            url,
            {

                "business_id": $("#business_id").val(),
                "business_tracking": $("#business_tracking").val(),
                "consume_type": $("#consume_type").val(),
                "reality_consume": $("#reality_consume").val(),
                "perdict": $("#perdict_consume").val(),
                "cost_declarant":$("#cost_declarant").val(),
                "cost_description": $("#cost_description").val()
            },
            function (flag) {
                if (flag == 1) {
                    layer.msg("恭喜您操作成功!", {
                        time: 1000,
                        end: function () {
                            window.location.href = "follow.html";
                        }
                    });
                } else {
                    alert("操作失败!");
                    window.location.href = "follow.html";
                }
            }, "json");
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    function openUpdateUser(data) {
        mainIndex = layer.open({
            type: 1,
            title: '修改商机信息',
            content: $("#saveOrUpdateDiv"),
            area: ['750px', '450px'],
            success: function (index) {
                form.val("dataFrm", data);
                // layer.msg(JSON.stringify(data.field));
                url = "track.do?opt=update&id="+data.id;
                setTimeout(function () {
                    //发起异步
                    $.getJSON("bus.do?opt=query", function (Bus_Cons) {
                        $("#business").empty();
                        $.each(Bus_Cons, function (i, bus_Con) {
                            var option = "<option value='"+bus_Con.id;
                            if(bus_Con.id == data.id){
                                option = option + "'selected = 'selected'>" + bus_Con.name + "</option>";
                            }else{
                                option = option + "'>" + bus_Con.name + "</option>";
                            }
                            $("#business").append(option);
                            form.render();
                        });
                    });
                }, 300);
                //下拉框渲染
                    //发起异步
                    $.getJSON("bus.do?opt=queryType", function (Bus_types) {
                        $("#type").empty();
                        $.each(Bus_types, function (i, bus_type) {
                            var option = "<option value='"+bus_type.id;
                            if(bus_type.id == data.type){
                                option = option + "' selected = 'selected'>" + bus_type.name + "</option>";
                            }else{
                                option = option + "'>" + bus_type.name + "</option>";
                            }
                            $("#type").append(option);
                            form.render();
                        });

                    });
            }
        });
    }
});