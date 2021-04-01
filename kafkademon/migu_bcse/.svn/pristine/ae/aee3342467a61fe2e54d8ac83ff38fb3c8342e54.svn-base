'use strict';
angular.module('seApp')
	.directive('maxlines', function() {
	  return {
	    restrict: 'A',
	    require: 'ngModel',
	    link: function(scope, elem, attrs, ngModel) {
	      var maxLines = 1;
	      attrs.$observe('maxlines', function(val) {
	        maxLines = parseInt(val);
	      });
	      ngModel.$validators.maxlines = function(modelValue, viewValue) {
	        var numLines = (viewValue || '').split("\n").length;
	        if(numLines>maxLines){
	        	var array=(viewValue || '').split("\n");
	        	var value=array.slice(0,maxLines);
	        	ngModel.$setViewValue(value.join('\n'));
	        	ngModel.$render();
	        }
	        return numLines <= maxLines;
	      };
	      scope.$watch(attrs['ngModel'], function(newValue, oldValue) {
	      	if (newValue&&newValue!==oldValue) {
	      		ngModel.$setViewValue(newValue.join('\n'));
	      		ngModel.$render();
	      	}
	      }, true);
	      attrs.$observe('maxlinesPreventEnter', function(preventEnter) {
	        // if attribute value starts with 'f', treat as false. Everything else is true
	        preventEnter = (preventEnter || '').toLocaleLowerCase().indexOf('f') !== 0;
	        if (preventEnter) {

	          addKeypress();
	        } else {
	          removeKeypress();
	        }
	      });

	      function addKeypress() {
	        elem.on('keypress', function(event) {
	          // test if adding a newline would cause the validator to fail
	          if (event.keyCode == 13 && !ngModel.$validators.maxlines(ngModel.$modelValue + '\n', ngModel.$viewValue + '\n')) {
	            event.preventDefault();
	          }
	        });
	      }

	      function removeKeypress() {
	        elem.off('.maxlines');
	      }
	      scope.$on('$destroy', removeKeypress);
	    }
	  };
	})
