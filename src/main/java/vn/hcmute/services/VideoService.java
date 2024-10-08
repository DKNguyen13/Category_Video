package vn.hcmute.services;

import vn.hcmute.dao.VideoDao;
import vn.hcmute.dao.interf.IVideoDao;
import vn.hcmute.entity.Video;
import vn.hcmute.services.interf.IVideoService;

import java.util.List;

public class VideoService implements IVideoService {
    IVideoDao videoDao = new VideoDao();

    @Override
    public void Insert(Video video) {
        Video vid = videoDao.FindById(video.getVideoId());
        if(vid==null){
            videoDao.Insert(video);
        }
        else{
            System.out.println("Đã tồn tại tài khoản");
        }
    }

    @Override
    public void Delete(Video video) throws Exception {
        Video vid = videoDao.FindById(video.getVideoId());
        if(vid!=null){
            videoDao.Delete(video);
        }
        else{
            System.out.println("Không tồn tại tài khoản");
        }
    }

    @Override
    public void Update(Video video) {
        Video vid = videoDao.FindById(video.getVideoId());
        if(vid!=null){
            videoDao.Update(video);
        }
        else{
            System.out.println("Không tồn tại tài khoản");
        }
    }

    @Override
    public Video FindById(String id) {
        return videoDao.FindById(id);
    }

    @Override
    public List<Video> FindAll() {
        return videoDao.FindAll();
    }

    @Override
    public List<Video> FindListById(String id) {
        return videoDao.FindListById(id);
    }
}
