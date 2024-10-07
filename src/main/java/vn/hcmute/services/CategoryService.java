package vn.hcmute.services;

import vn.hcmute.dao.CategoryDao;
import vn.hcmute.entity.Category;
import vn.hcmute.services.interf.ICategoryService;

import java.util.List;

public class CategoryService implements ICategoryService {
    CategoryDao dao = new CategoryDao();

    @Override
    public void InsertCategory(Category category) {
        dao.InsertCategory(category);
    }

    @Override
    public void DeleteCategory(Category category) throws Exception {
        dao.DeleteCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        dao.updateCategory(category);
    }

    @Override
    public Category FindById(int id) {
        return dao.FindById(id);
    }

    @Override
    public List<Category> FindAll() {
        return dao.FindAll();
    }

    @Override
    public List<Category> findByCategoryname(String catname) {
        return dao.findByCategoryname(catname);
    }

    @Override
    public List<Category> findAll(int page, int pagesize) {
        return dao.findAll(page, pagesize);
    }

    @Override
    public int count() {
        return dao.count();
    }
}
