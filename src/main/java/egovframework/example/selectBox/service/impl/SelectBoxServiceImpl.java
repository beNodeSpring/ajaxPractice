package egovframework.example.selectBox.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.selectBox.service.SelectBoxService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("selectBoxService")
public class SelectBoxServiceImpl implements SelectBoxService {

	@Resource(name="selectBoxMapper")
	private SelectBoxMapper selectBoxMapper;
	
	@Override
	public List<EgovMap> selectParentBoxList() throws Exception {
		// TODO Auto-generated method stub
		return selectBoxMapper.selectParentBoxList();
	}

	@Override
	public List<EgovMap> selectChildBoxList(String param) throws Exception {
		// TODO Auto-generated method stub
		return selectBoxMapper.selectChildBoxList(param);
	}


}
