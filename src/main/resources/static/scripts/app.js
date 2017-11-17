var app=angular.module('mySceApp',['ngRoute', 'ngAnimate']);

app.config(function($controllerProvider, $compileProvider, $filterProvider, $provide) {
  app.register = {
    controller: $controllerProvider.register,
    directive: $compileProvider.directive,
    filter: $filterProvider.register,
    factory: $provide.factory,
    service: $provide.service
  };
  app.asyncjs = function (js) {
        return ["$q", "$route", "$rootScope", function ($q, $route, $rootScope) {
            var deferred = $q.defer();
            
            var dependencies = angular.copy(js);
            //alert(Array.isArray(dependencies));
            if (Array.isArray(dependencies)) {
                for (var i = 0; i < dependencies.length; i++) {
                    //dependencies[i] += "?v=" + v;
                }
            } else {
            	//alert('123');
                //dependencies += "?v=" + v;//v是版本号
                
            }
            //alert(dependencies);
            $script(dependencies, function () {
            	//alert('1');
                $rootScope.$apply(function () {
                	//alert('2');
                    deferred.resolve();
                });
            });
            //alert('3');
            return deferred.promise;
        }];
    }
});