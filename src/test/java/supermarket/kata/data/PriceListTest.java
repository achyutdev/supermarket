package supermarket.kata.data;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PriceListTest {


	@Test
	public void testPriceList() {
		assertEquals(50, PriceList.A.getPrice(),0.001);
		assertEquals(30, PriceList.B.getPrice(),0.001);
		assertEquals(20, PriceList.C.getPrice(),0.001);
		assertEquals(15, PriceList.D.getPrice(),0.001);
	}

}
