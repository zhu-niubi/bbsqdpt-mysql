package cc.mrbird.web.service.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.web.dao.MainForumMapper;
import cc.mrbird.web.domain.MainForum;
import cc.mrbird.web.service.MainForumService;
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

@Service("mainForumService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MainForumServiceImpl extends BaseService<MainForum>  implements MainForumService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MainForumMapper mainForumMapper;

    @Override
    public List<MainForum> findMainForumByPage(MainForum mainForum, QueryRequest request) {
        try {
            return this.mainForumMapper.findMainForumByPage(mainForum);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }
    /**
     * 根据id查询分类信息
     * @param id
     * @return
     */
    @Override
    public MainForum findById(Integer id) {
        try {
            return this.mainForumMapper.findById(id);
        } catch (Exception e) {
            log.error("error", e);
            return null;
        }
    }
    /**
     * 根据标题名查询分类信息
     * @param title
     * @return
     */
    @Override
    public MainForum findByTitle(String title) {
        try {
            return this.mainForumMapper.findByTitle(title);
        } catch (Exception e) {
            log.error("error", e);
            return null;
        }
    }

    /**
     * 新增分类信息
     * @param mainForum
     * @return
     */
    @Override
    public void addMainForum(MainForum mainForum) {
        try {
             this.mainForumMapper.addMainForum(mainForum);
        } catch (Exception e) {
            log.error("error", e);
        }
    }
    /**
     * 删除分类信息
     * @param ids
     * @return
     */
    @Override
    public void deleteMainForums(String ids) {
        try {
            List<String> list = Arrays.asList(ids.split(","));
            for(String idd:list){
                int id = Integer.parseInt(idd);
                this.mainForumMapper.deleteMainForum(id);
            }
        } catch (Exception e) {
            log.error("error", e);
        }
    }
    /**
     * 修改分类信息
     * @param mainForum
     * @return
     */
    @Override
    public void updateMainForum(MainForum mainForum) {
        try {
            this.mainForumMapper.updateMainForum(mainForum);
        } catch (Exception e) {
            log.error("error", e);
        }
    }
}
