package org.example.service;

import org.example.model.Card;
import org.example.model.Category;
import org.example.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService{
    private CardRepository cardRepository;
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void add(long categoryId, Card card) {
        Category category = categoryService.findById(categoryId);
        card.setCategory(category);
        try {
            this.cardRepository.save(card);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Такая катрочка уже есть в базе");
        }
    }

    @Override
    public Card get(long id) {
        return this.cardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Не удалось найти карту"));
    }

    @Override
    public List<Card> findAllByCategoryId(long categoryId) {
        return this.cardRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Card> findAllByUserId(long userId) {
        return this.cardRepository.findAllByUserId(userId);
    }

    @Override
    public Card update(Card card) {
        Card old = get(card.getId());
        old.setAnswer(card.getAnswer());
        old.setQuestion(card.getQuestion());
        try {
            this.cardRepository.save(old);
            return card;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Такая карта уже есть в базе");
        }
    }

    @Override
    public Card delete(long id) {
        Card card = get(id);
        this.cardRepository.deleteById(id);
        return card;
    }
}
