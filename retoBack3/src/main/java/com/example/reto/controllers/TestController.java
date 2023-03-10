/**
 * 
 */
package com.example.reto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.reto.entity.TestLab;
import com.example.reto.service.TestService;


@RestController
@RequestMapping ("/test")
public class TestController {
	
	
	@Autowired
	private TestService testServiceImpl;
	
	@GetMapping
	public ResponseEntity<?> getAllTest() {
		
		List<TestLab> testGet = this.testServiceImpl.getListTest();
			if (testGet.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}else {
			
			return ResponseEntity.ok(testGet);
		} 
	}
	
	@PostMapping
	public ResponseEntity<?> saveTest(@RequestBody TestLab test){
		try {
			TestLab savedTest= this.testServiceImpl.saveTest(test);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedTest);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		
		}
	
	
	@PutMapping ("/{idTest}")
	public ResponseEntity<?> updateTest(@PathVariable ("idTest") int idTest,@RequestBody TestLab test){
		try {
			TestLab updatedTest= this.testServiceImpl.updateTest(idTest, test);
			return ResponseEntity.status(HttpStatus.CREATED).body(updatedTest);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		}
	
	
	
	

	@RequestMapping (value ="{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTest (@PathVariable int id){
		
		try {
			this.testServiceImpl.deleteTest(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	}
	
	
	
	@RequestMapping (value ="{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable int id){
				
		try {
			TestLab testById= this.testServiceImpl.getById(id);
			return ResponseEntity.status(HttpStatus.OK).body(testById);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}
		
	}

}