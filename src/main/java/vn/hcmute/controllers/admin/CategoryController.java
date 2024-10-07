package vn.hcmute.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.hcmute.entity.Category;
import vn.hcmute.services.CategoryService;
import vn.hcmute.services.interf.ICategoryService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import vn.hcmute.utils.Constant;


@MultipartConfig(fileSizeThreshold= 1024 * 1024, maxFileSize= 1024 * 1024*5, maxRequestSize = 1024 * 1024*5*5)
@WebServlet(urlPatterns = {"/admin/categories","/admin/category/add","/admin/category/insert","/admin/category/edit"
        ,"/admin/category/update","/admin/category/delete","/admin/category/search"})
public class CategoryController extends HttpServlet {
    ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        if (url.contains("categories")) {
            List<Category> list = categoryService.FindAll();
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/views/admin/category_list.jsp").forward(req, resp);
        } else if (url.contains("add")) {
            req.getRequestDispatcher("/views/admin/category_add.jsp").forward(req, resp);
        } else if (url.contains("edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Category cate = categoryService.FindById(id);
            req.setAttribute("cate", cate);
            req.getRequestDispatcher("/views/admin/category_edit.jsp").forward(req, resp);
        }
        else if (url.contains("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                // Tìm lại thực thể trước khi xóa
                Category cate = categoryService.FindById(id);
                if (cate != null) {
                    categoryService.DeleteCategory(cate);
                } else {
                    throw new Exception("Không tìm thấy danh mục với ID: " + id);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        if (url.contains("insert")) {
            String categoryName = req.getParameter("categoryname");
            String status = req.getParameter("status");
            int statuss = Integer.parseInt(status);
            String fname = "";
            Category cate = new Category();
            cate.setCategoryname(categoryName);
            cate.setStatus(statuss);
            String uploadPath = Constant.DIR;
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            try{
                Part part = req.getPart("images");
                if (part.getSize() > 0) {
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    //Doi ten file
                    int index = fileName.lastIndexOf(".");
                    String ext = fileName.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    //upload file
                    part.write(uploadPath + "/" + fname);
                    //ghi ten file vao data
                    cate.setImages(fname);
                }
                else{
                    cate.setImages("Samsung.jpg");
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }

            categoryService.InsertCategory(cate);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
        /*else if (url.contains("update")) {
            String id = req.getParameter("categoryid");
            String categoryName = req.getParameter("categoryname");
            String status = req.getParameter("status");
            int statuss = Integer.parseInt(status);
            String images = "Samsung.jpg";
            CategoryModel cate = new CategoryModel();
            cate.setCategoryname(categoryName);
            cate.setCategoryid(Integer.parseInt(id));
            cate.setImages(images);
            cate.setStatus(statuss);
            categoryService.edit(cate);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }

         */
        else if (url.contains("update")) {
            String id = req.getParameter("categoryId");
            String categoryName = req.getParameter("categoryname");
            String status = req.getParameter("status");
            int statuss = Integer.parseInt(status);
            Category cate = new Category();
            cate.setCategoryname(categoryName);
            cate.setCategoryId(Integer.parseInt(id));
            cate.setStatus(statuss);
            String fname = "";
            String uploadPath = Constant.DIR;
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            try{
                Part part = req.getPart("images");
                if (part.getSize() > 0) {
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    //Doi ten file
                    int index = fileName.lastIndexOf(".");
                    String ext = fileName.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    //upload file
                    part.write(uploadPath + "/" + fname);
                    //ghi ten file vao data
                    cate.setImages(fname);
                }
                else{
                    cate.setImages("Samsung.jpg");
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }
            categoryService.updateCategory(cate);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }
}
