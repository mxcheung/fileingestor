package org.baeldung.service;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Service
public class CsvDataLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvDataLoader.class);
	public static final String USERS_FILE = "user.csv";
	public static final String TRANSACTIONS_FILE = "transaction.csv";

	public <T> List<T> loadObjectList(Class<T> type, String fileName) {
	    try {
	        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
	        CsvMapper mapper = new CsvMapper();
	        File file = new ClassPathResource(fileName).getFile();
	        MappingIterator<T> readValues = 
	          mapper.reader(type).with(bootstrapSchema).readValues(file);
	        return readValues.readAll();
	    } catch (Exception e) {
	    	LOGGER.error("Error occurred while loading object list from file " + fileName, e);
	        return Collections.emptyList();
	    }
	}
	
	
	
}