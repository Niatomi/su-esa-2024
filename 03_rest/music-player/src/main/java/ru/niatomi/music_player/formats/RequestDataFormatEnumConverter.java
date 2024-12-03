package ru.niatomi.music_player.formats;

import org.springframework.core.convert.converter.Converter;
import ru.niatomi.music_player.exceptions.BadRequestDataFormat;

public class RequestDataFormatEnumConverter implements Converter<String, RequestDataFormat> {

    @Override
    public RequestDataFormat convert(String source) {
        try {
            return RequestDataFormat.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestDataFormat();
        }
    }
}