package com.monitor;

import java.util.Random;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DBHealthIndicator  implements HealthIndicator{

	@Override
	public Health health() {
		if(isDbHealthy()) {
			return Health.up().withDetail("Exterenal DB svc","Healthy").build();
		}
		return Health.down().withDetail("Exterenal DB svc","Is not Healthy").build();
	}

	// mimics a call to an external service or db
	private boolean isDbHealthy() {
		Random random = new Random();
		System.out.println(random.nextBoolean());
		return random.nextBoolean();
	}
}
