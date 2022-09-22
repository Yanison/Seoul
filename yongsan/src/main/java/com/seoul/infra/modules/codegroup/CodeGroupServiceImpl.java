package com.seoul.infra.modules.codegroup;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeGroupServiceImpl implements CodeGroupService  {
	
	@Autowired
	CodeGroupDao dao;
	
	@Override
	public List<CodeGroup> selectCList(CodeGroup vo) throws Exception{
		return dao.selectCList(vo);
	}
	
	@PostConstruct
	public void selectListCachedCodeArrayList() throws Exception {
		List<CodeGroup> codeListFromDb = (ArrayList<CodeGroup>) dao.selectListCachedCodeArrayList();
		codeListFromDb = (ArrayList<CodeGroup>) dao.selectListCachedCodeArrayList();
		CodeGroup.cachedCodeArrayList.clear(); 
		CodeGroup.cachedCodeArrayList.addAll(codeListFromDb);
		System.out.println("cachedCodeArrayList: " + CodeGroup.cachedCodeArrayList.size() + " chached !");
	}
	//공통코드 모듈 캐시 저장.
	
	
	public static List<CodeGroup> selectListCachedCode(String infrCgSeq) throws Exception {
		System.out.println("@@@@@@@@@@@infrCgSeq : "+ infrCgSeq);
		List<CodeGroup> rt = new ArrayList<CodeGroup>();
		System.out.println("@@@@@@@@@@@infrCgSeq : "+ infrCgSeq);
		
		for(CodeGroup codeRow : CodeGroup.cachedCodeArrayList) {
			if (codeRow.getInfrCgSeq().equals(Integer.valueOf(infrCgSeq))) {
				rt.add(codeRow);
				System.out.println("@@@@@@@@"+ codeRow.getInfrCgSeq());
			} else {
				// by pass
			}
		}
		return rt;
	}
	
//	public static String selectOneCachedCode(int code) throws Exception {
//		String rt = "";
//		for(CodeGroup codeRow : CodeGroup.cachedCodeArrayList) {
//			if (codeRow.getInfrCcgSeq().equals(Integer.toString(code))) {
//				rt = codeRow.getInfrCcgName();
//			} else {
//				// by pass
//			}
//		}
//		return rt;
//	}
	
	
	public static void clear() throws Exception {
		CodeGroup.cachedCodeArrayList.clear();
	}
	
}
