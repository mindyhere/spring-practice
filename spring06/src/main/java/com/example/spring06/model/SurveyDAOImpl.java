package com.example.spring06.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SurveyDAOImpl implements SurveyDAO {
	@Autowired
	SqlSession sqlSession;

	@Override
	public SurveyDTO showSurvey(int survey_idx) {
		// 문제내용
		return sqlSession.selectOne("show_survey", survey_idx);
	}

	@Override
	public void save(AnswerDTO dto) {
		// 응답저장
		sqlSession.insert("save_answer", dto);

	}

	@Override
	public List<AnswerDTO> showResult(int survey_idx) {
		return sqlSession.selectList("show_result", survey_idx);
	}
}
