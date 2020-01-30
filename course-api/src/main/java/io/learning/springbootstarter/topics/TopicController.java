package io.learning.springbootstarter.topics;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	
	@RequestMapping("/topics")
	public List<Topic> getAllTopics() {
		return Arrays.asList(
				new Topic("Spring", "Spring FrameWork", "Spring FrameWork Description"),
				new Topic("java", "Core Java", "Java Description"),
				new Topic("JS", "JS", "JS Description")
				
				);
	}
}
