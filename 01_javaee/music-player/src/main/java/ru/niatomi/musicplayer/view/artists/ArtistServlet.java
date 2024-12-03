/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ru.niatomi.musicplayer.view.artists;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotFoundException;
import ru.niatomi.musicplayer.models.domain.Album;
import ru.niatomi.musicplayer.models.domain.Artist;
import ru.niatomi.musicplayer.models.domain.Song;
import ru.niatomi.musicplayer.service.ArtistService;
import ru.niatomi.musicplayer.view.utils.HTMLComponents;
import ru.niatomi.musicplayer.view.utils.JSFetchArgs;
import ru.niatomi.musicplayer.view.utils.PathResolver;

/**
 *
 * @author nia
 */
@WebServlet(name = "Artist", urlPatterns = {"/artist"})
@Stateful
public class ArtistServlet extends HttpServlet {

    
    @EJB
    private ArtistService as;
    
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
            response.sendError(0, "artist_id is required");
        }
        
        String artistName = "";
        Artist artist;
        try {
            Integer artist_id = Integer.valueOf(request.getParameter("id"));
            artist = as.getArtist(artist_id);
            artistName = artist.getName();
        } catch (NotFoundException e) {
            response.sendError(0, e.getMessage());
            return;
        }
        Set<Album> artistAlbums = as.getArtistAlbums(artist.getId());
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Artist: " +  artistName + "</title>");
            out.println("<meta http-equiv=\"refresh\" content=\"1\">");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1>");
            out.println("Artist card: " + artistName);
            out.println("</h1>");
            
            Map<String, String> artistParams = new HashMap<String, String>();
            artistParams.put("artist_id", artist.getId().toString());
            String postJSFetch = JSFetchArgs.postJSFetch(request.getContextPath() + "/artist", artistParams);
            out.println(String.format(HTMLComponents.HTML_BTN, "Play songs", postJSFetch));
            
            out.println("<hr/>");
            out.println("<hr/>");
            
            out.println("<h2>Songs</h2>");
            for (Song song : artist.getSongs()) {
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
                postJSFetch = JSFetchArgs.postJSFetch(request.getContextPath() + "/artist", songParams);
                out.println(String.format(HTMLComponents.HTML_BTN, "Play this song", postJSFetch));
                
                out.println("</span>");
                out.println("<hr/>");
            }
            out.println("<hr/>");
            out.println("<h2>Albums</h2>");
            for (Album artistAlbum : artistAlbums) {
                out.println("<hr/>");
                out.println("<span>");
                
                out.println("<p>");
                out.println(artistAlbum.getAlbumName());
                out.println("</p>");
                
                Map<String, String> songParams = new HashMap<String, String>();
                songParams.put("album_id", artistAlbum.getId().toString());
                postJSFetch = JSFetchArgs.postJSFetch(request.getContextPath() + "/artist", songParams);
                out.println(String.format(HTMLComponents.HTML_BTN, "Go to this album", postJSFetch));
                
                out.println("</span>");
                out.println("<hr/>");
            }
            out.println("<hr/>");
            
            
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
        // processRequest(request, response);
        Set<String> keySet = request.getParameterMap().keySet();
        if (keySet.isEmpty() || (!keySet.contains("artist_id") && !keySet.contains("song_id") && !keySet.contains("album_id"))) {
            response.sendError(0, "artist_id or song_id is required");
        }
        
        if (keySet.contains("artist_id")) {
            Integer artistId = Integer.valueOf(request.getParameter("artist_id"));
            as.playArtistSongs(artistId);
        }
        if (keySet.contains("song_id")) {
            Integer songId = Integer.valueOf(request.getParameter("song_id"));
            as.playArtistSong(songId);
        }
        if (keySet.contains("album_id")) {
            Integer albumId = Integer.valueOf(request.getParameter("album_id"));
            String target = PathResolver.to("album", "artist", request);
            response.sendRedirect(target + "?id=" + albumId.toString());
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
