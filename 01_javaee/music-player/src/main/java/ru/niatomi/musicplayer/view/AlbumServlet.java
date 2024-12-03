/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ru.niatomi.musicplayer.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.niatomi.musicplayer.models.domain.Album;
import ru.niatomi.musicplayer.models.domain.Song;
import ru.niatomi.musicplayer.service.AlbumService;
import ru.niatomi.musicplayer.view.utils.HTMLComponents;
import ru.niatomi.musicplayer.view.utils.JSFetchArgs;

/**
 *
 * @author nia
 */
@WebServlet(name = "Album", urlPatterns = {"/album"})
public class AlbumServlet extends HttpServlet {

    
    @EJB
    private AlbumService as;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        Set<String> keySet = request.getParameterMap().keySet();
        if (keySet.isEmpty() || !keySet.contains("id")) {
            response.sendError(0, "album_id is required");
        }
        Integer albumId = Integer.valueOf(request.getParameter("id"));
        Album album = as.getAlbum(albumId);
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>");
            out.println(album.getAlbumName());
            out.println("</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<hr/>");
            out.println("<h1>" + album.getAlbumName() + "</h1>");
            out.println("<h2>Total count is: " + as.countTotalListenCount(album)+ "</h2>");
            out.println("<hr/>");
            out.println("<hr/>");
            for (Song song : album.getSongs()) {
                out.println("<hr/>");
                out.println("<span>");
                
                out.println("<p>");
                out.println(song.getName());
                out.println("</p>");
                
                out.println("<p>");
                out.println("Listen count: " + song.getListenCount());
                out.println("</p>");
                
                Map<String, String> songParams = new HashMap<String, String>();
                songParams.put("song_id", song.getId().toString());
                String postJSFetch = JSFetchArgs.postJSFetch(request.getContextPath() + "/album", songParams);
                out.println(String.format(HTMLComponents.HTML_BTN, "Play this song", postJSFetch));
                
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Set<String> keySet = request.getParameterMap().keySet();
        if (keySet.isEmpty() || (!keySet.contains("song_id"))) {
            response.sendError(0, "artist_id or song_id is required");
        }
        
        if (keySet.contains("song_id")) {
            Integer songId = Integer.valueOf(request.getParameter("song_id"));
            as.playAlbumSong(songId);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
