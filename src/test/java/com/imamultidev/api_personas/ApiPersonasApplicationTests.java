package com.imamultidev.api_personas;

import com.imamultidev.api_personas.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.context.annotation.Import;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@Import(TestConfig.class)
class ApiPersonasApplicationTestsDisabled {

	@Test
	void contextLoads() {
		// La prueba verificará si el contexto se carga correctamente
	}

}
