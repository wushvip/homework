/**
 * Created by Echo on 2017/3/10.
 */
'use strict';
angular.module('seApp')
  .directive('analysisCost',['$window','$timeout', function($window,$timeout ) {
    return {
      restrict: 'EA',
      scope: {
        costAppList: '=info'
      },
      templateUrl: 'views/analysis/analysis.cost.html',
      controller: [ '$scope','$rootScope','$timeout','Handle','restService','restUrl','Messages','toaster','Menus', function($scope, $rootScope, $timeout, Handle, restService, restUrl,Messages,toaster,Menus) {
        var userId = $rootScope.userId;
        /*date picker setting*/
        $scope.startTime = Handle.yesterday;
        $scope.endTime = Handle.yesterday;
        $scope.type = '1';
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
        $scope.$watch('startTime', function (newValue, oldValue) {
          $scope.endDateOptions.minDate = newValue;
        });
        $scope.$watch('endTime', function (newValue, oldValue) {
          $scope.startDateOptions.maxDate = newValue;
        });
        $scope.startOpen = function () {
          $timeout(function () {
            $scope.startPopupOpened = true;
          });
        };
        $scope.endOpen = function () {
          $timeout(function () {
            $scope.endPopupOpened = true;
          });
        };
        $scope.startPopupOpened = false;
        $scope.endPopupOpened = false;

        /**
         * [getSearchCost 搜索耗时]
         * @param  {[int]} start [开始时间]
         * @param  {[int]} end   [结束时间]
         */
        $scope.getSearchCost = function(start,end,type) {
          $scope.option = angular.copy(Menus.echartsConfig);
          $scope.option.xAxis[0].name = '单位(天)';
          $scope.option.yAxis[0].name = '单位(毫秒)';
          $scope.option.id = 'cost';
          $scope.type = type;
          if(type==="4") {
            start = Handle.getNumFromDate(start);
            end = Handle.getNumFromDate(end);
          }
          var dimension = end===start&&start===-1?'hour':'day';
          if(dimension === 'hour') {
            $scope.option.xAxis[0].name = '单位(小时)';
          }
          var userList = Handle.getUserList($scope.costAppList);
          if(!userList.length) {
            toaster.pop('info','提示', Messages.noUserList,3000);
          }else {
            $scope.submitting = true;
            restService.promiseRequest(restUrl.getUrl('statistics')+'/cost','POST',{},{userId:userId,dimension: dimension,startDate:start,endDate:end,userList:userList}).then(function(res){
              if(res&&res.data.length) {
                handleResData(res.data);
              }else {

              }
            },function(error) {
              console.log(error);
            }).finally(function() {
              $scope.submitting = false;
            });
          }
        };

        $scope.getSearchCost(-1,-1,'1');

        function handleResData(data) {
          angular.forEach(data, function(resData,i) {
            var seriesItem={
              type:'line',
              data:[]
            };
            var xAxis = [], yAxis=[], yAxisMax = [], yAxisMin = [], yAxisAvg = [];
            //var series = {};
            var seriesMax = angular.copy(seriesItem);
            var seriesMin = angular.copy(seriesItem);
            var seriesAvg = angular.copy(seriesItem);
            var legendName = resData.userName + ': ' + resData.appName;
            angular.forEach(resData.resultMap, function(item,key) {
              xAxis.push(key);
              //yAxis.push(parseInt(item));
              var yAxis = item.split(',');
              yAxisMax.push(parseInt(yAxis[0]));
              yAxisMin.push(parseInt(yAxis[1])||0);
              yAxisAvg.push(parseInt(yAxis[2])||0);
            });
            //设置y轴
            seriesMax.data = yAxisMax;
            seriesMax.name = legendName+':最大值';
            $scope.option.series.push(seriesMax);

            seriesMin.name = legendName+':最小值';
            seriesMin.data = yAxisMin;
            $scope.option.series.push(seriesMin);
            seriesAvg.name=legendName+':平均值';
            seriesAvg.data=yAxisAvg;
            $scope.option.series.push(seriesAvg);
            /*series.name = legendName;
            series.type = 'line';
            series.data = yAxis;*/
            $scope.option.xAxis[0].data = xAxis;

            //$scope.option.series.push(series);
            $scope.option.legend.data.push(resData.userName + ': ' + resData.appName);
          });
        }

      }],
      link: function(scope,element, attr, ctrl) {
        var elem = angular.element($('#echarts-cost'));
        if (!scope.$echartsInstance) {
          scope.$echartsInstance = {};
        }
        scope.$watch('option', function () {
          scope.$echartsInstance['cost'] = echarts.init(elem[0]);
          scope.$echartsInstance['cost'].setOption(scope.option);
        },true);
        window.addEventListener("resize",function(){
          scope.$echartsInstance.cost.resize();
        });
      }
    };
  }]);
