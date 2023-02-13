package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.System;

import java.util.List;

/**
 * 消息dao层
 */
public interface SystemMapper  extends MyMapper<System> {

    System selectByPrimaryKey(Integer id);


    List<System> findSystemByPage(System record);

    void updateStatusById(Integer id);
}