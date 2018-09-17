package org.baeldung.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CheckSumServiceTest {

	CsvDataLoader csvDataLoader;
	CheckSumService checkSumService;
	@Before
	public void setup() throws Exception {
		csvDataLoader = new CsvDataLoader();
		checkSumService = new CheckSumService();
	}


	@Test
	public void shouldCalcCheckSum() {
		assertEquals(Long.valueOf(64000L), checkSumService.calcTransactionCheckSum(29L, 93L, 1000L) );
	}

	@Test
	public void shouldCalcTranCheckSum() {
		Transaction transaction = new Transaction();
		transaction.setAccountNumber("215000042993");
		transaction.setQuantity(1000L);
		assertEquals(Long.valueOf(64000L), checkSumService.calcTransactionCheckSum(transaction) );
	}

	@Test
	public void shouldCalcTranCheckSumTotal() {
		List<Transaction> transactions = csvDataLoader.loadObjectList(Transaction.class, CsvDataLoader.TRANSACTIONS_FILE);
		assertFalse(transactions.isEmpty());
		assertEquals(10, transactions.size());
		assertEquals(Long.valueOf(1126400L), checkSumService.calcTransactionCheckSumTotal(transactions) );
	}

	@Test
	public void shouldLoadTransactions() {
		List<Transaction> transactions = csvDataLoader.loadObjectList(Transaction.class, CsvDataLoader.TRANSACTIONS_FILE);
		assertFalse(transactions.isEmpty());
		assertEquals(10, transactions.size());
		assertEquals(Long.valueOf(112640L), checkSumService.calcTransactionCheckSum(transactions) );
	}
	
}
