<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tomcat.apache.org/hello-taglib" prefix="h" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
    <head>
        <title>Application</title>
        <style type="text/css">
            .error{
                border: 1px solid red;
                }
            .errorMessage{
                color: red;
                }
        </style>
    </head>

    <body>
        <h1><h:hello name="${name}"/></h1>


        <form:form action="/add" modelAttribute="book"> <%-- two-way binding ... can make changes on server side or HTML side and it will update the other --%>
            <form:input path="title" placeholder="Title..." cssErrorClass="error"/> <%-- this will apply the styling IF there is an error with the binding result...dont have to write anymore logic --%>
            <form:errors path="title" cssClass = "errorMessage" />

            <form:input path="author" placeholder="Author..." cssErrorClass="error"/>
            <form:errors path="author" cssClass = "errorMessage" />

            <input type="submit" value="Add Book!"/>
        </form:form>

        <c:forEach items = "${books}" var="book">
            ${book.title} by ${book.author} <br>
        </c:forEach>

        <img src="/static/improving.png" />
    </body>
</html>