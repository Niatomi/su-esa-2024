/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.niatomi.musicplayer.view.utils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author nia
 */
public class PathResolver {
    
    public static String to(String to, String from, HttpServletRequest origin) {
        return origin.getHeader("Referer").replaceFirst(from, to);
    }
    
}
