<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title><%@ page contentType="text/html;charset=UTF-8" language="java" %>
      <html>
      <head>
      <title>Add Video</title>
  </head>
  <body>
  <h1>Edit Video</h1>
  <form action="${pageContext.request.contextPath}/admin/video/add" method="post" enctype="multipart/form-data">
    <input type="hidden" name="videoId" value="${video.videoId}">

    <label for="title">Title: </label><br>
    <input type="text" id="title" name="title" value="${video.title}"><br>

    <label for="description">Description: </label><br>
    <textarea id="description" name="description">${video.description}</textarea><br>

    <label for="poster">Poster: </label><br>
    <img height="150" width="200" src="${video.poster}"/> <!-- Hiển thị ảnh hiện tại -->
    <input type="file" id="poster" name="poster"><br>

    <label for="views">Views: </label><br>
    <input type="number" id="views" name="views" value="${video.views}"><br>

    <label for="status">Status: </label><br>
    <input type="number" id="status" name="status" value="${video.active}"><br><br>

    <input type="submit" value="Update">
  </form>
  </body>
</html>
</title>
  </head>
  <body>
  
  </body>
</html>
