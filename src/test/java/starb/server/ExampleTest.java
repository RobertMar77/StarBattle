package starb.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import starb.server.controller.ExampleController;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ExampleTest {

	@Autowired
	private ExampleController controller;

	@Test
	public void foo() {

	}
}