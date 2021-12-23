package com.newklearz.controllers;

import static com.newklearz.controllers.Utils.getAlphaNumericString;
import static com.newklearz.controllers.Utils.getMoreChars;
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
        ResponseEntity<BoardDTO> boardNegativeID = boardController.getBoard(Integer.MAX_VALUE + 3488799);
        assertEquals(HttpStatus.BAD_REQUEST, boardNegativeID.getStatusCode());

        /**
         * Retrieve board with an in-existent id
         */
        ResponseEntity<BoardDTO> boardInexistentId = boardController.getBoard(213123123);
        assertEquals(HttpStatus.NOT_FOUND, boardInexistentId.getStatusCode());

        /**
         * Retrieve board with id value of zero
         */
        ResponseEntity<BoardDTO> boardZeroId = boardController.getBoard(0);
        assertEquals(HttpStatus.BAD_REQUEST, boardZeroId.getStatusCode());

        /**
         * Retrieve board with alphanumeric id value
         */
        ResponseEntity<BoardDTO> boardAlphaNumericId = boardController.getBoard(Integer.parseInt("asdaakjshd123sah"));
        assertEquals(HttpStatus.BAD_REQUEST, boardAlphaNumericId);
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
        ResponseEntity<BoardDTO> testBoardNullName = boardController.createBoard(new BoardDTO(null, null));
        assertEquals(HttpStatus.BAD_REQUEST, testBoardNullName.getStatusCode());

        /**
         * Create board with empty string name
         */
        ResponseEntity<BoardDTO> testEmptyStringName = boardController.createBoard(new BoardDTO(null, ""));
        assertEquals(HttpStatus.BAD_REQUEST, testEmptyStringName.getStatusCode());

        /**
         * Create board with blank space name
         */
        ResponseEntity<BoardDTO> testBlankSpaceName = boardController.createBoard(new BoardDTO(null, " "));
        assertEquals(HttpStatus.BAD_REQUEST, testBlankSpaceName.getStatusCode());
        /**
         * Create board with same name as an existing one
         */
        ResponseEntity<BoardDTO> testSameName = boardController.createBoard(new BoardDTO(null, boardDTO.getName()));
        assertEquals(HttpStatus.CONFLICT, testSameName.getStatusCode());

        /**
         * Create board with more than 100 characters in name
         */
        ResponseEntity<BoardDTO> testMoreCharsName = boardController.createBoard(new BoardDTO(null, getMoreChars()));
        assertEquals(HttpStatus.BAD_REQUEST, testMoreCharsName.getStatusCode());
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
        ResponseEntity<BoardDTO> createBoard = boardController.createBoard(new BoardDTO(null, "abc"));
        assertNotNull(createBoard);
        assertEquals(HttpStatus.OK, createBoard.getStatusCode());

        ResponseEntity<BoardDTO> foundBoardBeforeUpdate = boardController.getBoard(boardDTO.getId());
        assertNotNull(foundBoardBeforeUpdate);
        assertEquals(HttpStatus.OK, foundBoardBeforeUpdate.getStatusCode());

        BoardDTO boardBeforeUpdate = foundBoardBeforeUpdate.getBody();
        boardBeforeUpdate.setName("abc");

        /**
         * Update board with an existing name
         */
        ResponseEntity<BoardDTO> requestUpdateBoard = boardController.updateBoard(boardBeforeUpdate.getId(), boardBeforeUpdate);
        assertEquals(HttpStatus.CONFLICT, requestUpdateBoard.getStatusCode());
    }
}