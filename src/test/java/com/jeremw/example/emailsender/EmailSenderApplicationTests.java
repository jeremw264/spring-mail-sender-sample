package com.jeremw.example.emailsender;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class EmailSenderApplicationTests {

	@Test
	void contextLoads() {
		assertDoesNotThrow(() -> EmailSenderApplication.main(new String[] {}));
	}

}
