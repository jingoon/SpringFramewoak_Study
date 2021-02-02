package kr.co.repository;

import kr.co.domain.MemberVO;

public interface MemberDAO {
	public String getTime();
	public void insert(MemberVO vo);
}
