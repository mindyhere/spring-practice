package com.example.spring03.service.pdf;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring03.model.shop.CartDAO;
import com.example.spring03.model.shop.CartDTO;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service // service bean으로 등록
public class PdfServiceImpl implements PdfService {
	@Autowired
	CartDAO cartDao;

	@Override
	public String createPdf() {
		String result = "";
		try {
			Document document = new Document(); // pdf문서 객체
			// pdf파일을 저장하는 객체
			PdfWriter.getInstance(document, new FileOutputStream("c:/work/sample.pdf"));
			document.open(); // pdf문서 오픈
			
			// 한글처리를 위한 폰트설정
			BaseFont baseFont = BaseFont.createFont("c:/windows/fonts/malgun.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(baseFont, 12);
			
			// 4개의 셀이 있는 테이블
			PdfPTable table = new PdfPTable(4);
			
			// 문단처리
			Chunk chunk = new Chunk("장바구니", font);
			Paragraph ph = new Paragraph(chunk);
			ph.setAlignment(Element.ALIGN_CENTER); // 가운데정렬
			document.add(ph); // pdf문서에 문단추가
			document.add(Chunk.NEWLINE); // 줄바꿈 문자추가

			// 테이블의 셀구성
			PdfPCell cell1 = new PdfPCell(new Phrase("상품명", font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase("단가", font));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("수량", font));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(new Phrase("금액", font));
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell4);

			List<CartDTO> items = cartDao.list("kim");
			for (int i = 0; i < items.size(); i++) {
				CartDTO dto = items.get(i); // i번째 레코드

				PdfPCell cellProductName = new PdfPCell(new Phrase(dto.getProduct_name(), font));
				table.addCell(cellProductName);
				// 셀에는 문자열만 가능하므로 숫자를 문자열로 바꾸는 처리가 필요함
				PdfPCell cellPrice = new PdfPCell(new Phrase("" + dto.getPrice(), font));
				table.addCell(cellPrice);

				PdfPCell cellAmount = new PdfPCell(new Phrase("" + dto.getAmount(), font));
				table.addCell(cellAmount);

				PdfPCell cellMoney = new PdfPCell(new Phrase("" + dto.getMoney(), font));
				table.addCell(cellMoney);
			}
			
			document.add(table); // pdf문서에 테이블 추가
			document.close(); // pdf문서 닫기
			result = "pdf파일이 생성되었습니다.";
		} catch (Exception e) {
			e.printStackTrace();
			result = "pdf파일 생성실패...";
		}
		return result;
	}
}
