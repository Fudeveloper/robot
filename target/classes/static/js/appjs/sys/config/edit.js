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
		url : "/sys/config/update",
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
            remark : {
				required : true
			},
            value : {
				required : true,
				// minlength : 2
			},
            name : {
				required : true,
				// minlength : 6
			},

		},
		messages : {

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