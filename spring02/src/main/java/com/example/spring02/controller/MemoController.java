package com.example.spring02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.MemoDAO;
import com.example.spring02.model.MemoDTO;

@Controller
@RequestMapping("/memo") // 디렉토리(공통적인 매핑) → http://localhost/memo
public class MemoController {
	@Autowired
	MemoDAO memoDao;

	@GetMapping("/list.do") // 상세URL
	public ModelAndView list(ModelAndView mav) {
		List<MemoDTO> items = memoDao.list();
		mav.setViewName("memo/list"); // 뷰 지정
		mav.addObject("list", items); // 데이터
		return mav; // 포워드
	}

	@PostMapping("/insert.do")
	public String insert(MemoDTO dto) {
		memoDao.insert(dto.getWriter(), dto.getMemo());
		return "redirect:/memo/list.do";
	}

	@GetMapping("/view/{idx}") // rest 방식(url에 변수를 포함) → @PathVariable
	public ModelAndView view(@PathVariable(name = "idx") int idx, ModelAndView mav) {
		mav.setViewName("memo/view");
		mav.addObject("dto", memoDao.view(idx));
		return mav;
	}

	@PostMapping("/update/{idx}")
	public String update(@PathVariable(name = "idx") int idx, MemoDTO dto) {
		memoDao.update(dto);
		return "redirect:/memo/list.do";
	}

	@PostMapping("/delete/{idx}")
	public String delete(@PathVariable(name = "idx") int idx) {
		memoDao.delete(idx);
		return "redirect:/memo/list.do";
	}
}
