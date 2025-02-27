package by.vb.blogservicejava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application Runner
 */

@SpringBootApplication
public class BlogServiceJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogServiceJavaApplication.class, args);
	}
}

// TODO:
// 1. Implement filters for reactions GET all. Post, delete and put methods.
// 2. Add Register, Login, Auth, and so on (work on spring-security module)
// 3. Add tests.

// Documentation functionality
