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
        videoDao.Insert(video);
    }

    @Override
    public void Delete(Video video) throws Exception {
        videoDao.Delete(video);
    }

    @Override
    public void Update(Video video) {
        videoDao.Update(video);
    }

    @Override
    public Video FindById(String id) {
        return videoDao.FindById(id);
    }

    @Override
    public List<Video> FindAll() {
        return videoDao.FindAll();
    }
}
