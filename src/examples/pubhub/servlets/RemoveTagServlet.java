package examples.pubhub.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Servlet implementation class RemoveTagServlet
 */

@WebServlet("/RemoveTag")
public class RemoveTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String isbn13 = request.getParameter("isbn13");
		String remove_tag = request.getParameter("remove_tag");

		BookDAO bDao = DAOUtilities.getBookDAO();
		TagDAO tDao = DAOUtilities.getTagDAO();

		Book book = bDao.getBookByISBN(isbn13);
		Tag tag = new Tag(remove_tag);
		tDao.removeTag(tag, book);

		request.setAttribute("book", book);
		request.setAttribute("remove_tag", remove_tag);

		// We can use a forward here, because if a user wants to refresh their browser on this page,
		// it will just show them the most recent details for their book. There's no risk of data
		// miss-handling here.
		//		request.getRequestDispatcher("modifyTag.jsp").forward(request, response);
		response.sendRedirect("ModifyTag?isbn13=" + isbn13);

	}

}
