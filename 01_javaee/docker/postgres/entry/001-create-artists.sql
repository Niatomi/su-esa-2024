\connect music_bucket


CREATE TABLE public.artists
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    name character varying(100) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.artists
    OWNER to postgres;