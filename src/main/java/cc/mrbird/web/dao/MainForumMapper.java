package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.Advice;
import cc.mrbird.web.domain.MainForum;

import java.util.List;

public interface MainForumMapper extends MyMapper<MainForum> {
   /**
     * 分页查询前端分类列表
     *
     * @param mainForum
     * @return
     */
   List<MainForum> findMainForumByPage(MainForum mainForum);

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
     * @param id
     * @return
     */
    void deleteMainForum(Integer id);

    /**
     * 修改分类信息
     * @param mainForum
     * @return
     */
    void updateMainForum(MainForum mainForum);
}