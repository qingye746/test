 //控制层 
app.controller('goodsController' ,function($scope,$controller   ,goodsService, itemCatService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		goodsService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		goodsService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}

	//查询实体 
	$scope.findOne=function(id){				
		goodsService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}

	//添加
    $scope.add=function(){
        $scope.entity.goodsDesc.introduction=editor.html();
        goodsService.add( $scope.entity  ).success(

            function(response){
                if(response.success){
                    alert("添加成功");
                    //重新查询
                    $scope.entity={};
                    //清空文本编辑器
                    editor.html();
                }else{
                    alert(response.message);
                }
            }
        );
    }
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		goodsService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		goodsService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}

	//上传文件
	$scope.uploadFile = function () {
		uploadService.uploadFile().success(
			function (response) {
				if(response.success){
					$scope.image_entity.url=response.massage;
				}else {
					alert(response.massage)
				}
            }
		)
    }

    $scope.status=['未审核','已审核','审核未通过','已关闭'];

    // 初始化商品分类列表
    $scope.itemCatList = [];

    $scope.findItemCatList=function () {
        itemCatService.findAll().success(
            function (response) {
                for(var i=0;i<response.length;i++){
                    // 以返回的数组id为坐标向itemCatList传入商品分类名称
                    $scope.itemCatList[response[i].id]=response[i].name;
                }
            }
        )
    };
    //修改审核状态
    $scope.updateStatus=function (status) {
        //$scope.selectIds选中的框的集合
       goodsService.updateStatus($scope.selectIds,status).success(
           function (response) {
               if(response.success){
                   $scope.reloadList();
                   $scope.selectIds=[];
               }else {
                   alert(response.message)
               }
           }
       )
    }
});
