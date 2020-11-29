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
        url: 'bus.do?opt=search' //数据接口
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
            //table_data = res.data;
            trNum = count;
            for(var i = 0;i<res.data.length;i++){
                var state = res.data[i].checkStatus;
                if(state != '[[${state01}]]'){
                    var index = res.data[i]['LAY_TABLE_INDEX'];
                    $(".layui-table tr[data-index="+index+"] input[type='checkbox']").prop('disabled',true);
                    $(".layui-table tr[data-index="+index+"] input[type='checkbox']").next().addClass('layui-btn-disabled');
                    $('.layui-table tr[data-index=' + index + '] input[type="checkbox"]').prop('name', 'caib');
                }else{
                    table_data.push(res.data[i])
                }
            }

        }
        ,
        cols: [
            [ //列表数据
                {
                    type: 'checkbox',
                    fixed: 'left'
                }, {
                type: 'numbers',
                title:'序号'
            },{
                field: 'id',
                title: '商机编号',
                width: 110,
                sort: true,
                align: 'center'
            }, {
                field: 'name',
                title: '商机名称',
                sort: true,
                width: 110,
                edit: true,
                align: 'center'
            }, {
                field: 'status',
                title: '商机状态',
                width: 100,
                align: 'center',
                    templet:function (data) {
                        if(data.status == '1'){
                            return '<font color=#00bfff>跟进中</font>';
                        }else if(data.status == '2'){
                            return '<font color=green>已报备</font>';
                        }else if(data.status     == '3'){
                            return '<font color=red>已流失</font>';

                        }
                    }
            }, {
                field: 'customers_id',
                title: '客户编号',
                width: 110,
                sort: true,
                align: 'center'
            }, {
                field: 'type_name',
                width: 100,
                title: '商机类型',
                align: 'center'
            },
                {
                field: 'business_type_id',
                width: 100,
                title: '商机类型',
                align: 'center',
                    hide:true
            }, {
                field: 'intention',
                title: '客户意向',
                width: 90,
                align: 'center'
                , templet: function (d) {
                    return d.intention == '1' ? '弱' : (d.intention == '2' ? '中' : (d.intention == '3' ? '强' : null))
                }
            }, {
                field: 'contact',
                title: '联系人',
                width: 100,
                align: 'center',
                hide:true
            },
                {
                field: 'contactman',
                title: '联系人',
                width: 100,
                align: 'center'
            },
                {
                    field: 'username',
                    title: '员工',
                    width: 60,
                    align: 'center'
                }, {
                field: 'remarks',
                title: '备注',
                width: 110,
                align: 'center'
            }, {
                field: 'operate',
                title: '操作',
                toolbar: '#userBar',
                width:295.,
                align: 'center',
                fixed: 'right'
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

    //添加
    $("#data-add-btn").on("click", function () {
        adddata();
    });

    function adddata() {
        mainIndex = layer.open({
            type: 1,
            title: '添加商机信息',
            content: $("#saveOrUpdateDiv"),
            area: ['650px', '500px'],
            success: function (index) {
                //清空表单数据
                $("#dataFrm")[0].reset();
                url = "bus.do?opt=add";
                setTimeout(function () {
                    //发起异步
                    $.getJSON("bus.do?opt=queryContact", function (Bus_Cons) {
                        $("#contact").empty();
                        $.each(Bus_Cons, function (i, bus_Con) {
                            $("#contact").append("<option value='"+bus_Con.id+"'>" + bus_Con.name+":"+bus_Con.phone + "</option>");
                            form.render();
                        });
                    });
                }, 300);
                //下拉框渲染
                setTimeout(function () {
                    //发起异步
                    $.getJSON("bus.do?opt=queryType", function (Bus_types) {
                        $("#business_type_id").empty();
                        $.each(Bus_types, function (i, bus_type) {

                            $("#business_type_id").append("<option value='"+bus_type.id+"'>" + bus_type.name + "</option>");
                            form.render();
                        });

                    });
                }, 500);
            }
        });
    }

    //模糊查询
    $("#searchSubmit").click(function () {
        var busid = $("#busid").val();
        var busintention = $("#busintention").val();
        var busstatus = $("#busstatus").val();
        var index = layer.msg('查询中,请稍后...', {icon: 16, time: true, shade: 0});
        //alert(search_name);
        setTimeout(function () {
            table.reload('testReload', {
                where: {name: busid, intention: busintention, status: busstatus}
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
                url: 'bus.do?opt=deletes',
                data: {'delArray': ids},

            });
            layer.msg('删除成功！', {icon: 1});
            location.reload();
        });
    }

    //删除操作
    function delbus(id){
        layer.confirm('确认要删除吗?',function (indes) {
        $.post("bus.do", { opt: "delete", id: id },
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
        $.get(
            url,
            {
                "name": $("#name").val(),
                "status": $("#status").val(),
                "customers_id": $("#customers_id").val(),
                "business_type_id": $("#business_type_id").val(),
                "contact": $("#contact").val(),
                "intention": $('input[type=radio][name=intention]:checked').val(),
                "userid": $("#userid").val(),
                "remarks": $("#remarks").val()
            },
            function (flag) {
                if (flag == 1) {
                    layer.msg("恭喜您操作成功!", {
                        time: 1000,
                        end: function () {
                            window.location.href = "content.html";
                        }
                    });
                } else {
                    alert("操作失败!");
                    window.location.href = "content.html";
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
    function set_show(id,t){
        var Urlss = '{:U("home/OrderSupplier/order_message")}&mer_id='+id;
        parent.layui.index.openTabsPage(Urlss, $(t).attr("title-data"));
    }

    //监听工具栏事件
    table.on('tool(userTable)', function (obj) {
        var data = obj.data;
        var id = data.id;
        var layEvent = obj.event;

        if (layEvent === 'del') {
            delbus(id);
        }
        if (layEvent === 'edit') {
            openUpdateUser(data);
        }
        if (layEvent === 'rep') {
            openReport(data);
        }if(layEvent === 'addPrice'){
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
       /* alert(JSON.stringify(data.field));
        alert($("#perdict_consume").val());
        alert($("#consume_type").val());*/
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
                            window.location.href = "content.html";
                        }
                    });
                } else {
                    alert("操作失败!");
                    window.location.href = "content.html";
                }
            }, "json");
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    function openReport(data) {
        mainIndex = layer.open({
            type: 1,
            title: '添加商机信息',
            content: $("#openReport"),
            area: ['700px', '600px'],
            success: function (index) {
                //清空表单数据
                $("#dataReport")[0].reset();
                url = "bus.do?opt=add";
            }
        });
    }


    function openUpdateUser(data) {
        mainIndex = layer.open({
            type: 1,
            title: '修改商机信息',
            content: $("#saveOrUpdateDiv"),
            area: ['800px', '400px'],
            success: function (index) {
                form.val("dataFrm", data);
                // layer.msg(JSON.stringify(data.field));
                url = "bus.do?opt=update&id="+data.id;
                    //发起异步
                    $.getJSON("bus.do?opt=queryContact", function (Bus_Cons) {
                        $("#contact").empty();
                        $.each(Bus_Cons, function (i, bus_Con) {
                            var option = "<option value='"+bus_Con.id;
                            if(bus_Con.id == data.contact){
                                option = option + "'selected = 'selected'>" + bus_Con.name +":" + bus_Con.phone + "</option>";
                            }else{
                                option = option + "'>" + bus_Con.name + ":" + bus_Con.phone + "</option>";
                            }
                            $("#contact").append(option);
                            form.render();
                        });
                    });
                //下拉框渲染
                setTimeout(function () {
                    //发起异步
                    $.getJSON("bus.do?opt=queryType", function (Bus_types) {
                        $("#business_type_id").empty();
                        $.each(Bus_types, function (i, bus_type) {
                            var option = "<option value='"+bus_type.id;
                            if(bus_type.id == data.business_type_id){
                                option = option + "'selected = 'selected'>" + bus_type.name + "</option>";
                            }else{
                                option = option + "'>" + bus_type.name + "</option>";
                            }
                            $("#business_type_id").append(option);
                            form.render();
                        });

                    });
                }, 500);
            }
        });
    }
});