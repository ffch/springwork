package com.cff.springwork.mybatis.mapper;


public interface AppSeqMapper {
	public int getNextSeq(String seqName);
	public int getNextSeqWithSize(String seqName,int size);
}
