<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Students List</h1>
<table border="2" width="70%" cellpadding="2">
    <tr><th>Id</th><th>Name</th><th>Age</th><th>Email</th><th>Edit</th><th>Delete</th></tr>
    <c:forEach var="std" items="${list}">
        <tr>
            <td>${std.id}</td>
            <td>${std.name}</td>
            <td>${std.age}</td>
            <td>${std.email}</td>
            <td><a href="editstd/${std.id}">Edit</a></td>
            <td><a href="deletestd/${std.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="stdform">Add New Student</a>