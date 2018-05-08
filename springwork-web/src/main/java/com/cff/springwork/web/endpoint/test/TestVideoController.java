package com.cff.springwork.web.endpoint.test;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController("testVideoController")
@RequestMapping("/testvideo")
public class TestVideoController {
	String mp4FilePath = "D:/download/";
	
	@RequestMapping("/mp4/{fileName}")
	public File test(@PathVariable("fileName") String fileName){
		File file = new File(mp4FilePath + fileName);
		return file;
	}
	
}
