/**
 @ Name：layui.regionCheckBox 中国省市复选框
 @ Author：wanmianji
 */

layui.define('form', function(exports){
	
	var $ = layui.$
	,form = layui.form
	,MOD_NAME = 'regionCheckBox', ELEM = '.layui-regionContent'
	,regionCheckBox = {
		index: layui.regionCheckBox ? (layui.regionCheckBox.index + 10000) : 0
		
		,set: function(options){
			var that = this;
			that.config = $.extend({}, that.config, options);
			return that;
		}
		
		,on: function(events, callback){
			return layui.onevent.call(this, MOD_NAME, events, callback);
		}
	}
	,thisIns = function(){
		var that = this
		,options = that.config
		,id = options.id || options.index;
		
		return {
			reload: function(options){
				that.reload.call(that, options);
			}
			,config: options
		}
	}
	,Class = function(options){
		var that = this;
		that.index = ++regionCheckBox.index;
		that.config = $.extend({}, that.config, regionCheckBox.config, options);
		that.render();
	};
	
	
	Class.prototype.config = {
		value: []
		,width: '550px'
		,border: true
		,change: function(result){}
		,ready: function(){}
	};
	
	Class.prototype.render = function(){
		var that = this
		,options = that.config;

		options.elem = $(options.elem);
		
		
		if(!options.elem.hasClass('layui-form') && options.elem.parents('.layui-form').length == 0){
			options.elem.addClass('layui-form');
		}
		options.elem.addClass('layui-regionContent');
		options.elem.css('width', options.width);
		if(!options.border){
			options.elem.css('border', 'none');
		}
		
		options.elem.html(getCheckBoxs(options.name));
		
		//初始值
		if(options.value.indexOf('所有权限') > -1){
			options.elem.find(':checkbox').prop('checked', true);
		}else{
			for(var i=0; i<options.value.length; i++){
				var value = options.value[i]
				,$elem = options.elem.find(':checkbox[value="'+value+'"]');
				
				$elem.prop('checked', true);
				
				if(value.indexOf('-') < 0){ //省
					$elem.parent().find('.city :checkbox').prop('checked', true);
				}
			}
		}
		form.render('checkbox');	
		
		renderParentDom();
		initName(options);
		
		options.elem.find('.parent').mouseover(function() {
			$(this).find('.city').show();
		});
		options.elem.find('.parent').mouseout(function() {
			$(this).find('.city').hide();
		});
		
		form.on('checkbox(regionCheckBox)', function(data) {
			if(data.elem.value == '所有权限') { //选择所有权限
				if(data.elem.checked) {
					options.elem.find(':checkbox').prop('checked', true);
				} else {
					options.elem.find(':checkbox').prop('checked', false);
				}
			} else {
				//选择省（不包括直辖市）
				if($(data.elem).parent().hasClass('parent')) { 
					if(data.elem.checked) {
						var ii = $(data.elem).next();
						$(data.elem).parent().find('.city :checkbox').prop('checked', true);
					} else {
						$(data.elem).parent().find('.city :checkbox').prop('checked', false);
					}
				}
				//选择城市
				if($(data.elem).parent().hasClass('city')) {
					$(data.elem).parents('.parent').attr('name', options.name);
					if(data.elem.checked) {
						var is_all = true;
						$(data.elem).parent().find(':checkbox').each(function(i, item) {
							if(! item.checked) {
								is_all = false;
								return false;
							}
						});
						if(is_all) {
							$(data.elem).parents('.parent').find(':checkbox:first').prop('checked', true);
						}
					} else {
						$(data.elem).parents('.parent').find(':checkbox:first').prop('checked', false);						
					}
				}
				//选择除所有权限外任意
				if(data.elem.checked) { 
					var is_all = true;
					options.elem.find(':checkbox[value!="所有权限"]').each(function(i, item) {
						if(! item.checked) {
							is_all = false;
							return false;
						}
					});
					if(is_all) {
						options.elem.find(':checkbox[value="所有权限"]').prop('checked', true);
					}
				} else {
					options.elem.find(':checkbox[value="所有权限"]').prop('checked', false);
				}
			}
			form.render('checkbox');
			
			renderParentDom();
			initName(options);
			
			options.change(data);
		});
		
		options.ready();
	}
  
	var regionList = {
		'商机':{
			'商机信息':['添加','修改','删除'],
			'商机跟进':['添加','修改','删除'],
			'商机费用':['添加','修改','删除','审批']
		},
		'权限':{
			'角色':['添加','修改','删除'],
			'资源':['添加','修改','删除'],
			'权限':['添加','修改','删除']
		}
	};
	
	function getCheckBoxs(name){
		var skin = 'primary';
		
		var boxs = '<div class="layui-form-item" style="margin-left:15px;">' +
				   '<input type="checkbox" name="' + name + '" value="所有权限" title="所有权限" lay-skin="' + skin + '" lay-filter="regionCheckBox">' +
				   '</div>';
		
		for(var area in regionList){
			boxs += '<div class="layui-form-item" style="margin-bottom:0;">' +
					'<label class="layui-form-label area">' + area + '：</label>' +
					'<div class="layui-input-block province">' +
					'<ul>';
			for(var province in regionList[area]){
				var city_num = regionList[area][province].length;
				boxs += '<li' + (city_num > 0 ? ' class="parent"' : '') + '>' +
						'<input type="checkbox" name="' + name + '" value="' + province + '" title="' + province + '" lay-skin="' + skin + '" lay-filter="regionCheckBox">';
				
				if(city_num > 0){
					boxs += '<div class="city">';
					for(var i=0; i<city_num; i++){
						var city = regionList[area][province][i];
						boxs += '<input type="checkbox" name="' + name + '" value="' + province + '-' + city + '" title="' + city + '" lay-skin="' + skin + '" lay-filter="regionCheckBox">';
					}	
					boxs += '</div>';					
				}
				
				boxs += '</li>';
			}
			boxs += '</div></div>';
		}
				   
		return boxs;
	}
	
	function initName(options){
		var $elem = $(options.elem);
		
		$elem.find(':checkbox').attr('name', options.name);
		
		if($elem.find(':checkbox[value="所有权限"]').prop('checked')){
			$elem.find(':checkbox[value!="所有权限"]').removeAttr('name');
		}else{
			$('.parent').find(':checkbox:first:checked').each(function() {
				$(this).parent().find('.city :checkbox').removeAttr('name');
			});
		}
	}
	
	function renderParentDom(){
		$('.parent').find(':checkbox:first').not(':checked').each(function() {
			var is_yes_all = true;
			var is_no_all = true;
			$(this).parent().find('.city :checkbox').each(function(i, item) {
				if(item.checked) {
					is_no_all = false;
				} else {
					is_yes_all = false;
				}
			});
			if(!is_yes_all && !is_no_all) {
				$(this).parent().find('.layui-icon:first').removeClass('layui-icon-ok');
				$(this).parent().find('.layui-icon:first').css('border-color', '#5FB878');
				$(this).parent().find('.layui-icon:first').css('background-color', '#5FB878');
			}
		});
	}
	
	regionCheckBox.render = function(options){
		var ins = new Class(options);
		return thisIns.call(ins);
	};
	
	layui.link(layui.cache.base + 'regionCheckBox/regionCheckBox.css?v=1', function(){
		
	}, 'regionCheckBox');

	exports('regionCheckBox', regionCheckBox);
});    