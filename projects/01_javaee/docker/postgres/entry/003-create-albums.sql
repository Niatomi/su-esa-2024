\connect music_bucket

CREATE TABLE public.albums
(
    song_id BIGINT NOT NULL,
    album_name character varying(100) NOT NULL,
    PRIMARY KEY (song_id, album_name),
    FOREIGN KEY (song_id)
        REFERENCES public.songs (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.albums
    OWNER to postgres;