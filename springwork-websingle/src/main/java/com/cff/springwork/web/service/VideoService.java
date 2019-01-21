package com.cff.springwork.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cff.springwork.model.video.VideoInfo;
import com.cff.springwork.mybatis.mapper.VideoInfoMapper;
import com.cff.springwork.web.entity.VideoListDto;

@Service
public class VideoService {
	@Autowired
	VideoInfoMapper videoInfoMapper;
	
	public VideoListDto getVideoInfoByCatid(String catid, int pageNum, int size, boolean isFirst){
		int startNum = pageNum * size;
		List<VideoInfo> lists = videoInfoMapper.getVideoInfoByCatid(catid, startNum, size);
		VideoListDto videoListDto = new VideoListDto();
		if(lists == null || lists.size() < 1){
			videoListDto.setCatid(catid);
			videoListDto.setSum(0);
			videoListDto.setTotalPageNum(0);
			videoListDto.setCurPageNum(0);
			videoListDto.setCoeds(null);
			return videoListDto;
		}
		if(isFirst){
			int total = videoInfoMapper.getVideoCountByCatid(catid);
			videoListDto.setSum(total);
			videoListDto.setTotalPageNum(total/size);
		}
		videoListDto.setCatid(catid);
		videoListDto.setCurPageNum(pageNum);
		List<String> coeds = new ArrayList<String>(lists.size());
		for(VideoInfo item : lists){
			String time = "";
			int minu = item.getLength()/60;
			int hour = minu/60;
			int seco = item.getLength() - minu * 60;
			if(hour > 0 )time += hour + "时";
			if(minu > 0){
				minu -= hour * 60;
				time += minu + "分";
			}
			time += seco + "秒";
			String imgDesc = "";
			for(int i =0 ;i < item.getRank();i++){
				imgDesc += "<img src=\"/img/video/xing2.png\" alt=\"\"> ";
			}
			for(int j=5;j> item.getRank();j--){
				imgDesc += "<img src=\"/img/video/xing1.png\" alt=\"\"> ";
			}
			String code = "<dl><dt><a href=\""+ item.getUrl() + "\">"
					+ "<img	src=\" " + item.getImg() + "\" alt=\"\"></a></dt>"
					+ "<dd class=\"dd3\"><p>" + time + "</p></dd><dd title=\""
					+ item.getTitle()+"\"></dd><dd>播放：" + item.getPoint()+ "次</dd>"
					+ "<dd class=\"dd4\"><p>评分：</p> " + imgDesc + "</dd></dl>";
			coeds.add(code);
		}
		videoListDto.setCoeds(coeds );
		return videoListDto;
	}
}
