<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="${pageContext.request.contextPath}/admin/category/insert" method="post" enctype="multipart/form-data">
    <label for="categoryname">Category Name: </label><br>
    <input type="text" id="categoryname" name="categoryname"><br>

    <label for="images">Images: </label><br>
    <img height="150" width="200" src=""/> <br>
    <input type="file" id="images" name="images"><br>

    <label for="status">Status: </label><br>
    <input type="text" id="status" name="status"><br><br>

    <input type="submit" value="Insert">
</form>
