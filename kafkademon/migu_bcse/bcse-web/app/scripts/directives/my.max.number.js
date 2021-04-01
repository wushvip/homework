/**
 * Created by Echo on 2017/3/24.
 */
'use strict';
angular.module('seApp')
    .directive('myMaxNumber', ['$timeout', function ($timeout) {
        return {
            restrict: 'EA',
            require: 'ngModel',
            scope: {
                maxNumber: '@myMaxNumber',
                currentPage: '@currentPage'
            },
            link: function (scope, element, attr, ctrl) {
                ctrl.$parsers.push(function (viewValue) {
                    var maxNumber = parseInt(scope.maxNumber, 10);
                    var curNumber = scope.currentPage; //当前页数
                    var transformedInput = viewValue.replace(/[^0-9]/g, '');
                    if (transformedInput !== viewValue||parseInt(transformedInput,10)<1||parseInt(transformedInput,10)>maxNumber) {
                        ctrl.$setViewValue(curNumber);
                        ctrl.$render();
                        return curNumber;
                    }
                    return viewValue;
                });
            }
        };
    }]);
