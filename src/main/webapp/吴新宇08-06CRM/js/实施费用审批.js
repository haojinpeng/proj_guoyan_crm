var jsondata = {
	"title": "newFlow_1",
	"nodes": {
		"demo_node_1": {
			"name": "开始",
			"left": 42,
			"top": 38,
			"type": "start round mix",
			"width": 26,
			"height": 26,
			"alt": true
		},
		"demo_node_2": {
			"name": "结束",
			"left": 919,
			"top": 44,
			"type": "end round mix",
			"width": 26,
			"height": 26,
			"alt": true
		},
		"demo_node_3": {
			"name": "实施费用审批",
			"left": 155,
			"top": 39,
			"type": "task",
			"width": 111,
			"height": 44,
			"marked": true,
			"alt": true
		},
		"demo_node_4": {
			"name": "主管审批",
			"left": 363,
			"top": 39,
			"type": "task",
			"width": 127,
			"height": 44,
			"alt": true
		},
		"1596682691401": {
			"name": "财务审批",
			"left": 531,
			"top": 41,
			"type": "task",
			"width": 125,
			"height": 39,
			"alt": true
		},
		"1596682801331": {
			"name": "总经理",
			"left": 712,
			"top": 41,
			"type": "task",
			"width": 117,
			"height": 40,
			"alt": true
		}
	},
	"lines": {
		"demo_line_5": {
			"type": "sl",
			"from": "demo_node_3",
			"to": "demo_node_4",
			"name": "提交申请"
		},
		"demo_line_6": {
			"type": "sl",
			"from": "demo_node_1",
			"to": "demo_node_3",
			"name": ""
		},
		"demo_line_7": {
			"type": "tb",
			"M": 14.5,
			"from": "demo_node_4",
			"to": "demo_node_3",
			"name": "驳回",
			"alt": true
		},
		"1596682744999": {
			"type": "sl",
			"from": "demo_node_4",
			"to": "1596682691401",
			"name": "通过",
			"alt": true
		},
		"1596682758146": {
			"type": "tb",
			"from": "1596682691401",
			"to": "demo_node_4",
			"name": "驳回",
			"alt": true,
			"M": 138.75
		},
		"1596682815031": {
			"type": "sl",
			"from": "1596682691401",
			"to": "1596682801331",
			"name": "同意",
			"alt": true
		},
		"1596682842476": {
			"type": "sl",
			"from": "1596682801331",
			"to": "demo_node_2",
			"name": "同意/不同意",
			"alt": true
		}
	},
	"areas": {},
	"initNum": 23
};