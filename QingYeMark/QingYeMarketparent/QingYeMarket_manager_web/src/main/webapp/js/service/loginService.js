//登陆服务层

app.service('LoginService',function ($http) {
    //读取登录人名称
    this.LoginName=function () {
        return $http.get("../login/name.do")
    }
});