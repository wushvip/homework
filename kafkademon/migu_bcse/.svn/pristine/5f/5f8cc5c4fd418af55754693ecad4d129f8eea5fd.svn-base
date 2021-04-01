/**
 * Created by Echo on 2017/3/3.
 */
'use strict';
/*英文字母开头，由数字、字母下划线构成*/
angular.module('seApp')
  .directive('inputvalid',function(){
    return{
      require:'ngModel',
      restrict: 'A',
      scope:{} ,
      link: function (scope, element, attr, ngModel) {
        var regexp = /^[a-zA-Z][a-zA-Z0-9_]*$/;

        ngModel.$parsers.unshift(function(viewValue) {
          if(!regexp.test(viewValue)) {
            ngModel.$setViewValue('');
            ngModel.$render();
            return ngModel.$viewValue;
          }
          else{
            return viewValue;
          }
        });

      }
    };
  });
