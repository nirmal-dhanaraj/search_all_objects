package com.idm.search.controller;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.idm.search.service.SearchAllObjectService;


@Controller
public class LoginController {
	

private final SearchAllObjectService searchAllObjectService;
	
	@Autowired
	LoginController(final SearchAllObjectService searchAllObjectService){
		
		this.searchAllObjectService= searchAllObjectService;
	}
		
	@RequestMapping("/searchAllObjects")
	public ResponseEntity<String> searchAllObjects() throws Exception {
		return searchAllObjectService.searchAllObjects();
	}

}