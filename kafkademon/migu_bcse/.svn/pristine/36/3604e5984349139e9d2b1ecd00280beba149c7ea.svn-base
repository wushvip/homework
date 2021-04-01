package com.chinamobile.cmss.bcse.tool.exception;

import java.util.HashMap;

import org.springframework.expression.EvaluationException;

import com.sun.tools.javac.resources.version;

/**
 * 
 * @ClassName: ExceptionConstants
 * @Description: TODO 异常添加
 * @author: chenmin
 * @date: 2016年2月18日 上午9:44:54
 * 
 */

public class ExceptionConstants {

	public static HashMap<String, String> map = new HashMap<String, String>();

	// 搜索api
	public static final String SearchError = "1000000";
	public static final String ParametersError = "1000001";
	public static final String ErrorRecorveryError = "1000002";
	public static final String CreateQueryError = "1000003";
	public static final String ExcuteQueryError = "1000004";
	public static final String QueryLogError = "1000009";
	public static final String AppStopError = "1000010";

	// 应用管理
	public static final String GetAppListError = "2000001";
	public static final String DeleAppError = "2000002";
	public static final String ModifyAppError = "2000003";
	public static final String ShowSignDetailAppError = "2000004";
	public static final String ShowAppStructureError = "2000005";
	public static final String SaveSignDetailAppInfoError = "2000006";
	public static final String SaveAppStructureError = "2000007";
	public static final String IsExistCreatingAppError = "2000008";
	public static final String CreateAppError = "2000009";
	public static final String UpdateIndexError = "2000010";
	public static final String RebuildIndexError = "2000011";
	public static final String ClearIndexError = "2000012";
	public static final String GetDataOperaHistoryError = "2000013";
	public static final String LocalUploadError = "2000014";
	public static final String FtpUploadError = "2000015";
	public static final String IndexCreateAppError = "2000016";
	public static final String IndexDeleteAppError = "2000017";
	public static final String IndexRebuildIndexError = "2000018";
	public static final String IndexUpdateDataError = "2000019";
	public static final String IndexgetParamError = "2000020";
	public static final String IndexDeleteDataError = "2000021";
	
	// 规则异常
	public static final String AddRuleError = "3000001";
	public static final String GetRuleFromDBError = "3000002";
	public static final String UpdateRuleError = "3000003";
	public static final String DelRuleError = "3000004";
	public static final String GetIndexedFieldsError = "3000005";
	public static final String GetShownableFieldsError = "3000006";
	public static final String GetSearchTestFieldError = "3000007";
	public static final String BashUpdateRuleError = "3000008";
	public static final String BashDeleteRuleError = "3000009";
	public static final String JedisConnectionError = "3000010";
	public static final String WriteRedisError = "3000011";
	public static final String ConfigXMLUpdateError = "3000012";
	public static final String InitialRedisError = "3000013";
	public static final String ExistedRuleNameError = "3000014";
	public static final String ConfigDownloadError = "3000015";
	public static final String ConfigUploadError = "3000016";
	public static final String ConfigWriteError = "3000017";
	public static final String ConfigReadError = "3000018";
	public static final String RoughRuleConvertError = "3000019";
	public static final String SolrReloadError = "3000020";
	public static final String EpitomeRuleConvertError = "3000021";
	public static final String GetSearchTestFieldsError = "3000022";
	public static final String MysqlSyncToRedisError = "3000023";
	public static final String GetRedisDataError = "3000024";
	public static final String SpreadIdFormatError = "300025";
	public static final String RuleNumOutOfLimit = "300026";
	public static final String ShieldKeyWordsNumOutOfLimit = "300027";
	public static final String ShieldKeyWordLengthOutOfLimit = "300028";
	public static final String ShieldIdIllegal = "300029";
	public static final String SpreadIdOutOfInt = "300030";
	public static final String SpreadIdOutOfLong = "300031";
	public static final String ShieldKeyWordIsEmpty = "300032";
	public static final String SpreadIdIsEmpty = "300033";

	// 分詞
	public static final String AnalysisError = "4000000";
	public static final String RecoveryError = "5000000";

	// 数据分析
	public static final String HotWordError = "6000000";

	// 用户管理
	public static final String UserError = "5000001";
	public static final String SendEmailException = "5000002";
	// 日志
	public static final String FillEmptyDataException = "6000001";
	// sql或者数据库异常
	public static final String SqlOrDatabaseException = "9000000";

	// 系统异常
	public static final String OtherException = "0000000";
	public static final String PackageException = "0000001";
	public static final String FileException = "0000002";
	
	//评测结果异常
	public static final String EvaluateError = "7000000";

