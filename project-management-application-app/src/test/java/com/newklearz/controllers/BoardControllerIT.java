package com.newklearz.controllers;

import static com.newklearz.controllers.Utils.getAlphaNumericString;
import static com.newklearz.controllers.Utils.getBlankSpace;
import static com.newklearz.controllers.Utils.getEmptyString;
import static com.newklearz.controllers.Utils.getIntegerZero;
import static com.newklearz.controllers.Utils.getMoreChars;
import static com.newklearz.controllers.Utils.getOutOfRangeValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.newklearz.SpringBootTestEnvironment;
import com.newklearz.dto.BoardDTO;

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
        assertEquals(boardDTO.getName(), retrievedBoard.getName());
    }

    @Test
    public void testRetrievalOfBoardNegative()
    {
        /**
         * Retrieve board with a negative id
         */
        ResponseEntity<BoardDTO> boardNegativeId = boardController.getBoard(Integer.MIN_VALUE);
        assertEquals(HttpStatus.BAD_REQUEST, boardNegativeId.getStatusCode());

        /**
         * Retrieve board with an in-existent id
         */
        ResponseEntity<BoardDTO> boardInexistentId = boardController.getBoard(Integer.MAX_VALUE);
        assertEquals(HttpStatus.NOT_FOUND, boardInexistentId.getStatusCode());

        /**
         * Retrieve board with id value of zero
         */
        ResponseEntity<BoardDTO> boardZeroId = boardController.getBoard(getIntegerZero());
        assertEquals(HttpStatus.BAD_REQUEST, boardZeroId.getStatusCode());

        /**
         * Retrieve board with Integer out of range id value
         */
        ResponseEntity<BoardDTO> outOfRangeIntegerBoardId = boardController.getBoard(getOutOfRangeValue());
        assertEquals(HttpStatus.BAD_REQUEST, outOfRangeIntegerBoardId);
    }

    @Test
    public void testCreateOfBoard()
    {
        BoardDTO testBoard = new BoardDTO(null, getAlphaNumericString());
        ResponseEntity<BoardDTO> createdBoard = boardController.createBoard(testBoard);
        assertNotNull(createdBoard);
        assertEquals(createdBoard.getStatusCode(), HttpStatus.OK);

        BoardDTO boardDTOFound = createdBoard.getBody();
        assertEquals(createdBoard.getBody().getId(), boardDTOFound.getId());
        assertEquals(testBoard.getName(), boardDTOFound.getName());
    }

    @Test
    public void testCreateOfBoardNegative()
    {
        /**
         * Create board with null name
         */
        ResponseEntity<BoardDTO> boardNullName = boardController.createBoard(new BoardDTO(null, null));
        assertEquals(HttpStatus.BAD_REQUEST, boardNullName.getStatusCode());

        /**
         * Create board with empty string name
         */
        ResponseEntity<BoardDTO> boardEmptyStringName = boardController.createBoard(new BoardDTO(null, getEmptyString()));
        assertEquals(HttpStatus.BAD_REQUEST, boardEmptyStringName.getStatusCode());

        /**
         * Create board with blank space name
         */
        ResponseEntity<BoardDTO> boardBlankSpaceName = boardController.createBoard(new BoardDTO(null, getBlankSpace()));
        assertEquals(HttpStatus.BAD_REQUEST, boardBlankSpaceName.getStatusCode());
        /**
         * Create board with same name as an existing one
         */
        ResponseEntity<BoardDTO> boardSameName = boardController.createBoard(new BoardDTO(null, boardDTO.getName()));
        assertEquals(HttpStatus.CONFLICT, boardSameName.getStatusCode());

        /**
         * Create board with more than 100 characters in name
         */
        ResponseEntity<BoardDTO> boardMoreCharsName = boardController.createBoard(new BoardDTO(null, getMoreChars()));
        assertEquals(HttpStatus.BAD_REQUEST, boardMoreCharsName.getStatusCode());
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
        assertEquals(foundBoardAfterUpdate.getStatusCode(), HttpStatus.OK);

        BoardDTO boardAfterUpdate = foundBoardAfterUpdate.getBody();
        assertEquals(boardBeforeUpdate.getId(), boardAfterUpdate.getId());
        assertEquals(boardBeforeUpdate.getName(), boardAfterUpdate.getName());
    }

    @Test
    public void testUpdateOfBoardNegative()
    {
        ResponseEntity<BoardDTO> createBoard = boardController.createBoard(new BoardDTO(null, getAlphaNumericString()));
        assertNotNull(createBoard);
        assertEquals(HttpStatus.OK, createBoard.getStatusCode());

        ResponseEntity<BoardDTO> foundBoardBeforeUpdate = boardController.getBoard(boardDTO.getId());
        assertNotNull(foundBoardBeforeUpdate);
        assertEquals(HttpStatus.OK, foundBoardBeforeUpdate.getStatusCode());

        BoardDTO boardBeforeUpdate = foundBoardBeforeUpdate.getBody();
        boardBeforeUpdate.setName(createBoard.getBody().getName());

        /**
         * Update board with an existing name
         */
        ResponseEntity<BoardDTO> requestUpdateBoard = boardController.updateBoard(boardBeforeUpdate.getId(), boardBeforeUpdate);
        assertEquals(HttpStatus.CONFLICT, requestUpdateBoard.getStatusCode());
    }
}