package com.sangkon.app.controller;


import com.sangkon.app.domain.Board;
import com.sangkon.app.repository.BoardRepository;
import com.sangkon.app.service.BoardService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@Controller
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board/list")
    public String board(@PageableDefault Pageable pageable,Model model) {
        model.addAttribute("boardList", boardService.findBoardList(pageable));
        return "board/list";
    }

    @GetMapping("/board")
    public String getInputForm(Model model) {
        model.addAttribute("board", new Board());
        return "board/form";
    }

    @PostMapping("/board")
    public String setInputForm(@Valid Board board,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "board/form";
        }
        boardService.saveBoard(board);
        return "redirect:/board";
    }

    @GetMapping("/board/list/{id}")
    public String getBoardItem(@PathVariable Long id, Model model) {
        Board board = boardService.getBoardItem(id);
        model.addAttribute("board", board);
        return "board/form";
    }

}
