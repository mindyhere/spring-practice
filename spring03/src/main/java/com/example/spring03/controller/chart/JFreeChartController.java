package com.example.spring03.controller.chart;

import java.io.FileOutputStream;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.service.chart.JFreeChartService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/jchart/*")
public class JFreeChartController {
	@Autowired
	JFreeChartService chartService;
	
	@GetMapping("chart1.do")
	public void createChart1(HttpServletResponse response) {
		try {
			JFreeChart chart=chartService.creatChart();
			ChartUtils.writeChartAsPNG(response.getOutputStream(), chart, 900, 500);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("chart2.do")
	public ModelAndView createChart2(HttpServletResponse response) {
		String message="";
		try {
			JFreeChart chart=chartService.creatChart();
			Document document= new Document();
			PdfWriter.getInstance(document, new FileOutputStream("c:/work/test.pdf"));
			document.open();
			Image png=Image.getInstance(ChartUtils.encodeAsPNG(chart.createBufferedImage(500, 500)));
			document.add(png);
			document.close();
			message="pdf파일이 생성되었습니다.";
		} catch (Exception e) {
			e.printStackTrace();
			message="파일생성 실패";
		}
		return new ModelAndView("chart/jchart02", "message", message);
	}
	
}
