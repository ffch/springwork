package com.cff.springwork.mybatis.mapper;

import java.util.List;

import com.cff.springwork.model.video.VideoInfo;

public interface VideoInfoMapper {
	public List<VideoInfo> getVideoInfoByCatid(String catid, int startNum, int size);
	
	public int getVideoCountByCatid(String catid);
}
