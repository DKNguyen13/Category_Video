package vn.hcmute.services.interf;

import vn.hcmute.entity.Video;

import java.util.List;

public interface IVideoService {
    void Insert(Video video);

    void Delete(Video video) throws Exception;

    void Update(Video video);

    Video FindById(String id);

    List<Video> FindAll();
    List<Video> FindListById(String id);
}
