'use strict';

/* Services */

var productCatalogueService = angular.module('ProductCatalogueService', []);

// Representing the remote RESTful ProductCatalogue
productCatalogueService.factory('ProductCatalogueProxy', ['$http',
    function($http) {
       var url = 'http://localhost:8080/ws_shop_skel/webresources/products';
	   
	   return {
            findAll: function() {
                return $http.get(url);
            },
            findRange: function(first, count) {
                return $http.get(url + "/range?fst=" + first + "&count=" + count);
            },
            find: function(id) {
                return $http.get(url + "/" + id);
            },
            update: function(id, product) {
                return $http.put(url + "/" + id, product);
            },
            create: function(product) {
                return $http.post(url, product);
            },
            delete: function(id) {
                return $http.delete(url + "/" + id);
            },
            count: function() {
                return $http.get(url + "/count");
            }
        };
    }]);

