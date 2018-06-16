// 以下为官方示例
$().ready(function() {
	validateRule();
	// $("#signupForm").validate();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$("#roleIds").val(getCheckedRoles());
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sys/warn/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg(data.msg);
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.msg(data.msg);
			}

		}
	});

}
function getCheckedRoles() {
	var adIds = "";
	$("input:checkbox[name=role]:checked").each(function(i) {
		if (0 == i) {
			adIds = $(this).val();
		} else {
			adIds += ("," + $(this).val());
		}
	});
	return adIds;
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
            id : {
				required : true
			},
            warnName : {
				required : true,
				// minlength : 2
			},
            receiver : {
				required : true,
				// minlength : 6
			},
            content : {
                required : true,
                // minlength : 6
            },
			agree : "required"
		},
		messages : {

            id : {
				required : icon + "请输入序号"
			},
            warnName : {
				required : icon + "请输入报警方式名称",
				// minlength : icon + "必须两个字符以上"
			},
			password : {
				required : icon + "请输入您的密码",
			},
            receiver:{
                required : icon + "请输入接收人",
			},
            content:{
                required : icon + "请输入内容",
            }
		}
	})
}
// var openDept = function(){
// 	layer.open({
// 		type:2,
// 		title:"选择部门",
// 		area : [ '300px', '450px' ],
// 		content:"/sys/dept/treeView"
// 	})
// }
// function loadDept( deptId,deptName){
// 	$("#deptId").val(deptId);
// 	$("#deptName").val(deptName);
// }