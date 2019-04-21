//自定义定义服务层
app.service("brandService", function ($http) {
    //分页
    this.findPage = function (page, rows) {
        return $http.get('../brand/findPage.do?page=' + page + '&rows=' + rows);
    };
    //查询单个品牌
    this.findOne = function (id) {
        return $http.get('../brand/findOne.do?id=' + id);
    };
    //新增品牌
    this.save = function (entity) {
        return $http.post('../brand/brandAdd.do?', entity);
    };

    //修改品牌
    this.update = function (entity) {
        return $http.post('../brand/brandUpdate.do?', entity);
    };

    //删除品牌
    this.del = function (selectIds) {
        return $http.get('../brand/brandDelete.do?ids=' + selectIds);
    };

    this.search = function (page, rows, entity) {

        return $http.post('../brand/search.do?page=' + page + '&rows=' + rows, entity)
    }
    //下拉列表
    this.selectOptionList = function () {
        return  $http.get('../brand/selectOptionList.do');
    }
});