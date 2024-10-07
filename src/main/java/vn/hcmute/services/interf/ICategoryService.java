package vn.hcmute.services.interf;

import vn.hcmute.entity.Category;

import java.util.List;

public interface ICategoryService {

        void InsertCategory(Category category);

        void DeleteCategory(Category category) throws Exception;

        void updateCategory(Category category);

        Category FindById(int id);

        List<Category> FindAll();

        List<Category> findByCategoryname(String catname);

        List<Category> findAll(int page, int pagesize);

        int count();

}
