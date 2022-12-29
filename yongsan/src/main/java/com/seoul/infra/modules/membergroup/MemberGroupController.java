package com.seoul.infra.modules.membergroup;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seoul.infra.common.MailService;
import com.seoul.infra.modules.codegroup.CodeGroupServiceImpl;

@Controller
@RequestMapping(value="/yongsancode/")
public class MemberGroupController {
	
	@Autowired
	MemberGroupServiceImpl service;
	@Autowired
	MailService mailService;
	
	@RequestMapping(value = "memberGroupList")
	public String memberGroupList(@ModelAttribute("vo") MemberGroup vo,Model model) throws Exception {
		

		vo.setParamsPaging(service.selectOneCnt(vo));
		
		List<MemberGroup> list = service.selectMlist(vo);
		model.addAttribute("list", list);
		int selectOneCnt = service.selectOneCnt(vo);
		System.out.println("@@@selectOneCnt" + selectOneCnt);
		
		return "infra/hwangdmin/membergroup/memberGroupList"; 
	}
	@ResponseBody
	@RequestMapping(value="memberGroupUpdt")
	public String memberGroupUdpt(@ModelAttribute("vo") MemberGroup vo,Model model, MemberGroup dto ) throws Exception{
		System.out.println("pw ::"+ vo.getMemberPw());
		int addUser = service.addUser(dto);
		System.out.println(addUser);
		
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping(value = "duplicationCheck")
	public int checkId(MemberGroup dto) throws Exception {
		
		return service.isDuple(dto);
	}
	
	@ResponseBody
	@RequestMapping(value = "requestEmailValidation")
	public String requestEmailValidation(MemberGroup dto) throws Exception {
		
		return mailService.sendMailViaSmtpGmail(dto.getMemberEmail());
	}
	
	@ResponseBody
	@RequestMapping(value = "requestUserLogin")
	public HashMap<String,String> selectOneLogin(MemberGroup dto,HttpSession session) throws Exception {
		HashMap<String,String> rp = new HashMap<String,String>();
		MemberGroup selectOneLogin = service.selectOneLogin(dto);
		
		if(selectOneLogin.getCnt() == 1) {
			session.setAttribute("memberName", selectOneLogin.getMemberNickname());
	        session.setAttribute("memberSeq", selectOneLogin.getMemberSeq());
	        
	        System.out.println("session.setAttribute : getMemberName :: "+ selectOneLogin.getMemberNickname());
	        System.out.println("session.setAttribute : getMemberSeq :: "+ selectOneLogin.getMemberSeq());
			rp.put("rp", "success");
			return rp;
		}else {
			rp.put("rp", "fail");
			return rp;
		}
		
		
	}
	
	@ResponseBody
	@RequestMapping(value = "requestAddUser")
	public HashMap<String,String> requestAddUser(MemberGroup dto) throws Exception {
		
		HashMap<String,String> rp = new HashMap<String,String>();
		
		int adduser = service.addUser(dto);
		int sum = 0;
		
		if(adduser == 1) {
			
			dto.setShOption(1);
			dto.setShOption2(50000000);
			int addBalance1 = service.addBalance(dto);
			dto.setShOption(2);
			dto.setShOption2(0);
			int addBalance2 = service.addBalance(dto);
			dto.setShOption(3);
			dto.setShOption2(0);
			int addBalance3 = service.addBalance(dto);
			dto.setShOption(4);
			dto.setShOption2(0);
			int addBalance4 = service.addBalance(dto);
			
			sum = addBalance1 + addBalance2 + addBalance3 + addBalance4;
			if(sum == 4) {
				rp.put("rp", "success");
			}else {
				rp.put("rp", "failed to addBalance");
			}
			return rp;
		}else {
			rp.put("rp", "failed to adduser");
			return rp;
		}
	}
	
	@RequestMapping("excelDownload")
    public void excelDownload(MemberGroup vo, HttpServletResponse httpServletResponse) throws Exception {
		
//		setSearch(vo);
		vo.setParamsPaging(service.selectOneCnt(vo));

		if (vo.getTotalRows() > 0) {
			List<MemberGroup> list = service.selectMlist(vo);
			
//			Workbook workbook = new HSSFWorkbook();	// for xls
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Sheet1");
	        CellStyle cellStyle = workbook.createCellStyle();        
	        Row row = null;
	        Cell cell = null;
	        int rowNum = 0;
			
//	        each column width setting	        
	        sheet.setColumnWidth(0, 2100);
	        sheet.setColumnWidth(1, 3100);

//	        Header
	        String[] tableHeader = {"Seq", "이름", "아이디", "성별", "생일", "이메일", "모바일", "등록일", "수정일"};

	        row = sheet.createRow(rowNum++);
	        
			for(int i=0; i<tableHeader.length; i++) {
				cell = row.createCell(i);
	        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	        	cell.setCellStyle(cellStyle);
				cell.setCellValue(tableHeader[i]);
			}

//	        Body
	        for (int i=0; i<list.size(); i++) {
	            row = sheet.createRow(rowNum++);
	            
//	            String type: null 전달 되어도 ok
//	            int, date type: null 시 오류 발생 하므로 null check
//	            String type 이지만 정수형 데이터가 전체인 seq 의 경우 캐스팅	            
	            
	            cell = row.createCell(0);
	        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	        	cell.setCellStyle(cellStyle);
	            cell.setCellValue(list.get(i).getMemberSeq());
	            
	            cell = row.createCell(1);
	        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	        	cell.setCellStyle(cellStyle);
	        	cell.setCellValue(list.get(i).getMemberName());
	        	
	            cell = row.createCell(2);
	        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	        	cell.setCellStyle(cellStyle);
	        	cell.setCellValue(list.get(i).getMemberId());
	        	
	            cell = row.createCell(3);
	        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	        	cell.setCellStyle(cellStyle);
	            if(list.get(i).getMemberGender() != null) cell.setCellValue(CodeGroupServiceImpl.selectListCachedCode(list.get(i).getMemberGender().toString()).toString());
	            
	            cell = row.createCell(4);
	            cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            cell.setCellStyle(cellStyle);
	            cell.setCellValue(list.get(i).getRegDateAt());
	            
	            cell = row.createCell(5);
	            cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            cell.setCellStyle(cellStyle);
	            cell.setCellValue(list.get(i).getMemberEmail());
	            
	            cell = row.createCell(6);
	            cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            cell.setCellStyle(cellStyle);
	            cell.setCellValue(list.get(i).getMemberTel());    
	            
	            cell = row.createCell(7);
	        	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	        	cell.setCellStyle(cellStyle);
	        	if(list.get(i).getRegDateAt() != null) cell.setCellValue(list.get(i).getRegDateAt().toString());
	            
	            cell = row.createCell(8);
	            cellStyle.setAlignment(HorizontalAlignment.CENTER);
	            cell.setCellStyle(cellStyle);
	            if(list.get(i).getModDateAt() != null) cell.setCellValue(list.get(i).getModDateAt().toString());
	        }

	        httpServletResponse.setContentType("ms-vnd/excel");
//	        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=example.xls");	// for xls
	        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=example.xlsx");

	        workbook.write(httpServletResponse.getOutputStream());
	        workbook.close();
		}
    }
	
	 

}
