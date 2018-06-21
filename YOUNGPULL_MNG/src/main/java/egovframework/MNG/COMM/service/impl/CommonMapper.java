package egovframework.MNG.COMM.service.impl;

import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("commonMapper")
public interface CommonMapper {

	Map<String,Object> selectFileView(Map paramMap) throws Exception;
	
}
