package com.kakao.itstudy.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.kakao.itstudy.dto.ItemDTO;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class PdfView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(
			Map<String, Object> model, 
			Document document, 
			PdfWriter writer,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		//전달된 데이터 가져오기
		List<ItemDTO>list = (List<ItemDTO>) model.get("list");
		
		//테이블에 출력
		Table table = new Table(3, list.size() + 1);
		table.setPadding(5);
		
		//한글을 사용하기 위해서는 한글이 지원되는 폰트를 설정
		BaseFont bfKorean = 
				BaseFont.createFont(
					request.getRealPath("/")+"/malgun.ttf",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(bfKorean);
		
		Cell cell = new Cell(new Paragraph("이름", font));
		cell.setHeader(true);
		table.addCell(cell);
		
		cell = new Cell(new Paragraph("설명", font));
		cell.setHeader(true);
		table.addCell(cell);
		
		cell = new Cell(new Paragraph("가격", font));
		cell.setHeader(true);
		table.addCell(cell);
		table.endHeaders();
		
		//데이터 출력
		for(ItemDTO item : list) {
			Cell imsi = new Cell(
				new Paragraph(item.getItemname(), font));
			table.addCell(imsi);
			
			imsi = new Cell(
				new Paragraph(item.getDescription(), font));
			table.addCell(imsi);
				
			imsi = new Cell(
				new Paragraph(item.getPrice() + "원", font));
			table.addCell(imsi);
		}
		document.add(table);
	}

}





