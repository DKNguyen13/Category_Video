package vn.hcmute.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.hcmute.configs.JPAConfig;
import vn.hcmute.dao.interf.ICategoryDao;
import vn.hcmute.entity.Category;
import java.util.List;

public class CategoryDao implements ICategoryDao {
    @Override
    public void InsertCategory(Category category) {
        // Tạo EntityManager từ cấu hình JPA
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();

        try {
            // Bắt đầu giao dịch
            trans.begin();
            enma.persist(category);
            trans.commit();
        } catch (Exception e) {
            // Xử lý lỗi
            e.printStackTrace();

            // Cuối cùng, hoàn tác giao dịch nếu có lỗi
            if (trans.isActive()) {
                trans.rollback();
            }

            // Ném lại ngoại lệ để thông báo lỗi
            throw e;
        } finally {
            // Đóng EntityManager
            if (enma.isOpen()) {
                enma.close();
            }
        }
    }
    @Override
    public void DeleteCategory(Category category) throws Exception {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();

        try {
            trans.begin();
            if (category != null) {
                // Hợp nhất vào EntityManager nếu cần
                category = enma.merge(category);
                enma.remove(category);
            } else {
                throw new Exception("Không tìm thấy danh mục");
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) {
                trans.rollback();
            }
            throw e;
        } finally {
            if (enma.isOpen()) {
                enma.close();
            }
        }
    }

    @Override
    public void updateCategory(Category category) {
        // Tạo EntityManager từ cấu hình JPA
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();

        try {
            // Bắt đầu giao dịch
            trans.begin();

            // Sử dụng merge để cập nhật đối tượng vào cơ sở dữ liệu
            enma.merge(category);

            // Cam kết giao dịch
            trans.commit();
        } catch (Exception e) {
            // Xử lý lỗi
            e.printStackTrace();

            // Hoàn tác giao dịch nếu có lỗi
            if (trans.isActive()) {
                trans.rollback();
            }

            // Ném lại ngoại lệ để thông báo lỗi
            throw e;
        } finally {
            // Đóng EntityManager
            if (enma.isOpen()) {
                enma.close();
            }
        }
    }

    @Override
    public Category FindById(int id) {
        EntityManager enma = JPAConfig.getEntityManager();
        Category category = enma.find(Category.class, id);
        return category;
    }
    @Override
    public List<Category> FindAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
        return query.getResultList();
    }
    @Override
    public List<Category> findByCategoryname(String catname) {


        EntityManager enma = JPAConfig.getEntityManager();


        String jpql = "SELECT c FROM Category c WHERE c.categoryname like :catname";


        TypedQuery<Category> query= enma.createQuery(jpql, Category.class);


        query.setParameter("catname", "%" + catname + "%");


        return query.getResultList();


    }
    @Override
    public List<Category> findAll(int page, int pagesize) {


        EntityManager enma = JPAConfig.getEntityManager();


        TypedQuery<Category> query= enma.createNamedQuery("Category.findAll", Category.class);


        query.setFirstResult(page*pagesize);


        query.setMaxResults(pagesize);


        return query.getResultList();


    }


    @Override
    public int count() {


        EntityManager enma = JPAConfig.getEntityManager();


        String jpql = "SELECT count(c) FROM Category c";


        Query query = enma.createQuery(jpql);


        return ((Long)query.getSingleResult()).intValue();


    }



}
