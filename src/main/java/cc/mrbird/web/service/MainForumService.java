package cc.mrbird.web.service;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.IService;
import cc.mrbird.web.domain.MainForum;

import java.util.List;

public interface MainForumService extends IService<MainForum> {
    /**
     * 分页查询前端分类列表
     *
     * @param mainForum
     * @param request
     * @return
     */
    List<MainForum> findMainForumByPage(MainForum mainForum, QueryRequest request);

    /**
     * 根据id查询分类信息
     *
     * @param id
     * @return
     */
    MainForum findById(Integer id);

    /**
     * 根据标题查询分类信息
     *
     * @param title
     * @return
     */
    MainForum findByTitle(String title);
    /**
     * 新增分类信息
     * @param mainForum
     * @return
     */
    void addMainForum(MainForum mainForum);

    /**
     * 删除分类信息
     * @param ids
     * @return
     */
    void deleteMainForums(String ids);

    /**
     * 修改分类信息
     * @param mainForum
     * @return
     */
    void updateMainForum(MainForum mainForum);

}
