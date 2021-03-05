window.onload = function () {
    if(typeof(qiniu) == "undefined") {
        setTimeout(function () {
            layui.layer.msg("文件上传加载失败！ 将无法上传文件！");
        },2000);
        return;
    }
    myQiniu.initToken();
    setTimeout(function () {
        myQiniu.initToken();
    },3600*1000);
};
var myQiniu = {
    head: "http://images.mix2.club/",
    initToken : function(){
        layui.$.post(top.layui.api.api+"qiniu/token", {}, function (res) {
            if (res.code === 0) {
                myQiniu.upToken = res.data;
            } else {
                layer.msg(res.msg);
            }
        });
    },
    upToken : null,
    /**
     * 参数格式
     * {
     *     file : 文件对象
     *     next : 方法   用于进度条
     *     complete : 方法 成功回调
     *     error : 方法 失败回调
     * }
     * @param op
     */
    upload : function (op) {
        let config = {
            //表示是否使用 cdn 加速域名，为布尔值，true 表示使用，默认为 false。
            useCdnDomain: true,
            // 根据具体提示修改上传地区,当为 null 或 undefined 时，自动分析上传域名区域
            region: null
        };
        let putExtra = {
            fname: "",
            //文件原文件名
            params: {},
            //用来放置自定义变量
            mimeType: null
            //用来限制上传文件类型，为 null 时表示不对文件类型限制；限制类型放到数组里： ["image/png", "image/jpeg", "image/gif"]
        };
        let observable = qiniu.upload(op.file, null, this.upToken, putExtra, config);
        observable.subscribe({
            next: (res) => {
                // 主要用来展示进度
                if(op.next !== undefined){
                    op.next(res);
                }
            },
            error: (err) => {
                // 失败报错信息
                if(op.error !== undefined){
                    op.error(err);
                }
            },
            complete: (res) => {
                // 接收成功后返回的信息
                if(op.complete !== undefined){
                    op.complete(res);
                }
            }
        })

    }
};