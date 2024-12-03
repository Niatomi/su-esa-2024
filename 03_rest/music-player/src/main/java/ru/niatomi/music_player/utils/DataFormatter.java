package ru.niatomi.music_player.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class DataFormatter {

    public static String generateXML(List<Object> objects) {
        String result = "";
        try {
            File file = ResourceUtils.getFile("classpath:xsls/xsl2html.xml");
            result = Files.readString(file.toPath());
            XmlMapper xmlMapper = new XmlMapper();
            result = result.replaceFirst("<dataPlaceholder/>", xmlMapper.writeValueAsString(objects));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String generateJSON(List<Object> objects) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(objects);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
