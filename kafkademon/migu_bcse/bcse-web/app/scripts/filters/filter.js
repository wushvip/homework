/**
 * Created by Echo on 2017/3/2.
 */
'use strict';

angular.module('seApp')
  .filter('startOrStopFilter',function(){
    return function(input){
      return input?'开始':'暂停';
    };
  })
  .filter('appStatusFilter', function() {
    return function(input) {
      var output;
      if (parseInt(input) === 0) {
        output = '停用';
      }else if(parseInt(input) === 1) {
        output = '启用';
      }else if(parseInt(input) === 2) {
        output = '禁用';
      }else {
        output = '未知状态';
      }
      return output;
    };
  })
  .filter('displayStatus', function() {
    return function(input) {
      var output;
      if (parseInt(input) === 0) {
        output ='启用';
      }else if (parseInt(input) === 1) {
        output ='停用';
      }else if (parseInt(input) === 2) {
        output = '禁用';
      }else {
        output = '未知状态';
      }
      return output;
    };
  })

  .filter('to_trusted', ['$sce', function ($sce) {
    return function (text) {
      return $sce.trustAsHtml(text.toString());
    };
  }])

  .filter('setDecimal', [function () {
    return function (input, places) {
      var factor;
      if (isNaN(input)){ return input; }
      // If we want 1 decimal place, we want to mult/div by 10
      // If we want 2 decimal places, we want to mult/div by 100, etc
      // So use the following to create that factor
      factor = '1' + new Array(+(places > 0 && places + 1)).join('0');
      return Math.round(input * factor) / factor;
    };
  }])
  //用于历史记录中
  .filter('typeToExpress', function () {
    return function (input) {
      input = input || '';
      var output = '';
      switch(input){
        case '1':
          output='索引更新';
          break;
        case '2':
          output='索引清空';
          break;
        case '4':
          output='索引删除';
          break;
      }
      return output;
    };
  });
