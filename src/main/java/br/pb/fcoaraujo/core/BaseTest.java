package br.pb.fcoaraujo.core;

import static br.pb.fcoaraujo.core.DriverFactory.killDriver;

import org.junit.After;

public class BaseTest {
	
	@After
	public void finaliza() {
		killDriver();
	}

}
