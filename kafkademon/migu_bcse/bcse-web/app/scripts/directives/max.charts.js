'use strict';
angular.module('seApp')
	.directive('maxcharts',function(){
		return {
			restrict: 'A',
			require: 'ngModel',
			link: function (scope, elem, attrs, ctrl) {
            var maxcharts = parseInt(attrs.maxcharts, 10);
            ctrl.$parsers.push(function (viewValue) {
              var charLines = (viewValue || '').split("\n");
              for(var idx=0;idx<charLines.length;idx++){
                var item=charLines[idx];
                if(item.length>maxcharts){
                  charLines[idx]=item.substr(0,maxcharts);
                }
              }
              var value=charLines.join('\n');
              ctrl.$setViewValue(value);
              ctrl.$render();
              return value;
            });
			}
		};
	});
