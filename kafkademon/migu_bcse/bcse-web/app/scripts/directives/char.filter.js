/**
 * Created by Echo on 2017/3/20.
 */
'use strict';
angular.module('seApp')
  .directive('charFilter',function(){
    return{
      restrict:'A',
      require:'ngModel',
      link:function(scope,elem,attrs,ctrl){
        ctrl.$parsers.push(function(viewValue){
          var pattern = new RegExp(/;|；|\ /g);
          var value = viewValue.replace(pattern, '');
          ctrl.$setViewValue(value);
          ctrl.$render();
          return value;
        });
      }
    };
  });
