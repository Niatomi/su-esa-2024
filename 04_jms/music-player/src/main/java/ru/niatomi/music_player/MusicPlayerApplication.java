package ru.niatomi.music_player;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;


@SpringBootApplication
@EnableJms
public class MusicPlayerApplication {

	public static void main(String[] args) {

		SpringApplication.run(MusicPlayerApplication.class, args);

	}

}
