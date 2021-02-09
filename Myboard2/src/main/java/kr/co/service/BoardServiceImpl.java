package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.domain.BoardVO;
import kr.co.repository.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardVO> list() {
		// TODO Auto-generated method stub
		return boardDAO.list();
	}

	@Override
	public BoardVO read(int bno) {
		// TODO Auto-generated method stub
		return boardDAO.read(bno);
	}

	@Override
	public void insert(BoardVO vo) {
		boardDAO.insert(vo);
		
	}

	@Override
	public BoardVO update(int bno) {
		// TODO Auto-generated method stub
		return boardDAO.read(bno);
	}

	@Override
	public void update(BoardVO vo) {
		boardDAO.update(vo);
		
	}

	@Override
	public void delete(int bno) {
		boardDAO.delete(bno);
		
	}

}
