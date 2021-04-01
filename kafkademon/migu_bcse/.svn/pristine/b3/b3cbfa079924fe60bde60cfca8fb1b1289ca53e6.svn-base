/**
 * Created by Echo on 2017/2/28.
 */
'use strict';
angular.module('test')
  .controller('testCtrl', ['$scope','$rootScope','$stateParams','$filter','restService','restUrl','Handle', function ($scope,$rootScope,$stateParams,$filter,restService,restUrl,Handle) {
    /*variable defined*/
    var userId = $rootScope.userId;
    var appId = $stateParams.appId;
    $scope.apps = [];
    $scope.configRules = [];
    $scope.facetRules = [];
    $scope.page = angular.copy($scope.pubObj.page);
    $scope.isOpen = false;
    //存储分组信息
    //$scope.clFilter = [];
    $scope.facetFields = [];
    //搜索条件集合
    $scope.searchQuery = {
      appInfo: null,
      appId: null,
      configRule: null,
      facetRule: null,
      clFilter: []
    };
    $scope.advance = {
      allKeys: null,
      allKeys2: [],
      wholeKeys: null,
      anyKey: null,
      noAnyKey: null
    };
    $scope.results = {};
    getAllApps();
    /*$scope function defined*/
    $scope.getAllApps = function(val) {
      return restService.promiseRequest(restUrl.getUrl('apps'),'GET',{},{userId: userId,pageNum:-1,searchText:val})
        .then(function(res) {
          if(res) {
            var apps = res.appList;
            return apps.map(function(app) {
              return app;
            })
          }
        })
    };
    $scope.executeSearch = function(searchQuery) {
      $scope.searchQuery.clFilter = [];
      $scope.advance.allKeys2 = [];  //初始化分词体验结果变量
      $scope.page.currentPage = 1;	//每次请求数据都是从第一页开始
      $scope.page.inputCurPage = 1;
      if($scope.advance.allKeys) {
        getAnalyzer($scope.advance.allKeys);
      }else {
        getSearchResults(searchQuery,true);
      }
    };
    $scope.toggleAdvance = function() {
      $scope.isOpen = !$scope.isOpen;
    };
    $scope.changeApp = function() {
      getConfigRules();
      getFacetRules();
    };
    // 判断是否为string类型
    $scope.isStr = function(val) {
      return typeof val === 'string';
    };
    //分类选中情况
    $scope.getClassify = function(fieldKeyItem,curClassify) {
      var existFlag=false;
      //循环分类二维数组中的键，若不存在则设置键为数组
      angular.forEach($scope.searchQuery.clFilter,function(clItem){
        if(clItem[fieldKeyItem]){
          existFlag=true;
          var clIdx = clItem[fieldKeyItem].indexOf(curClassify);
          if (clIdx == -1) {
            clItem[fieldKeyItem].push(curClassify);
          }else {
            clItem[fieldKeyItem].splice(clIdx,1);
          }
        }
      });
      if(!existFlag){
        var item={};
        item[fieldKeyItem]=[];
        item[fieldKeyItem].push(curClassify);
        $scope.searchQuery.clFilter.push(item);
      }
      $scope.page.currentPage = 1;	//每次请求数据都是从第一页开始
      $scope.page.inputCurPage = 1;
      getSearchResults($scope.searchQuery,false);
    };
    $scope.clFilterChecked = function(fieldKeyItem,classifyItem) {
      var clFilterChecked = angular.copy($scope.searchQuery.clFilter);
      var isExist = false;
      for (var i = 0; i < clFilterChecked.length; i++) {
        if (!angular.isUndefined(clFilterChecked[i][fieldKeyItem])) {
          for (var j = 0; j < clFilterChecked[i][fieldKeyItem].length; j++) {
            if(clFilterChecked[i][fieldKeyItem][j] == classifyItem) {
              isExist = true;
            }
          }
        }
      }
      return isExist;
    };
    $scope.getData = function (pageNo) {
      if(pageNo) {
        if(parseInt(pageNo,10)>=1&&parseInt(pageNo,10)<=$scope.page.maxPages) {
          $scope.page.currentPage = pageNo;
          $scope.page.inputCurPage = pageNo;
          getSearchResults($scope.searchQuery,false);
        }
      }
    };

    /*function defined*/
    function getConfigRules() {
      if($scope.searchQuery.appInfo.appId&&$scope.searchQuery.appInfo.userId) {
        restService.promiseRequest(restUrl.getUrl('config')+'/rough','GET',{},{userId: $scope.searchQuery.appInfo.userId,appId:$scope.searchQuery.appInfo.appId}).then(function(res){
          if(res) {
            $scope.configRules = res.ruleList;
            if(!res.ruleList.length) {
              $scope.toaster.pop('info','',$scope.messages.noConfigs('排序配置'), 3000);
            }
          }else {
            $scope.configRules = [];
          }
        },function(error) {
          console.log(error);
        })
      }
    }
    function getFacetRules() {
      if($scope.searchQuery.appInfo.appId&&$scope.searchQuery.appInfo.userId) {
        restService.promiseRequest(restUrl.getUrl('config')+'/facet','GET',{},{userId: $scope.searchQuery.appInfo.userId,appId:$scope.searchQuery.appInfo.appId}).then(function(res){
          if(res) {
            $scope.facetRules = res.ruleList;
            if(!res.ruleList.length) {
              $scope.toaster.pop('info','',$scope.messages.noConfigs('分组统计'), 3000);
            }
          }else {
            $scope.facetRules = [];
          }
        },function(error) {
          console.log(error);
        })
      }
    }
    function getAnalyzer(segText) {
      var segField = 'ANSJ';
      $scope.submitting = true;
      restService.promiseRequest(restUrl.getUrl('analyzer'),'GET',{},{userId: $scope.searchQuery.appInfo.userId,appId:$scope.searchQuery.appInfo.appId,searchQuery:segText,fieldSearch:segField}).then(function(res){
        if(res) {
          $scope.advance.allKeys2 = res.analysis;
          getSearchResults($scope.searchQuery,true);
        }else {
          $scope.searchQuery.allKeys2 = [];
        }
        $scope.submitting = false;
      },function(error) {
        $scope.submitting = false;
      });
    }
    /**
     * [getAllApps 获取所有应用后获取规则信息]
     * */
    function getAllApps () {
      restService.request(restUrl.getUrl('apps'),'GET',{},{userId: userId,pageNum:-1},function(res){
        //$scope.apps = res.appList;
        if (angular.isDefined(appId) && appId != '') {
          $scope.searchQuery.appInfo = getInfoById(appId,res.appList);
        }else if(res.appList.length){
          $scope.searchQuery.appInfo = res.appList[0];
        }
        getConfigRules();
        getFacetRules();
      },function(error) {
        console.log(error);
      });
    }
    /**
     * [getInfoById 通过appId获取appInfo]
     * @param id {string} [应用ID]
     * @return [应用信息]*/
    function getInfoById(id,apps) {
      var items = $filter('filter')(apps, {appId:id},true);
      return items.length?items[0]:null;
    }
    /**
     * [getSearchResults 获取搜索结果]
     * @param searchQuery {object} [封装条件]
     * @param flag {boolean} [是否需要获取新的分组统计信息]
     * */
    function getSearchResults(searchQuery,flag) {
      console.log(searchQuery);
      //如果开启了高级搜索，则封装高级搜索条件于searchQuery.text中
      var text = angular.copy(searchQuery.text);
      if($scope.isOpen) {
        text = assembleAdvance(text,$scope.advance).join(' AND ');
      }
      if(!text) {
        $scope.toaster.pop('error','','搜索内容不能为空！', 3000);
      }else {
        var data = {
          userId: searchQuery.appInfo.userId,
          appId:searchQuery.appInfo.appId,
          searchQuery:text,
          filterJson:clFilterHandle(searchQuery.clFilter),
          pageIndex:$scope.page.currentPage,
          pageNum:$scope.page.numPerPage
        };
        if(searchQuery.configRule) {
          data.sortConfig = searchQuery.configRule.ruleName;
        }
        if(searchQuery.facetRule) {
          data.facetRule = searchQuery.facetRule.ruleName;
        }
        $scope.submitLoading = true;
        restService.promiseRequest(restUrl.getUrl('search'),'POST',{},data).then(function(res){
          if(res) {
            handleResults(res,flag);
          }
        },function(error) {
          console.log(error);
        }).finally(function() {
          $scope.submitLoading = false;
        });
      }
    }
    /**
     * [handleResults 处理搜索结果返回数据，包括搜索耗时、搜索结构条数、分组信息数据、分组分类]
     * @param res {object} [返回对象]
     * @param flag {boolean} [是否需要获取新的分组统计信息]
     * */
    function handleResults(res,flag) {
      //搜索耗时
      $scope.results.costTime = res.costTime;
      //搜索结果条数
      $scope.results.resultNum = res.totalItems;
      //分组信息数据
      $scope.results.facetFields = res.facetList;
      if (flag) {
        $scope.facetFields = angular.copy($scope.results.facetFields);
      }
      //分组分类
      $scope.results.clFilter = res.clFilter;
      //所有搜索内容数据
      $scope.results.contents = res.resultList;
      $scope.page.totalItems = res.totalItems;
      $scope.page.maxPages = Math.ceil($scope.page.totalItems / $scope.page.numPerPage);
    }
    /**
     * [addPlusInKeys 针对str进行空格分割，并且每个元素加上+进行再次组装string]
     * @param str {string|array} [切割对象字符串]
     * @return plusStr {string} 以加号替换空格后的字符串
     * */
    function addPlusInKeys(str) {
      var arr = [],newArr = [];
      if(angular.isArray(str)) {
        arr = str;
      }else {
        if(str) {
          arr = str.split(' ');
        }
      }
      angular.forEach(arr, function(item) {
        if(item) {
          newArr.push('+'+item);
        }
      });
      return newArr.join(' ');
    }
    /**
     * [assembleAdvance 组装高级搜索条件]
     * @param conditions {object} [组装对象包含allKeys2，wholeKeys，anyKey，noAnyKey]
     * @return {array} [返回已封装好的各个条件]*/
    function assembleAdvance(text,advance) {
      var arr = [];
      if(text) {
        arr.push(text);
      }
      if(advance.allKeys2.length) {
        arr.push('('+addPlusInKeys(advance.allKeys2)+')');
      }
      if(advance.wholeKeys) {
        arr.push('+("'+advance.wholeKeys+'")');
      }
      if(advance.anyKey) {
        //arr.push('OR');
        arr.push('('+advance.anyKey+')');
      }
      if(advance.noAnyKey) {
        arr.push('-('+advance.noAnyKey+')');
      }
      return arr;
    }
    /**
     * [clFilterHandle 组装clFilter数组为字符串形式]
     * @param arr {array} [封装对象数组clFilter]
     * @return str [string形式输出]
     * */
    function clFilterHandle(arr) {
      var obj={};
      angular.forEach(arr, function(item1) {
        angular.forEach(item1, function(item2,key2) {
          if(item2 != '') {
            obj[key2] = item2.join(',');
          }
        });
      });
      return obj;
    }
  }]);
