package cc.mrbird.web.service;


import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.web.domain.System;
import java.util.List;

public interface SystemService {
    /**
     * 分页查询消息列表
     * @param system
     * @param request
     * @return
     */
    public List<System> findSystemByPage(System system, QueryRequest request);

    /**
     * 根据主键查询消息详情
     * @param id
     * @return
     */
    public System findById(Integer id);

    /**
     * 发送消息
     * @param systemId
     * @param userIds
     */
    public void sendSystem(String systemId,String userIds);

    /**
     * 新增消息
     * @param system
     */
    public void addSystem(System system);

    /**
     * 批量删除消息
     * @param ids
     */
    public void deleteSystems(String ids);
}
