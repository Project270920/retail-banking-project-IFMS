package com.project.ifms.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class TestResource
{
	@GetMapping(value="test", produces={"application/json", "application/xml"})
	public ResponseEntity<String> test()	{
		return new ResponseEntity<String>("Test", HttpStatus.OK);
	}
}
