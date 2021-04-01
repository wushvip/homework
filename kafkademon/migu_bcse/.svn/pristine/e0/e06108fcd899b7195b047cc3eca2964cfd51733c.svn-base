/**
 * Created by Echo on 2017/3/1.
 */
'use strict';
angular.module('seApp')
  //设置input框可输入数字区域
	.directive('formatInt', function(){
	    return {
	    	restrict: 'EA',
	        require: 'ngModel',
	        scope: {},
	        link: function(scope, ele, attr, ngModel){
	        	var maxVal = attr.max;
	        	var minVal = attr.min;
	        	var regexp = /^[0-9]*$/;//数字正则表达式
            ele.val(ngModel.$viewValue || minVal);	//默认值

				ngModel.$parsers.unshift(function(viewValue) {
	        		viewValue = viewValue.replace(/\b(0+)/gi,"");	//去除前面的0

					if (viewValue!='') {
						if (!regexp.test(viewValue)) {
							viewValue = '';
						}else {
							if (maxVal&&maxVal!='') {
								if (parseInt(viewValue)>parseInt(maxVal)) {
				        			viewValue = maxVal;
								}
							}
							if (minVal&&minVal!='') {
								if (parseInt(viewValue)<parseInt(minVal)) {
									viewValue = minVal;
								}
							}
						}
					}else{
					}
					ngModel.$setViewValue(viewValue);
		        	ngModel.$render();
					return ngModel.$viewValue;
				});
        }
	    };
	});
