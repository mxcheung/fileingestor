package org.baeldung.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CsvDataLoaderTest {

	CsvDataLoader csvDataLoader;

	@Before
	public void setup() throws Exception {
		csvDataLoader = new CsvDataLoader();
	}

	@Test
	public void shouldLoadCsv() {
		List<User> users = csvDataLoader.loadObjectList(User.class, CsvDataLoader.USERS_FILE);
		assertFalse(users.isEmpty());
		assertEquals(2, users.size());

	}

	
	@Test
	public void shouldLoadTransactions() {
		List<Transaction> transactions = csvDataLoader.loadObjectList(Transaction.class, CsvDataLoader.TRANSACTIONS_FILE);
		assertFalse(transactions.isEmpty());
		assertEquals(10, transactions.size());
		
		
		
	}
	
}
