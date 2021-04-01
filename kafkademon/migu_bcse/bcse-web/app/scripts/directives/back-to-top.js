/**
 * Created by Echo on 2016/5/19.
 */
angular
  .module('seApp')
    .directive("backToTop", ['$window',function($window) {
        return {
            restrict: 'E',
            replace: true,
            template: '<a class="W_gotop" ng-show="visible"><i class="glyphicon glyphicon-menu-up"></i></a>',
            link: function (scope, element, attrs) {
                angular.element($window).bind("scroll", function() {
                    if (this.pageYOffset >= 100) {
                        scope.visible = true;
                        //console.log('Scrolled below header.');
                    } else {
                        scope.visible = false;
                        //console.log('Header is in view.');
                    }
                    scope.$apply();
                });
                element.on('click', function() {
                    //angular.element(document).animate({scrollTop:0}, 'fast');
                    $('html,body').animate({scrollTop:0}, 'fast');
                })
            }
        }
    }]);
