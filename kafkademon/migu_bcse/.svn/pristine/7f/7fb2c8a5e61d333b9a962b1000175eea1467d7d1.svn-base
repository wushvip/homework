package com.chinamobile.cmss.bcse.datastatistics.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.chinamobile.cmss.bcse.datastatistics.bean.HotWordsBean;
import com.chinamobile.cmss.bcse.datastatistics.bean.LogReqBean;

/** 
 * @ClassName: HotWordsBeanDao 
 * @Description: TODO 热词获取接口
 * @author: chenmin
 * @date: 2016年1月29日 下午6:01:00  
 */
public interface HotWordsDao {
  

    /** 
     * @Title: selectHotSearchWordAsDays 
     * @Description: TODO 按天获取最近热词
     * @param infoParam
     * @return
     * @return: ArrayList<HotWordsBean>
     */
    ArrayList<HotWordsBean> selectHotSearchWordAsDays(LogReqBean logReqBean);
    void insertHotWord(HashMap<String, String> map);
    int getTotalItemsHotWord(LogReqBean logReqBean);
  //-----ADD_zhuxiang 2017.02.23 Start-----
    ArrayList<HotWordsBean> selectHotSearchWordAsWeek(LogReqBean logReqBean);
    ArrayList<HotWordsBean> selectHotSearchWordAsMonth(LogReqBean logReqBean);
    ArrayList<HotWordsBean> selectHotSearchWordYesterday(LogReqBean logReqBean);
    int getTotalItemsHotWordAsWeek(LogReqBean logReqBean);
    int getTotalItemsHotWordAsMonth(LogReqBean logReqBean);
    int getTotalItemsHotWordYesterday(LogReqBean logReqBean);
	//-----ADD_zhuxiang 2017.02.23 End-----
    
}