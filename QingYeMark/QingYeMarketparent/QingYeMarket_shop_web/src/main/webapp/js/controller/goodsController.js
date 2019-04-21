//控制层 goodsController,$location传递静态页面的内容
app.controller('goodController', function ($scope, $controller, goodsService,$location, uploadService, itemCatService, typeTemplateService) {

    $controller('baseController', {$scope: $scope});//继承

    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        goodsService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        );
    }

    //分页
    $scope.findPage = function (page, rows) {
        goodsService.findPage(page, rows).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    //查询实体
    $scope.findOne = function (id) {
        var id = $location.search()['id'];
        if(id==null){
            return
        }
        goodsService.findOne(id).success(
            function (response) {

                $scope.entity = response;
                //商品介绍
                editor.html($scope.entity.goodsDesc.introduction);
                //商品图片字符串数据转json
                $scope.entity.goodsDesc.itemImages =JSON.parse($scope.entity.goodsDesc.itemImages);
                //扩展属性字符串数据转json
                $scope.entity.goodsDesc.customAttributeItems=JSON.parse($scope.entity.goodsDesc.customAttributeItems);
                //规格参数字符串数据转json
                $scope.entity.goodsDesc.specificationItems=JSON.parse($scope.entity.goodsDesc.specificationItems);

                //规格对象名字
                for(var i = 0 ;i<$scope.entity.itemList.length;i++){
                    $scope.entity.itemList[i].spec=JSON.parse( $scope.entity.itemList[i].spec);
                }
            }
        );
    };


    //添加
    $scope.save = function () {
        //提取文本编辑器的值
        $scope.entity.goodsDesc.introduction=editor.html();
        var serviceObject;//服务层对象
        if($scope.entity.goods.id!=null){//如果有ID
            serviceObject=goodsService.update( $scope.entity ); //修改
        }else{
            serviceObject=goodsService.add( $scope.entity  );//增加
        }
        serviceObject.success(
            function(response){
                if(response.success){
                    alert('保存成功');
                    location.href='goods.html';
                }else{
                    alert(response.massage);
                }
            }
        );
    };


    //批量删除
    $scope.dele = function () {
        //获取选中的复选框
        goodsService.dele($scope.selectIds).success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();//刷新列表
                    $scope.selectIds = [];
                }
            }
        );
    }

    $scope.searchEntity = {};//定义搜索对象

    //搜索
    $scope.search = function (page, rows) {
        goodsService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }
    $scope.image_entity = {};
    //上传文件
    $scope.uploadFile = function () {
        //uploadService
        uploadService.uploadFile().success(
            function (response) {
                if (response.success) {
                    $scope.image_entity.url = response.massage;
                } else {
                    alert(response.massage)
                }
            }
        )
    }
    $scope.entity = {goods: {}, goodsDesc: {itemImages: [],specificationItems:[]}};//定义页面实体结构
    //添加图片列表
    //将当前上传的图片实体存入图片列表
    $scope.add_image_entity = function () {
        $scope.entity.goodsDesc.itemImages.push($scope.image_entity)
    }
    //移除图片
    $scope.remove_image_entity = function (index) {
        $scope.entity.goodsDesc.itemImages.splice(index, 1);

    }
    //读取一级分类

    $scope.selectItemCat1List = function () {
        itemCatService.findByParentId(0).success(
            function (response) {
                $scope.itemCat1List = response;
            }
        )
    };
    //读取2级分类 newValue变化后的数据，oldValue变化前的数据
    // 监控1级分类的变化
    $scope.$watch("entity.goods.category1Id", function (newValue, oldValue) {
        itemCatService.findByParentId(newValue).success(
            function (response) {
                $scope.itemCat2List = response;
            }
        )
    })
    //读取3级分类 newValue变化后的数据，oldValue变化前的数据
    // 监控2级分类的变化
    $scope.$watch("entity.goods.category2Id", function (newValue, oldValue) {
        itemCatService.findByParentId(newValue).success(
            function (response) {
                $scope.itemCat3List = response;
            }
        )
    })
    // 监控3级分类的变化,获取模板ID
    $scope.$watch("entity.goods.category2Id", function (newValue, oldValue) {
        itemCatService.findOne(newValue).success(
            function (response) {
                $scope.entity.goods.typeTemplateId = response.typeId;
            }
        )
    });

    // 监控模板的变化,获取对应的规格参数
    $scope.$watch("entity.goods.typeTemplateId", function (newValue, oldValue) {
        typeTemplateService.findOne(newValue).success(
            function (response) {
                $scope.typeTemplateId= response;

                //将字符串类型转化为json类型
                $scope.typeTemplateId.brandIds=JSON.parse($scope.typeTemplateId.brandIds);
                if($location.search()['id']==null){
                    //扩展属性
                    $scope.entity.goodsDesc.customAttributeItems =JSON.parse($scope.typeTemplateId.customAttributeItems);
                }

            }
        );
        //
        typeTemplateService.findSpecList(newValue).success(
            function (response) {
               $scope.specList =response;
            }
        )
    });
     //勾选网络规格，value是值，name是规格名字
    $scope.updateSpecAttribute =function ($event,name, value) {
        var object = $scope.searchObjectByKey($scope.entity.goodsDesc.specificationItems,'attributeName',name);

        if (object!=null){
            //如果被勾选了
            if($event.target.checked){
                //如果有参规格名称对象没有值，则将值添加到其中
                object.attributeValue.push(value);
            }else {
               //取消勾选
                //移除选项，1代表删除元素的个数
                object.attributeValue.splice(object.attributeValue.indexOf(value),1);
                if(object.attributeValue.length==0){
                    //如果规格对象中什么参数都没有则删除整个对象
                    $scope.entity.goodsDesc.specificationItems.splice(
                        $scope.entity.goodsDesc.specificationItems.indexOf(object),1);
                }
            }


        }else {
            //如果没有规格名称，则添加规格名称对象
            $scope.entity.goodsDesc.specificationItems.push({"attributeName":name,"attributeValue":[value]})
        }
    };

    //创建SKU列表
    $scope.createItemList=function(){
        //组合实体类中的SKU列表itemList，包括b_item表内数据，和将b_item表内数据打包成spec元素并进行初始化.
        $scope.entity.itemList=[{spec:{},price:0,num:99999,status:'0',isDefault:'0' }]
        //用户勾选的变量
       var items=$scope.entity.goodsDesc.specificationItems;
        //对Items里的对象进行遍历。每个遍历出的对象根据Value值进行遍历，得出（对象个数*value值个数乘积）的条数结果
        for(var i=0;i<items.length;i++){
            $scope.entity.itemList=addColumn($scope.entity.itemList,items[i].attributeName,items[i].attributeValue);
        }
    };

    //深克隆方法
    addColumn =function (list,columnName,columnValues) {
        var newList=[];
        for(var i=0;i<list.length;i++){
            //获取第一层对象,作为原始数据进行保存
            var oldRow=list[i];
            for (var j=0;j<columnValues.length;j++){
                //深克隆获取第一层对象的相同的对象但不同地址
                var newRow = JSON.parse(JSON.stringify(oldRow));
                // 添加第一层对象的数据变为第二层数据
                newRow.spec[columnName]=columnValues[j]
                newList.push(newRow)
            }
        }
        //返回克隆后的对象
        return newList;
    };

    $scope.status=['未审核','已审核','审核未通过','已关闭'];

    // 初始化商品分类列表
    $scope.itemCatList = [];

    $scope.findItemCatList=function () {
        itemCatService.findAll().success(
            function (response) {
                for(var i=0;i<response.length;i++){
                    // 以返回的数组id为坐标向itemCatList传入商品分类名称
                    $scope.itemCatList[response[i].id]=response[i].name;
                   // alert($scope.itemCatList)
                }
            }
        )
    };
    
    $scope.checkAttributeValue=function (SpecName,optionName) {
        var items =  $scope.entity.goodsDesc.specificationItems;
        var object = $scope.searchObjectByKey(items,'attributeName',SpecName);

        if (object!=null){
            if(object.attributeValue.indexOf(optionName)>=0){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }

    }
});

