/**
 * Created by Echo on 2017/3/6.
 */
/*
* [locals] [本地数据存储]
* [set] [存储单个属性]
* [get] [读取单个属性]
* [setObject] [存储对象，以JSON格式存储]
 * [getObject] [读取对象]*/
'use strict';
angular.module('seApp')
  .factory('locals',['$window',function($window){
    return{
      set :function(key,value){
        $window.localStorage[key]=value;
      },
      get:function(key){
        return  $window.localStorage[key];
      },
      setObject:function(key,value){
        $window.localStorage[key]=JSON.stringify(value);
      },
      getObject: function (key) {
        return JSON.parse($window.localStorage[key] || '{}');
      }
    };
  }]);
