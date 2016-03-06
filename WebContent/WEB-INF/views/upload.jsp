<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> -->
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 
    <form action="./upload" method="POST" enctype="multipart/form-data">
        File to upload: <input type="file" name="file"><br /> 
        Name: <input type="text" name="name"><br /> <br />
        <!-- <sec:csrfInput/> -->
        <input type="submit" value="Upload"> Press here to upload the file!
    </form>
