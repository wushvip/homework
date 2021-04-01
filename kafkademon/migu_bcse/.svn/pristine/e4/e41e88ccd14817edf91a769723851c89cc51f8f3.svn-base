package com.chinamobile.cmss.bcse.search.tool;

import java.util.ArrayList;
import java.util.Stack;

import com.chinamobile.cmss.bcse.search.bean.SortBean;

/**
 *
 * @author chenmin
 * @date   2015年10月29日
 *
 * TODO  解析字符串参数
 *
 */
public class StringTool {
	
	public static void main(String[] args){
		
		
	}
	
	/** 
	 * @Title: isTrueExpression 
	 * @Description: TODO 表达式是否正确，括号是否匹配
	 * @return: void
	 */
	public static boolean isTrueExpression(String expression){
		if(expression==null || expression.equals("")){
			return false;
		}
		Stack<Character> stack =new Stack<>();
		for(int i=0;i<expression.length();i++){
			Character temp=expression.charAt(i);
			if(temp=='('){
				stack.add(temp);
			}else if (temp==')'){
				if(stack.size()>0){
					stack.pop();
				}else{
					return false;
				}
			}
		}
		if(stack.size()==0){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * 解析这种逻辑真的..
	 * 
	 * @param expresstion
	 * 
	 */
	public static void ParseExpression(String expresstion,ArrayList<SortBean> sortRuleList) {
		/* exactmatch(name) & docWeight:des+publicationDate:des; */
			String[] ruleArray = expresstion.split(";");
			for (String rule : ruleArray) {
				if (rule == null || rule.equals("")) {
					continue;
				}

				String[] dataRule = rule.split("#");
				if (rule.contains("exactmatch")) {
					SortBean exactSortBean = new SortBean();
					exactSortBean.name = "exactmatch";
					if (dataRule.length > 0 && dataRule[0] != null) {
						String fieldString = dataRule[0].replace("exactmatch(", "");
						fieldString = fieldString.replace(")", "");
						exactSortBean.setMatchField(fieldString);
					}
					if (dataRule.length == 2 && dataRule[1] != null) {
						String[] sortRule = dataRule[1].split("\\+");
						exactSortBean.setSortArray(sortRule);
					}
					sortRuleList.add(exactSortBean);
				} else if (rule.contains("textRelLevel")) {
					SortBean textRelevalBean = new SortBean();
					textRelevalBean.name = "textRelLevel";
					if (dataRule.length > 0 && dataRule[0] != null) {
						String fieldString = dataRule[0].replace("textRelLevel(", "");
						fieldString = fieldString.replace(")", "");
						String[] keyValue = {};
						String operator = "";
						if (fieldString.contains(">=")) {
							keyValue = fieldString.split(">=");
							operator = ">=";
						} else if (fieldString.contains("<=")) {
							keyValue = fieldString.split("<=");
							operator = "<=";
						} else if (fieldString.contains("<")) {
							keyValue = fieldString.split("<");
							operator = "<";
						} else if (fieldString.contains(">")) {
							keyValue = fieldString.split(">");
							operator = ">";
						} else if (fieldString.contains("!=")) {
							keyValue = fieldString.split("!=");
							operator = "!=";
						} else if (fieldString.contains("=")) {
							keyValue = fieldString.split("=");
							operator = "=";
						}
						if (keyValue.length == 2) {
							textRelevalBean.setMatchField(keyValue[0]);
							textRelevalBean.setOperator(operator);
							textRelevalBean.setStandard(Integer.parseInt(keyValue[1].trim()));
						}
					}
					if (dataRule.length == 2 && dataRule[1] != null) {
						String[] sortRule = dataRule[1].split("\\+");
						textRelevalBean.setSortArray(sortRule);
					}
					sortRuleList.add(textRelevalBean);
				}
			
			}


	}

	
	/**
	 * 
	 * 解析分类筛选的参数
	 * @param clFilter
	 * @return String 
	 * 
	 * */
	public static String parseClFilter(String clFilter){
		String tempstring="";
		boolean isFlag=isTrueExpression(clFilter);
		if(!isFlag){
			return tempstring;
		}
		int flag=0;
		ArrayList<String> resultList=new ArrayList<String>();
		for(int i=0;i<clFilter.length();){
			if(clFilter.charAt(i)=='['){
				i++;
				tempstring="";
				while(i<clFilter.length()&&clFilter.charAt(i)!=']'){
					tempstring=tempstring+clFilter.charAt(i);
					i++;
				}
				if(i==clFilter.length()&&clFilter.charAt(i-1)!=']'){
					return "";
				}
				resultList.add(tempstring);
				i++;
				continue;
			}
			if(clFilter.charAt(i)=='&'){
				resultList.add(" AND ");
				i++;
				continue;
			}
			if(clFilter.charAt(i)=='|'){
				resultList.add(" OR ");
				i++;
				continue;
			}
			if(clFilter.charAt(i)=='('){
				resultList.add("(");
				i++;
				continue;
			}
			if(clFilter.charAt(i)==')'){
				resultList.add(")");
				i++;
				continue;
			}
			if(i==flag){
				break;
			}
			
		}
		String resultString=null;
		if(resultList.size()>0){
			resultString="(";
			for(int i=0;i<resultList.size();i++){
				resultString=resultString+resultList.get(i);
			}
			resultString=resultString+")";
			resultString=resultString.replaceAll("#", ":");
			resultString=resultString.replaceAll("!", "-");
		}
		System.out.println(resultString);
		return resultString;
		
	}

}
