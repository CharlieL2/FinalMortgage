package tests;

import static org.junit.Assert.*;
import pikas.*;

import org.junit.Test;

public class EngineTests {

	@Test
	public void test() {
		Engine Tester = new Engine(56000,300,0.06,30,0);
		assertEquals("AmtMort Test", Tester.getAmtMort(), 140104.96, 0.01);
	}

}
