package vn.hcmute.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.hcmute.configs.JPAConfig;
import vn.hcmute.dao.interf.IVideoDao;
import vn.hcmute.entity.Video;

import java.util.List;

public class VideoDao implements IVideoDao {
    @Override
    public void Insert(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();

        try {
            trans.begin();
            enma.persist(video); // Lưu video vào cơ sở dữ liệu
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) {
                trans.rollback(); // Hoàn tác giao dịch nếu có lỗi
            }
            throw e;
        } finally {
            if (enma.isOpen()) {
                enma.close(); // Đóng EntityManager
            }
        }
    }

    @Override
    public void Delete(Video video) throws Exception {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();

        try {
            trans.begin();
            if (video != null) {
                video = enma.merge(video); // Hợp nhất video vào EntityManager
                enma.remove(video); // Xóa video
            } else {
                throw new Exception("Không tìm thấy video");
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) {
                trans.rollback(); // Hoàn tác giao dịch nếu có lỗi
            }
            throw e;
        } finally {
            if (enma.isOpen()) {
                enma.close(); // Đóng EntityManager
            }
        }
    }

    @Override
    public void Update(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();

        try {
            trans.begin();
            enma.merge(video); // Cập nhật video
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) {
                trans.rollback(); // Hoàn tác giao dịch nếu có lỗi
            }
            throw e;
        } finally {
            if (enma.isOpen()) {
                enma.close(); // Đóng EntityManager
            }
        }
    }

    @Override
    public Video FindById(String id) {
        EntityManager enma = JPAConfig.getEntityManager();
        return enma.find(Video.class, id); // Tìm video theo ID
    }

    @Override
    public List<Video> FindAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
        return query.getResultList(); // Trả về danh sách tất cả video
    }

    @Override
    public List<Video> FindListById(String categoryId) {
        // Do là khóa ngoại tham chiếu nên dùng khóa ngoại này để tìm kiếm danh sách video thuộc category
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Video> query = enma.createQuery("SELECT v FROM Video v WHERE v.category.categoryId = :categoryId", Video.class);
        query.setParameter("categoryId", Integer.parseInt(categoryId)); // Chuyển đổi categoryId từ String sang int
        return query.getResultList(); // Trả về danh sách video theo categoryId
    }
}
