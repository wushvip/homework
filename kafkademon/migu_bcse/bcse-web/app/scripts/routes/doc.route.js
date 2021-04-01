/**
 * Created by Echo on 2017/3/27.
 */
angular.module('doc',[]);

angular.module('doc')
  .config(['$stateProvider', function($stateProvider) {
    //配置路由
    $stateProvider
      .state('manage.doc', {
        url: '/doc',
        views: {
          'wrapper': {
            templateUrl: 'views/doc/doc.html',
            controller: 'docCtrl'
          }
        }
      })
      .state('manage.doc.um', {
        url: "/um",
        templateUrl: 'views/doc/doc.um.html'
      })
      .state('manage.doc.create', {
        url: "/create",
        templateUrl: 'views/doc/doc.create.html'
      })
      .state('manage.doc.apps', {
        url: "/apps",
        templateUrl: 'views/doc/doc.apps.html'
      })
      .state('manage.doc.quickStart', {
        url: "/quickStart",
        templateUrl: 'views/doc/doc.app.quickStart.html'
      })
      .state('manage.doc.basic', {
        url: "/basic",
        templateUrl: 'views/doc/doc.app.basic.html'
      })
      .state('manage.doc.struct', {
        url: "/struct",
        templateUrl: 'views/doc/doc.app.struct.html'
      })
      .state('manage.doc.index', {
        url: "/index",
        templateUrl: 'views/doc/doc.app.index.html'
      })
      .state('manage.doc.order', {
        url: "/order",
        templateUrl: 'views/doc/doc.app.order.html'
      })
      .state('manage.doc.dic', {
        url: "/dic",
        templateUrl: 'views/doc/doc.app.dic.html'
      })
      .state('manage.doc.suggestion', {
        url: "/suggestion",
        templateUrl: 'views/doc/doc.app.suggestion.html'
      })
      .state('manage.doc.shield', {
        url: "/shield",
        templateUrl: 'views/doc/doc.app.shield.html'
      })
      .state('manage.doc.spread', {
        url: "/spread",
        templateUrl: 'views/doc/doc.app.spread.html'
      })
      .state('manage.doc.facet', {
        url: "/facet",
        templateUrl: 'views/doc/doc.app.facet.html'
      })
      .state('manage.doc.analyzier', {
        url: "/analyzier",
        templateUrl: 'views/doc/doc.app.analyzier.html'
      })
      .state('manage.doc.recovery', {
        url: "/recovery",
        templateUrl: 'views/doc/doc.app.recovery.html'
      })
      .state('manage.doc.error', {
        url: "/error",
        templateUrl: 'views/doc/doc.app.error.html'
      })
      .state('manage.doc.analysis', {
        url: "/analysis",
        templateUrl: 'views/doc/doc.app.analysis.html'
      })
      .state('manage.doc.hot', {
        url: "/hot",
        templateUrl: 'views/doc/doc.app.hot.html'
      })
      .state('manage.doc.dataAnalysis', {
        url: "/dataAnalysis",
        templateUrl: 'views/doc/doc.dataAnalysis.html'
      })
      .state('manage.doc.test', {
        url: "/test",
        templateUrl: 'views/doc/doc.test.html'
      })
      .state('manage.doc.eval', {
        url: "/eval",
        templateUrl: 'views/doc/doc.eval.html'
      })
      .state('manage.doc.foreword', {
        url: "/foreword",
        templateUrl: 'views/doc/sdk.foreword.html'
      })
      .state('manage.doc.version', {
        url: "/version",
        templateUrl: 'views/doc/sdk.version.html'
      })
      .state('manage.doc.prepare', {
        url: "/prepare",
        templateUrl: 'views/doc/sdk.prepare.html'
      })
      .state('manage.doc.client', {
        url: "/client",
        templateUrl: 'views/doc/sdk.client.html'
      })
      .state('manage.doc.explain', {
        url: "/explain",
        templateUrl: 'views/doc/sdk.explain.html'
      })
      .state('manage.doc.search', {
        url: "/search",
        templateUrl: 'views/doc/sdk.search.html'
      })
      .state('manage.doc.suggest', {
        url: "/suggest",
        templateUrl: 'views/doc/sdk.suggest.html'
      })
      .state('manage.doc.seg', {
        url: "/seg",
        templateUrl: 'views/doc/sdk.seg.html'
      })
      .state('manage.doc.hotWords', {
        url: "/hotWords",
        templateUrl: 'views/doc/sdk.hotWords.html'
      })
      .state('manage.doc.hotWordsBydate', {
        url: "/hotWordsBydate",
        templateUrl: 'views/doc/sdk.hotWordsBydate.html'
      })
      .state('manage.doc.searchCost', {
        url: "/searchCost",
        templateUrl: 'views/doc/sdk.searchCost.html'
      })
      .state('manage.doc.searchTimes', {
        url: "/searchTimes",
        templateUrl: 'views/doc/sdk.searchTimes.html'
      })
      .state('manage.doc.errorCorrect', {
        url: "/errorCorrect",
        templateUrl: 'views/doc/sdk.errorCorrect.html'
      })
      .state('manage.doc.updateIndex', {
        url: "/updateIndex",
        templateUrl: 'views/doc/sdk.updateIndex.html'
      })
      .state('manage.doc.clearIndex', {
        url: "/clearIndex",
        templateUrl: 'views/doc/sdk.clearIndex.html'
      })
      .state('manage.doc.deleteIndex', {
        url: "/deleteIndex",
        templateUrl: 'views/doc/sdk.deleteIndex.html'
      })
      .state('manage.doc.updatePartIndex', {
        url: "/updatePartIndex",
        templateUrl: 'views/doc/sdk.updatePartIndex.html'
      })
      .state('manage.doc.jsonDeleteIndex', {
        url: "/jsonDeleteIndex",
        templateUrl: 'views/doc/sdk.jsonDeleteIndex.html'
      })
      .state('manage.doc.updateMulIndex', {
        url: "/updateMulIndex",
        templateUrl: 'views/doc/sdk.updateMulIndex.html'
      })
      .state('manage.doc.stopWords', {
        url: "/stopWords",
        templateUrl: 'views/doc/sdk.stopWords.html'
      })
      .state('manage.doc.similarWords', {
        url: "/similarWords",
        templateUrl: 'views/doc/sdk.similarWords.html'
      })
      .state('manage.doc.extendWords', {
        url: "/extendWords",
        templateUrl: 'views/doc/sdk.extendWords.html'
      })
      .state('manage.doc.correctUpload', {
        url: "/correctUpload",
        templateUrl: 'views/doc/sdk.correctUpload.html'
      })
      .state('manage.doc.getSearchRules', {
        url: "/getSearchRules",
        templateUrl: 'views/doc/sdk.getSearchRules.html'
      })
      .state('manage.doc.addSearchRules', {
        url: "/addSearchRules",
        templateUrl: 'views/doc/sdk.addSearchRules.html'
      })
      .state('manage.doc.updateSearchRules', {
        url: "/updateSearchRules",
        templateUrl: 'views/doc/sdk.updateSearchRules.html'
      })
      .state('manage.doc.deleteSearchRules', {
        url: "/deleteSearchRules",
        templateUrl: 'views/doc/sdk.deleteSearchRules.html'
      })
      .state('manage.doc.batchDelSearchRules', {
        url: "/batchDelSearchRules",
        templateUrl: 'views/doc/sdk.batchDelSearchRules.html'
      })
      .state('manage.doc.getSensitiveRules', {
        url: "/getSensitiveRules",
        templateUrl: 'views/doc/sdk.getSensitiveRules.html'
      })
      .state('manage.doc.addSensitiveRules', {
        url: "/addSensitiveRules",
        templateUrl: 'views/doc/sdk.addSensitiveRules.html'
      })
      .state('manage.doc.updateSensitiveRules', {
        url: "/updateSensitiveRules",
        templateUrl: 'views/doc/sdk.updateSensitiveRules.html'
      })
      .state('manage.doc.deleteSensitiveRules', {
        url: "/deleteSensitiveRules",
        templateUrl: 'views/doc/sdk.deleteSensitiveRules.html'
      })
      .state('manage.doc.batchDelSensitiveRules', {
        url: "/batchDelSensitiveRules",
        templateUrl: 'views/doc/sdk.batchDelSensitiveRules.html'
      })
      .state('manage.doc.javaSdkRes', {
        url: "/javaSdkRes",
        templateUrl: 'views/doc/sdk.javaSdkRes.html'
      })
      .state('manage.doc.help', {
        url: "/help",
        templateUrl: 'views/doc/faq.help.html'
      })
      .state('manage.doc.service', {
        url: "/service",
        templateUrl: 'views/doc/faq.service.html'
      })
      .state('manage.doc.question', {
        url: "/question",
        templateUrl: 'views/doc/faq.question.html'
      })
      .state('manage.doc.protocol', {
        url: "/protocol",
        templateUrl: 'views/doc/api.protocol.html'
      })
      .state('manage.doc.authentic', {
        url: "/authentic",
        templateUrl: 'views/doc/api.authentic.html'
      })
      .state('manage.doc.intelInterface', {
        url: "/intelInterface",
        templateUrl: 'views/doc/api.intelInterface.html'
      })
      .state('manage.doc.searchInterface', {
        url: "/searchInterface",
        templateUrl: 'views/doc/api.searchInterface.html'
      })
      .state('manage.doc.segmentInterface', {
        url: "/segmentInterface",
        templateUrl: 'views/doc/api.segmentInterface.html'
      })
      .state('manage.doc.correctInterface', {
        url: "/correctInterface",
        templateUrl: 'views/doc/api.correctInterface.html'
      })
      .state('manage.doc.hotWordInterface', {
        url: "/hotWordInterface",
        templateUrl: 'views/doc/api.hotWordInterface.html'
      });

  }]);
