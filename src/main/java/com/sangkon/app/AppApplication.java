package com.sangkon.app;

import com.sangkon.app.domain.Board;
import com.sangkon.app.domain.BoardType;
import com.sangkon.app.domain.User;
import com.sangkon.app.repository.BoardRepository;
import com.sangkon.app.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserRepository userRepository,
									BoardRepository boardRepository) throws Exception {
		return (args) -> {
			User user = userRepository.save(
				User.builder()
					.name("havi")
					.password("test")
					.email("havi@gmail.com")
					.createdDate(LocalDateTime.now())
					.build()
			);

			user.setName("John Doe");
			userRepository.save(user);

			IntStream.rangeClosed(1, 200).forEach(index ->
				boardRepository.save(
					Board.builder()
					.title("게시글 : " + index)
					.subTitle("순서 : " + index)
					.content("콘텐츠"+index)
					.boardType(BoardType.free)
					.createdDate(LocalDateTime.now())
					.updatedDate(LocalDateTime.now())
					.user(user)
					.build()
				)
			);
		};
	}
}
