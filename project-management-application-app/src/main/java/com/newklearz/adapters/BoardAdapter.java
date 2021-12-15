package com.newklearz.adapters;

import java.util.List;
import java.util.stream.Collectors;

import com.newklearz.DTO.BoardDTO;
import com.newklearz.repository.board.Board;

public class BoardAdapter
{
    public static BoardDTO toDTO(Board board)
    {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(board.getId());
        boardDTO.setName(board.getName());
        return boardDTO;
    }

    public static Board toEntity(BoardDTO boardDTO)
    {
        Board board = new Board();
        if (boardDTO != null)
        {
            board.setId(boardDTO.getId());
            board.setName(boardDTO.getName());
        }
        else
        {
            return new Board();
        }
        return board;
    }

    public static List<BoardDTO> toDTOList(List<Board> boards)
    {
        return boards
            .stream().map(BoardAdapter::toDTO)
            .collect(Collectors.toList());
    }

    public static List<Board> toEntityList(List<BoardDTO> boardDTOList)
    {
        return boardDTOList
            .stream()
            .map(BoardAdapter::toEntity)
            .collect(Collectors.toList());
    }
}