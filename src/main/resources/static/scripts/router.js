	//“/"变”%2F”问题
	app.config(['$locationProvider', function($locationProvider) {   $locationProvider.hashPrefix(''); }]);
	app.config(function($routeProvider) {

	    $routeProvider
			.when('/',{
				templateUrl: '/system',//java controller
	            controller: 'mainController'
			})
	        // system
	        .when('/system', {
	            templateUrl: '/system',//java controller
	            controller: 'mainController'
	        })

	        // userManage
	        .when('/UserManage', {
	        	url:'/UserManage',
	        	//templateUrl: '/View/SysUser/index.html',对应static文件夹下的文件
	            templateUrl: '/UserManage',//可以是java controller，也可是静态资源的html页
	            controller: 'UserCtrl',
	            //controllerUrl: '/ViewModel/SysUser/SysUserViewModel.js',
	            resolve: {
	                //load: app.asyncjs(['plugin/controller.js','plugin/jquery.ui.datepicker.js'])
	                load: app.asyncjs(['/ViewModel/SysUser/SysUserViewModel.js'])	               
	              }
	        })

	        // contact page 静态页面html也可以
	        .when('/contact', {
	            templateUrl: 'tpl/page-contact.html',
	            controller: 'contactController'
	        })
	        .otherwise({
	            //redirectTo: '/403'
	        	templateUrl: '/403'
	        }); 

	}); 