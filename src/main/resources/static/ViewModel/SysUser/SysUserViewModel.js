
"use strict"
app.register.controller('UserCtrl', function ($rootScope, $scope, $http) {
	//$scope.firstName='John';$scope.lastName='Doe';
	
	$scope.data = {};
    $scope.rows = [];
    $scope.query={
    		username:'',
    		email:'',
    		gender:1,
    		cnname:''
    };
    
    //查询
    $scope.queryselect=function()
    {
    	//alert('1');
    	table.draw();
    }
    
    //重置
    $scope.queryreset=function()
    {
    	$scope.query={
        		username:'',
        		email:'',
        		gender:1,
        		cnname:''
        };
    }

    //添加
    $scope.add = function() {
        $scope.data = {
            no : 'No.1234567890',
            quantity : 100,
            'date' : '2016-12-30'
        };
        $('#myModal').modal();
    }

    //编辑
    $scope.edit = function(id) {
        for ( var i in $scope.rows) {
            var row = $scope.rows[i];
            if (id == row.id) {
                $scope.data = row;
                return;
            }
        }
    }

    //移除
    $scope.remove = function(id) {
        for ( var i in $scope.rows) {
            var row = $scope.rows[i];
            if (id == row.id) {
                $scope.rows.splice(i, 1);
                return;
            }
        }
    }

    //保存
    $scope.save = function() {
        $http({
            url : '/save',
            method : 'POST',
            data : $scope.data
        }).success(function(r) {
            //保存成功后更新数据
            $scope.get(r.id);
        });
    }

    //删除
    $scope.del = function(id) {
        $http({
            url : '/delete?id=' + id,
            method : 'POST',
        }).success(function(r) {
            //删除成功后移除数据
            $scope.remove(r.id);
        });
    }

    //获取数据
    $scope.get = function(id) {
        $http({
            url : '/get?id=' + id,
            method : 'POST',
        }).success(function(data) {
            for ( var i in $scope.rows) {
                var row = $scope.rows[i];
                if (data.id == row.id) {
                    row.no = data.no;
                    row.date = data.date;
                    row.quantity = data.quantity;
                    return;
                }
            }
            $scope.rows.push(data);
        });
    }

    //初始化载入数据
    $http({
        url : '/getuserList',
        method : 'POST'
    }).then(function successCallback(response) {
        //$scope.names = response.data.sites;
    	
    	$scope.rows=response.data;
    	/*for(var i in response.data)
    		{
    		//alert(response.data[i]);
    		 $scope.rows.push(response.data[i]);
    		 
    		}*/
    }, function errorCallback(response) {
        // 请求失败执行代码
    	alert('failure');
    });
    /*success(function(rows) {
    	alert(rows);
        for ( var i in rows) {
        	alert(i);
            var row = rows[i];
            $scope.rows.push(row);
        }
    });*/
    
    var table = angular.element("#datatable").DataTable({
        "sAjaxSource": '/getuserbypage',//"ajax": '/getuserbypage'
        "sServerMethod": "POST",
        "bProcessing": true,
        "bAutoWidth": true,
        "bServerSide": true,
        "bPaginate": true,
        "bFilter" : true, //是否启动过滤、搜索功能
        "bSort": true,//是否允许排序
		deferRender : true,
		scrollY : 380,
		scrollCollapse : true,
		//scroller: true,
		fixedHeader : true,
        "columns": [
            {"data": "id", "bSortable": false,"sTitle" : "用户ID"},
            {"data": "username","sTitle" : "用户名"},
            {"data": "email","sTitle" : "电子邮箱"},
            {"sTitle" : "操作","bSortable": false}
        ],
        "columnDefs": [
            {
                "targets": [3],
                "data": "id",
                "sTitle": "操作",
                "render": function(data, type, full) {
                    return "<a href='/update?id=" + data + "'>编辑</a>";
                }
            }
        ],
        "sDom": "Tflt<'row DTTTFooter'<'col-sm-6'i><'col-sm-6'p>>",
        "iDisplayLength": 10,
        "oTableTools": {
            "aButtons": [
                "copy", "csv", "xls", "pdf", "print"
            ],
            "sSwfPath": "assets/swf/copy_csv_xls_pdf.swf"
        },
        "oLanguage": { //国际化配置  
            "sProcessing" : "正在获取数据，请稍后...",    
            "sLengthMenu" : "显示 _MENU_ 条",    
            "sZeroRecords" : "没有您要搜索的内容",    
            "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",    
            "sInfoEmpty" : "记录数为0",    
            "sInfoFiltered" : "(全部记录数 _MAX_ 条)",    
            "sInfoPostFix" : "",    
            "sSearch" : "搜索",    
            "sUrl" : "",    
            "oPaginate": {    
                "sFirst" : "第一页",    
                "sPrevious" : "上一页",    
                "sNext" : "下一页",    
                "sLast" : "最后一页"    
            }  
        },
        "aaSorting": [],
        "fnServerParams": function (aoData) {
        	//alert('123');
            aoData.push({  
                name: "param",  
                value: JSON.stringify($scope.query) 
            });
            /*aoData.push(
            { "name": "Name", "value": $scope.searchFiles.name },
            { "name": "Ip", "value": $scope.searchFiles.ip }
            );*/
        }
    });
    
    $('#datatable tbody')
    .on( 'mouseenter', 'td', function () {
        var colIdx = table.cell(this).index().column;

        $( table.cells().nodes() ).removeClass( 'highlight' );
        $( table.column( colIdx ).nodes() ).addClass( 'highlight' );
    } );
    
    angular.element("#Btnserach").click(function () {
        //table.draw();
    	alert('search');
    });
   
});
