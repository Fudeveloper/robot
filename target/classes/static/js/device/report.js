function init() {
    $(".layui-form-switch").click(function () {
        var $prepare = $("#prepare_time");
        $prepare.toggle();

        //隐藏元素时,不置入表单
        if ($prepare.css("display") !== "none") {
            $prepare.find("input").attr("name", "prepare_time").attr("lay-verify","required")
        } else {
            $prepare.find("input").attr("name", "").attr("lay-verify","")
        }
    });
}



