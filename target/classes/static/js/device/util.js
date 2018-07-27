//构造img path
function construct_img_path(status, img_id) {
    return '/img/device/' + status + '/' + img_id + '.gif';
}

//根据状态，img_id 更换图片
function change_img_by_status(img_id, status) {
    // console.log($img)
    var $img = $("#img" + img_id);
    console.log($img);
    $img.attr("src", construct_img_path(status, img_id));
    $img.css("cursor", "pointer");
    $img.click(function () {
        // alert("1")

    })
}

// 根据img_id 绑定弹框事件
function add_submit_page(img_id, interval_id) {
    layer.open({
        title: '故障记录',
        type: 2,
        area: ['730px', '540px'],
        fixed: true,
        maxmin: true,
        content: '/device/report?id=' + img_id ,
        scrollbar: false,
        resize: false,
        id: "report_page"
    });

}

//闪烁图片（当前状态和故障状态交替）
function blink_img(img_id,change_status) {
    var i = 0;
    var $img = $("#img" + img_id);
    var before_src = $img.attr("src");
    var warn_src = construct_img_path(change_status, img_id);
    $img.css("cursor", "pointer");
    $img.click(function () {
        add_submit_page($img.attr("id"));
    });

    function inner() {
        // console.log($img);
        if (i === 0) {
            $img.attr("src", warn_src);
            i = 1;
        } else {
            $img.attr("src", before_src);
            i = 0
        }
    }

    return setInterval(inner, 600)
}

// 格式化时间
function dataFormat(now, mask) {
    var d = now;
    var zeroize = function (value, length) {
        if (!length) length = 2;
        value = String(value);
        for (var i = 0, zeros = ''; i < (length - value.length); i++) {
            zeros += '0';
        }
        return zeros + value;
    };

    return mask.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g, function ($0) {
        switch ($0) {
            case 'd':
                return d.getDate();
            case 'dd':
                return zeroize(d.getDate());
            case 'ddd':
                return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][d.getDay()];
            case 'dddd':
                return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][d.getDay()];
            case 'M':
                return d.getMonth() + 1;
            case 'MM':
                return zeroize(d.getMonth() + 1);
            case 'MMM':
                return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][d.getMonth()];
            case 'MMMM':
                return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][d.getMonth()];
            case 'yy':
                return String(d.getFullYear()).substr(2);
            case 'yyyy':
                return d.getFullYear();
            case 'h':
                return d.getHours() % 12 || 12;
            case 'hh':
                return zeroize(d.getHours() % 12 || 12);
            case 'H':
                return d.getHours();
            case 'HH':
                return zeroize(d.getHours());
            case 'm':
                return d.getMinutes();
            case 'mm':
                return zeroize(d.getMinutes());
            case 's':
                return d.getSeconds();
            case 'ss':
                return zeroize(d.getSeconds());
            case 'l':
                return zeroize(d.getMilliseconds(), 3);
            case 'L':
                var m = d.getMilliseconds();
                if (m > 99) m = Math.round(m / 10);
                return zeroize(m);
            case 'tt':
                return d.getHours() < 12 ? 'am' : 'pm';
            case 'TT':
                return d.getHours() < 12 ? 'AM' : 'PM';
            case 'Z':
                return d.toUTCString().match(/[A-Z]+$/);
            // Return quoted strings with the surrounding quotes removed
            default:
                return $0.substr(1, $0.length - 2);
        }
    });
};

//  取得get参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

function get_img_index_by_element(img) {
    var id =  $(img).attr("id")
    return id.split("img")[1]
}