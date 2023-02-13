package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.Notice;

import java.util.List;

public interface NoticeMapper extends MyMapper<Notice> {
    /**
     * 分页查询前端公告列表
     * @param notice
     * @param
     * @return
     */
    public List<Notice> findNoticeByPage(Notice notice);

    /**
     * 根据id查询公告信息
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
     * @param id
     * @return
     */
    public void deleteNotice(Integer id);

    /**
     * 修改公告信息
     * @param notice
     * @return
     */
    public void updateNotice(Notice notice);

}