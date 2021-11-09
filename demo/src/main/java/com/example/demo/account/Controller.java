package com.example.demo.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	

	
	@GetMapping("/get-countries")
	public Map getCountry() {
		Map response = new HashMap();
		Map records =null;
		List recordsList =new ArrayList();
		response.put("success", false);
		response.put("message", "Invalid inputs!");
		response.put("records", recordsList);
		try {
			
			String countryquery ="SELECT Id,Country,countrycurrency  FROM tblLKCountry ORDER BY Country ";
			
			List<Map<String,Object>> countryList = jdbcTemplate.queryForList(countryquery);
			
			for(Map<String,Object> row : countryList)
			{
				records = new HashMap();
				records.put("id", row.get("Id"));
				records.put("country", row.get("Country"));
				records.put("countrycurrency",row.get("countrycurrency"));
				recordsList.add(records);
			}
			response.put("success", true);
			response.put("message", "Succesfully retrived country list!");
			response.put("records", recordsList);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}

}
