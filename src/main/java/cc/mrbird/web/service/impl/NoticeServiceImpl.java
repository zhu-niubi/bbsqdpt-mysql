package cc.mrbird.web.service.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.web.dao.NoticeMapper;
import cc.mrbird.web.domain.Notice;
import cc.mrbird.web.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *客户端用户服务层
 * Created by cui on 2018/10/25.
 */

@Service("noticeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class NoticeServiceImpl extends BaseService<Notice>  implements NoticeService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> findWebUserByPage(Notice notice, QueryRequest request) {
        try {
            return this.noticeMapper.findNoticeByPage(notice);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }
    /**
     * 根据id查询公告信息
     * @param id
     * @return
     */
    @Override
    public Notice findById(Integer id) {
        try {
            return this.noticeMapper.findById(id);
        } catch (Exception e) {
            log.error("error", e);
            return null;
        }
    }
    /**
     * 新增公告信息
     * @param notice
     * @return
     */
    @Override
    public void addNotice(Notice notice) {
        try {
             this.noticeMapper.addNotice(notice);
        } catch (Exception e) {
            log.error("error", e);
        }
    }
    /**
     * 删除公告信息
     * @param ids
     * @return
     */
    @Override
    public void deleteNotices(String ids) {
        try {
            List<String> list = Arrays.asList(ids.split(","));
            for(String idd:list){
                int id = Integer.parseInt(idd);
                this.noticeMapper.deleteNotice(id);
            }
        } catch (Exception e) {
            log.error("error", e);
        }
    }
    /**
     * 修改公告信息
     * @param notice
     * @return
     */
    @Override
    public void updateNotice(Notice notice) {
        try {
            this.noticeMapper.updateNotice(notice);
        } catch (Exception e) {
            log.error("error", e);
        }
    }
}
