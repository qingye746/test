app.service('uploadService',function ($http) {
    this.uploadFile=function () {
        //上传带着2进制的数据的类，上传需要的类

        var formDate = new FormData();
        formDate.append("file",file.files[0])
        return $http({
            //上传方式
            method:'POST',
            //上传地址
            url:"../upload.do",
            //上传的数据
            data: formDate,
            //定义上传的数据类型为未定义，否则就是json
            headers: {'Content-Type':undefined},
            //对整个表单进行
            transformRequest: angular.identity
        });
    }
});