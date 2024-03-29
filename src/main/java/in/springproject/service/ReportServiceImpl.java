package in.springproject.service;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.springproject.entitu.EligibilityDetails;
import in.springproject.repository.EligibilityDetailsRepo;
import in.springproject.request.SearchRequest;
import in.springproject.request.SearchResponse;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	EligibilityDetailsRepo eligRepo;

	@Override
	public List<SearchResponse> search(SearchRequest request) {
		// TODO Auto-generated method stub
		
		List<SearchResponse> response = new ArrayList<>();
		
		EligibilityDetails queryBuilder = new EligibilityDetails();
		
		String planName = request.getPlanName();
		if(planName!=null && !planName.equals(""))
		{
			queryBuilder.setPlanName(planName);
		}

		String planStatus = request.getPlanStatus();
		if(planStatus!=null && !planStatus.equals(""))
		{
			queryBuilder.setPlanStatus(planStatus);
		}
		
		LocalDate planStartDate = request.getPlanStartDate();
		if(planStartDate!=null && !planStartDate.equals(""))
		{
			queryBuilder.setPlanStartDate(planStartDate);
		}
		
		LocalDate planEndDate = request.getPlanEndDate();
		if(planEndDate!=null && !planEndDate.equals(""))
		{
		    queryBuilder.setPlanEndDate(planEndDate);	
		}

		Example<EligibilityDetails> example = Example.of(queryBuilder);
		
		List<EligibilityDetails> entities = eligRepo.findAll(example);
		
		for(EligibilityDetails eligibilityDetails : entities)
		{
			SearchResponse sr = new SearchResponse();
			BeanUtils.copyProperties(eligibilityDetails, sr);
			response.add(sr);
		}
		
		
		return response;
	}

	
	
	public List<EligibilityDetails> findByCriteria(SearchRequest request){
        return eligRepo.findAll(new Specification<EligibilityDetails>() {
//            @Override
//            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> predicates = new ArrayList<>();
//                if(employeeName!=null) {
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("employeeName"), "%"+employeeName+"%")));
//                }
//                if(employeeRole!=null){
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("employeeRole"), employeeRole)));
//                }
//                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//            }

			@Override
			public Predicate toPredicate(Root<EligibilityDetails> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				if(request.getPlanName()!=null)
				{
					
//					//EQUALS
					predicates.add(criteriaBuilder.and(criteriaBuilder.
							equal(root.get("planName"), request.getPlanName())));
					
//					//LIKE
//					predicates.add(criteriaBuilder.and(criteriaBuilder.
//							like(root.get("planName"), "%"+request.getPlanName()+"%")));
					
					
//					predicates.add(criteriaBuilder.and(criteriaBuilder.
//							like(root.get("planName"), request.getPlanName())));
		
					//IN
//					if(request.getPlanName().getClass().isArray())
//					{
//						predicates.add(criteriaBuilder.and(criteriaBuilder.
//								in(root.get("planName").in(request.getPlanName()))));
//					}
				
				}
				//GREATER THAN , LESS THAN
				if(request.getPlanStartDate()!=null)
				{
					predicates.add(criteriaBuilder.and(criteriaBuilder.
							greaterThanOrEqualTo(root.get("planStartDate"),request.getPlanStartDate())));
				}
				
				return criteriaBuilder.and(predicates.toArray (new Predicate[predicates.size()]));
			
//				
			}
        });
    }



	@Override
	public void generateExcel(HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		List<EligibilityDetails> entities = eligRepo.findAll();
		
		HSSFWorkbook workBook =  new HSSFWorkbook();
		HSSFSheet sheet =  workBook.createSheet();
		HSSFRow headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("S.No");
		headerRow.createCell(1).setCellValue("Name");
		headerRow.createCell(2).setCellValue("Mobile");
		headerRow.createCell(3).setCellValue("Gender");
		headerRow.createCell(4).setCellValue("SSN");
		
		
		
		entities.forEach(entity -> {
			int i=1;
			HSSFRow dataRow = sheet.createRow(i);
			dataRow.createCell(0).setCellValue(entity.getName());
			dataRow.createCell(1).setCellValue(entity.getName());
			dataRow.createCell(2).setCellValue(entity.getName());
			dataRow.createCell(3).setCellValue(entity.getName());
			i++;
		});
		
		ServletOutputStream outputStream = response.getOutputStream();
		workBook.write(outputStream);
		workBook.close();
		outputStream.close();
		
	}



	@Override
	public void generatePdf(HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		List<EligibilityDetails> entities = eligRepo.findAll();
		
		Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		
		Paragraph p = new Paragraph("Search Report", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(p);
		
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
		table.setSpacingBefore(10);
		
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		
		font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		
		cell.setPhrase(new Phrase("Name", font));
		
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("E-mail",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Phno",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Gender",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("SSN",font));
		table.addCell(cell);
		
		for(EligibilityDetails entity : entities)
		{
			table.addCell(entity.getName());
			table.addCell(entity.getEmail());
			table.addCell(String.valueOf(entity.getMobile()));
			table.addCell(String.valueOf(entity.getGender()));
			table.addCell(String.valueOf(entity.getSsn()));
		}
		
	}
	
	
	
	
	
	
	
	
}
