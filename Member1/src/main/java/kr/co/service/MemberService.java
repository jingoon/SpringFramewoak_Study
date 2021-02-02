package kr.co.service;

import kr.co.domain.MemberVO;

public interface MemberService {
	public String getTime();
	public void insert(MemberVO vo);
	public MemberVO userInfo(String id);
	public void update(MemberVO vo);
}
