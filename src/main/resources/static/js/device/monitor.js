function init() {

    var init_status = "waiting";
    init_imgs_by_status(init_status);
    // {"normal","running","waiting","warning"}
    var status_enums = {1:"正常",2:"待机",3:"运行",4:"故障未报修",5:"故障已报修"};
    // alert(status_enums[a])
    $("#img1").contextMenu({
        menu: [
            {
                text: "当下状态",
                callback: function () {
                    layer.alert("当下状态："+"正常")
                }
            },
            {
                text: "基本情况",
                callback: function () {
                    // alert(1)
                    layer.open({
                        title: '设备基本情况',
                        type: 2,
                        area: ['730px', '540px'],
                        fixed: true,
                        maxmin: true,
                        content: '/device/baseinfo?id=1',
                        scrollbar: false,
                        resize: false,
                        id: "report_page"
                    });
                }
            },
            {
                text: "周边环境",
                callback: function () {
                    layer.open()
                }
            },
            {
                text: "首检",
                callback: function () {
                    layer.open()
                }
            },
            {
                text: "待产计划",
                callback: function () {
                    layer.open()
                }
            }

        ]
    });


}

//关闭按钮
// $("#cancel").on("click", function (event) {
//     // alert(1)
//     var index = parent.layer.getFrameIndex(window.name);
//     parent.layer.close(index)
//     event.stopPropagation()
// });

//取消图片闪烁
function cancel_blink_img(img_id) {
    $("#" + img_id).attr("src", construct_img_path("warning", 2))
}


function init_imgs_by_status(init_status) {

    var imgs=$("img");
    $.each(imgs,function (index,img) {
        $(img).attr("src",construct_img_path(init_status,get_img_index_by_element(this)))
    })
}


