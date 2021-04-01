/**
 * Created by Echo on 2017/3/3.
 */
'use strict';
/**
 * [构建数据表]
 */
angular .module('seApp')
.directive('sheetData',['$filter','Limitation',function($filter,Limitation){
  return{
    restrict:'EA',
    scope: true,
    link: function(scope) {
      //关键字数组列表
      var columnNameLimit = Limitation;
      var newTable={tableName:'main',isMainTable:'1',fields:[],sourceOptions:[]};
      /*初始化行数据
       **fieldName 字段名 string 必填
       **isPk 是否主键 string
       **fieldType 字段类型 string 必填
       **isSearch 是否可搜索 string 必填
       **isShow 是否可展示 string 必填
       **isMutivalue 是否多值 string 必填
       **dynamicField 是否动态字段 string 必填
       **srcField 源字段 string
       */
      var newColumn = {
        fieldName: '',
        isPk: '0',
        fieldType: 'STRING',
        isSearch: '1',
        isShow: '1',
        isMutivalue: '0',
        dynamicField: '0',
        srcField: []
      };
      //设置column的主键
      scope.checkColumnKey=function(table,column,idx){
        //如果本身已经选为主键，可以取消选择
        if(column.isPk=='1'){
          return false;
        }
        //如果不是STRING或者INT或者LONG，则不能设置为主键
        var fieldType = column['fieldType'];
        if(fieldType!=='STRING'&&fieldType!=='INT'&&fieldType!=='LONG'){
          return true;
        }
        //如果源字段不为空或者动态字段为是，则不可选为主键
        if(column.srcField.length||column.dynamicField === "1"){
          return true;
        }
        //如果该表中已经存在主键，则不能再选为主键
        for (var i=0;i<table['fields'].length;i++) {
          if(table['fields'][i].isPk=='1'){
            return true;
          }
        };
        return false;
      };
      scope.changeType = function(table,idx,fieldType) {
        scope.setPkByType(table, idx, fieldType);
        scope.setMutiByType(table, idx, fieldType);
      };
      //如果不是STRING或者INT或者LONG，则不能设置为主键
      scope.setPkByType=function(table,idx,fieldType){
        if(angular.uppercase(fieldType)!=='STRING'&&angular.uppercase(fieldType)!=='INT'&&angular.uppercase(fieldType)!=='LONG'){
          table.fields[idx].isPk='0';
        }
      };
      //如果为DATE类型，则不可设置多值
      scope.setMutiByType=function(table,idx,fieldType){
        var limitedType = ["DATE"];
        if(limitedType.indexOf(angular.uppercase(fieldType)) !== -1){
          table.fields[idx].isMutivalue='0';
          table.fields[idx].srcField.length=0;
        }
      };
      //改变源字段以及动态字段时判断主键选择逻辑
      scope.srcFieldChange = function(table,column,idx) {
        // setPkByField(column);
        setSrcByfield(table, column, idx);
      };
      //动态字段为“是”，则不可作为源字段下拉选项，将源字段中的field置为空
      var setSrcByfield = function(table, column, idx) {
        //判断是否为动态字段，若是，则源字段中对应field置为空，多值置为0
        var value = angular.copy(column['fieldName']);
        if (column['dynamicField'] === '1') {
          column['isMutivalue'] = '0';
          value = '';
          //移除已选择字段
          checkSourceField(table,column['fieldName']);
          table.sourceOptions[idx]={field:value};
        }else {
          table.sourceOptions[idx]={field:value};
        }
      };
      //设置主表
      scope.checkMasterTable=function(tableidx){
        //改connection,删除主表被连接的外键关系
        scope.tableList[tableidx].isMainTable='1';
        //改主表
        scope.mainTable=tableidx;

        for (var i=0;i<scope.tableList.length;i++) {
          if(i!=tableidx){
            scope.tableList[i].isMainTable='0';
          }
        }
      };

      scope.deleteColumn=function (table,column,idx) {
        table.fields.splice(idx,1);
        if (column['fieldName']) {
          //移除源字段选项
          removeRourceOptions(table,column['fieldName']);
          //移除已选择字段
          checkSourceField(table,column['fieldName']);
        }
      };
      //移除sourceOptions中的选项
      var removeRourceOptions = function(table,value) {
        var data = table.sourceOptions, selectArr= $filter('filter')(data,{field:value},true), idx;
        if (selectArr.length) {
          idx = data.indexOf(selectArr[0]);
          table['sourceOptions'].splice(idx,1);
        }
      };
      scope.addColumn=function(tableidx){
        scope.tableList[tableidx].fields.push(angular.copy(newColumn));
        scope.tableList[tableidx].sourceOptions.push({field:angular.copy(newColumn['fieldName'])});
      };
      scope.addTable=function(){
        /*if(scope.tableList.length){
          newTable={tableName:'main',isMainTable:'0',fields:[],sourceOptions:[]};
        }*/
        scope.tableList.push(angular.copy(newTable));
      };

      scope.deleteTable=function(tableidx){//接此处开始  删除connection 和talbes
        //更新tables
        scope.tableList.splice(tableidx,1);
        //delete(scope.tableNameList[tableidx]);
      };
      //字段名称改动后，删除srcField里面对应的field
      var checkSourceField=function(table,delValue){
        angular.forEach(table.fields,function(field,key){
          var idx=field.srcField.indexOf(delValue);
          if(idx>-1){
            field.srcField.splice(idx,1);
          }
        });

      };
      //字段名改变后，更改sourceOptions
      var setResource=function(table,column,idx){
        var value = angular.copy(column['fieldName']);
        //当前编辑的field若改变，更新sourceOptions的值
        if(table.sourceOptions[idx].field!=value){
          checkSourceField(table,table.sourceOptions[idx].field);
          //判断是否为动态字段，若是，则源字段中对应field置为空
          value = column['dynamicField'] === '0'?value:'';
          table.sourceOptions[idx]={field:value};
        }
      };
      //检查行字段名是否是保留字或者关键字
      var checkNameLimit = function(table,fieldName,idx) {
        if(columnNameLimit.indexOf(fieldName.toUpperCase())!==-1){
          scope.toaster.pop('warning','', scope.messages.create.fieldReserved(fieldName),3000);
          table.fields[idx].fieldName='';
          return false;
        }
        return true;
      };
      //检查行字段名是否重名
      var checkNameRepeat = function(table,column,idx) {
        var dataFields = table.fields;
        if (column&&column['fieldName']!=="") {
          var matchArr = $filter('filter')(dataFields, {fieldName:column['fieldName']},true);
          if (matchArr.length > 1) {
            column['fieldName'] = "";
            scope.toaster.pop('warning','', scope.messages.create.fieldExist(column['fieldName']),3000);
          }
        }
        return true;
      };
      //更改列名
      scope.checkColumnName=function(table,column,idx){
        //空
        var thisColumnName=table.fields[idx].fieldName;
        if(thisColumnName==null||thisColumnName==''){
          return;
        }
        //检查字段名称
        if (checkNameLimit(table,column['fieldName'],idx)&&checkNameRepeat(table,column,idx)) {
          setResource(table,column,idx);
        }
      };
      //点击添加多个源字段如果已经存在字段，则移除；若不存在，则添加
      scope.addSourceField = function(selectedField,column) {
        var idx = column['srcField'].indexOf(selectedField['field']),len;
        if (idx>-1) {
          column['srcField'].splice(idx,1);
        }else{
          column['srcField'].push(selectedField.field);
        }
        len = column['srcField'].length;
        //如果存在源字段，则不可设为主键；如果源字段长度大于等于1，则多值必须为1
        if(len){
          column.isPk='0';
          column.isMutivalue = '1';
        }else {
          // column.isMutivalue = '0';
        }
      };
      //源字段选项是否应该选中
      scope.checkSourceSelect=function(source,column){
        var idx=column['srcField'].indexOf(source.field);
        if(idx>-1){
          return true;
        }else{
          return false;
        }
      };
      //主键改动后，更改多值(主键必定可搜索和可展示、不可为多值)
      scope.setMutiple=function(column){
        if (column['isPk'] === '1') {
          column['isMutivalue'] = '0';
          column['isSearch'] = '1';
          column['isShow'] = '1';
        }
      };
      //多值不可点击的条件：当前字段为主键，当前字段为动态字段，当前字段类型为规定的类型
      scope.mutiValAndSrcFieldIsDisabled = function(column) {
        if (column.isPk==='1'||column.dynamicField==='1') {
          return true;
        }
        var limitedType = ["DATE"];
        if(limitedType.indexOf(angular.uppercase(column.fieldType)) !== -1){
          return true;
        }
        return false;
      };

    }
  }
}]);
