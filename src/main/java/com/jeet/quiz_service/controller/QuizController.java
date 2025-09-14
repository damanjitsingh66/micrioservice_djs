package com.jeet.quiz_service.controller;

import com.jeet.quiz_service.dto.QuestionWrapper;
import com.jeet.quiz_service.dto.Response;
import com.jeet.quiz_service.model.Quiz;
import com.jeet.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestParam String category, @RequestParam int numq, @RequestParam String title) {
        return quizService.createQuiz(category, numq, title);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id) {
        return quizService.getQuizQuestionsById(id);
     }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> getCalculatedResult(@PathVariable Integer id,@RequestBody List<Response> responses){
      return quizService.getCalculateResult(id,responses);
    }
}
