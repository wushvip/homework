/**
 * Created by Echo on 2017/3/4.
 */
/*
* some handle string/array or others function method here
* */
'use strict';
angular.module('seApp')
.service('Handle', [ '$filter','toaster','Messages', function ($filter,toaster,Messages) {
    var userRole = ['0', '1'];
    var userRoleRouteMap = {
      '0': [],
      '1': ['manage.app.sample', 'manage.app.basic', 'manage.app.struct', 'manage.app.index', 'manage.users']
    };
    function adminRoute(route) {
      var validUrlForAdmin = userRoleRouteMap['0'];
      if (validUrlForAdmin) {
        for (var i = 0; i < validUrlForAdmin.length; i++) {
          if(validUrlForAdmin[i] == route) {
            return true;
          }
        };
      };
      return false;
    }
    function userNotRoute(route) {
      var inValidUrlForUser = userRoleRouteMap['1'];
      if (inValidUrlForUser.indexOf(route) != -1) {
        return true;
      };
      return false;
    }

    /**
     * trim [删除左右两端的空格]
     * */
    function trim(str){
      return str.replace(/(^\s*)|(\s*$)/g, "");
    }
    /**
     * isChineseChar [是否含有中文]
     * */
    function isChineseChar(str) {
      var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
      return reg.test(str);
    }

    /**
     * isFullwidthChar [是否含有全角符号的函数]
     * */
    function isFullwidthChar(str){
      var reg = /[\uFF00-\uFFEF]/;
      return reg.test(str);
    }

    /**
     * cleanArrTrim [清除字符串中前后空格]
     * */
    function cleanArrTrim(arr) {
      var newArr = [],tmp;
      angular.forEach(arr, function(item) {
        tmp = trim(item);
        if(tmp.length) {
          newArr.push(tmp);
        }
      });
      return newArr;
    }

    /**
     * removeRepetition [去重复]
     * */
    function removeRepetition(arr) {
      var tmp = [];
      for(var i in arr) {
        //该元素在tmp中不存在才允许添加
        if (tmp.indexOf(arr[i])===-1) {
          tmp.push(arr[i]);
        }
      }
      return tmp;
    }

    /**
     * removeEmpty [去数组空元素]
     * */
    function removeEmpty(arr) {
      if (!(arr instanceof Array) || arr.length === 0) return false;
      var newArr = [];
      for (var i = 0; i < arr.length; i++) {
        if(trim(arr[i]) === "" || typeof(arr[i]) === "undefined")
        {
          continue;
        }
        newArr.push(arr[i]);
      }
      return newArr;
    }

    /**处理数值*/
    function formatKMBT(y, formatter) {
      if (!formatter) {
        formatter = function (v) {
          return Math.round(v * 100) / 100;
        };
      }
      y = Math.abs(y);
      if (y >= 1000000000000) {
        return formatter(y / 1000000000000) + 'T';
      } else if (y >= 1000000000) {
        return formatter(y / 1000000000) + 'B';
      } else if (y >= 1000000) {
        return formatter(y / 1000000) + 'M';
      } else if (y >= 1000) {
        return formatter(y / 1000) + 'K';
      } else if (y < 1 && y > 0) {
        return formatter(y);
      } else if (y === 0) {
        return '';
      } else {
        return formatter(y);
      }
    }
    //侧边导航栏
    var openMenu = function(firstMenu,secondMenu,docMenus) {
      if (!secondMenu) {
        firstMenu.isOpen = !firstMenu.isOpen;
      }
      if (secondMenu) {
        secondMenu.isOpen = !secondMenu.isOpen;
      }
      angular.forEach(docMenus,function(docMenu) {
        if (!secondMenu) {
          if (docMenu.head !== firstMenu.head) {
            docMenu.isOpen = false;
          }
        }
        if (secondMenu) {
          if (docMenu.head !== firstMenu.head) {
            docMenu.isOpen = false;
          }
          if(docMenu.subMenus) {
            angular.forEach(docMenu.subMenus,function(subMenu) {
              if (subMenu.isOpen !== undefined && subMenu.head !== secondMenu.head) {
                subMenu.isOpen = false;
              }
            });
          }
        }
      });
    };
    /**
    * [autoOpenMenu] [自动展开菜单栏]
    * @docMenus [{array}] [整个菜单栏]
    * @state [{string] [当前url的状态]
    * */
    var autoOpenMenu = function(state,docMenus) {
      angular.forEach(docMenus,function(docMenu) {
        if (docMenu.subMenus) {
          angular.forEach(docMenu.subMenus,function(subMenu) {
            //检查二级目录是否匹配state，如果匹配，则展开对应的一级目录；否则检查三级目录
            if (subMenu.subMenus === null && subMenu.surl === state) {
              docMenu.isOpen = true;
            }
            if (subMenu.subMenus) {
              angular.forEach(subMenu.subMenus,function(menu) {
                if (menu.subMenus === null && menu.surl === state) {
                  docMenu.isOpen = true;
                  subMenu.isOpen = true;
                }
              });
            }
          });
        }
      });
    };
    /**
    * [isExitedInFields] ({array}.{array}) [判断字段是否已经存在]
    * @ruleFields [当前规则字段]
    * @otherFields [其它规则字段]
    * */
    function isExitedInFields(ruleFields, otherFields) {
      var name;
      for (var i = 0; i < ruleFields.length; i++) {
        name = ruleFields[i].ruleName;
        var exitRule = $filter('filter')(otherFields,{'ruleName':name},true);
        if (exitRule.length===0) {
          return false;
        }
      }
      return true;
    }
    /**
     * [getIdxInRule] ({string}.{array},{string) [获取字符串在数组中的索引]
     * @field [指定数组对象中的哪一个元素]
     * */
    function getIdx(id, arr, field) {
      for(var i=0; i<arr.length; i++) {
        if (arr[i][field]===id) {
          return i;
        }
      }
    }
    /**
     * [checkSelectedRule 检查规则是否已存在]
     * @param {object} [指定判断规则]
     * @param {array} [在哪个数组中判断]
     * */
    function checkSelectedRule(rule,selectedRules) {
      if(selectedRules.length) {
        var items = $filter('filter')(selectedRules,{ruleName:rule.ruleName},true);
        if(items.length) {
          return true;
        }
        return false;
      }
    }
    /**
     * [getSelectedRules 获取选择的规则]
     * @param {object} [指定判断规则]
     * @param {array} [在哪个数组中判断]
     * @return {array} [新数组]
     * */
    function getSelectedRules(rule,selectedRules) {
      var items = $filter('filter')(selectedRules,{ruleName:rule.ruleName},true), index;
      if(items.length) {
        index = selectedRules.indexOf(items[0]);
        selectedRules.splice(index,1);
      }else{
        selectedRules.push(rule);
      }
      return selectedRules;
    }

    /**
     * [arr2StrInRuleName] ({array}.{string},{string}) [把数组按照指定的字符连接成字符串]
     * @arr [需要连接的数组]
     * @f [指定的字符]
     * @field [指定数组对象中的哪一个元素]
     * */
    function arr2Str(arr, f, field) {
      var str = '';
      angular.forEach(arr, function(item, key) {
        str += item[field] + f;
      });
      return str;
    }

    /**
     * [splitStr] ({string}.{string}) [把字符串转化为id标签的数组对象]
     * @str [需要转化的字符串]
     * @f [指定的字符]
     * */
    function arr2IdObj(arr) {
      var arr1 = [];
      angular.forEach(arr, function(item,key) {
        if (item!='') {
          arr1.push({id:item});
        }
      });
      return arr1;
    }
    /**
    * [getDate] （{date}） [计算日期离目前日期的相差数]
    * */
    function getNumFromDate(date){
      var oDate = new Date();
      var nY = oDate.getFullYear();
      var nM = oDate.getMonth();
      var nD = oDate.getDate();
      var newDate = new Date(nY,nM,nD,0,0,0);
      var lastDate = new Date(date.getFullYear(),date.getMonth(),date.getDate(),0,0,0);
      var result =(newDate.getTime()-lastDate.getTime())/86400000;
      return 0-result;
    }
    var myDate = new Date();
    /**
    * [formatNumber] ({int}) [形式化数值]
    * */
    function formatNumber(maxValue){
      if(maxValue>=1000000){
        return function(value){
          return (value/10000)+'万';
        }
      }
    }
    /**
    * [echartBasicOption echart图的基本设置]
    * */
    function echartSetting() {
      var basicOption={
        legend: {
          data:[]
        },
        xAxis : [
          {
            type: 'category',
            boundaryGap: false,
            data: []
          }
        ],
        yAxis : [
          {
            type : 'value'
          }
        ],
        series : [
          {
            name:'',
            type:'line',
            data:[],
          }
        ],
        grid:{
          y:75,
          y2:70
        }
      };
      var seriesItem={
        type:'line',
        data:[]
      };
      return {
        basicOption: basicOption,
        seriesItem: seriesItem
      }

    }
    /**
     * [getEchartData 处理返回数据组装echart数据]
     * @param  {[array]} options [指定options]
     * @param  {[array]} xAxis   [X轴数据]
     * @param  {[array]} yAxis   [Y轴数据]
     */
    function getEchartData(options,xAxis,yAxis) {

    }

    function setCookie ( value, expireDays) {
      var expireDate = new Date();
      expireDate.setDate(expireDate.getDate() + expireDays);
      angular.forEach(value, function(val, key) {
        //if(document.cookie.indexOf(key + "=") ===-1) {
          document.cookie = key + "=" + val + ";expires=" + expireDate.toGMTString();
        //}
      })
    }
    function getCookie (key) {
      var value = null;
      if (document.cookie.length > 0) {
        var start = document.cookie.indexOf(key + "=");
        if (start !== -1) {
          start = start + key.length+1;
          var end = document.cookie.indexOf(";", start);
          if (end == -1) {
            end = document.cookie.length;
          }
          value = document.cookie.substring(start, end);
        }
      }
      return value;
    }
    /**
     * [getUserList 获取查询条件UserList列表]
     * @param {array} [已选中的应用列表]*/
    function getUserList(appList) {
      var postParam = [];
      angular.forEach(appList,function(app){
        postParam.push({
          'userId':app.userId,
          'userName':app.userName,
          'appId':app.appId,
          'appName':app.appName
        });
      });
      return postParam;
    }


    /**
     * [getDeleteRuleName 获取被选中的规则名集合]
     * @param rules {array} [所有选中规则]
     * */
    function getDeleteRuleName(rules) {
      return rules.map(function(rule) {
        return rule.ruleName;
      })
    }
    /**
     * [getIncludeFields 获取字段中的id名集合]
     * @param fields {array} [所有字段列表]
     *  */
    function getIncludeFields(fields) {
      return fields.map(function(field) {
        return field.id;
      })
    }
    /**
     * [checkFileType 检查文件名是否符合要求]
     * @param fileName {string} [文件名]
     * @param typeArr {array} [格式类型]
     * */
    function checkFileType(fileName,typeArr) {
      var fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
      if(typeArr.indexOf(fileExtension)===-1) {
        toaster.pop('error','错误', Messages.file.format,3000);
        return false;
      }
      return true;
    }
    /**
     * [checkFileSize 检查文件大小是否符合要求]
     * @param fileSize {string} [文件大小]
     * @param size {string} [KB为单位]
     * */
    function checkFileSize(fileSize,size) {
      if(fileSize>size*1024||fileSize===0) {
        toaster.pop('error','错误', Messages.file.size,3000);
        return false;
      }
      return true;
    }
    /**
     * [checkNotEmpty 检查变量是否为空]
     * @param {arr or string} [参数可以为检测数组以及字符串]
     * @param {string} [提示文本]
     * */
    function checkNotEmpty(a,text) {
      if(!a||a.length === 0) {
        toaster.pop('error','错误', Messages.public.empty2(text),3000);
        return false;
      }
      return true;
    }
    /**
     * [limitSearchText 检查搜索文本内容长度是否超过限制]
     * @param val {string} [文本字符]
     * @param num {int} [限制数值大小]
     * @return exceeded {boolean} [是否超过限制字数]
     * @param val {string} [保留下的文本]
     * @param extraText {string} [多余的文本]
     * */
    function limitSearchText(val, num) {
      var result = getByteLen(val, num), exceeded = false, extraText;
      if (result['len'] > num) {
        exceeded = true;
      }
      return {
        exceeded: exceeded,
        val: exceeded?val.substring(0, result['idx']-1):val,
        extraText: exceeded?val.substr(result['idx']-1, 3):null
      }
    }
    /**
     * [getByteLen 比较字节数]
     * *param {string} [文本字符]
     * @param {int} [限制数值大小]
     * @return len {int} [文本的字节数]
     * @return idx {int} [需要截取的位置]
     * */
    function getByteLen(val, num) {
      var len = 0, idx = null;
      for (var i = 0; i < val.length; i++) {
        var a = val.charAt(i);
        if (a.match(/[^\x00-\xff]/ig) != null) {
          len += 2;
        }else {
          len += 1;
        }
        if (len===num) {
          idx = i;
        }
      }
      return {len:len,idx:idx};
    }
    return {
      adminRoute: adminRoute,
      userNotRoute: userNotRoute,
      trim: trim,
      isChineseChar: isChineseChar,
      isFullwidthChar: isFullwidthChar,
      cleanArrTrim: cleanArrTrim,
      removeRepetition: removeRepetition,
      removeEmpty: removeEmpty,
      formatKMBT: formatKMBT,
      openMenu: openMenu,
      autoOpenMenu: autoOpenMenu,
      isExitedInFields: isExitedInFields,
      getIdx: getIdx,
      checkSelectedRule: checkSelectedRule,
      getSelectedRules: getSelectedRules,
      arr2Str: arr2Str,
      arr2IdObj: arr2IdObj,
      getNumFromDate:getNumFromDate,
      today: myDate,
      yesterday: new Date(myDate.getTime()-3600*24*1*1000),
      formatNumber: formatNumber,
      echartSetting: echartSetting,
      getEchartData: getEchartData,
      setCookie: setCookie,
      getCookie: getCookie,
      getUserList: getUserList,
      getDeleteRuleName: getDeleteRuleName,
      getIncludeFields: getIncludeFields,
      checkFileType: checkFileType,
      checkFileSize: checkFileSize,
      checkNotEmpty: checkNotEmpty,
      getByteLen: getByteLen,
      limitSearchText: limitSearchText
    };
  }]);
