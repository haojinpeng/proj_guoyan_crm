
	//JavaScript代码区域
	//布局必须
	layui.use(['jquery', 'slider', 'element', 'carousel', 'rate', 'table', 'layer', 'laydate', 'form'], function() {

		var $ = layui.jquery,
			slider = layui.slider,
			element = layui.element,
			form = layui.form,
			table = layui.table,
			laydate = layui.laydate,
			rate = layui.rate,
			layer = layui.layer,
			carousel = layui.carousel;
			
		table.render({
			elem: '#userTable',
			height: "420px",
			url: 'js/工单记录.json',
			toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板					
			defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
				title: '提示',
				layEvent: 'LAYTABLE_TIPS',
				icon: 'layui-icon-tips'
			}],
			title: '工单记录数据表',
			cols: [
				[{
					type: 'checkbox',
					fixed: 'left',
		
		
				}, {
					field: 'id',
					title: 'ID',
					width: 80,
					fixed: 'left',
					sort: true
				}, {
					field: 'ku_name',
					title: '客户',
					width: 80
		
				}, {
					field: 'gd_number',
					title: '工单编号',
					width: 150
				}, {
					field: 'xm',
					title: '项目名称',
					width: 150
				}, {
					field: 'time_s',
					title: '接收时间',
					width: 120,
		
					sort: true
				}, {
					field: 'time_e',
					title: '截至处理极限',
					width: 120,
		
					sort: true
				}, {
					field: 'yh_nume',
					title: '创建用户',
					width: 80,
		
		
				}, {
					field: 'time',
					title: '生成时间',
					width: 150,
					edit: 'text',
					sort: true
				}, {
					field: 'zt',
					title: '当前状态',
		
					width: 150
				}, {
					fixed: 'right',
					title: '操作',
					toolbar: '#barDemo',
					width: 150
				}]
			],
			page: true
		});
		

		//监听头部工具栏事件
		table.on("toolbar(userTable)", function(obj) {
			switch (obj.event) {
				case 'add':
					openAddUser();
					break;
				case 'ss':
					layer.msg('搜索');
					break;
				case 'LAYTABLE_TIPS':
					layer.alert('这是工具栏右侧自定义的一个图标按钮');
					break;
			};
		});
		//监听行工具事件
		table.on('tool(userTable)', function(obj) {
			var data = obj.data; //获得当前行数据
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			if (layEvent === 'showit') { //删除
				openshowit();

			} else if (layEvent === 'doit') { //编辑
				opendoit();
			}

		});

		var url;
		var mainIndex;

		//打开添加页面
		function openAddUser() {
			mainIndex = layer.open({
				type: 1,
				title: '添加工单',
				content: $('#addDIV'),
				area: ['360px', '500px'],
				success: function(index) {
					//清空表单数据       
					$("#dataFrm")[0].reset();
					url = "user/addUser.action";
				}
			});
		}

		//打开处理页面
		function opendoit() {
			mainIndex = layer.open({
				type: 1,
				title: '工单处理',
				content: $("#doitDIV"),
				area: ['430px', '550px'],
				success: function(index) {
					//清空表单数据
					$("#dataFrm")[0].reset();
					url = "user/addUser.action";
				}
			});
		}
		function openshowit() {
			mainIndex = layer.open({
				type: 1,
				title: '工单预览',
				content: $("#showDIV"),
				area: ['430px', '550px'],
				success: function(index) {
					//清空表单数据
					$("#dataFrm")[0].reset();
					url = "user/addUser.action";
				}
			});
		}
		
		
		
		form.on('radio(testRadio1)',function(obj){
			
			var date = $(obj.elem)[0].value;
			if(date === 'zf'){
			        $("#ztzfshow").css("display", "block");
			    } else{
					 $("#ztzfshow").css("display", "none");
				}
		}) ;
		form.on('radio(testRadio)',function(obj){
			
			var date = $(obj.elem)[0].value;
			if(date === 'ok'){
			        $("#okhow").css("display", "block");
			    } else{
					 $("#okhow").css("display", "none");
				}
		}) ;
		
		 

		//日期时间范围
		laydate.render({
			elem: '#date',
			range: '~'
		});
		laydate.render({
			elem: '#jieshouTime'
		});
		laydate.render({
			elem: '#chuliTime',
			range: '~'

		});
		laydate.render({
			elem: '#shengchengTime'
		});


		//------------第二部分----------------------------------------------------------------------------------------------------------------------

		table.render({
			elem: '#userTable2',
			height: "420px",
			url: 'js/工单处理.json',
			toolbar: '#toolbarDemo2', //开启头部工具栏，并为其绑定左侧模板					
			defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
				title: '提示',
				layEvent: 'LAYTABLE_TIPS',
				icon: 'layui-icon-tips'
			}],
			title: '工单处理数据表',
			cols: [
				[{
					type: 'checkbox',
					fixed: 'left',
				}, {
					field: 'gd_number',
					title: '工单编号',
					width: 150,
					sort: true
				}, {
					field: 'js_man',
					title: '接受人',
					width: 150
				}, {
					field: 'wt_man',
					title: '委托人',
					width: 150
				}, {
					field: 'say',
					title: '处理描述',
					width: 220,

				}, {
					field: 'time',
					title: '截至期限',
					width: 200,
					edit: 'text',
					sort: true
				}, {
					field: 'zt',
					title: '当前状态',
					width: 150
				}, {
					fixed: 'right',
					title: '操作',
					toolbar: '#barDemo2',
					width: 200
				}]
			],
			page: true
		});

		//监听头部工具栏事件
		table.on("toolbar(userTable2)", function(obj) {
			switch (obj.event) {

				case 'ss2':
					layer.msg('搜索');
					break;
				case 'LAYTABLE_TIPS':
					layer.alert('这是工具栏右侧自定义的一个图标按钮');
					break;
			};
		});
		//监听行工具事件
		table.on('tool(userTable2)', function(obj) {
			var data = obj.data; //获得当前行数据
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			if (layEvent === 'zf') {
				layer.msg("转发");
				openzfDiV();

			} else if (layEvent === 'pj') { //编辑
				openpjDiV();
			}else if (layEvent === 'doit') { //编辑
				opendoit();
			}

		});

		var url;
		var mainIndex;

		//打开评价页面
		function openpjDiV() {
			mainIndex = layer.open({
				type: 1,
				title: '售后体验',
				content: $('#pjDIV'),
				area: ['360px', '500px'],
				success: function(index) {
					//清空表单数据       
					$("#dataFrm")[0].reset();
					url = "user/addUser.action";
				}
			});
		}
		//打开转发页面
		function openzfDiV() {
			mainIndex = layer.open({
				type: 1,
				title: '转发',
				content: $('#zfDIV'),
				area: ['360px', '500px'],
				success: function(index) {
					//清空表单数据       
					$("#dataFrm")[0].reset();
					url = "user/addUser.action";
				}
			});
		}

		laydate.render({
			elem: '#date2',
			range: '~'
		});

		//评价显示文字
		rate.render({
			elem: '#pjtest2',
			value: 2 //初始值
				,
			text: true //开启文本
		});
		//------------第三部分----------------------------------------------------------------------------------------------------------------------


		table.render({
			elem: '#userTable3',
			height: "420px",
			url: 'js/工单回访.json',
			toolbar: '#toolbarDemo3', //开启头部工具栏，并为其绑定左侧模板					
			defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
				title: '提示',
				layEvent: 'LAYTABLE_TIPS',
				icon: 'layui-icon-tips'
			}],
			title: '工单回访数据表',
			cols: [
				[{
					type: 'checkbox',
					fixed: 'left',
				}, {
					field: 'gd_number',
					title: '工单编号',
					width: 150,
					sort: true
				}, {
					field: 'cj_man',
					title: '创建用户',
					width: 150
				}, {
					field: 'du',
					title: '客户满意度',
					width: 80
				}, {
					field: 'say',
					title: '客户建议',
					width: 180

				}, {
					field: 'time',
					title: '回访时间',
					width: 150,
					edit: 'text',
					sort: true
				}, {
					field: 'cj_time',
					title: '创建时间',
					width: 150
				}, {
					field: 'ms',
					title: '描述',
					width: 200
				}, {
					fixed: 'right',
					title: '操作',
					toolbar: '#barDemo3',
					width: 160
				}]
			],
			page: true
		});

		//监听头部工具栏事件
		table.on("toolbar(userTable3)", function(obj) {
			switch (obj.event) {

				case 'cx3':
					layer.msg('查询');
					break;
				case 'LAYTABLE_TIPS':
					layer.alert('这是工具栏右侧自定义的一个图标按钮');
					break;
			};
		});
		//监听行工具事件
		table.on('tool(userTable3)', function(obj) {
			var data = obj.data; //获得当前行数据
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			if (layEvent === 'zf3') {
				layer.msg("转发");
				openzfDiV();

			} else if (layEvent === 'pj3') { //编辑
				openpjDiV();
			}

		});

		var url;
		var mainIndex;

		//打开评价页面
		function openpjDiV() {
			mainIndex = layer.open({
				type: 1,
				title: '售后体验',
				content: $('#pjDIV'),
				area: ['360px', '500px'],
				success: function(index) {
					//清空表单数据       
					$("#dataFrm")[0].reset();
					url = "user/addUser.action";
				}
			});
		}
		//打开转发页面
		function openzfDiV() {
			mainIndex = layer.open({
				type: 1,
				title: '转发',
				content: $('#zfDIV'),
				area: ['360px', '500px'],
				success: function(index) {
					//清空表单数据       
					$("#dataFrm")[0].reset();
					url = "user/addUser.action";
				}
			});
		}

		laydate.render({
			elem: '#date3',
			range: '~'
		});
		
		    
		
		
		

	}); 

