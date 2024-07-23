package BoardProject.demo.repository;

import BoardProject.demo.domain.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface QuestRepository extends JpaRepository<Quest, String> {
    @Query("SELECT q FROM Quest q WHERE q.member.id = :memberId AND q.day = :today")
    Optional<Quest> findByMemberIdAndDay(@Param("memberId") String memberId, @Param("today") LocalDate today);

    @Query("SELECT q FROM Quest q WHERE q.member.id = :memberId")
    List<Quest> findAllByMemberId(@Param("memberId") String memberId);

}