	static {

		map.put(GetRedisDataError, "从redis获取数据异常");
		map.put(AppStopError, "应用停用或无此应用");
		map.put(FileException, "文件处理异常");
		map.put(FillEmptyDataException, "填充空数据时异常");
		map.put(PackageException, "底层封装异常信息时出现异常");
		map.put(OtherException, "其他异常");
		map.put(SendEmailException, "发送邮件异常");
		map.put(SqlOrDatabaseException, "服务异常");
		map.put(UserError, "用户管理异常");
		map.put(SearchError, "搜索服务异常");
		map.put(ParametersError, "请求参数异常");
		map.put(ErrorRecorveryError, "纠错服务异常");
		map.put(CreateQueryError, "创建查询对象异常");
		map.put(ExcuteQueryError, "执行查询异常");
		map.put(GetAppListError, "获取应用列表异常");
		map.put(DeleAppError, "删除应用异常");
		map.put(ShowSignDetailAppError, "展示应用详细信息异常");
		map.put(ShowAppStructureError, "展示应用结构信息异常");
		map.put(SaveSignDetailAppInfoError, "保存应用详细信息异常");
		map.put(SaveAppStructureError, "保存应用结构信息异常");
		map.put(IsExistCreatingAppError, "创建应用已存在异常");
		map.put(CreateAppError, "创建应用异常");
		map.put(UpdateIndexError, "更新索引异常");
		map.put(RebuildIndexError, "重建索引异常");
		map.put(ClearIndexError, "清除索引异常");
		map.put(GetDataOperaHistoryError, "获得数据操作历史异常");
		map.put(LocalUploadError, "本地上传文件异常");
		map.put(FtpUploadError, "ftp文件上传异常");
		map.put(IndexgetParamError, "获取处理索引需要的参数时出现异常");
		map.put(IndexCreateAppError, "创建索引核异常");
		map.put(IndexDeleteAppError, "删除索引核异常");
		map.put(IndexRebuildIndexError, "重建索引核异常");
		map.put(IndexUpdateDataError, "索引更新数据异常");
		map.put(AddRuleError, "增加规则异常");
		map.put(GetRuleFromDBError, "获取数据库规则异常");
		map.put(UpdateRuleError, "更新规则操作异常");
		map.put(DelRuleError, "删除规则，数据库操作异常");
		map.put(GetIndexedFieldsError, "获取数据库建立索引字段异常");
		map.put(GetShownableFieldsError, "获取数据库可展示字段异常");
		map.put(BashUpdateRuleError, "批量更新规则，数据库操作异常");
		map.put(GetSearchTestFieldError, "获取数据库搜索测试字段异常");
		map.put(BashDeleteRuleError, "批量删除规则，数据库操作异常");
		map.put(SpreadIdFormatError, "搜索推广文档ID格式错误");
		map.put(ShieldKeyWordsNumOutOfLimit, "屏蔽配置规则关键词数量超上限");
		map.put(ShieldKeyWordLengthOutOfLimit, "屏蔽配置规则关键词长度超上限");
		map.put(ShieldIdIllegal, "屏蔽配置规则关键词不允许包含特殊字符");
		map.put(JedisConnectionError, "获取redis连接失败或读取redis数据超时");
		map.put(WriteRedisError, "写redis异常");
		map.put(ConfigXMLUpdateError, "配置文件更新异常");
		map.put(ExistedRuleNameError, "同名规则已存在异常");
		map.put(InitialRedisError, "初始化redis异常");
		map.put(ConfigDownloadError, "下载zookeeper配置文件异常");
		map.put(ConfigUploadError, "上传配置文件至zookeeper异常");
		map.put(RoughRuleConvertError, "粗排规则前后台参数转换异常");
		map.put(SolrReloadError, "solr配置文件更新上传zookeeper成功，reload操作异常");
		map.put(EpitomeRuleConvertError, "摘要配置规则前后台参数转换异常");
		map.put(GetSearchTestFieldsError, "获取搜索测试页面字段信息异常");
		map.put(MysqlSyncToRedisError, "mysql全量更新同步至redis异常");
		map.put(AnalysisError, "分词服务异常");
		map.put(RecoveryError, "纠错服务异常");
		map.put(SpreadIdOutOfInt,"搜索推广ID超出整型范围");
		map.put(SpreadIdOutOfLong, "搜索推广ID超出长整型范围");
		map.put(ShieldKeyWordIsEmpty, "存在屏蔽关键字为空字符串");
		map.put(SpreadIdIsEmpty, "存在搜索推广关键字为空字符串");
		map.put(EvaluateError, "执行评测异常");
	}

	public static void main(String[] args) {
		// ExceptionConstants.Parameters.name();
	}

}
