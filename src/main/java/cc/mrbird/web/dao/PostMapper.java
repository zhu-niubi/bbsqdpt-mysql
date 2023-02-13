package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.Post;

import java.util.List;

public interface PostMapper extends MyMapper<Post> {

    /**
     * 根据id查询论帖
     *
     * @param id
     * @return
     */
    public Post findPostById(Integer id);
    /**
     * 分页查询前端论帖列表
     *
     * @param post
     * @return
     */
    public List<Post> findPostByPage(Post post);
    /**
     * 分页查询异常论帖列表
     *
     * @param post
     * @return
     */
    public List<Post> findExpPostByPage(Post post);
    /**
     * 分页查询精华论帖列表
     *
     * @param post
     * @return
     */
    public List<Post> findBestPostByPage(Post post);

    /**
     * 分页查询待审核精华论帖列表
     *
     * @param post
     * @return
     */
    public List<Post> bestPassPost(Post post);
    /**
     * 新增论帖信息
     * @param post
     * @return
     */
    public void addPost(Post post);

    /**
     * 删除论帖信息
     * @param id
     * @return
     */
    public void deletePosts(Integer id);

    /**
     * 删除异常论帖信息
     * @param id
     * @return
     */
    public void deleteExpPosts(Integer id);

    /**
     * 删除精华论帖信息
     * @param id
     * @return
     */
    public void deleteBestPosts(Integer id);

    /**
     * 更变为精华帖
     * @param id
     * @return
     */
    public void updatePosts(Integer id);

    /**
     * 异常帖审核通过
     *
     * @param id
     * @return
     */
    public void updateEditPost(Integer id);

    /**
     * 精华帖帖审核通过
     *
     * @param id
     * @return
     */
    public void updateBestPassPost(Integer id);

    /**
     * 精华帖帖审核驳回
     *
     * @param id
     * @return
     */
    public void updateBestTurnPost(Integer id);
}