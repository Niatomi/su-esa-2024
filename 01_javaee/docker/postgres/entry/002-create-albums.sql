\connect music_bucket

CREATE TABLE public.albums
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    album_name character varying(100) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.albums
    OWNER to postgres;