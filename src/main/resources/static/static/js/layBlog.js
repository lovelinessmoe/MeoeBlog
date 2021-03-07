/**
 @Name: layuiQuietBlog - 静谧风格个人博客模板
 @Author: xuzhiwen
 @Copyright: layui.com
 */
layui.api = {
    api: "http://localhost:8080/"

    // api: "http://www.doubi.link:7070/"
};
layui.define(['element', 'carousel', 'laypage', 'form', 'laytpl'], function (exports) {
        var $ = layui.jquery
            , laytpl = layui.laytpl
            , element = layui.element
            , carousel = layui.carousel
            , laypage = layui.laypage
            , form = layui.form;
        var leftHtml = [
            "<li class=\"layui-nav-item\"><a href="+layui.api.api+"article>文章<br/>ARTICLE</a></li>",
            "<li class=\"layui-nav-item \"><a href="+layui.api.api+"message>留言<br/>MESSAGE</a></li>",
            "<li class=\"layui-nav-item last\"><a href="+layui.api.api+"about>关于<br/>ABOUT</a></li>"
        ]
        for (var i = 0; i < leftHtml.length; i++) {
            $(".layui-nav").append(leftHtml[i]);
        }
        var names = window.location.pathname.split('/');
        var leftItme = $(".layui-nav .layui-nav-item");
        switch (names[names.length - 1]) {
            case "message.html":
                $(leftItme[1]).addClass("layui-this");
                break;
            case "about.html":
                $(leftItme[2]).addClass("layui-this");
                break;
            default:
                $(leftItme[0]).addClass("layui-this");
                break;
        }
        var edit = false;
        document.onkeydown = function (event) {
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if (e && e.ctrlKey && e.keyCode == 81) {
                if (edit) {
                    return;
                }
                edit = true;
                var oid = getQueryVariable('oid');
                console.log("jsoid:"+oid);
                var url = "/article/Edit/";
                if(oid){
                    url += oid;
                }
                layer.open({
                    type: 2
                    , title: "文章内容"
                    , content: url
                    , maxmin: false
                    , area: ["80%", "80%"]
                    , btn: ["确定", '取消']
                    , yes: function (index, layero) {
                        var iframeWindow = top.window['layui-layer-iframe' + index]
                            , submitID = 'submit'
                            , submit = layero.find('iframe').contents().find('#' + submitID);
                        //监听提交
                        iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                            edit = false;
                            data.field.content = iframeWindow.layui.layedit.getContent(iframeWindow.layui.layedit.index);
                            $.post(layui.api.api + "/article/addOrUpdateArticle", data.field, function (res) {
                                if (res.code == 0) {
                                    layer.close(index);
                                }
                                layer.msg(res.msg, {
                                    icon: 1
                                });
                            });
                        });
                        submit.trigger('click');
                    }
                });
            } else if (e && e.keyCode == 13) {
                search();
            }
        };
        $(".layui-icon-search").click(function () {
            search();
        });

        function search() {
            window.location.href = './article.html?key=' + $(".seach-box .layui-input").val()
        }

        $.post(layui.api.api + "api/visit/addVisit", {goLink: document.referrer}, function (res) {
        });
        $.post(layui.api.api + "api/visit/getAllCount", {}, function (res) {
            $(".layui-card-body .visitors").html("<span >" + res.data + "</span><br/><span>访问人数</span>")
        });
        $.post(layui.api.api + "api/article/getAllCount", {}, function (res) {
            $(".layui-card-body .txt").html("<span >" + res.data + "</span><br/><span>文章</span>")
        });

        /*laypage.render({
          elem: 'demo1'
          ,count: 100 //数据总数
          ,jump: function(obj){
            //console.log(obj)
          }
        });*/


        exports('layBlog', {});
    }
);