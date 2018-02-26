package supermarket.kata.model;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateItemWithConstractor() {
		Item item1 = new Item("A", 12.0);
		assertEquals(item1.getName(), "A");
		assertEquals(item1.getPrice(), 12.0,0.01);
		
	}
	
	@Test
	public void testCreateItemWithDefaultConstructorAndGetterSetter() {
		Item item1 = new Item();
		item1.setName("A");
		item1.setPrice(222.00);
		assertEquals(item1.getName(), "A");
		assertEquals(item1.getPrice(), 222.00,0.01);
		
	}

	@After
	public void tearDown() throws Exception {
	}
}
