
package BoardProject.demo.domain;

import BoardProject.demo.dto.BoardDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Check(constraints = "board_view>=0")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    //board:member=>n:1관계
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //board:answer=>1:n관계
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    @Column(name = "board_title",nullable = false)
    private String title;

    @Column(name = "board_content",length = 50000, nullable = false)
    private String content;

    @Column(name = "board_reward_token", nullable = false)
    private Long rewardToken;

    @Column(name = "board_view", nullable = false)
    private Long view;

    @Column(name = "board_updateTime", nullable = false)
    private Timestamp updateTime;


    //board:Attachment=> 1:n관계
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Attachment> attachedFiles = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.ALL)
    private GptAnswer gptAnswer;


    public static Board toBoard(BoardDTO boardDTO) {
        Board board = new Board();
        board.setId(boardDTO.getId());
        board.setMember(boardDTO.getMember());
        board.setAnswers(boardDTO.getAnswers());
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setRewardToken(boardDTO.getRewardToken());
        board.setView(boardDTO.getView());
        board.setUpdateTime(boardDTO.getUpdateTime());
        return board;
    }
}
