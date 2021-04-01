/**
 * Created by Echo on 2017/3/3.
 */
/*输入框限制输入长度*/
'use strict';
angular.module('seApp')
  .directive('charMax', function() {
    return {
      require: 'ngModel',
      link: function (scope, element, attrs, ngModelCtrl) {
        var maxlength = Number(attrs.charMax);
        function limitLength(text) {
          if (text.length > maxlength) {
            var transformedInput = text.substring(0, maxlength);
            ngModelCtrl.$setViewValue(transformedInput);
            ngModelCtrl.$render();
            return transformedInput;
          }
          return text;
        }
        ngModelCtrl.$parsers.push(limitLength);
      }
    };
  });
