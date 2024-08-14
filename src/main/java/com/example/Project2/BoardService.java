package com.example.Project2;

import com.example.Project2.model.Board;
import com.example.Project2.repo.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepo;
    public BoardService(BoardRepository boardRepo) {
        this.boardRepo = boardRepo;
    }

    // READ ONE
    public Board readOne(Long id) {
        Board board = boardRepo.findById(id).orElseThrow();
        return board;
    }

    // READ ALL
    public List<Board> readAll() {
        return boardRepo.findAll();
    }



}
