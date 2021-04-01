/**
 * Created by Echo on 2017/8/21.
 */
'use strict';
angular.module('seApp')
	.directive('leftSidebar', ['$location', '$timeout', function($location, $timeout){
	    return {
	    	restrict: 'EA',
	        scope: {},
	        link: function(scope, elements, attr, ngModel){
	        	var path = $location.path();
	        	var pathKeywords = path.split('/');
                var pathKeyword = pathKeywords[pathKeywords.length-1];
                
                //循环所有一级菜单
                angular.forEach(elements, function(element) {
                	$timeout(function() {
                		var secondClassEles = $(element).find('ul.second-class > li');
	                	angular.forEach(secondClassEles, function(secondClassEle) {
	                		if (pathKeyword === $(secondClassEle).attr('head')) {
	                			$(element).addClass('active');
	                			$(secondClassEles).addClass('active');
	                		}else {
	                			$(element).removeClass('active');
	                			$(secondClassEles).removeClass('active');
	                		}
	                	})
                	},0);
                })
        	}
	    };
	}]);
