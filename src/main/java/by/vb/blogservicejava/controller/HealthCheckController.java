package by.vb.blogservicejava.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "HealthCheck", description = "End-points to verify the working status of the service.")
@RestController
@RequestMapping("/health-check")
public class HealthCheckController {

	@GetMapping("/ping")
	public ResponseEntity<String> ping() {
		return ResponseEntity.ok().body("Success");
	}
}
