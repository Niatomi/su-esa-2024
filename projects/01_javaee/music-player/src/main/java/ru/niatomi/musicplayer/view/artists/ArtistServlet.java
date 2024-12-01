/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ru.niatomi.musicplayer.view.artists;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotFoundException;
import ru.niatomi.musicplayer.models.domain.Artist;
import ru.niatomi.musicplayer.models.domain.Song;
import ru.niatomi.musicplayer.service.ArtistService;
import ru.niatomi.musicplayer.service.ArtistsService;
import ru.niatomi.musicplayer.service.StupidService;

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Artist: " +  artistName + "</title>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1>");
            out.println("Artist card: " + artistName);
            out.println("</h1>");
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
                
                out.println("</span>");
                out.println("<hr/>");
            }
            out.println("<hr/>");
            
            
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
