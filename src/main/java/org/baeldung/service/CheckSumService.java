package org.baeldung.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CheckSumService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CheckSumService.class);

	public Long calcTransactionCheckSum(Transaction transaction) {
		String accountNumber = transaction.getAccountNumber();
		Long sum1 = Long.parseLong(accountNumber.substring(8, 10));
		Long sum2 = Long.parseLong(accountNumber.substring(10, 12));
		Long sum3 = transaction.getQuantity();
		return calcTransactionCheckSum(sum1,sum2, sum3);
	}

	public Long calcTransactionCheckSum(List<Transaction> transactions) {
		Long sum5 =calcTransactionCheckSumTotal(transactions);
		sum5 = sum5 /  Long.valueOf(transactions.size()) ;
		return sum5;
	}

	public Long calcTransactionCheckSumTotal(List<Transaction> transactions) {
		Long sum5 = 0L;
		for (Transaction transaction : transactions) {
			sum5 = sum5 + calcTransactionCheckSum(transaction);
		}
		return sum5;
	}

	public Long calcTransactionCheckSum(Long sum1, Long sum2, Long sum3) {
		Long sum4 = Math.abs(sum1 - sum2) * sum3;
		return sum4;
	}

	
}