package org.example.repository;

import org.example.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByCategoryId(long categoryId);

    @Modifying
    @Query("SELECT c FROM Card c WHERE c.category.user.id = :userId")
    List<Card> findAllByUserId(@Param("userId") long userId);
}
