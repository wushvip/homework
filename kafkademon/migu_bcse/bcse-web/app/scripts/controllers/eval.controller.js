/**
 * Created by Echo on 2017/2/28.
 */
'use strict';
angular.module('eval')
  .controller('evalCtrl', ['$scope','$rootScope', '$state','$filter','locals','restService','restUrl', function ($scope,$rootScope, $state, $filter,locals,restService,restUrl) {
    $scope.nav = {};
    $scope.evalObj = {
      appList: [],
      selectedApp: null,
      configRules: [],
      sortConfig: null,
      isThirdPart: false,
      appFieldsForSelect: [],
      evalFields: [],
      appId: null,
      dataId: null,
      file: null,
      showItem: 5,
      templateUrl: null,
      pointInfo: [],
      totalPoint: {}
    };
    $scope.nav.steps=[{
      label:'填写基本信息',
      state: 'manage.eval',
      actived:false,
      passed:false
    },
      {
        label:'',
        state: null,
        actived:false,
        passed:false
      },
      {
        label:'上传语料',
        state: 'manage.eval.corpus',
        actived:false,
        passed:false
      },
      {
        label:'',
        state: null,
        actived:false,
        passed:false
      },
      {
        label:'评测结果分析',
        state: 'manage.eval.result',
        actived:false,
        passed:false
      }];

    /*define variables*/
    var userId = $rootScope.userId;
    var errorMsg = null;
    //返回格式字段初始化
    $scope.dataTypeOptions = ['json','xml'];
    /*model初始化定义*/
    $scope.evalObj.dataType = $scope.dataTypeOptions[0];
    $scope.modelOptions = {
      debounce: {
        default: 500,
        blur: 250
      },
      getterSetter: true
    };
    //getAllApps();

    /*$scope function defined*/
    //切换应用触发事件
    $scope.changeApp = function() {
      $scope.evalObj.appFieldsForSelect.length = 0;
      $scope.evalObj.evalFields.length = 0;
      getAppStruct();
      getConfigRules();
    };
    /*如果当前选中字段为删除，则执行addEvalFields函数；如果为增加，则判断当前已选中字段是否已超过限制个数，未超过则还行addEvalFields函数，否则给于提示；*/
    $scope.addEvalFields = function(field) {
      if (!field['isselected']) {
        addEvalFields(field);
      }else {
        if ($scope.evalObj.evalFields&&$scope.evalObj.evalFields.length < 2) {
          addEvalFields(field);
        }else {
          if (field['isselected']) {
            field['isselected'] = false;
          }
          $scope.toaster.pop('warning','', $scope.messages.evaluation.limitation, 3000);
        }
      }
    };

    $scope.next = function() {
      if (!checkSelectedApp() || !checkSortConfig() || !checkEvalFields() || !checkDataType()) {
        $scope.toaster.pop('warning','', errorMsg, 3000);
      }else {
        restService.promiseRequest(restUrl.getUrl('evaluation')+'/app','POST',{},{userId: userId,appId:$scope.evalObj.selectedApp.appId,isThirdPart:$scope.evalObj.isThirdPart?'1':'0',sortConfig:$scope.evalObj.sortConfig,evaluateField:$scope.evalObj.evalFields.join(','),dataType:$scope.evalObj.dataType==='json'?'0':'1'})
          .then(function(res){
          if(res) {
            $scope.evalObj.appId = res.appId;
            //locals.set('evalAppId', angular.copy($scope.evalObj.appId));
            $state.go('manage.eval.corpus');
          }else {
            $scope.analysisResult = null;
          }
          $scope.submitting = false;
        },function(error) {
            console.log(error);
            $scope.submitting = false;
        }).finally(function() {
            locals.set('evalAppId', angular.copy($scope.evalObj.appId));
            console.log(locals.get('evalCorpusId'));
          });

      }
    };
      //steps用来控制上面进行到哪一步。catived表示显示蓝色，passed表示是否完成
    $scope.navInit = function() {
      angular.forEach($scope.nav.steps,function(step) {
        step.actived = false;
        step.passed = false;
      });
    };
    $scope.navInit();
    $scope.nav.steps[0]['actived'] = true;
    $scope.clickGoStatus = function(isPassed, status) {
      if(isPassed&&status) {
        $state.go(status);
      }
    };
    $scope.getAllApps = function(val) {
      var searchText = val?val:'';
      return restService.promiseRequest(restUrl.getUrl('apps'),'GET',{},{userId: userId,pageNum:-1,searchText:searchText})
        .then(function(res) {
          if(res) {
            var apps = res.appList;
            return apps.map(function(app) {
              return app;
            })
          }
        })
    };

    $scope.checkFieldIsPk = function(field) {
      return checkFieldIsPk(field);
    };
    $scope.removeField = function(fieldName) {
      removeField(fieldName);
    };
    /*function defined*/
    /**
     * [checkSelectedApp 检查选择应用是否为空以及是否存在]
     */
    function checkSelectedApp() {
      if (!$scope.evalObj.selectedApp) {
        errorMsg = $scope.messages.evaluation.appSelect;
        return false;
      }
      if ($scope.noResults) {
        errorMsg = $scope.messages.evaluation.appExist;
        return false;
      }
      return true;
    }
    function checkSortConfig() {
      if (!$scope.evalObj.sortConfig) {
        errorMsg = $scope.messages.evaluation.configSelect;
        return false;
      }
      return true;
    }
    function checkEvalFields() {
      if (!$scope.evalObj.evalFields||!$scope.evalObj.evalFields.length) {
        errorMsg = $scope.messages.evaluation.fieldSelect;
        return false;
      }
      if ($scope.evalObj.evalFields.length&&$scope.evalObj.evalFields.length < 2) {
        errorMsg = $scope.messages.evaluation.nameSelect;
        return false;
      }
      return true;
    }
    function checkDataType() {
      if (!$scope.evalObj.dataType) {
        errorMsg = $scope.messages.evaluation.formatSelect;
        return false;
      }
      return true;
    }
    function getAppStruct(){
      if ($scope.evalObj.selectedApp&&$scope.evalObj.selectedApp['appId']) {
        restService.request(restUrl.getUrl('apps')+'/:appId/structure','GET',{appId:$scope.evalObj.selectedApp['appId']},{userId: userId},function(res){
          if (res&&res.appTableList&&res.appTableList.length) {
            var tableList=res.appTableList[0];
            $scope.evalObj.appFieldsForSelect = tableList.fields?tableList.fields:[];
            addDefaultEvalField(findPkField(tableList.fields));
          }else {
            $scope.evalObj.appFieldsForSelect = [];
          }
        });
      }
    }
    function getConfigRules() {
      restService.promiseRequest(restUrl.getUrl('config')+'/rough','GET',{},{userId: $scope.evalObj.selectedApp['userId'],appId:$scope.evalObj.selectedApp['appId']}).then(function(res){
        if(res) {
          $scope.evalObj.configRules = res.ruleList;
          if(!res.ruleList.length) {
            $scope.toaster.pop('info','',$scope.messages.noConfigs('排序配置'), 3000);
          }
        }else {
          $scope.evalObj.configRules = [];
        }
      },function(error) {

      })
    }
    function addEvalFields(field) {
      var idx = $scope.evalObj.evalFields.indexOf(field['fieldName']);
      if (idx === -1&&field.isselected) {
        $scope.evalObj.evalFields.push(field['fieldName']);
      }
      if (idx > -1&&!field.isselected) {
        $scope.evalObj.evalFields.splice(idx,1);
      }
    }
    function checkFieldIsPk(field) {
      return field&&field['isPk'] === '1'?true:false;
    }
    function addDefaultEvalField(field) {
      if (checkFieldIsPk(field)) {
        addEvalFields(field);
      }
    }
    function findPkField(fields) {
      for (var i = 0; i < fields.length; i++) {
        if(fields[i]['isPk'] === '1') {
          fields[i]['isselected'] = true;
          return fields[i];
        }
      }
    }
    /**
     *[removeField 删除evalObj.evalFields中已选字段]
     * @param fieldName {string}[字段名]
     * */
    function removeField(fieldName) {
      var removeField = $filter('filter')($scope.evalObj.appFieldsForSelect,{fieldName:fieldName});
      if (removeField.length) {
        if (checkFieldIsPk(removeField[0])) {
          $scope.toaster.pop('warning','', $scope.messages.evaluation.forbidden, 3000);
        }else {
          removeField[0]['isselected'] = false;
          var removeIdx = $scope.evalObj.evalFields.indexOf(fieldName);
          if (removeIdx > -1) {
            $scope.evalObj.evalFields.splice(removeIdx,1);
          }
        }
      }
    }


  }]);
