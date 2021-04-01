/**
 * Created by Echo on 2017/3/10.
 */
'use strict';
angular.module('seApp')
  .directive('analysisNum', ['$window', '$timeout', function($window, $timeout) {
    return {
      restrict: 'EA',
      scope: {
        numAppList: '=info'
      },
      templateUrl: 'views/analysis/analysis.num.html',
      controller: ['$scope','$rootScope','$timeout','Handle','restService','restUrl','Messages','toaster','Menus', function($scope,$rootScope, $timeout, Handle, restService, restUrl,Messages,toaster,Menus) {
        var userId = $rootScope.userId;
        /*date picker setting*/
        $scope.startTime = Handle.today;
        $scope.endTime = Handle.today;
        $scope.type = '0';
        $scope.startDateOptions = {
          formatYear: 'yy',
          minDate: null,
          maxDate: $scope.endTime,
          startingDay: 1
        };
        $scope.endDateOptions = {
          formatYear: 'yy',
          minDate: $scope.startTime,
          maxDate: Handle.today,
          startingDay: 1
        };
        $scope.$watch('startTime', function(newValue,oldValue){
          $scope.endDateOptions.minDate = newValue;
        });
        $scope.$watch('endTime', function(newValue,oldValue){
          $scope.startDateOptions.maxDate = newValue;
        });
        $scope.startOpen = function() {
          $timeout(function() {
            $scope.startPopupOpened = true;
          });
        };
        $scope.endOpen = function() {
          $timeout(function() {
            $scope.endPopupOpened = true;
          });
        };
        $scope.startPopupOpened = false;
        $scope.endPopupOpened = false;

        /**
         * [getSearchNum 数据量]
         * @param  {[int]} start [开始时间]
         * @param  {[int]} end   [结束时间]
         */
        $scope.getSearchNum = function(start,end,type) {
          $scope.option = angular.copy(Menus.echartsConfig);
          $scope.option.xAxis[0].name = '单位(天)';
          $scope.option.yAxis[0].name = '单位(条)';
          $scope.option.yAxis[0].min = 0;
          //$scope.option.yAxis[0].interval = 1;
          $scope.option.id = 'num';
          $scope.type = type;
          if(type==="4") {
            start = Handle.getNumFromDate(start);
            end = Handle.getNumFromDate(end);
          }
          var dimension = end===start&&start===-1?'hour':'day';
          if(dimension === 'hour') {
            $scope.option.xAxis[0].name = '单位(小时)';
          }
          var userList = Handle.getUserList($scope.numAppList);
          if(!userList.length) {
            toaster.pop('info','提示', Messages.noUserList,3000);
          }else {
            $scope.submitting = true;
            restService.promiseRequest(restUrl.getUrl('statistics')+'/num','POST',{},{userId:userId,dimension: dimension,startDate:start,endDate:end,userList:userList}).then(function(res){
              if(res&&res.data.length) {
                angular.forEach(res.data, function(resData,i) {
                  var xAxis = [];
                  var yAxis=[];
                  var series = {};
                  var legendName = resData.userName + ': ' + resData.appName;
                  angular.forEach(resData.resultMap, function(item,key) {
                    xAxis.push(key);
                    yAxis.push(parseInt(item));
                  });
                  series.name = legendName;
                  series.type = 'line';
                  series.data = yAxis;
                  $scope.option.xAxis[0].data = xAxis;
                  $scope.option.series.push(series);
                  $scope.option.legend.data.push(resData.userName + ': ' + resData.appName);
                });
              }else {

              }
            },function(error) {
              console.log(error);
            }).finally(function() {
              $scope.submitting = false;
            });
          }
        };

        $scope.getSearchNum(0,0,'0');

      }],
      link: function(scope,element, attr, ctrl) {
        var elem = angular.element($('#echarts-num'));
        if (!scope.$echartsInstance) {
          scope.$echartsInstance = {};
        }
        scope.$watch('option', function () {
          scope.$echartsInstance['num'] = echarts.init(elem[0]);
          scope.$echartsInstance['num'].setOption(scope.option);
        },true);
        window.addEventListener("resize",function(){
          scope.$echartsInstance.num.resize();
        });
      }
    };
  }]);
