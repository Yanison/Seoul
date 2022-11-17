package com.seoul.infra.modules.membergroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/member")
public class MemberRestController{

	@Autowired
	MemberGroupServiceImpl service;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
//	@GetMapping("")
	public List<MemberGroup> selectList(MemberGroup dto) throws Exception {
		List<MemberGroup> list = service.selectMlist(dto);
		return list;
	}
	

	@RequestMapping(value = "/{seq}", method = RequestMethod.GET)
//	@GetMapping("/{seq}")
	public MemberGroup selectOne(@PathVariable int seq, MemberGroup dto) throws Exception {
		dto.setMemberSeq(seq);
		MemberGroup item = service.selectMOne(dto);
		return item;
	}
	
	@PostMapping(value = "/naverLoginProc")
	public MemberGroup naverLogin(MemberGroup member)throws Exception{
		
		
		
		return member;
	}
	
	

//	@RequestMapping(value = "", method = RequestMethod.POST)
////	@PostMapping("")
//	public String insert(@RequestBody MemberGroup dto) throws Exception {
//		service.addUser(dto);
//		return dto.getIfmmSeq();
//	}
//	
//	
//	@RequestMapping(value = "/{seq}", method = RequestMethod.PATCH)
////	@PatchMapping("/{seq}")
//	public void update(@PathVariable String seq, @RequestBody MemberGroup dto) throws Exception {
//		dto.setIfmmSeq(seq);
//		service.(dto);
//	}
	
	
	
}
