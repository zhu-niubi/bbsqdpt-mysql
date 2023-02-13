package cc.mrbird.web.service;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.IService;
import cc.mrbird.web.domain.Post;

import java.util.List;

public interface PostService extends IService<Post> {

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
     * @param request
     * @return
     */
    public List<Post> findPostByPage(Post post, QueryRequest request);
    /**
     * 分页查询异常论帖列表
     *
     * @param post
     * @param request
     * @return
     */
    public List<Post> findExpPostByPage(Post post, QueryRequest request);
    /**
     * 分页查询精华论帖列表
     *
     * @param post
     * @param request
     * @return
     */
    public List<Post> findBestPostByPage(Post post, QueryRequest request);
    /**
     * 分页查询待审核精华论帖列表
     *
     * @param post
     * @param request
     * @return
     */
    public List<Post> bestPassPost(Post post, QueryRequest request);


    /**
     * 新增论帖信息
     * @param post
     * @return
     */
    public void addPost(Post post);

    /**
     * 删除论帖信息
     * @param ids
     * @return
     */
    public void deletePosts(String ids);

    /**
     * 删除异常论帖信息
     * @param ids
     * @return
     */
    public void deleteExpPosts(String ids);

    /**
     * 删除精华论帖信息 findPostById
     * @param ids
     * @return
     */
    public void deleteBestPosts(String ids);
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

    /**
     * 统计论贴数量
     * @param post
     * @return
     */
    public int getPostCount(Post post);
}
