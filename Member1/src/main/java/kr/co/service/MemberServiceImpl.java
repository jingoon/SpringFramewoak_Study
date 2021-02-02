package kr.co.service;

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

}
