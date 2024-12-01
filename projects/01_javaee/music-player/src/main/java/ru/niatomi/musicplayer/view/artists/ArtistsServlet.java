/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ru.niatomi.musicplayer.view.artists;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.niatomi.musicplayer.models.domain.Artist;
import ru.niatomi.musicplayer.service.ArtistsService;
import ru.niatomi.musicplayer.view.utils.HTMLComponents;
import ru.niatomi.musicplayer.view.utils.JSFetchArgs;
import ru.niatomi.musicplayer.view.utils.PathResolver;

/**
 *
 * @author nia
 */

@WebServlet(name = "Artists", urlPatterns = { "/artists" })
@Stateful
public class ArtistsServlet extends HttpServlet {

    @EJB
    private ArtistsService as;

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Artist> artists = as.getArtists();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Artists</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Artists Page</h1>");
            out.println("<hr/>");
            for (Artist a : artists) {
                out.println("<span>");

                out.println("<p>");
                out.println(a.getName());
                out.println("</p>");

                Map<String, String> params = new HashMap<String, String>();
                params.put("artist_id", a.getId().toString());
                String postJSFetch = JSFetchArgs.postJSFetch(request.getContextPath() + "/artists", params);
                out.println(String.format(HTMLComponents.HTML_BTN, "Go to " + a.getName() + " page", postJSFetch));

                out.println("</span>");
                out.println("<hr/>");

            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        Set<String> keySet = request.getParameterMap().keySet();
        if (keySet.isEmpty() || !keySet.contains("artist_id")) {
            response.sendError(0, "artist_id is required");
        }
        Integer artistId = Integer.valueOf(request.getParameter("artist_id"));
        
        String target = PathResolver.to("artist", "artists", request);
        response.sendRedirect(target + "?id=" + artistId.toString());
    }

}
