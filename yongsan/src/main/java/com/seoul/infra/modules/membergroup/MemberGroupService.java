package com.seoul.infra.modules.membergroup;

import java.util.List;

public interface MemberGroupService {
	
	public List<MemberGroup> selectMlist(MemberGroup vo) throws Exception;
	
	public int selectOneCnt (MemberGroup vo) throws Exception;
	
	public MemberGroup selectOneLogin (MemberGroup dto) throws Exception;
	public MemberGroup selectOneId (MemberGroup dto) throws Exception;
	
	public int isDupleId (MemberGroup vo) throws Exception;

	public int addUser(MemberGroup dto) throws Exception;

}
