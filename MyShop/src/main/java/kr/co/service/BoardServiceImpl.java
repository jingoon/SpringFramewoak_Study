package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.repository.BoardDAO;
import kr.co.repository.ReplyDAO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private ReplyDAO replyDAO;
	
	@Override
	public List<BoardVO> list(PageTO<BoardVO> to) {
		// TODO Auto-generated method stub
		return boardDAO.list(to);
	}

	@Transactional
	@Override
	public BoardVO read(int bno) {
		boardDAO.updateViewCnt(bno);	// 추가. viewCnt증가 메서드 호출
		return boardDAO.read(bno);
	}

	@Transactional
	@Override
	public void insert(BoardVO vo) {
		
		boardDAO.insert(vo);

		// 업로드 파일 목록 DB저장
		if(vo.getFiles() == null) {
			return;
		}
		for (String file : vo.getFiles()) {
			boardDAO.addAttach(file, vo.getBno());
		}
		
	}
	
	@Override
	public BoardVO update(int bno) {
		BoardVO vo = boardDAO.read(bno);
		return vo;
	}
	
	@Transactional
	@Override
	public void update(BoardVO vo) {
		boardDAO.update(vo);
		
		// 해당 글 DB파일 목록
		List<String> list = boardDAO.getAttach(vo.getBno());
		// 해당글 파일 전부 삭제
		for (int i = 0; i < list.size(); i++) {
			boardDAO.deleteAttach(list.get(i), vo.getBno());
		}
		
		// 수정된 파일목록
		String[] newFiles = vo.getFiles();
		// 수정 파일 없으면 종료
		if(newFiles == null) {
			return;
		}
		// 수정 파일 전부 저장
		for (String file : newFiles) {
			boardDAO.addAttach(file, vo.getBno());
		}
				
	}

	@Transactional
	@Override
	public void delete(int bno) {
		replyDAO.deleteReplies(bno);	// 댓글삭제를 먼저 해준다
		boardDAO.delete(bno);
		
	}

	@Override
	public Integer getAmount() {
		// TODO Auto-generated method stub
		return boardDAO.getAmount();
	}

	@Override
	public List<String> getAttach(int bno) {
		// TODO Auto-generated method stub
		return boardDAO.getAttach(bno);
	}

}
