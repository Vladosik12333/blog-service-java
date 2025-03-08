package by.vb.blogservicejava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogServiceJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogServiceJavaApplication.class, args);
	}
}

// TODO:
// 1. Add tests.

// Review Security. Add Reaction by Id. Post not needed so 'cause only admins can access it.
// Documentation and logging
