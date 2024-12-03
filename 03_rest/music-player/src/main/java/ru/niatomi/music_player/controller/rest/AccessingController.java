package ru.niatomi.music_player.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.niatomi.music_player.formats.RequestDataFormat;

@RequestMapping(value = "/objects")
public interface AccessingController {

    @GetMapping("/{className}")
    ResponseEntity<String> getObjects(
            @RequestHeader(value = "RequestDataFormat", defaultValue = "XML") RequestDataFormat rdf,
            @PathVariable String className);
}
