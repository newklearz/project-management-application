package com.newklearz.controllers;

import com.newklearz.DTO.BoardDTO;
import com.newklearz.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController implements BoardResource
{
    private final BoardService boardService;

    public BoardController(BoardService boardService)
    {
        this.boardService = boardService;
    }

    @Override
    public ResponseEntity<List<BoardDTO>> getBoards()
    {
        return ResponseEntity.ok(boardService.findAll());
    }

    @Override
    public ResponseEntity<BoardDTO> getBoard(Integer id)
    {
        return ResponseEntity.ok(boardService.findById(id));
    }

    @Override
    public ResponseEntity<BoardDTO> createBoard(BoardDTO boardDTO)
    {
        return ResponseEntity.ok(boardService.createBoard(boardDTO));
    }

    @Override
    public ResponseEntity<BoardDTO> updateBoard(Integer id, BoardDTO boardDTO)
    {
        return ResponseEntity.ok(boardService.updateBoard(id, boardDTO));
    }
}