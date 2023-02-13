package cc.mrbird.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ProjectPath {
    private static String rootPath = "";

    private ProjectPath() {
        init();
    }

    private static void init() {
        String path = ProjectPath.class.getResource("ProjectPath.class")
                .toString();
        try {
            path = URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        path.replaceAll("\\\\", "/");
        int index = path.indexOf("WEB-INF");
        if (index == -1) {
            index = path.indexOf("bin");
        }
        if (index == -1) {
            index = path.indexOf("lib");
        }
        if (index == -1) {
            int index2 = path.indexOf("jar!");
            if (index2 != -1) {
                path = path.substring(0, index2);
                index = path.lastIndexOf("/");
            }
        }
        path = path.substring(0, index);
        if (path.startsWith("jar")) {
            path = path.substring(9);
        }
        if (path.startsWith("file")) {
            path = path.substring(5);
        }
        if (path.endsWith("/") || path.endsWith("\\")) {
            path = path.substring(0, path.length() - 1);
        }
        // linux环境下第一个/是需要的
        String os = System.getProperty("os.name").toLowerCase();
        if (os.startsWith("win")) {
            path = path.substring(1);
        }
        rootPath = path;
    }

    /**
     * 获取应用的根目录，路径分隔符为/，路径结尾无/
     *
     * @return
     */
    public static String getProjectPath() {
        if ("".equals(rootPath)) {
            init();
        }
        return rootPath;
    }
}
