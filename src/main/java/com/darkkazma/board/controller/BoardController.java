package com.darkkazma.board.controller;

import com.darkkazma.board.dto.BoardDto;
import com.darkkazma.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    // 생성자 주입 ( Constructor based Injection ) 방식
    // Field injection 이나  Setter based Injection 를 사용할 경우
    // NullPointException 방지, Field 의 final 선언 가능, 순환참조 방지등의 이점이 있음.
    private final BoardService boardService;
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    /** 초기화면 [존재하는 글의 목록을 가져온다. ] **/
    @GetMapping("/")
    public String list(Model model, @RequestParam(value="page", defaultValue="1") Integer pageNum){
        List<BoardDto> boardDtoList = boardService.getBoardlist(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardDtoList);
        model.addAttribute("pageList", pageList);
        return "board/list.html";
    }

    @GetMapping("/post")
    public String write(){
        return "board/write.html";
    }


    @PostMapping("/post")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long id, Model model){

        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("boardDto", boardDto);

        return "board/detail.html";
    }


    /* 수정 버튼 선택 시   Action */
    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);

        model.addAttribute("boardDto", boardDto);
        return "board/update.html";
    }

    /* 수정 완료 버튼 선택 시 Action */
    @PutMapping("/post/edit/{no}")
    public String update(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    /* 게시글 삭제 Action */
    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long id){
        boardService.deletePost(id);
        return "redirect:/";
    }


    @GetMapping("/board/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model){
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);
        model.addAttribute("boardList", boardDtoList);

        return "/board/list.html";
    }
}
