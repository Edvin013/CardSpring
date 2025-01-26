package org.example.service;

import org.example.model.Card;

import java.util.List;

public interface CardService {
    void add(long categoryId, Card card);

    List<Card> findAllByCategoryId(long categoryId);

    List<Card> findAllByUserId(long userId);

    Card get(long id);

    Card delete(long id);

    Card update(Card card);
}
