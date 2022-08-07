package com.dearme.demo.domain.board.service;

import com.dearme.demo.domain.board.dto.board.*;
import com.dearme.demo.domain.board.dto.comment.CommentResponseDto;
import com.dearme.demo.domain.board.entity.Board;
import com.dearme.demo.domain.board.entity.Comment;
import com.dearme.demo.domain.board.exception.board.NoBoardDeletePermissionException;
import com.dearme.demo.domain.board.exception.board.NoBoardSavePermissionException;
import com.dearme.demo.domain.board.exception.board.NoBoardUpdatePermissionException;
import com.dearme.demo.domain.board.exception.board.NoExistBoardException;
import com.dearme.demo.domain.board.repository.BoardRepository;
import com.dearme.demo.domain.board.repository.CommentRepository;
import com.dearme.demo.domain.user.entity.Type;
import com.dearme.demo.domain.user.entity.User;
import com.dearme.demo.domain.user.exception.NoExistUserException;
import com.dearme.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    @Override
    @Transactional
    public BoardSaveResponseDto boardSave(String id, BoardSaveRequestDto dto){

        Board board;
        board = dto.toBoardEntity();
        User user = userRepository.findUserById(id).orElseThrow(() -> {
            throw new NoExistUserException();
        });
        if(user.getType().equals(Type.USER)){
            board.setUser(user);
            boardRepository.save(board);
            return new BoardSaveResponseDto(board.getBoardid());
        }else{
            throw new NoBoardSavePermissionException();
        }
    }
    @Override
    public Page<BoardsViewResponseDto> getBoards(PageRequest pageRequest){
        Page<Board> page = boardRepository.findAll(pageRequest);

        Page<BoardsViewResponseDto> map = page.map(board -> new BoardsViewResponseDto(board.getBoardid(),
                board.getUser().getNickName(),
                board.getTitle(),
                board.getHitcnt(),
                board.getTitle()));

        return map;
    }
    @Transactional
    public BoardViewResponseDto getBoard(Long boardid){
        Board board= boardRepository.findBoardByBoardid(boardid).orElseThrow(()->{
            throw new NoExistBoardException();
        });
        List<Comment> temp = board.getComments();

        board.updateHitCnt();
        List<CommentResponseDto> commentResponseDto = new ArrayList<>();
        for(Comment c: temp){
            commentResponseDto.add(new CommentResponseDto(c.getUser().getNickName(),
                    c.getDate(),
                    c.getContents()));
        }

        return new BoardViewResponseDto(board.getBoardid(),
                board.getUser().getNickName(),
                board.getTitle(),
                board.getContents(),
                board.getHitcnt(),
                board.getDate(),
                commentResponseDto);
    }

    @Transactional
    public BoardUpdateResponseDto updateBoard(String id, Long boardid, BoardUpdateRequestDto dto) {


        Board board = boardRepository.findBoardByBoardid(boardid).orElseThrow(()->{
            throw new NoExistBoardException();
        });
        User user = userRepository.findUserById(id).orElseThrow(() -> {
            throw new NoExistUserException();
        });
        if(user.getUserId().equals(board.getUser().getUserId())){
            board.update(dto.getTitle(), dto.getContents(), dto.getDate());
            return new BoardUpdateResponseDto(board.getBoardid());
        }else{
            throw new NoBoardUpdatePermissionException();
        }
    }

    @Transactional
    public void deleteBoard(String id, Long boardid) {
        Board board = boardRepository.findBoardByBoardid(boardid).orElseThrow(()->{
            throw new NoExistBoardException();
        });
        User user = userRepository.findUserById(id).orElseThrow(() -> {
            throw new NoExistUserException();
        });
        if(user.getUserId().equals(board.getUser().getUserId())){
            boardRepository.delete(board);
        }else{
            throw new NoBoardDeletePermissionException();
        }
    }


}