package com.bol.assignment;

import com.bol.assignment.model.Player;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AssignmentApplicationTests {

	@Test
	public void createNewPlayer(){
		Player player = new Player();
	}

}
