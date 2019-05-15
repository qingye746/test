var app = angular.module('qingyemarket', []);
//定义过滤器
app.filter('trustHtml',['$sce',function ($sce) {
    // date:传入参数时被过滤的内容
    return function (date) {
        //信任html，返回的是过滤后的内容
        return $sce.trustAsHtml(date)

    }
}]);