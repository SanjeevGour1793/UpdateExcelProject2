<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Upload File</title>
</head>
<body>
<form action="UploadFile" method="post" enctype="multipart/form-data">
Select File : <input type="file" name="filetoupload">
<br/>
<input type="submit" value="Upload File">
</form>
</body>
</html>