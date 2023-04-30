package patterns.task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// не вдалося зробити html output,не розібрався
public class MoviesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Movie> movies = MovieLibrary.getMovies();
//        request.setAttribute("movies", movies);
//        request.getRequestDispatcher("/movies.jsp").forward(request, response);\\
        PrintWriter pw = response.getWriter();

        pw.println("<html>");
        pw.println("<h1> Hello word </h1>");
        pw.println("/html");
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {

    }
}
