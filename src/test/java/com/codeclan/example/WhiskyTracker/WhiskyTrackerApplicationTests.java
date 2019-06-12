package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canGetWhiskysByYear() {
		assertEquals(2, whiskyRepository.getWhiskysByYear(2018).size());
	}

	@Test
	public void canGetDistilleriesByRegion() {
		assertEquals(2, distilleryRepository.getDistilleriesByRegion("Speyside").size());
	}

	@Test
	public void canGetWhiskyByAgeByDistillery() {
		Distillery distillery1 = new Distillery("Glendronach", "Highland");
		distilleryRepository.save(distillery1);


		Whisky whisky1 = new Whisky("The Glendronach Revival", 15, 2018, distillery1);
		whiskyRepository.save(whisky1);

		Distillery distillery2 = new Distillery("Rosebank", "Lowland");
		distilleryRepository.save(distillery2);

		Whisky whisky2 = new Whisky("The Rosebank 12 - Flora and Fona", 12, 1991, distillery2);
		whiskyRepository.save(whisky2);


		Distillery distillery3 = new Distillery("Macallan", "Speyside");
		distilleryRepository.save(distillery3);


		Whisky whisky3 = new Whisky("The Macallan Anniversary Malt", 25, 1995, distillery3);
		whiskyRepository.save(whisky3);

		Whisky whisky4 = new Whisky("The Glendronach Original", 12, 2018, distillery1);
		whiskyRepository.save(whisky4);

		Distillery distillery4 = new Distillery("Balvenie", "Speyside");
		distilleryRepository.save(distillery4);
		assertEquals(2, whiskyRepository.getWhiskyByAgebyDistillery(15, distillery1).size());
	}
}
