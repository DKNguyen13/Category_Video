package vn.hcmute.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.hcmute.entity.Video;
import vn.hcmute.services.VideoService;
import vn.hcmute.services.interf.IVideoService;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/videos","/admin/video/add","/admin/video/edit"})
public class VideoController extends HttpServlet {
    IVideoService videoService = new VideoService();
    String categoryid ="";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        if(url.contains("videos")) {
            List<Video> videoList = videoService.FindAll();
            categoryid = req.getParameter("id");
            req.setAttribute("videoList", videoList);
            req.setAttribute("cateid", categoryid);
            req.getRequestDispatcher("/views/admin/video_list.jsp").forward(req, resp);
        }
        else if(url.contains("edit")) {
            String videoid = req.getParameter("id");
            req.setAttribute("vId",videoid);
            req.getRequestDispatcher("/views/admin/video_edit.jsp").forward(req, resp);
        }
    }
}
