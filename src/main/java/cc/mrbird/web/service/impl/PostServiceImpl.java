package cc.mrbird.web.service.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.web.dao.PostMapper;
import cc.mrbird.web.domain.Post;
import cc.mrbird.web.service.PostService;
import cc.mrbird.web.service.PostService;
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
 * Created by shen on 2018/10/25.
 */

@Service("postService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PostServiceImpl extends BaseService<Post>  implements PostService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PostMapper postMapper;
    /**
     * 根据id查询论帖信息
     * @param id
     * @return
     */
    @Override
    public Post findPostById(Integer id) {
        try {
            return this.postMapper.findPostById(id);
        } catch (Exception e) {
            log.error("error", e);
            return null;
        }
    }

    /**
     * 查询论帖信息
     * @param post
     * @return
     */
    @Override
    public List<Post> findPostByPage(Post post, QueryRequest request) {
        try {
            return this.postMapper.findPostByPage(post);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }
    /**
     * 查询异常论帖信息
     * @param post
     * @return
     */
    @Override
    public List<Post> findExpPostByPage(Post post, QueryRequest request) {
        try {
            return this.postMapper.findExpPostByPage(post);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }
    /**
     * 查询精华论帖信息
     * @param post
     * @return
     */
    @Override
    public List<Post> findBestPostByPage(Post post, QueryRequest request) {
        try {
            return this.postMapper.findBestPostByPage(post);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }
    /**
     * 查询待审核的精华论帖信息
     * @param post
     * @return
     */
    @Override
    public List<Post> bestPassPost(Post post, QueryRequest request) {
        try {
            return this.postMapper.bestPassPost(post);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    /**
     * 新增论帖信息
     * @param Post
     * @return
     */
    @Override
    public void addPost(Post Post) {
        try {
             this.postMapper.addPost(Post);
        } catch (Exception e) {
            log.error("error", e);
        }
    }
    /**
     * 删除论帖信息
     * @param ids
     * @return
     */
    @Override
    public void deletePosts(String ids) {
        try {
            List<String> list = Arrays.asList(ids.split(","));
            for(String idd:list){
                int id = Integer.parseInt(idd);
                this.postMapper.deletePosts(id);
            }
        } catch (Exception e) {
            log.error("error", e);
        }
    }

    /**
     * 删除异常论帖信息
     * @param ids
     * @return
     */
    @Override
    public void deleteExpPosts(String ids) {
        try {
            List<String> list = Arrays.asList(ids.split(","));
            for(String idd:list){
                int id = Integer.parseInt(idd);
                this.postMapper.deleteExpPosts(id);
            }
        } catch (Exception e) {
            log.error("error", e);
        }
    }
    /**
     * 删除精华论帖信息
     * @param ids
     * @return
     */
    @Override
    public void deleteBestPosts(String ids) {
        try {
            List<String> list = Arrays.asList(ids.split(","));
            for(String idd:list){
                int id = Integer.parseInt(idd);
                this.postMapper.deleteBestPosts(id);
            }
        } catch (Exception e) {
            log.error("error", e);
        }
    }
    /**
     * 更变为精华帖论帖信息
     * @param id
     * @return
     */
    @Override
    public void updatePosts(Integer id) {
        try {
            this.postMapper.updatePosts(id);
        } catch (Exception e) {
            log.error("error", e);
        }
    }
    /**
     * 异常帖审核通过
     *
     * @param id
     * @return
     */
    @Override
    public void updateEditPost(Integer id) {
        try {
            this.postMapper.updateEditPost(id);
        } catch (Exception e) {
            log.error("error", e);
        }
    }
    /**
     * 精华帖审核通过
     *
     * @param id
     * @return
     */
    @Override
    public void updateBestPassPost(Integer id) {
        try {
            this.postMapper.updateBestPassPost(id);
        } catch (Exception e) {
            log.error("error", e);
        }
    }

    /**
     * 精华帖审核驳回
     *
     * @param id
     * @return
     */
    @Override
    public void updateBestTurnPost(Integer id) {
        try {
            this.postMapper.updateBestTurnPost(id);
        } catch (Exception e) {
            log.error("error", e);
        }
    }

    @Override
    public int getPostCount(Post post) {
        return postMapper.selectCount(post);
    }
}
