package com.emsi.Site_de_Reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SiteDeReservationApplication {
	public static void main(String[] args) {
		SpringApplication.run(SiteDeReservationApplication.class, args);
	}
}
