package ru.niatomi.music_player.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Target is not found");
    }

}
