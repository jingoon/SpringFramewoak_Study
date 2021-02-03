package kr.co.repository;

import java.util.List;

import kr.co.domain.MemberVO;

public interface MemberDAO {
	public String getTime();
	public void insert(MemberVO vo);
	public List<MemberVO> list();
	public MemberVO read(String userId);
	public int update(MemberVO vo);
	public int delete(MemberVO vo);


}
