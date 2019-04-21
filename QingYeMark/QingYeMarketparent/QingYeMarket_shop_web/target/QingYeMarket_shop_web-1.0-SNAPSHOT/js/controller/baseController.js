app.controller('baseController',function ($scope) {
    // 控制层共性抽取
    $scope.searchEntity = {};//定义搜索对象
    $scope.selectIds = [];//选中的ID集合

    //刷新列表
    $scope.reloadList = function () {
        //切换页码
        $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage)
    };

    //复选更新
    $scope.updateSelection = function ($event, id) {

        if ($event.target.checked) {
            //如果复选框被选中,push方法：向数组中添加元素
            $scope.selectIds.push(id);
        } else {

            var idx = $scope.selectIds.indexOf(id);
            //从数组的指定位置移除指定个数的元素 ，参数1为位置  ，参数2位移除的个数
            $scope.selectIds.splice(idx, 1);
        }

    };
    /**
     分页控件配置
     *
     paginationConf 变量各属性的意义：
     currentPage：当前页码
     totalItems:总条数
     itemsPerPage:每页记录数
     perPageOptions：页码选项
     onChange：更改页面时触发事件
     * @type {{currentPage: number, totalItems: number, itemsPerPage: number, perPageOptions: number[], onChange: onChange}}
     */

    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function () {
            $scope.reloadList();//重新加载
        }
    };

    $scope.jsonToString =function (jsonString,key) {
        //将json字符串转换为json对象
        var json = JSON.parse(jsonString);
        var value="";
        for(var i=0;i<json.length;i++){
            if(i>0){
                value+=","
            }
            //[i][key]=json[i].key,获取文本框的值
            value+=json[i][key];
        }
        return value;
    }
    //在list集合中根据key的值查询是否存在规格对象
    $scope.searchObjectByKey = function (list,key,keyValue) {
        for(var i =0;i<list.length;i++){
            //若果存在则返回规格对象
            if(list[i][key]==keyValue){
                return list[i];
            }
        }
        return null;
    }
});