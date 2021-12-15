package com.newklearz.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.newklearz.SpringBootTestEnvironment;
import com.newklearz.DTO.BoardDTO;

public class BoardControllerIT extends SpringBootTestEnvironment
{
    @Test
    public void testRetrievalOfBoards()
    {
        ResponseEntity<List<BoardDTO>> board = boardController.getBoards();
        assertNotNull(board);
        assertEquals(board.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRetrievalOfBoard()
    {
        ResponseEntity<BoardDTO> board = boardController.getBoard(boardDTO.getId());
        assertEquals(board.getStatusCode(), HttpStatus.OK);
        assertNotNull(board);

        BoardDTO retrievedBoard = board.getBody();
        assertNotNull(retrievedBoard);

        assertEquals(boardDTO.getId(), retrievedBoard.getId());
    }

    @Test
    public void testCreateOfBoard()
    {
        BoardDTO testBoard = new BoardDTO();
        ResponseEntity<BoardDTO> board = boardController.createBoard(testBoard);
        assertNotNull(board);
        assertEquals(board.getStatusCode(), HttpStatus.OK);

        BoardDTO boardDTOFound = board.getBody();
        assertEquals(board.getBody().getId(), boardDTOFound.getId());
    }

    @Test
    public void testUpdateOfBoard()
    {
        ResponseEntity<BoardDTO> foundBoardBeforeUpdate = boardController.getBoard(boardDTO.getId());
        assertNotNull(foundBoardBeforeUpdate);

        BoardDTO boardBeforeUpdate = foundBoardBeforeUpdate.getBody();
        boardBeforeUpdate.setName("abc");

        ResponseEntity<BoardDTO> requestUpdateBoard = boardController.updateBoard(boardBeforeUpdate.getId(), boardBeforeUpdate);
        assertNotNull(requestUpdateBoard);
        assertEquals(requestUpdateBoard.getStatusCode(), HttpStatus.OK);

        ResponseEntity<BoardDTO> foundBoardAfterUpdate = boardController.getBoard(boardBeforeUpdate.getId());
        assertNotNull(foundBoardAfterUpdate);

        BoardDTO boardAfterUpdate = foundBoardAfterUpdate.getBody();
        assertEquals(boardBeforeUpdate.getId(), boardAfterUpdate.getId());
    }
}