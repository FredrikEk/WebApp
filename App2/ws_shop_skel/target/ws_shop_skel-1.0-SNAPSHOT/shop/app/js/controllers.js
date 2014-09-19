'use strict';

/*
 * Controllers
 */

var productCatalogueControllers = angular.module('ProductCatalogueControllers', []);

// General navigation controller (possibly useful?)
productCatalogueControllers.controller('NavigationCtrl', ['$scope', '$location',
    function($scope, $location) {
        
        $scope.navigate = function(url) {
            $location.path(url);
        };
				
    }]);

productCatalogueControllers.controller('ProductListCtrl', ['$scope', 'ProductCatalogueProxy',
	function($scope, ProductCatalogueProxy) {		
		$scope.orderProp = 'id';
        $scope.pageSize = '10';
        $scope.currentPage = 0;

		ProductCatalogueProxy.count()
				.success(function(count){
					$scope.count = count.value;
				})
				.error(function() {
					console.log("count: error");
				});
		getRange();
		$scope.$watch('currentPage', function() {
			getRange();
		});
		function getRange() {
			var fst = $scope.pageSize * $scope.currentPage;
            ProductCatalogueProxy.findRange(fst, $scope.pageSize)
                    .success(function(products) {
                        $scope.products = products;
                    }).error(function() {
						console.log("findRange: error");
					});
		}
		$scope.$watch('pageSize', function() {
			getRange();
		});
	}]);

productCatalogueControllers.controller('ProductDetailCtrl', ['$scope',
    '$location', '$routeParams', 'ProductCatalogueProxy',
    function($scope, $location, $routeParams, ProductCatalogueProxy) {
        ProductCatalogueProxy.find($routeParams.id)
                .success(function(product) {
                    $scope.product = product;
                }).error(function() {
            console.log("selectByPk: error");
        });

        // A listener
        $scope.update = function(product) {
			$scope.product;
            ProductCatalogueProxy.update($routeParams.id, $scope.product)
                    .success(function() {
                        $location.path('/products');
                    }).error(function() {
                ; // TODO;
            });
            //$location.path("products");
        };
        // A listener
        $scope.delete = function() {
            // Really delete?? message
            ProductCatalogueProxy.delete($routeParams.id)
                    .success(function() {
                        $location.path('/products');
                    }).error(function() {
                ; // TODO;
            });
        };
    }]);

productCatalogueControllers.controller('ProductNewCtrl', ['$scope', '$location', 'ProductCatalogueProxy',
	function($scope, $location, ProductCatalogueProxy) {
		 $scope.save = function() {
            ProductCatalogueProxy.create($scope.product)
                    .success(function() {
                        $location.path('/products');
                    }).error(function() {
                ; // TODO;
            });
        };
	}]);
