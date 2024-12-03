package ru.niatomi.music_player.exceptions;

public class BadRequestDataFormat extends RuntimeException {

    public BadRequestDataFormat() {
        super("Only xml and json data allowed");
    }
}
