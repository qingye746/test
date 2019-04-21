//定义控制器
app.controller("brandController", function ($scope, $controller, brandService) {
    //控制器继承（伪继承）
    $controller('baseController',{$scope:$scope});

    //分页
    $scope.findPage = function (page, rows) {
        brandService.findPage(page, rows).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        )
    };
    //新增或添加品牌
    $scope.save = function () {
        var object = null;
        if ($scope.entity.id != null) {
            object = brandService.update($scope.entity);
        } else {
            object = brandService.save($scope.entity);
        }
        object.success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();//重新加载
                } else {
                    alert(response.massage);
                }
            }
        )
    };
    //查询品牌
    $scope.findOne = function (id) {
        brandService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        )
    };

    //删除品牌
    $scope.del = function () {
        brandService.del($scope.selectIds).success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();//重新加载
                } else {
                    alert(response.massage);
                }
            }
        )
    };

    //查询分页
    $scope.search = function (page, rows) {

        brandService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        )
    }
});