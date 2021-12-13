package com.newklearz.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newklearz.DTO.BoardDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Board")
public interface BoardResource
{
    String BOARD_COMMON_PREFIX = "/api/v1/boards";

    @ApiOperation("Retrieves all boards")
    @ResponseBody
    @GetMapping(BOARD_COMMON_PREFIX)
    ResponseEntity<List<BoardDTO>> getBoards();

    @ApiOperation("Retrieves a board")
    @ResponseBody
    @GetMapping(BOARD_COMMON_PREFIX + "/{id}")
    ResponseEntity<BoardDTO> getBoard(@PathVariable("id") Integer id);

    @ApiOperation("Creates a board")
    @ResponseBody
    @PostMapping(BOARD_COMMON_PREFIX)
    ResponseEntity<BoardDTO> createBoard(@RequestBody BoardDTO boardDTO);

    @ApiOperation("Updates a board")
    @ResponseBody
    @PutMapping(BOARD_COMMON_PREFIX + "/{id}")
    ResponseEntity<BoardDTO> updateBoard(@PathVariable("id") Integer id, @RequestBody BoardDTO boardDTO);
}