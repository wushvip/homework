/**
 * Created by Echo on 2017/3/6.
 */
'use strict';
angular.module('seApp')
  .factory('Menus',['Handle', function(Handle) {
    var docs =  [
      {
        head: '产品使用手册',
        className: 'doc-use-icon',
        surl: null,
        url: null,
        isOpen: false,
        subMenus:[
          {
            head: '系统管理',
            surl:'manage.doc.um',
            url:'manage/doc/um',
            className: null,
            subMenus: null
          },
          {
            head: '应用管理',
            surl: null,
            url: null,
            isOpen: false,
            className: 'application-manage-icon',
            subMenus: [
              {
                head: '创建应用',
                surl: 'manage.doc.create',
                url: 'manage/doc/create',
                className: null,
                subMenus: null
              },
              {
                head: '应用列表管理',
                surl: 'manage.doc.apps',
                url: 'manage/doc/apps',
                className: null,
                subMenus: null
              }
            ]
          },
          {
            head: '基本配置',
            surl: null,
            url: null,
            isOpen: false,
            className: 'application-manage-icon',
            subMenus: [
              {
                head: '功能快速入口',
                surl: 'manage.doc.quickStart',
                url: 'manage/doc/quickStart',
                className: null,
                subMenus: null
              },
              {
                head: '基本信息',
                surl: 'manage.doc.basic',
                url: 'manage/doc/basic',
                className: null,
                subMenus: null
              },
              {
                head: '应用结构',
                surl: 'manage.doc.struct',
                url: 'manage/doc/struct',
                className: null,
                subMenus: null
              },
              {
                head: '索引处理',
                surl: 'manage.doc.index',
                url: 'manage/doc/index',
                className: null,
                subMenus: null
              }
            ]
          },
          {
            head: '高级配置',
            surl: null,
            url: null,
            isOpen: false,
            className: 'application-manage-icon',
            subMenus: [
              {
                head: '排序配置',
                surl: 'manage.doc.order',
                url: 'manage/doc/order',
                className: null,
                subMenus: null
              },
              {
                head: '词库上传',
                surl: 'manage.doc.dic',
                url: 'manage/doc/dic',
                className: null,
                subMenus: null
              },
              {
                head: '智能联想',
                surl: 'manage.doc.suggestion',
                url: 'manage/doc/suggestion',
                className: null,
                subMenus: null
              },
              {
                head: '屏蔽配置',
                surl: 'manage.doc.shield',
                url: 'manage/doc/shield',
                className: null,
                subMenus: null
              },
              {
                head: '搜索推广',
                surl: 'manage.doc.spread',
                url: 'manage/doc/spread',
                className: null,
                subMenus: null
              },
              {
                head: '分组统计',
                surl: 'manage.doc.facet',
                url: 'manage/doc/facet',
                className: null,
                subMenus: null
              },
              {
                head: '分词体验',
                surl: 'manage.doc.analyzier',
                url: 'manage/doc/analyzier',
                className: null,
                subMenus: null
              },
              {
                head: '纠错展示',
                surl: 'manage.doc.recovery',
                url: 'manage/doc/recovery',
                className: null,
                subMenus: null
              }
            ]
          },
          {
            head: '日志统计分析',
            surl: null,
            url: null,
            isOpen: false,
            className: 'sigle-application-manage-icon',
            subMenus: [
              {
                head: '错误日志',
                surl: 'manage.doc.error',
                url: 'manage/doc/error',
                className: null,
                subMenus: null
              },
              {
                head: '数据分析',
                surl: 'manage.doc.analysis',
                url: 'manage/doc/analysis',
                className: null,
                subMenus: null
              },
              {
                head: '关键词热度',
                surl: 'manage.doc.hot',
                url: 'manage/doc/hot',
                className: null,
                subMenus: null
              }
            ]
          },
          {
            head: '数据分析',
            surl: 'manage.doc.dataAnalysis',
            url: 'manage/doc/dataAnalysis',
            className: null,
            subMenus: null
          },
          {
            head: '搜索测试',
            surl: 'manage.doc.test',
            url: 'manage/doc/test',
            className: null,
            subMenus: null
          },
          {
            head: '评测体系',
            surl: 'manage.doc.eval',
            url: 'manage/doc/eval',
            className: null,
            subMenus: null
          }
        ]
      },
      /*{
        head: 'BCSE-API参考手册',
        surl: null,
        url: null,
        className: 'doc-api-icon',
        subMenus: [
          {
            head: 'API调用说明',
            surl: null,
            url: null,
            isOpen: false,
            className: 'doc-instrut-icon',
            subMenus: [
              {
                head: '通信协议和规则',
                surl: 'manage.doc.protocol',
                url: '/doc/protocol',
                className: null,
                subMenus: null
              },
              {
                head: '鉴权',
                surl: 'manage.doc.authentic',
                url: '/doc/authentic',
                className: null,
                subMenus: null
              }
            ]
          },
          {
            head: 'API接口',
            surl: null,
            url: null,
            isOpen: false,
            className: 'doc-interface-icon',
            subMenus: [
              {
                head: '智能联想接口',
                surl: 'manage.doc.intelInterface',
                url: 'manage/doc/intelInterface',
                className: null,
                subMenus: null
              },
              {
                head: '搜索结果接口',
                surl: 'manage.doc.searchInterface',
                url: 'manage/doc/searchInterface',
                className: null,
                subMenus: null
              },
              {
                head: '分词接口',
                surl: 'manage.doc.segmentInterface',
                url: 'manage/doc/segmentInterface',
                className: null,
                subMenus: null
              },
              {
                head: '纠错接口',
                surl: 'manage.doc.correctInterface',
                url: 'manage/doc/correctInterface',
                className: null,
                subMenus: null
              },
              {
                head: '热词接口',
                surl: 'manage.doc.hotWordInterface',
                url: 'manage/doc/hotWordInterface',
                className: null,
                subMenus: null
              }
            ]
          }
        ]
      },*/
      {
        head: 'BCSE-SDK参考手册',
        surl: null,
        url: null,
        isOpen: false,
        className: 'doc-sdk-icon',
        subMenus: [
          {
            head: 'JavaSDK手册',
            surl: null,
            url: null,
            isOpen: false,
            className: 'doc-javasdk-icon',
            subMenus: [
              {
                head: '前言',
                surl: 'manage.doc.foreword',
                url: 'manage/doc/foreword',
                className: null,
                subMenus: null
              },
              {
                head: '版本说明',
                surl: 'manage.doc.version',
                url: 'manage/doc/version',
                className: null,
                subMenus: null
              },
              {
                head: '使用教程',
                surl: 'manage.doc.prepare',
                url: 'manage/doc/prepare',
                className: null,
                subMenus: null
              },
              {
                head: 'BCSEClient客户端',
                surl: 'manage.doc.client',
                url: 'manage/doc/client',
                className: null,
                subMenus: null
              },
              {
                head: '鉴权说明',
                surl: 'manage.doc.explain',
                url: 'manage/doc/explain',
                className: null,
                subMenus: null
              },
              {
                head: '搜索功能',
                surl: 'manage.doc.search',
                url: 'manage/doc/search',
                className: null,
                subMenus: null
              },
              {
                head: '智能提示',
                surl: 'manage.doc.suggest',
                url: 'manage/doc/suggest',
                className: null,
                subMenus: null
              },
              {
                head: '分词功能',
                surl: 'manage.doc.seg',
                url: 'manage/doc/seg',
                className: null,
                subMenus: null
              },
              {
                head: '热词功能',
                surl: 'manage.doc.hotWords',
                url: 'manage/doc/hotWords',
                className: null,
                subMenus: null
              },
              {
                head: '指定日期获取热词',
                surl: 'manage.doc.hotWordsBydate',
                url: 'manage/doc/hotWordsBydate',
                className: null,
                subMenus: null
              },
              {
                head: '搜索耗时',
                surl: 'manage.doc.searchCost',
                url: 'manage/doc/searchCost',
                className: null,
                subMenus: null
              },
              {
                head: '搜索次数',
                surl: 'manage.doc.searchTimes',
                url: 'manage/doc/searchTimes',
                className: null,
                subMenus: null
              },
              {
                head: '纠错功能',
                surl: 'manage.doc.errorCorrect',
                url: 'manage/doc/errorCorrect',
                className: null,
                subMenus: null
              },
              {
                head: '更新索引',
                surl: 'manage.doc.updateIndex',
                url: 'manage/doc/updateIndex',
                className: null,
                subMenus: null
              },
              {
                head: '清空索引',
                surl: 'manage.doc.clearIndex',
                url: 'manage/doc/clearIndex',
                className: null,
                subMenus: null
              },
              {
                head: '删除索引',
                surl: 'manage.doc.deleteIndex',
                url: 'manage/doc/deleteIndex',
                className: null,
                subMenus: null
              },
              {
                head: '索引部分字段更新',
                surl: 'manage.doc.updatePartIndex',
                url: 'manage/doc/updatePartIndex',
                className: null,
                subMenus: null
              },
              {
                head: 'Json删除索引',
                surl: 'manage.doc.jsonDeleteIndex',
                url: 'manage/doc/jsonDeleteIndex',
                className: null,
                subMenus: null
              },
              {
                head: '索引多条直接更新',
                surl: 'manage.doc.updateMulIndex',
                url: 'manage/doc/updateMulIndex',
                className: null,
                subMenus: null
              },
              {
                head: '停用词功能',
                surl: 'manage.doc.stopWords',
                url: 'manage/doc/stopWords',
                className: null,
                subMenus: null
              },
              {
                head: '同义词功能',
                surl: 'manage.doc.similarWords',
                url: 'manage/doc/similarWords',
                className: null,
                subMenus: null
              },
              {
                head: '扩展词功能',
                surl: 'manage.doc.extendWords',
                url: 'manage/doc/extendWords',
                className: null,
                subMenus: null
              },
              {
                head: '纠错词典上传功能',
                surl: 'manage.doc.correctUpload',
                url: 'manage/doc/correctUpload',
                className: null,
                subMenus: null
              },
              {
                head: '获取搜索推广规则',
                surl: 'manage.doc.getSearchRules',
                url: 'manage/doc/getSearchRules',
                className: null,
                subMenus: null
              },
              {
                head: '添加搜索推广规则',
                surl: 'manage.doc.addSearchRules',
                url: 'manage/doc/addSearchRules',
                className: null,
                subMenus: null
              },
              {
                head: '更新搜索推广规则',
                surl: 'manage.doc.updateSearchRules',
                url: 'manage/doc/updateSearchRules',
                className: null,
                subMenus: null
              },
              {
                head: '删除搜索推广规则',
                surl: 'manage.doc.deleteSearchRules',
                url: 'manage/doc/deleteSearchRules',
                className: null,
                subMenus: null
              },
              {
                head: '批量删除搜索推广规则',
                surl: 'manage.doc.batchDelSearchRules',
                url: 'manage/doc/batchDelSearchRules',
                className: null,
                subMenus: null
              },
              {
                head: '获取敏感词规则',
                surl: 'manage.doc.getSensitiveRules',
                url: 'manage/doc/getSensitiveRules',
                className: null,
                subMenus: null
              },
              {
                head: '添加敏感词规则',
                surl: 'manage.doc.addSensitiveRules',
                url: 'manage/doc/addSensitiveRules',
                className: null,
                subMenus: null
              },
              {
                head: '更新敏感词规则',
                surl: 'manage.doc.updateSensitiveRules',
                url: 'manage/doc/updateSensitiveRules',
                className: null,
                subMenus: null
              },
              {
                head: '删除敏感词规则',
                surl: 'manage.doc.deleteSensitiveRules',
                url: 'manage/doc/deleteSensitiveRules',
                className: null,
                subMenus: null
              },
              {
                head: '批量删除敏感词规则',
                surl: 'manage.doc.batchDelSensitiveRules',
                url: 'manage/doc/batchDelSensitiveRules',
                className: null,
                subMenus: null
              },
              {
                head: 'BCSE返回参数说明',
                surl: 'manage.doc.javaSdkRes',
                url: 'manage/doc/javaSdkRes',
                className: null,
                subMenus: null
              }
            ]
          }
        ]
      },
      {
        head: 'FAQ',
        surl: null,
        url: null,
        isOpen: false,
        className: 'doc-fqa-icon',
        subMenus: [
          {
            head: '如何获得帮助',
            surl: 'manage.doc.help',
            url: 'manage/doc/help',
            className: null,
            subMenus: null
          },
          {
            head: '售后服务',
            surl: 'manage.doc.service',
            url: 'manage/doc/service',
            className: null,
            subMenus: null
          },
          {
            head: '常见问题解答',
            surl: 'manage.doc.question',
            url: 'manage/doc/question',
            className: null,
            subMenus: null
          }
        ]
      }
    ];
    var settings = [
      {
        head: '基本配置',
        className: 'basic-setting-icon',
        surl: null,
        url: null,
        isOpen: false,
        subMenus:[
          {
            head: '基本信息',
            surl :'manage.app.basic',
            url: null,
            className: 'basic',
            subMenus: null
          },
          {
            head: '应用结构',
            surl: 'manage.app.struct',
            url: null,
            className: 'struct',
            subMenus:null
          },
          {
            head: '索引处理',
            surl: 'manage.app.index',
            url: null,
            className: 'index',
            subMenus: null
          }
        ]
      },
      {
        head: '高级配置',
        surl: null,
        url: null,
        isOpen: false,
        className: 'config-icon',
        subMenus: [
          {
            head: '排序配置',
            surl: 'manage.app.order',
            url: null,
            className: 'order',
            subMenus: null
          },
          {
            head: '词库上传',
            surl: 'manage.app.dic',
            url: null,
            className: 'dic',
            subMenus: null
          },
          {
            head: '智能联想',
            surl: 'manage.app.suggestion',
            url: null,
            className: 'suggestion',
            subMenus: null
          },
          {
            head: '屏蔽配置',
            surl: 'manage.app.shield',
            url: null,
            className: 'shield',
            subMenus: null
          },
          {
            head: '搜索推广',
            surl: 'manage.app.spread',
            url: null,
            className: 'spread',
            subMenus: null
          },
          {
            head: '分组统计',
            surl: 'manage.app.facet',
            url: 'doc/result',
            className: 'facet',
            subMenus: null
          },
          {
            head: '分词体验',
            surl: 'manage.app.analyzier',
            url: null,
            className: 'analyzier',
            subMenus: null
          },
          {
            head: '纠错展示',
            surl: 'manage.app.recovery',
            url: null,
            className: 'recovery',
            subMenus: null
          }
        ]
      },
      {
        head: '日志统计分析',
        surl: null,
        url: null,
        isOpen: false,
        className: 'statistic-icon',
        subMenus: [
          {
            head: '错误日志',
            surl: 'manage.app.error',
            url: null,
            className: 'error',
            subMenus: null
          },
          {
            head: '数据分析',
            surl: 'manage.app.analysis',
            url: null,
            className: 'analysis',
            subMenus: null
          },
          {
            head: '关键词热度',
            surl: 'manage.app.hot',
            url: null,
            className: 'hot',
            subMenus: null
          }
        ]
      }
    ];
    var option = {
      tooltip : {
        trigger: 'axis'
      },
      legend: {
        data:[],
        width: '100%'
      },
      calculable : true,
      grid: {},
      xAxis : [
        {
          name:'时间',
          type : 'category',
          boundaryGap : false,
          axisLabel: {
            //interval: 0,
            rotate: 45
          },
          data : []
        }
      ],
      yAxis : [
        {
          name: 'y轴',
          type : 'value',
          min: 0,
          axisLabel : {
            formatter: function (v) {
              return Handle.formatKMBT(v);
            }
          }
        }
      ],
      series : []
    };

    return {
      settings: settings,
      docs: docs,
      echartsConfig: option
    };
  }]);
