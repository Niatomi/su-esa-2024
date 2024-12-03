package ru.niatomi.music_player.controller.rest.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.niatomi.music_player.formats.RequestDataFormat;
import ru.niatomi.music_player.controller.rest.AccessingController;
import ru.niatomi.music_player.service.AccessingService;
import ru.niatomi.music_player.utils.DataFormatter;

import java.util.List;

@RestController
public class AccessingControllerImpl implements AccessingController {

    @Autowired
    private AccessingService accessingService;

    @Override
    public ResponseEntity<String> getObjects(RequestDataFormat rdf, String className) {

        className = className.substring(0, 1).toUpperCase() + className.substring(1);
        accessingService.isClassNameExists(className);
        List<Object> objects = accessingService.getObjectsByClassName(className);

        String result = "";
        HttpHeaders responseHeaders = new HttpHeaders();
        if (rdf.equals(RequestDataFormat.XML)) {
            responseHeaders.setContentType(MediaType.APPLICATION_XML);
            result = DataFormatter.generateXML(objects);
        }
        else if (rdf.equals(RequestDataFormat.JSON)) {
            responseHeaders.setContentType(MediaType.APPLICATION_JSON);
            result = DataFormatter.generateJSON(objects);
        }
        return new ResponseEntity<String>(result, responseHeaders, HttpStatus.OK);
    }

}
