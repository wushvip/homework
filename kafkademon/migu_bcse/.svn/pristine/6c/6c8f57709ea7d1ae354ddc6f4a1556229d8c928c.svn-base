/**
 * Created by Echo on 2017/2/28.
 */
'use strict';
angular.module('seApp')
  .directive('passwordMatch',['$parse', function ($parse) {
    return {
      require: 'ngModel',
      restrict: 'A',
      link: function(scope, elem, attrs, ctrl) {
        //This part does the matching
        scope.$watch(function() {
          return (ctrl.$pristine && angular.isUndefined(ctrl.$modelValue)) || $parse(attrs.passwordMatch)(scope) === ctrl.$modelValue;
        }, function(currentValue) {
          ctrl.$setValidity('match', currentValue);
        });

        //This part is supposed to check the strength
        /*ctrl.$parsers.unshift(function(viewValue) {
          var pwdValidLength, pwdHasLetter, pwdHasNumber;

          pwdValidLength = (viewValue && viewValue.length >= 8 ? true : false);
          pwdHasLetter = (viewValue && /[A-z]/.test(viewValue)) ? true : false;
          pwdHasNumber = (viewValue && /\d/.test(viewValue)) ? true : false;

          if( pwdValidLength && pwdHasLetter && pwdHasNumber ) {
            ctrl.$setValidity('pwd', true);
          } else {
            ctrl.$setValidity('pwd', false);
          }
          return viewValue;
        });*/
      }
    };
  }]);
