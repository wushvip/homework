/**
 * Created by Echo on 2017/3/12.
 */
angular.module('seApp')

  .factory('restService', ['$q', '$resource', '$location', '$rootScope','toaster','Signature','Handle', function($q, $resource, $location, $rootScope,toaster, Signature, Handle) {
    var restReceive = {
      process: function(res, successFunc, restUrl, method, data) {
        if (res.status === "FAIL") {
          toaster.pop('error','错误', res.message,3000);
          return false;
        } else {
          var key = res.result ? res.result.secretKey : null;
          if (key) {
            // set key to cookie, expired after 1 day
            Handle.setCookie({secretKey: key},1);
          }
          if (typeof(successFunc) === 'function') {
            successFunc(res.result);
          }
          //toaster.pop('success','成功', res.message,3000);
          return res.result;
        }
      },
      processError: function(errorRes, errorFunc, restUrl, method) {
        console.log(errorRes);
        if (typeof(errorFunc) === 'function') {
          errorFunc(errorRes);
        } else {
          $rootScope.errorTitle = '与服务器通信失败！';
          $rootScope.errorInterface = restUrl + ":" + method;
          $rootScope.errorMessage = 'status code: ' + errorRes.code + '; Message: ' + errorRes.statusText;
          $location.path('/error');
          return false;
        }
      }
    };
    /**
     * objKeySort [对象key值排序]
     * @param {object} [排序的对象]
     * @return {object} [返回排好序的对象值集合]
     * */
    function objKeySort(obj) {//排序的函数
      var newkey = Object.keys(obj).sort();
      //先用Object内置类的keys方法获取要排序对象的属性名，再利用Array原型上的sort方法对获取的属性名进行排序，newkey是一个数组
      var newObj = {};//创建一个新的对象，用于存放排好序的键值对
      for (var i = 0; i < newkey.length; i++) {//遍历newkey数组
        newObj[newkey[i]] = obj[newkey[i]];//向新创建的对象中按照排好的顺序依次增加键值对
      }
      return newObj;
    }
    /**@param value {object} [url变量对象值]*/
    function prepareRequest (restUrl, method, value,data, resourceObj) {
      var methodArray = ['GET', 'DELETE', 'HEAD', 'POST', 'PUT'];
      var noPayloadMethods = ['GET', 'DELETE', 'HEAD'];
      if (!restUrl || methodArray.indexOf(method) === -1) {
        return null;
      }

      var restParam = {};
      var restResource;
      /**如果登录接口则不设置header*/
      var userId = Handle.getCookie('userId')?Handle.getCookie('userId'):'';
      var time = new Date().getTime()+'';
      var secretKey = Handle.getCookie('secretKey')?Handle.getCookie('secretKey'):'';
      var contentType = 'application/json;charset=UTF-8';
      var dataSort = objKeySort(data);
      restParam.data = (data === null) ? null : JSON.stringify(dataSort);
      //console.log(restParam.data.replace(/\"/g,''));
      var contentMD5 = md5(restParam.data.replace(/\"/g,''));
      //console.log(contentMD5);
      var stringToSign = [method.toUpperCase(), contentMD5, contentType, time,'', ''].join('\n');
      var signature = Signature.HMACSHA1(secretKey,stringToSign);
      //if (noPayloadMethods.indexOf(method) !== -1) {
        restResource = resourceObj(restUrl, value, {
          action: {
            method: method,
            params: restParam.data,
            headers: {
              'content-type': contentType,
              'Authorization': ['SE', ' ', userId, ':', signature].join(''),
              'SE-Date': time,
              'SE-User': userId
            }
          }
        });
      /*} else {
        restResource = resourceObj(restUrl, value, {
          action: {
            method: method,
            headers: {
              'content-type': contentType,
              'Authorization': ['SE', ' ', userId, ':', Signature].join(''),
              'SE-Date': time,
              'SE-User': userId
            }
          }
        });
      }*/

      return restResource;
  }
    var restService = {
      /**
       * @ngdoc function
       * @name restService.request
       * @module restService
       * @kind function
       *
       * @description
       * 发起一个RESTFul请求，如果返回正常，调用回调函数处理返回结果。
       *
       * 使用例子：
       ```
       var elm = ...;
       var $rootScope = angular.injector(['ng']).get('$rootScope');
       var processFunc = function(result) {
             var captcha = "data:image/png;base64," + result.imageBase64;
             elm.find('img').attr('src', captcha);
             $rootScope.captchaId = result.captchaId;
           }
       restService.request(restUrl.getUrl('captcha'), 'GET', data, processFunc);
       ```
       *
       * @param {String} restUrl 资源URI
       * @param {String} method 请求方法，可以'POST'、'GET'、'PUT'、'DELETE'中的其中之一
       * @param {Object} data 请求中带的数据
       * @param {Function} processFunc 处理返回结果的回调函数，参数为从服务器返回的JSON数据
       * @returns null
       */
      request: function(restUrl, method,value, data, processFunc, processErrorFunc) {
        var restResource = prepareRequest(restUrl, method,value, data, $resource);
        if (!restResource) {
          return;
        }

        restResource.action(data, function(res) {
          //process处理返回数据，并且processFunc对返回数据进行界面显示处理
          restReceive.process(res, processFunc, restUrl, method, data);
        }, function(errorRes) {
          restReceive.processError(errorRes, processErrorFunc, restUrl, method);
        });
      },

      /**
       * @ngdoc function
       * @name restService.promiseRequest
       * @module restService
       * @kind function
       *
       * @description
       * 发起一个RESTFul请求，如果返回正常，返回一个promise对象，该promise对象的then函数可以接受回调函数作为参数。
       * 该回调函数处理返回结果，有一个参数result，可能是一个JSON对象，或者是false：
       * 如果是JSON对象，表示服务器成功返回了结果，该JSON对象即为返回的结果，如果是null则表示无需向客户端返回数据。
       * 如果是false，表示服务器返回的消息有错误（FAIL），错误信息已经在
       * $rootScope.errorMessage里面设置了。
       *
       * 使用例子：
       ```
       var elm = ...;
       var $rootScope = angular.injector(['ng']).get('$rootScope');
       var processFunc = function(result) {
         if(result === false) {
            // do some work for error processing.
            return;
         }
         var captcha = "data:image/png;base64," + result.imageBase64;
         elm.find('img').attr('src', captcha);
         $rootScope.captchaId = result.captchaId;
       }
       restService.promiseRequest(restUrl.getUrl('captcha'), 'GET', data).then(processFunc);
       ```
       *
       * @param {String} restUrl 资源URI
       * @param {String} method 请求方法，可以'POST'、'GET'、'PUT'、'DELETE'中的其中之一
       * @param {Object} data 请求中带的数据
       * @returns {Promise} a promise object
       */
      promiseRequest: function(restUrl, method, value, data) {
        var d = $q.defer();
        var restResource = prepareRequest(restUrl, method,value, data, $resource);
        if (!restResource) {
          return false;
        }

        restResource.action(data, function(res) {
          d.resolve(restReceive.process(res, null, restUrl, method, data));
        }, function(errorRes) {
          d.reject(restReceive.processError(errorRes, null, restUrl, method));
        });
        return d.promise;
      },

      processRes: function(res, restUrl, method) {
        return restReceive.process(res, null, restUrl, method);
      }
    };

    return restService;

  }])

.factory('restUrl', ['$location', '$rootScope', function($location, $rootScope) {
  var restUrl = {
    getUrl: function(resourceName) {
      return $rootScope.system+resourceName;
      /*if (angular.isDefined($rootScope.baseIp)) {
        var baseUrl;
        if($rootScope.port&&$rootScope.system){
          baseUrl = 'http://' + $rootScope.baseIp  + ":" + $rootScope.port + "/" + $rootScope.system + "/";
        }
        return baseUrl + resourceName;
      } else {
        if (!angular.isDefined($rootScope.restInterfaceUrls) || !angular.isDefined($rootScope.restInterfaceUrls[resourceName])) {
          $rootScope.errorMessage = 'The REST interface of \'' + resourceName + '\' is not defined!';
          $location.path('/error');
          return null;
        }
        return $rootScope.restInterfaceUrls[resourceName];
      }*/
    }
  };
  return restUrl;
}]);

