package com.example.spring06.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring06.model.AnswerDTO;
import com.example.spring06.model.SurveyDAO;
import com.example.spring06.model.SurveyDTO;

@Controller
public class SurveyController {
	@Autowired
	SurveyDAO surveyDao;

	@GetMapping("/")
	public ModelAndView show_survey() {
		SurveyDTO dto = surveyDao.showSurvey(1);
		return new ModelAndView("main", "dto", dto);
		//						 뷰 이름	 변수		값
	}

	@PostMapping("/save_survey.do")
	public String save_survey(AnswerDTO dto) {
		surveyDao.save(dto);
		return "success";
	}

	@GetMapping("show_result.do")
	public ModelAndView show_result(@RequestParam(name = "survey_idx") int survey_idx) {
		Map<String, Object> map = new HashMap<>();
		SurveyDTO dto = surveyDao.showSurvey(survey_idx);
		List<AnswerDTO> items = surveyDao.showResult(survey_idx);
		map.put("dto", dto);
		map.put("list", items);
		return new ModelAndView("result", "map", map);
	}
}
