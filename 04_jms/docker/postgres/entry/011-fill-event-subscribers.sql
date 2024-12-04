\connect music_bucket

INSERT INTO public.event_subscribers(
	email, event_id, condition)
	VALUES
('playervoker@gmail.com', 1, 'artists.name->Periphery'),
('playervoker@gmail.com', 2, 'songs'),
('playervoker@gmail.com', 3, 'songs.artist->Metallica')
;