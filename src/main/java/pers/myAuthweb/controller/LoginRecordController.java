package pers.myAuthweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pers.myAuthweb.service.LoginRecordService;

/*
 * author : LH 2018-05-21 PM
 */
@RestController
@RequestMapping("api/loginRecord")
public class LoginRecordController {
	
	@Autowired
	LoginRecordService recordService;
	
	
}
