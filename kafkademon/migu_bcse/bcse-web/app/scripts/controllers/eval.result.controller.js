/**
 * Created by Echo on 2017/3/6.
 */
'use strict';
angular.module('eval')
  .controller('resultCtrl', ['$scope','$rootScope', '$state','$http','locals','$uibModal','$window','restService','restUrl', function ($scope,$rootScope, $state, $http,locals, $uibModal,$window,restService,restUrl) {
    /*variables defined*/
    console.log($rootScope.env);
    var userId = $rootScope.userId;
    var templateUrls = ['views/eval/result.instruction.html','views/eval/result.reporter.html'];
    $scope.showItems = [{id:5,label:'5条'},{id:10,label:'10条'},{id:15,label:'15条'},{id:20,label:'20条'}];
    $scope.num = 20;
    $scope.evalObj.templateUrl = templateUrls[0];
    $scope.evalObj.dataId = $scope.evalObj.dataId||locals.get('evalCorpusId');
    $scope.evalObj.appId = $scope.evalObj.appId||locals.get('evalAppId');
    /*$scope function defined*/
    $scope.showInstruction = function() {
      getTemplateUrl('ins');
    };
    $scope.exeEvaluation = function() {
      getTemplateUrl('res');
      exeEvaluation();
    };
    $scope.exportReporter = function() {
      $window.document.forms['downloadForm'].submit();
    };
    $scope.openDetails = function(pointInfo) {
      $uibModal.open({
        templateUrl: 'views/eval/result.detail.html',
        controller: ['$scope','$uibModalInstance','items', function($scope,$uibModalInstance,items) {
          $scope.pointInfo = items.pointInfo;
          $scope.infactRes = arrayToJson($scope.pointInfo.searchIdResults, $scope.pointInfo.searchNameResults);
          $scope.dataRes = arrayToJson($scope.pointInfo.dataIdResults, $scope.pointInfo.dataNameResults);
          $scope.cancel = function() {
            $uibModalInstance.dismiss('cancel');
          };
          function arrayToJson(arr1,arr2) {
            var arr=[];
            for (var i = 0; i < arr1.length; i++) {
              arr.push({"id":arr1[i],"title":arr2[i]});
            }
            return arr;
          }

        }],
        backdrop: "static",
        resolve: {
          items: function() {
            return {
              pointInfo: pointInfo
            };
          }
        }
      });
    };
    $scope.previous = function() {
      $state.go('manage.eval.corpus');
    };

    /*function defined*/
    function getTemplateUrl(temp) {
      $scope.evalObj["templateUrl"] = temp === 'ins'?templateUrls[0]:templateUrls[1];
    }
    function exeEvaluation() {
      $scope.submitting = true;
      $scope.evalObj.pointInfo = [];
      $scope.evalObj.totalPoint = {};
      restService.promiseRequest(restUrl.getUrl('evaluation')+'/result','POST',{},{userId:userId,appId:$scope.evalObj.appId,dataId:$scope.evalObj.dataId, evaluateNumber:$scope.evalObj.showItem}).then(function(res){
        if(res) {
          $scope.evalObj.pointInfo = res.points;
          $scope.evalObj.totalPoint = res.totalPoint;
          $scope.exeEvaluationSuccess = true;  //评测成功后才可导出EXCEL
        }
        $scope.submitting = false;
      },function(error) {
        console.log(error);
        $scope.submitting = false;
      });
    }

    /*steps control*/
    $scope.nav.steps[0]['passed'] = true;
    $scope.nav.steps[2]['passed'] = true;
    $scope.nav.steps[4]['actived'] = true;

  }]);
