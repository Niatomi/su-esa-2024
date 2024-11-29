\connect music_bucket

CREATE TABLE public.songs
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    artist_id BIGINT NOT NULL,
    name character varying(100) NOT NULL,
    listen_count bigint NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    FOREIGN KEY (artist_id)
        REFERENCES public.artists (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.songs
    OWNER to postgres;