package ru.niatomi.music_player.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IterableToListObject {

    public static <T> List<Object> getCollectionFromIterable(Iterable<T> itr)
    {
        // Create an empty Collection to hold the result
        List<Object> cltn = new ArrayList<Object>();

        // Iterate through the iterable to
        // add each element into the collection
        for (T t : itr)
            cltn.add((Object) t);

        // Return the converted collection
        return cltn;
    }
}
