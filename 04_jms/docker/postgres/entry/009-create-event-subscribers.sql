\connect music_bucket

CREATE TABLE public.event_subscribers
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    email character varying(100) NOT NULL,
    event_id bigint NOT NULL,
    condition character varying,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.event_subscribers
    OWNER to postgres;