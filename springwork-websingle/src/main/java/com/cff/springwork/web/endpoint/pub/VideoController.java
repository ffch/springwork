package com.cff.springwork.web.endpoint.pub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cff.springwork.web.entity.VideoListDto;
import com.cff.springwork.web.service.VideoService;

@RestController("videoController")
@RequestMapping("/video")
public class VideoController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	VideoService videoService;
	private int size = 10;;
	@RequestMapping("/list")
	public VideoListDto list(@RequestParam String catid, @RequestParam int curPageNum,@RequestParam int isFirst){
		boolean first = false;
		if(isFirst == 1)first = true;		
		return videoService.getVideoInfoByCatid(catid, curPageNum, size , first);
	}
	
}
