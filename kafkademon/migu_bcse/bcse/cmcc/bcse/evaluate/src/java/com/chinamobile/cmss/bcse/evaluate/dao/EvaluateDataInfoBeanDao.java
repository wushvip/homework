package com.chinamobile.cmss.bcse.evaluate.dao;

import com.chinamobile.cmss.bcse.evaluate.bean.EvaluateDataInfoBean;

public interface EvaluateDataInfoBeanDao {
    int deleteByPrimaryKey(String id);

    int insert(EvaluateDataInfoBean record);

    EvaluateDataInfoBean selectByPrimaryKey(String id);

}