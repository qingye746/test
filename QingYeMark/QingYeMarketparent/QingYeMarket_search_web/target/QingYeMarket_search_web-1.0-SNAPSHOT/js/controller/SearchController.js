app.controller('searchController', function ($scope, searchService,$location) {
    //定义点击分类标签的搜索对象的格式 category:商品分类
    $scope.searchMap = {
        'keywords': '',
        'category': '',
        'brand': '',
        'spec': {},
        'price': '',
        'pageNo': 1,
        'pageSize': 20,
        'sort': '',
        'sortField': ''
    };

    //搜索
    $scope.search = function () {
        //将传来的当前页转为Int类型

        searchService.search($scope.searchMap).success(
            function (response) {
                $scope.resultMap = response;
                //每次查询初始化页数
                // $scope.searchMap.pageNo=1;
                buildPageLabel();//构建分页栏

            }
        )
    };

    buildPageLabel = function () {
        //构建分页栏
        $scope.pageLabel = [];
        var firstPage = 1;//开始的页码
        var lastPage = $scope.resultMap.totalPages;//尾页
        if ($scope.resultMap.totalPages > 5) {
            //如果当前页码小于等于三页怎展示前五页内容

            if ($scope.searchMap.pageNo <= 3) {
                lastPage = 5
            } else if ($scope.searchMap.pageNo >= $scope.resultMap.totalPages - 2) {
                //若当前页码大于总页数-2则展示尾5页
                firstPage = $scope.resultMap.totalPages - 4;
            } else {
                // 展示当前页的前俩页和后俩页
                firstPage = $scope.searchMap.pageNo - 2;

                lastPage = $scope.searchMap.pageNo + 2;

            }

        }

        //构建页码
        for (var i = firstPage; i <= lastPage; i++) {
            $scope.pageLabel.push(i);
        }
    };
    //页面添加规格选项

    $scope.addSearchItem = function (key, value) {
        if (key == 'category' || key == 'brand' || key == 'price') {
            // category:手机
            $scope.searchMap[key] = value;
        } else {
            $scope.searchMap.spec[key] = value;
        }
        $scope.search();//后端查询
    };

    //撤销搜索项
    $scope.deleteSearchItem = function (key) {
        if (key == 'category' || key == 'brand' || key == 'price') {
            //还原为原来的样子
            $scope.searchMap[key] = '';
        } else {
            //根据key删除规格
            delete $scope.searchMap.spec[key];
        }
        $scope.search();//后端查询
    };

    //分页查询
    $scope.queryByPage = function (pageNO) {

        if (pageNO < 1 || pageNO > $scope.resultMap.totalPages) {
            return;
        }
        //searchMap里的当前页的值为前端传过来的值
        $scope.searchMap.pageNo = pageNO;
        $scope.searchMap.pageNo = parseInt($scope.searchMap.pageNo);
        $scope.search();//后端查询
    };

    //判断当前页为第一页
    $scope.isTopPage = function () {

        if ($scope.searchMap.pageNo == 1) {
            return true;
        } else {
            return false;
        }
    };

    //判断当前页是否未最后一页
    $scope.isEndPage = function () {
        if ($scope.searchMap.pageNo == $scope.resultMap.totalPages) {
            return true;
        } else {
            return false;
        }
    };

    //排序
    $scope.sortSearch = function (sort,sortField) {
        //从前端获取值放入searchMap集合中
        $scope.searchMap.sort=sort;
        $scope.searchMap.sortField=sortField;

        $scope.search();//后端查询
    };

    //判断关键字是不是品牌
    $scope.keywordsIsBrand=function(){
        for(var i = 0;i < $scope.resultMap.brandList.length ; i++ ){
            if($scope.searchMap.keywords.indexOf( $scope.resultMap.brandList[i].text)>=0){
                //如果搜索内容不包含在手机品牌里就返回true
                return true;
            }

        }
        return false;
    };

    //加载查询字符串
    $scope.loadkeywords=function(){
        $scope.searchMap.keywords = $location.search()['keywords'];
        $scope.search();//后端查询
    }
});