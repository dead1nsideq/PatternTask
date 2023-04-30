<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="patterns.task.Movie " %>
<%@ page import="java.util.List" %>
<%@ page import="patterns.task.MovieLibrary" %>

<!DOCTYPE html>
<html>
<head>
    <title>Movies List</title>
</head>
<body>
<h1>Movies List</h1>
<table>
    <tr>
        <th>Title</th>
        <th>Genres</th>
        <th>Actors</th>
        <th>Director</th>
        <th>Short Review</th>
        <th>Count of Rent</th>
        <th>Price Code</th>
        <th>Audience Type</th>
    </tr>
    <%
        List<Movie> movies = MovieLibrary.getMovies();
        request.setAttribute("movies", movies);
        for (Movie movie : movies) {
    %>
    <tr>
        <td><%= movie.getTitle() %></td>
        <td><%= movie.getGenres().toString() %></td>
        <td><%= movie.getActorSet().toString() %></td>
        <td><%= movie.getDirectorName() %></td>
        <td><%= movie.getShortReview() %></td>
        <td><%= movie.getCountOfRent() %></td>
        <td><%= movie.getPriceCode().getClass().getSimpleName() %></td>
        <td><%= movie.getAudienceType().getClass().getSimpleName() %></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
