package com.sangkon.app.service;

import com.sangkon.app.domain.Board;
import com.sangkon.app.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Page<Board> findBoardList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1,
                pageable.getPageSize());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        return boardRepository.findAll(pageable);
    }

    public  Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

}

