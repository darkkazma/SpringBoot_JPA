package com.darkkazma.board.dto;



import com.darkkazma.board.domain.entity.Board;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public Board toEntity(){
        Board build = Board.builder()
                .id(id)
                .writer(writer)
                .content(content)
                .title(title)
                .build();
        return build;
    }


    @Builder
    public BoardDto(Long id, String title, String content, String writer, LocalDateTime createdDate
    , LocalDateTime modifiedDate){
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content =content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;

    }
}
