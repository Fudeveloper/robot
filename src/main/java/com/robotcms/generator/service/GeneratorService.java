/**
 * 
 */
package com.robotcms.generator.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface GeneratorService {
	List<Map<String, Object>> list();

	byte[] generatorCode(String[] tableNames);
}
