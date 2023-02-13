package cc.mrbird.web.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.system.domain.User;
import cc.mrbird.web.domain.MainForum;
import cc.mrbird.web.domain.Post;
import cc.mrbird.web.service.MainForumService;
import cc.mrbird.web.service.PostService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
public class PostController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PostService postService;
    @Autowired
    private MainForumService mainForumService;
    private static final String ON = "on";
    @Log("生成论帖视图")
    @RequestMapping("post")
    @RequiresPermissions("post:list")
    public String index(Model model) {
        User user = super.getCurrentUser();
        model.addAttribute("post", user);
        return "web/post/post";
    }
    @Log("生成异常帖视图")
    @RequestMapping("expPost")
    @RequiresPermissions("post:list")
    public String indexExp(Model model) {
        User user = super.getCurrentUser();
        model.addAttribute("post", user);
        return "web/post/expPost";
    }
    @Log("生成精华帖视图")
    @RequestMapping("bestPost")
    @RequiresPermissions("post:list")
    public String indexBest(Model model) {
        User user = super.getCurrentUser();
        model.addAttribute("post", user);
        return "web/post/bestPost";
    }
    @Log("生成待审核的精华帖视图")
    @RequestMapping("bestPassPost")
    @RequiresPermissions("post:list")
    public String indexpass(Model model) {
        User user = super.getCurrentUser();
        model.addAttribute("post", user);
        return "web/post/bestPassPost";
    }
    @Log("获取论帖信息")
    @RequestMapping("post/list")
    @RequiresPermissions("post:list")
    @ResponseBody
    public Map<String, Object> postList(QueryRequest request, Post post) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Post> list = this.postService.findPostByPage(post, request);
        for(Post post1:list){
            if(post1.getPhotoUrl()!=null&&!"".equals(post1.getPhotoUrl())){
                String url = post1.getPhotoUrl().replaceAll("\\\\", "//");
                String photo = "<img src=toShowPic?photo="+url+" style=cursor:pointer;width:140px;height:140px;> </img>";
                post1.setPhotoUrl(photo);
            }
            if(post1.getCardContent()!=null){
                String content =post1.getCardContent();
                String contents = "<div style=cursor:pointer;width:200px;height:140px;text-align: center;>"+content+"</div>";
                post1.setCardContent(contents);
            }
        }
        PageInfo<Post> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }

    @Log("获取异常论帖信息")
    @RequestMapping("post/getExp")
    @RequiresPermissions("post:list")
    @ResponseBody
    public Map<String, Object> getExp(QueryRequest request, Post post) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Post> list = this.postService.findExpPostByPage(post, request);
        for(Post post1:list){
            if(post1.getPhotoUrl()!=null&&!"".equals(post1.getPhotoUrl())){
                String url = post1.getPhotoUrl().replaceAll("\\\\", "//");
                String photo = "<img src=toShowPic?photo="+url+" style=cursor:pointer;width:140px;height:140px;> </img>";
                post1.setPhotoUrl(photo);
            }
            if(post1.getCardContent()!=null){
                String content =post1.getCardContent().replaceAll("<[^>]+>","");
                String contents = "<div style=cursor:pointer;width:140px;height:140px;>"+content+"</div>";
                post1.setCardContent(contents);
            }
        }
        PageInfo<Post> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }
    @Log("获取精华论帖信息")
    @RequestMapping("post/getBest")
    @RequiresPermissions("post:list")
    @ResponseBody
    public Map<String, Object> getBest(QueryRequest request, Post post) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Post> list2 = this.postService.findBestPostByPage(post, request);
        for(Post post1:list2){
            if(post1.getPhotoUrl()!=null&&!"".equals(post1.getPhotoUrl())){
                String url = post1.getPhotoUrl().replaceAll("\\\\", "//");
                String photo = "<img src=toShowPic?photo="+url+" style=cursor:pointer;width:140px;height:140px;> </img>";
                post1.setPhotoUrl(photo);
            }
            if(post1.getCardContent()!=null){
                String content =post1.getCardContent().replaceAll("<[^>]+>","");
                String contents = "<div style=cursor:pointer;width:140px;height:140px;>"+content+"</div>";
                post1.setCardContent(contents);
            }
        }
        PageInfo<Post> pageInfo = new PageInfo<>(list2);
        return getDataTable(pageInfo);
    }

    @Log("获取待审核的精华论帖信息")
    @RequestMapping("post/bestPassPost")
    @RequiresPermissions("post:list")
    @ResponseBody
    public Map<String, Object> bestPassPost(QueryRequest request, Post post) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Post> list2 = this.postService.bestPassPost(post, request);
        for(Post post1:list2){
            if(post1.getPhotoUrl()!=null&&!"".equals(post1.getPhotoUrl())){
                String url = post1.getPhotoUrl().replaceAll("\\\\", "//");
                String photo = "<img src=toShowPic?photo="+url+" style=cursor:pointer;width:140px;height:140px;> </img>";
                post1.setPhotoUrl(photo);
            }
            if(post1.getCardContent()!=null){
                String content =post1.getCardContent().replaceAll("<[^>]+>","");
                String contents = "<div style=cursor:pointer;width:140px;height:140px;>"+content+"</div>";
                post1.setCardContent(contents);
            }
        }
        PageInfo<Post> pageInfo = new PageInfo<>(list2);
        return getDataTable(pageInfo);
    }

    @Log("新增论帖信息")
    @RequiresPermissions("post:add")
    @RequestMapping("post/add")
    @ResponseBody
    public ResponseBo addpost(Post post) {
        try {
        String photoUrl = post.getPhotoUrl();
        //对字节数组字符串进行Base64解码并生成图片
        if (photoUrl != null && !"".equals(photoUrl)) { //图像数据不为空
        BASE64Decoder decoder = new BASE64Decoder();
        //Base64解码
        byte[] b = decoder.decodeBuffer(photoUrl.replace("data:image/jpeg;base64,", ""));
        //idPhotoUrl = idPhotoUrl.replace("data:image/jpeg;base64,", "");
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {//调整异常数据
                b[i] += 256;
            }
          }
            //生成jpg图片
            Properties prop = new Properties();
            InputStream ins = this.getClass().getResourceAsStream("/config/photoUrl.properties");
            prop.load(ins);
            String imgFilePath = prop.getProperty("TITLEURL") + UUID.randomUUID() + ".jpg";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            post.setPhotoUrl(imgFilePath);
        }else{
            Properties prop = new Properties();
            InputStream ins = this.getClass().getResourceAsStream("/config/photoUrl.properties");
            prop.load(ins);
            String imgFilePath = prop.getProperty("DEFAULT");
            post.setPhotoUrl(imgFilePath);
        }
            //根据分类名查询分类
            MainForum mainForum =mainForumService.findByTitle(post.getTypeTitle());
            post.setForum(mainForum.getId());
            //后端发帖人id固定,后端发帖统一是此id
            post.setUserId(3);
            post.setSendDate(new Date());
            post.setGreatNum(0);
            post.setIfDelete(1);
            post.setPostType(0);
            post.setReplyNum(0);
            post.setIfPass(1);
            post.setViewNum(0);
            this.postService.addPost(post);
            return ResponseBo.ok("新增论帖成功！");
        } catch (Exception e) {
            log.error("新增论帖失败", e);
            return ResponseBo.error("新增论帖失败，请联系网站管理员！");
        }
    }

    @Log("新增论帖内容图片上传")
    @RequestMapping("/upload")
    @ResponseBody
    public Map<String, String> upload(@RequestParam(value="myFileName") MultipartFile file, HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        String separator = System.getProperty("file.separator");

        // 用于前端图片显示的路径  http://localhost:8080/upload/
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
                + separator +"upload" + separator;
        // 用于保存图片至项目的路径 D:\_eclipsework\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\JYSystem\ upload\
        // 或者 String uploadDir = request.getSession().getServletContext().getRealPath("upload") + separator;
        String uploadDir = ProjectPath.getProjectPath() + separator +"upload" + separator;
        byte[] bytes = null;
        try {
            bytes = file.getBytes();
            File dirPath = new File(uploadDir);
            if (!dirPath.exists()) {
                if (!dirPath.mkdirs()) {
                }
            }
            /**
             * 构建新的图片名称
             */
            String fileName = file.getOriginalFilename();
            int index = fileName.lastIndexOf(".");
            String extName = index > -1 ? fileName.substring(index) : ""; // .jpg
            String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
            String newFileName = uuid + extName;

            /**
             * 保存图片至项目
             */
            String filePath = uploadDir + newFileName;
            File descFile = new File(filePath);
            FileCopyUtils.copy(bytes, descFile);

            map.put("data", basePath + newFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }


    @Log("删除论帖")
    @RequiresPermissions("post:delete")
    @RequestMapping("post/delete")
    @ResponseBody
    public ResponseBo deletePosts(String ids) {
        try {
            this.postService.deletePosts(ids);
            return ResponseBo.ok("删除论帖成功！");
        } catch (Exception e) {
            log.error("删除论帖失败", e);
            return ResponseBo.error("删除论帖失败，请联系网站管理员！");
        }
    }
    @Log("删除异常论帖")
    @RequiresPermissions("post:delete")
    @RequestMapping("expPost/delete")
    @ResponseBody
    public ResponseBo deleteExpPosts(String ids) {
        try {
            this.postService.deleteExpPosts(ids);
            return ResponseBo.ok("删除异常论帖成功！");
        } catch (Exception e) {
            log.error("删除异常论帖失败", e);
            return ResponseBo.error("删除异常论帖失败，请联系网站管理员！");
        }
    }
    @Log("删除精华论帖")
    @RequiresPermissions("post:delete")
    @RequestMapping("bestPost/delete")
    @ResponseBody
    public ResponseBo deleteBestPosts(String ids) {
        try {
            this.postService.deleteBestPosts(ids);
            return ResponseBo.ok("删除精华论帖成功！");
        } catch (Exception e) {
            log.error("删除精华论帖失败", e);
            return ResponseBo.error("删除精华论帖失败，请联系网站管理员！");
        }
    }

    @Log("展示论帖图片 ")
    @RequestMapping("toShowPic")
    @ResponseBody
    public void toShowPic(HttpServletResponse response, String photo) {
        File imgFile = new File(photo);
        FileInputStream fin = null;
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            fin = new FileInputStream(imgFile);
            byte[] arr = new byte[1024 * 10];
            int n;
            while ((n = fin.read(arr)) != -1) {
                output.write(arr, 0, n);
            }
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Log("获取分类名称")
    @RequestMapping("post/getMainForum")
    @ResponseBody
    public Map<String, Object> getMainForum(QueryRequest request,MainForum mainForum) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<MainForum> list = this.mainForumService.findMainForumByPage(mainForum, request);
        List<String> titles = new ArrayList<>();
        for(MainForum mainForum1:list){
            titles.add(mainForum1.getTitle());
        }
        PageInfo<String> pageInfo = new PageInfo<>(titles);
        return getDataTable(pageInfo);
    }

    @Log("更换为精华帖")
    @RequiresPermissions("post:update")
    @RequestMapping("post/updatePost")
    @ResponseBody
    public ResponseBo updatePosts(Integer id) {
        try {
           Post post = postService.findPostById(id);
           if(post.getPostType()==1){
               return ResponseBo.warn("该贴已是精华帖！");
           }else{
               this.postService.updatePosts(id);
               return ResponseBo.ok("更变成功！");
           }
        } catch (Exception e) {
            log.error("更变失败", e);
            return ResponseBo.error("更变失败，请联系网站管理员！");
        }
    }

    @Log("异常帖审核通过")
    @RequiresPermissions("post:update")
    @RequestMapping("post/updateEditPost")
    @ResponseBody
    public ResponseBo updateEditPost(Integer id) {
        try {
                this.postService.updateEditPost(id);
                return ResponseBo.ok("审核通过！");
        } catch (Exception e) {
            log.error("审核失败", e);
            return ResponseBo.error("审核失败，请联系网站管理员！");
        }
    }
    @Log("精华帖审核通过")
    @RequiresPermissions("post:update")
    @RequestMapping("post/updateBestPassPost")
    @ResponseBody
    public ResponseBo updateBestPassPost(Integer id) {
        try {
            this.postService.updateBestPassPost(id);
            return ResponseBo.ok("审核通过！");
        } catch (Exception e) {
            log.error("审核失败", e);
            return ResponseBo.error("审核失败，请联系网站管理员！");
        }
    }
    @Log("精华帖审核驳回")
    @RequiresPermissions("post:update")
    @RequestMapping("post/updateBestTurnPost")
    @ResponseBody
    public ResponseBo updateBestTurnPost(Integer id) {
        try {
            this.postService.updateBestTurnPost(id);
            return ResponseBo.ok("审核驳回！");
        } catch (Exception e) {
            log.error("审核失败", e);
            return ResponseBo.error("审核失败，请联系网站管理员！");
        }
    }
}
