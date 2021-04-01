package com.chinamobile.cmss.bcse.sdk.exception;




/** 
 * 
 * @ClassName: ExceptionConstants 
 * @Description: TODO  异常添加
 * @author: chenmin
 * @date: 2016年2月18日 上午9:44:54
 *   
 */

public class  ExceptionConstants {
	// 返回信息
	//成功返回码
	public static final String SUCCESS_CODE = "200";
	public static final String FAIL_SYS_CODE = "1000000";
	public static final String FAIL_SERIVICE_CODE = "2000000";
	
	
	//搜索api
	//public static final String SearchError="1000000";
	public static final String ParametersError="1000001";
	public static final String ErrorRecorveryError="1000002";
	public static final String CreateQueryError="1000003";
	public static final String ExcuteQueryError="1000004";
	//应用管理
	public static final String GetAppListError="2000001";
	public static final String DeleAppError="2000002";
	public static final String ModifyAppError="2000003";
	public static final String ShowSignDetailAppError="2000004";
	public static final String ShowAppStructureError="2000005";
	public static final String SaveSignDetailAppInfoError="2000006";
	public static final String SaveAppStructureError="2000007";
	public static final String IsExistCreatingAppError="2000008";
	public static final String CreateAppError="2000009";
	public static final String UpdateIndexError="2000010";
	public static final String RebuildIndexError="2000011";
	public static final String ClearIndexError="2000012";
	public static final String GetDataOperaHistoryError="2000013";
	public static final String LocalUploadError="2000014";
 	public static final String FtpUploadError="2000015";
	public static final String IndexCreateAppError="2000016";
	public static final String IndexDeleteAppError="2000017";
	public static final String IndexRebuildIndexError="2000018";
	public static final String IndexUpdateDataError="2000019";
	//规则异常
	public static final String AddRuleError="3000001";
	public static final String ShowRuleError="3000002";
	public static final String UpdateRuleError="3000003";
	public static final String DelRuleError="3000004";
	public static final String GetIndexedFieldsError="3000005";
	public static final String GetShownableFieldsError="3000006";
	public static final String GetSearchTestFieldError="3000007";
	public static final String BashUpdateRuleError="3000008";
	public static final String BashDeleteRuleError="3000009";
	public static final String ReadRedisError="3000010";
	public static final String WriteRedisError="3000011";
	public static final String ConfigXMLUpdateError="3000012";
	public static final String InitialRedisError="3000013";

	//用戶
	public static final String UserPrivilegesError="0000001";
	public static void main(String[] args){
		//ExceptionConstants.Parameters.name();
	}

}
