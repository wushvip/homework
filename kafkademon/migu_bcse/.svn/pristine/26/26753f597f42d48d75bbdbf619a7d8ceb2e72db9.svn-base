package com.chinamobile.cmss.bcse.cleardata;

import java.util.ArrayList;
import java.util.List;
import com.chinamobile.cmss.bcse.tool.tools.LogUtil;

/**
 * 清洗的基类,其他清洗规则请继承此类
 * 
 * @author jinjing
 * 
 */
public abstract class ClearMethodBase {

	private List<String[]> lineList;
	private ArrayList<String> clearColoum;

	protected ClearMethodBase(List<String[]> lineList, ArrayList<String> clearColoum) {
		this.lineList = lineList;
		this.clearColoum = clearColoum;
	}

	public void clearDateField() {
		if (lineList.size() == 0 || lineList == null) {
			return;
		}
		try {
			// 获取表头
			String[] tableTitle = lineList.get(0);
			int coloum = tableTitle.length;
			int errorColomn = 0;
			outer: for (int i = 1; i < lineList.size(); i++) {
				try {
					String[] temp = lineList.get(i);
					// 判断当前行的列数是否正确,不正确删
					if (temp.length != coloum) {
						errorColomn++;
						lineList.remove(i);
						continue;
					}

					for (int j = 0; j < temp.length; j++) {
						if (clearColoum.contains(tableTitle[j])) {
							try {
								// 调用规则清洗
								temp[j] = clear(temp[j]);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println(temp[j]);
								errorColomn++;
								lineList.remove(i);
								i--;
								continue outer;
							}
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
					errorColomn++;
					lineList.remove(i);
				}
			}

			System.out.println("删除行数：" + errorColomn);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.loggerEntrance(LogUtil.INDEX_LOG, e.getMessage());
		}

	}

	/**
	 * 每种清洗规则自己实现这部分策略
	 * 
	 * @throws Exception
	 */
	protected abstract String clear(String source) throws Exception;
}
