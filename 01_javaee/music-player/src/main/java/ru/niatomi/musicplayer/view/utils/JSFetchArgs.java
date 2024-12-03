/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.niatomi.musicplayer.view.utils;

import java.util.Map;

/**
 *
 * @author nia
 */
public class JSFetchArgs {

    public static String postJSFetch(String path, Map<String, String> params) {

        String func = "fetch('" + path + "', {" +
                "method: 'POST', redirect: 'follow'," +
                "headers: {'Content-Type': 'application/x-www-form-urlencoded'}," +
                "body: new URLSearchParams({";

        for (String key : params.keySet()) {
            func += key + ": " + "'" + params.get(key) + "',";
        }

        func += "})})";
        func += ".then((response) => {"
                + "if (response.redirected) {" +
                "window.location.href = response.url;" +
                "}})";
        return func;
    }

}
