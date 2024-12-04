\connect music_bucket

CREATE TABLE public.events_backlog
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    which_table character varying(30) NOT NULL,
    old character varying(100),
    new character varying(100),
    event_id bigint,
    entity_id bigint,
    entity_attribute character varying(100) NOT NULL,
    at timestamp without time zone DEFAULT NOW(),
    PRIMARY KEY (id),
    FOREIGN KEY (event_id)
        REFERENCES public.event_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.events_backlog
    OWNER to postgres;