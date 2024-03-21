package com.example.spring06.model;

import java.util.List;

public interface SurveyDAO {
	SurveyDTO showSurvey(int survey_idx);

	void save(AnswerDTO dto);

	List<AnswerDTO> showResult(int survey_idx);
}
