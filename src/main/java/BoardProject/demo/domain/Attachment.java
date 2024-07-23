package BoardProject.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Attachment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "attachment_id")
  private Long id;

  @Column(nullable = false, name = "attachment_origin_file_name")
  private String originFilename;

  @Column(nullable = false, name = "attachment_store_file_name",unique = true)
  private String storeFilename;

  @Column(nullable = false, name = "attachment_file_path")
  private String filePath;

  @Column(nullable = false, name = "attachment_file_extension")
  private String fileExtension;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id")
  private Board board;


  @Builder
  public Attachment(String originFilename, String storePath, String filePath, String fileExtension, Board board) {
    this.originFilename = originFilename;
    this.storeFilename = storePath;
    this.filePath = filePath;
    this.fileExtension = fileExtension;
    this.board = board;
  }
  public Attachment(){

  }
}