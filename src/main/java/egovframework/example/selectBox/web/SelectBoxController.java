package egovframework.example.selectBox.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.example.cmmn.JsonUtil;
import egovframework.example.selectBox.service.SelectBoxService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class SelectBoxController {

	@Resource(name="selectBoxService")
	private SelectBoxService selectBoxService;
	
	@RequestMapping("/selectBox.do")
	public String selectBoxMain(ModelMap model) throws Exception {
		List<EgovMap> parentList = selectBoxService.selectParentBoxList();
		
		model.addAttribute("parentList", parentList);
		
		System.out.println("parentList: "+parentList);
		return "selectBox/selectBox.tiles";
	}
	
	// produces가 헤더에 정보를 주는 곳이므로 json이라고 적으면 json으로 보낸다
	@RequestMapping(value="/childSelectBox.do", produces="application/json; charset=utf-8")
	@ResponseBody 
	public String childSelectBoxMain(@RequestParam String param, ModelMap model, HttpServletRequest request) throws Exception {
		List<EgovMap> childList = selectBoxService.selectChildBoxList(param);
		
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).put("SUCCESS", "SUCCESS");
		}
		//model.addAttribute("childList", childList);
		
		System.out.println("childList: "+childList);

		// 이 방식은 json으로 보내버리므로 화면에서 Json.parse할 필요가 없다.
		return JsonUtil.ListToJson(childList);
	}
}
