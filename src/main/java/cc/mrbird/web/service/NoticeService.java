package cc.mrbird.web.service;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.IService;
import cc.mrbird.web.domain.Notice;

import java.util.List;

public interface NoticeService extends IService<Notice> {
    /**
     * 分页查询前端公告列表
     *
     * @param notice
     * @param request
     * @return
     */
    public List<Notice> findWebUserByPage(Notice notice, QueryRequest request);

    /**
     * 根据id查询公告信息
     *
     * @param id
     * @return
     */
    public Notice findById(Integer id);
    /**
     * 新增公告信息
     * @param notice
     * @return
     */
    public void addNotice(Notice notice);

    /**
     * 删除公告信息
     * @param ids
     * @return
     */
    public void deleteNotices(String ids);

    /**
     * 修改公告信息
     * @param notice
     * @return
     */
    public void updateNotice(Notice notice);

}
