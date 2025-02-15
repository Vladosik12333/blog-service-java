package by.vb.blogservicejava.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HealthCheck Controller
 */

@RestController
@RequestMapping("/health-check")
public class HealthCheckController {

	/**
	 * Ping method
	 *
	 * @return response "Success"
	 */

	@GetMapping("/ping")
	public ResponseEntity<String> ping() {
		return ResponseEntity.ok().body("Success");
	}
}
