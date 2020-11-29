
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
                    $.each(Bus_types, function (i, bus_type) {
                        $("#businesstype").append("<option value='" + bus_type.id + "'>" + bus_type.name + "</option>");
                        form.render();
                    });

                });
            }, 100);

        });
        //渲染表格3
        table.render({
            elem: '#userTable' //渲染的目标对象
            ,
            url: 'buscost.do?opt=search' //数据接口
            ,
            title: '费用申请表' //数据导出来的标题
            /* ,toolbar:"<div>xxx</div>" */
            ,
            // toolbar: "#userToolBar2" //表格的工具条
            // 	,
            // defaultToolbar: []
            // 	/* ,height:300 */
            // 	,
            // height: 500
            // 	,
            cellMinWidth: 80 //设置列的最小默认宽度
            ,
            done:function(res, curr, count) {
                merge(res);
            },
            // totalRow: true //开启合并行
            // 	,
            page: true //是否启用分页
            /*   ,limit:20 //设置每页显示条数 默认为10
              ,limits:[20,40,60,80] */
            ,
            text: {
                none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
            },
            cols: [
                [ //列表数据
                    {
                        type: 'checkbox',
                        fixed: 'left'
                    },
                    {
                        type: 'numbers',
                        title:'序号'
                    },{
                    field: 'id',
                    title: '申请编号',
                    sort: true,
                    align: 'center',
                }, {
                    field: 'business_id',
                    title: '商机名称',
                    sort: true,
                    edit: true,
                    align: 'center',
                    hide:true
                },{
                    field: 'business_name',
                    title: '商机名称',
                    sort: true,
                    width:110,
                    align: 'center',
                },
                    {
                        field: 'consume_type',
                        title: '消费类型',
                        width:110,
                        sort: true,
                        edit: true,
                        align: 'center',
                        hide:true
                    },{
                    field: 'type_name',
                    title: '消费类型',
                    width:110,
                    sort: true,
                    align: 'center'

                },{
                    field: 'business_tracking',
                    title: '跟进编号',
                    width:100,
                    sort: true,
                    align: 'center'

                },
                    {
                    field: 'reality_consume',
                    title: '实际金额',
                        width:100,
                    align: 'center'
                },{
                    field: 'perdict_consume',
                    title: '计划金额',
                    width:100,
                    align: 'center'
                },
                    {
                    field: 'audit_status',
                    title: '审核状态',
                        width:110,
                    align: 'center',
                    templet:function(msg){
                        if(msg.audit_status == '2'){
                            return '<font color = red>已驳回</font>'
                        }
                        if(msg.audit_status == '1'){
                            return '<font color = green>已通过</font>'
                        }
                        if(msg.audit_status == '0'){
                            return '<font color = skyblue>审核中</font>'
                        }
                    }
                },  {
                    field: 'cost_declarant',
                    title: '申报人',
                    sort: true,
                    align: 'center',
                    hide:true
                },{
                    field: 'username',
                    title: '申报人',
                    width:100,
                    sort: true,
                    align: 'center'
                },
                    {
                        field: 'cost_description',
                        title: '费用描述',
                        sort: true,
                        width:130,
                        align: 'center'
                }, {
                    field: 'operate',
                    title: '操作',
                    toolbar: '#userBar',
                    align: 'center',
                    width:200,
                    fixed:'right'
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
        //和并重复列
        function merge(res) {

            var data = res.data;
            var mergeIndex = 0; //定位需要添加合并属性的行数
            var mark = 1; //这里涉及到简单的运算，mark是计算每次需要合并的格子数
            var columsName = ['business_name','username']; //需要合并的列名称
            var columsIndex = [4,5,3,12,11,10]; //需要合并的列索引值

            for (var k = 0; k < columsName.length; k++) { //这里循环所有要合并的列
                var trArr = $(".layui-table-body>.layui-table").find("tr"); //所有行
                for (var i = 1; i < res.data.length; i++) { //这里循环表格当前的数据
                    var tdCurArr = trArr.eq(i).find("td").eq(columsIndex[k]); //获取当前行的当前列
                    var tdPreArr = trArr.eq(mergeIndex).find("td").eq(columsIndex[k]); //获取相同列的第一列

                    if (data[i][columsName[k]] === data[i - 1][columsName[k]]) { //后一行的值与前一行的值做比较，相同就需要合并
                        mark += 1;
                        tdPreArr.each(function() { //相同列的第一列增加rowspan属性
                            $(this).attr("rowspan", mark);
                        });
                        tdCurArr.each(function() { //当前行隐藏
                            $(this).css("display", "none");
                        });
                    } else {
                        mergeIndex = i;
                        mark = 1; //一旦前后两行的值不一样了，那么需要合并的格子数mark就需要重新计算
                    }
                }
                mergeIndex = 0;
                mark = 1;
            }
        }
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

        //模糊查询
        $("#searchSubmit").click(function () {
            var businesstype = $("#businesstype").val();
            var busrecorder = $("#busrecorder").val();
            var date = $("#date").val();
            var index = layer.msg('查询中,请稍后...', {icon: 16, time: true, shade: 0});
            //alert(search_name);
            setTimeout(function () {
                table.reload('testReload', {
                    where: {type: businesstype, recorder: busrecorder, createdate: date}
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
                    url: 'buscost.do?opt=deletes',
                    data: {'delArray': ids},

                });
                layer.msg('删除成功！', {icon: 1});
                location.reload();
            });
        }
        //删除操作
        function delbus(id){
            layer.confirm('确认要删除吗?',function (index) {
                $.post("buscost.do", { opt: "delete", id: id },
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
                                window.location.href = "price.html";
                            }
                        });
                    } else {
                        alert("操作失败!");
                        window.location.href = "price.html";
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

        //监听商机首页事件
        table.on('toolbar(userTable)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id)
            switch (obj.event) {
                case 'doadd':
                    openAddUser();
                    break;
                case 'doSearch':
                    break;
                case 'dodelete':
                    layer.msg("是否确定删除");
                    break;
            }

        });

        function openAddUser() {
            mainIndex = layer.open({
                type: 1,
                title: '添加商机信息',
                content: $("#saveOrUpdateDiv"),
                area: ['600px', '500px'],
                success: function (index) {
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    url = "bus.do?opt=add";

                }
            });
        }

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
            if (layEvent === 'rep') {
                openReport(data);
            }
        });

        function openReport(data) {
            mainIndex = layer.open({
                type: 1,
                title: '添加商机信息',
                content: $("#openUpdatePrice"),
                area: ['650px', '600px'],
                success: function (index) {
                    //清空表单数据
                    $("#dataFrmPrice")[0].reset();
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
                    url = "buscost.do?opt=update&id="+data.id;
                        //发起异步
                        $.getJSON("bus.do?opt=query", function (Bus_Cons) {
                            $("#business_id").empty();
                            $.each(Bus_Cons, function (i, bus_Con) {
                                var option = "<option value='"+bus_Con.id;
                                if(bus_Con.id == data.business_id){
                                    option = option + "'selected = 'selected'>" + bus_Con.name + "</option>";
                                }else{
                                    option = option + "'>" + bus_Con.name + "</option>";
                                }
                                $("#business_id").append(option);
                                form.render();
                            });
                        });
                    //下拉框渲染
                    setTimeout(function () {
                    //发起异步
                    $.getJSON("bus.do?opt=queryType", function (Bus_types) {
                        $("#consume_type").empty();
                        $.each(Bus_types, function (i, bus_type) {
                            var option = "<option value='"+bus_type.id;
                            if(bus_type.id == data.consume_type){
                                option = option + "' selected = 'selected'>" + bus_type.name + "</option>";
                            }else{
                                option = option + "'>" + bus_type.name + "</option>";
                            }
                            $("#consume_type").append(option);
                            form.render();
                        });

                    });
                    }, 200);
                    setTimeout(function () {
                    //发起异步
                    $.getJSON("bus.do?opt=queryUser", function (Bus_types) {
                        $("#cost_declarant").empty();
                        $.each(Bus_types, function (i, bus_type) {
                            var option = "<option value='"+bus_type.id;
                            if(bus_type.id == data.cost_declarant){
                                option = option + "' selected = 'selected'>" + bus_type.username + "</option>";
                            }else{
                                option = option + "'>" + bus_type.username + "</option>";
                            }
                            $("#cost_declarant").append(option);
                            form.render();
                        });
                    });
                    }, 400);
                    //发起异步
                    $.getJSON("bus.do?opt=queryFid", function (Bus_types) {
                        $("#business_tracking").empty();
                        $.each(Bus_types, function (i, bus_type) {
                            var option = "<option value='"+bus_type.id;
                            if(bus_type.id == data.business_tracking){
                                option = option + "'selected = 'selected'>" + bus_type.id + "</option>";
                            }else{
                                option = option + "'>" + bus_type.id + "</option>";
                            }
                            $("#business_tracking").append(option);
                            form.render();
                        });

                    });
                }
            });
        }
    });