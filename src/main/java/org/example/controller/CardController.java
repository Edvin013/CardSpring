package org.example.controller;

import org.example.dto.ResponseResult;
import org.example.model.Card;
import org.example.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    private CardService cardService;

    @Autowired
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping(path = "/{categoryId}")
    public ResponseEntity<ResponseResult<Card>> add(@PathVariable long categoryId,
                                                    @RequestBody Card card) {
        try {
            this.cardService.add(categoryId, card);
            return new ResponseEntity<>(new ResponseResult<>(null, card), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Card>> get(@PathVariable long id) {
        try {
            Card card = this.cardService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, card), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path = "/search/{categoryId}")
    public ResponseEntity<ResponseResult<List<Card>>> getByCategoryId(
            @PathVariable long categoryId) {
        List<Card> cards = this.cardService.findAllByCategoryId(categoryId);
        return new ResponseEntity<>(new ResponseResult<>(null, cards), HttpStatus.OK);
    }

    @GetMapping(path = "/search/user/{userId}")
    public ResponseEntity<ResponseResult<List<Card>>> getByUserId(
            @PathVariable long userId) {
        List<Card> cards = this.cardService.findAllByUserId(userId);
        return new ResponseEntity<>(new ResponseResult<>(null, cards), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Card>> update(@RequestBody Card card) {
        try {
            Card res = this.cardService.update(card);
            return new ResponseEntity<>(new ResponseResult<>(null, res), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Card>> delete(@PathVariable long id) {
        try {
            Card card = this.cardService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, card), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
