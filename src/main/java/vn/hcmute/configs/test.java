package vn.hcmute.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class test {
    public static void main(String[] args) {
        /*
        // Tạo EntityManager từ cấu hình JPA
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();

        try {
            // Bắt đầu giao dịch
            trans.begin();

            // Thực hiện các thao tác với cơ sở dữ liệu ở đây
            // Ví dụ: enma.persist(entity);

            // Cam kết giao dịch
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
        }*/
    }


}
