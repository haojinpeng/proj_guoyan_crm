
		   	
     	layui.use(['jquery','slider','element','carousel','rate','table','layer','laydate','form'],function(){
			
			var $ = layui.jquery,
			  slider = layui.slider,
			  element = layui.element,
			  form = layui.form,
			  table = layui.table,
			  laydate = layui.laydate,
			  rate = layui.rate,
			  layer = layui.layer,
			  carousel = layui.carousel;
			  
			  //绑定时间选择器
			  			laydate.render({
			  				elem:'#startTime'
			  			});
			  			laydate.render({
			  				elem:'#endTime'
			  			})
						//渲染数据表格
									var tableIns=table.render({
										 elem: '#userTable'   //渲染的目标对象
									    ,url:'js/users.json' //数据接口
									    ,title: '用户数据表'//数据导出来的标题
									    ,toolbar:"#userToolBar"   //表格的工具条
									    ,height:'520px'
									    ,cellMinWidth:100 //设置列的最小默认宽度
									    ,done:function(res, curr, count){
									    	
									    }
									    ,page: true  //是否启用分页
									    ,cols: [[   //列表数据
									      {type: 'checkbox', fixed: 'left'}
									      ,{field:'id', title:'ID', sort:true}
									      ,{field:'username', title:'名称', sort:true,align:'center'}
//									       ,{field:'age', title:'年龄', sort:true,align:'center'}
//									        ,{field:'contrast', title:'客户联系人', sort:true,align:'center'
//									        	window.location.href = './guest.html';
//									        }
//									      
//									      ,{field:'sex', title:'性别',align:'center',templet:function(d){
//									    	  return d.sex=='1'?'男':'女';
//									      }}
//									       ,{field:'user', title:'登录名', sort:true,align:'center'}
//									        ,{field:'password', title:'密码', sort:true,align:'center'}
//									      ,{field:'city', title:'城市',align:'center'}
									      ,{fixed: 'right', title:'操作', toolbar: '#userBar', width:220,align:'center'}
									    ]]
									});
									
									
									
									var tableIns=table.render({
										 elem: '#selectuserTable'   //渲染的目标对象
									    ,url:'js/users.json' //数据接口
									    ,title: '用户数据表'//数据导出来的标题
									    ,toolbar:"#userToolBar"   //表格的工具条
									    ,height:'full-300'
									    ,cellMinWidth:100 //设置列的最小默认宽度
									    ,done:function(res, curr, count){
									    	
									    }
									    ,page: true  //是否启用分页
									    ,cols: [[   //列表数据
									      {type: 'checkbox', fixed: 'left'}
									      ,{field:'id', title:'ID', sort:true}
									      ,{field:'username', title:'用户名', sort:true,align:'center'}
									       ,{field:'age', title:'年龄', sort:true,align:'center'}
//									      ,{field:'age', title:'年龄', align:'center',  templet: function(res){
//									        return '<em><font color=red>'+ res.email +'</font></em>'
//									      }}
									      ,{field:'sex', title:'性别',align:'center',templet:function(d){
									    	  return d.sex=='1'?'男':'女';
									      }}
									       ,{field:'user', title:'登录名', sort:true,align:'center'}
									        ,{field:'password', title:'密码', sort:true,align:'center'}
//									      ,{field:'city', title:'城市',align:'center'}
									      ,{fixed: 'right', title:'操作', toolbar: '#userBar', width:220,align:'center'}
									    ]]
									});
	//监听头部工具栏事件
			table.on("toolbar(userTable)",function(obj){
				 switch(obj.event){
				    case 'add':
				      openAddUser();
				    break;
				    case 'batchDelete':
				      layer.msg('批量删除');
				    break;
				  };
			});
				//监听行工具事件
					   table.on('tool(userTable)', function(obj){
						   var data = obj.data; //获得当前行数据
						   var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
						  if(layEvent === 'del'){ //删除
							 layer.msg("删除");
						     layer.confirm('真的删除行么', function(index){
						       layer.close(index);
						       //向服务端发送删除指令
						     });
						   } else if(layEvent === 'edit'){ //编辑
							   openUpdateUser(data);
						   }
						 });
			
		            var url;
					var mainIndex;
				
				//打开添加页面
						function openAddUser(){
							mainIndex=layer.open({
								type:1,
								title:'添加用户',
								content:$("#saveOrUpdateDiv"),
								area:['800px','400px'],
								success:function(index){
									//清空表单数据       
									$("#dataFrm")[0].reset();
									url="user/addUser.action";
								}
							});
						}
						
						//打开修改页面
									function openUpdateUser(data){
										mainIndex=layer.open({
											type:1,
											title:'修改用户',
											content:$("#saveOrUpdateDiv"),
											area:['800px','400px'],
											success:function(index){
												form.val("dataFrm",data);
												url="user/updateUser.action";
											}
										});
									}
		});
