\connect music_bucket

CREATE TABLE public.event_types
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    description character varying(30) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.event_types
    OWNER to postgres;