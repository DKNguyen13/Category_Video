package vn.hcmute.dao.interf;

import vn.hcmute.entity.Category;
import vn.hcmute.entity.Video;

import java.util.List;

public interface IVideoDao {
    void Insert(Video  video);

    void Delete(Video video) throws Exception;

    void Update(Video video);

    Video FindById(String id);

    List<Video> FindAll();
    List<Video> FindListById(String id);

}
