package BoardProject.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class Quest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "quest_id")
  private Long id;

  //board:member=>n:1관계
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @Column(name = "quest_day",nullable = false)
  private LocalDate day;

  @Column(name = "quest_adoption", nullable = false)
  private boolean adoption;

  @Column(name = "quest_create_board", nullable = false)
  private boolean creatBoard;

  @Column(name = "quest_create_answer",nullable = false)
  private boolean creatAnswer;
  @Builder
  public Quest(Member member){
    this.member = member;
    this.day = LocalDate.now();
    this.adoption = false;
    this.creatBoard = false;
    this.creatAnswer = false;
  }

  public Quest() {
  }
}
