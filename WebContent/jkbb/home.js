// JavaScript Document
var HomepageFavorite = {
    //设为首页
    Homepage: function () {
        if (document.all) {
            document.body.style.behavior = 'url(#default#homepage)';
            document.body.setHomePage(window.location.href);
        }
        else if (window.sidebar) {
            if (window.netscape) {
                try {
                    netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
                }
                catch (e) {
                    alert("该操作被浏览器拒绝，如果想启用该功能，请在地址栏内输入 about:config,然后将项 signed.applets.codebase_principal_support 值该为true");
                    history.go(-1);   //这里我用firefox测试的时候出错，我把它注释掉，改成 return false;
                }
            }
            var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
            prefs.setCharPref('browser.startup.homepage', window.location.href);
        }
    }
    ,
    //加入收获
    Favorite: function Favorite(sURL, sTitle) {
        try {
            window.external.addFavorite(sURL, sTitle);
        }
        catch (e) {
            try {
                window.sidebar.addPanel(sTitle, sURL, "");
            }
            catch (e) {
                alert("加入收藏失败,请手动添加.");
            }
        }
    }
}

var flag = false;
function DrawImage(ImgD, FitWidth, FitHeight) {
    var image = new Image();
    image.src = ImgD.src;
    if (image.width > 0 && image.height > 0) {
        flag = true;
        if (image.width / image.height >= FitWidth / FitHeight) {
            if (image.width > FitWidth) {
                ImgD.width = FitWidth;
                ImgD.height = (image.height * FitWidth) / image.width;
            } else {
                ImgD.width = image.width;
                ImgD.height = image.height;
            }
            //ImgD.alt="原图片大小("+image.width+"×"+image.height+")";
        }
        else {
            if (image.height > FitHeight) {
                ImgD.height = FitHeight;
                ImgD.width = (image.width * FitHeight) / image.height;
            } else {
                ImgD.width = image.width;
                ImgD.height = image.height;
            }
            //ImgD.alt="原图片大小("+image.width+"×"+image.height+")";
        }
    }
}

//返回顶部
function goTop(acceleration, time) {
    acceleration = acceleration || 0.1; //加速度 acceleration
    time = time || 15; //时间间隔
    var x = 0, y = 0;
    if (document.documentElement) {
        x = document.documentElement.scrollLeft || 0;
        y = document.documentElement.scrollTop || 0;
    }
    var speed = 1 + acceleration; //滚动速度
    //  floor()返回值为小于等于其数值参数的最大整数值 假如 y=36 speed=1.1   36/1.1=32;
    // scrollTo()滚动条将要移动到x y 经过计算得出的值
    window.scrollTo(Math.floor(x / speed), Math.floor(y / speed));
    //如果 x (scrollLeft!=0)||y(scrollTop!=0)还未移动到顶部.所以setTimeout()继续执行该函数
    if (x > 0 || y > 0) {
        var invokeFunction = "goTop(" + acceleration + ", " + time + ")";
        window.setTimeout(invokeFunction, time);
    }
}