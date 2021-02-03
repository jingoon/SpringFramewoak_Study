package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.domain.MemberVO;
import kr.co.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{

	@Inject //@Autowired와 같다
	private MemberDAO memberDAO; // service는 DAO를 호출
	@Override
	public String getTime() {
		return memberDAO.getTime();
	}
	@Override
	public void insert(MemberVO vo) {
		memberDAO.insert(vo);
	}
	@Override
	public List<MemberVO> list() {
		return memberDAO.list();
	}
	@Override
	public MemberVO read(String userId) {
		return memberDAO.read(userId);
	}
	@Override
	public MemberVO update(String userId) {	//update view
		return memberDAO.read(userId);	
	}
	@Override
	public int update(MemberVO vo) {	//update
		return memberDAO.update(vo);
	}
	@Override
	public int delete(MemberVO vo) {
		int seccessCount = memberDAO.delete(vo);
		return seccessCount;
	}

}
