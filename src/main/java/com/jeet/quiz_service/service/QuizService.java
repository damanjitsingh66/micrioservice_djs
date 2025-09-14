package com.jeet.quiz_service.service;


import com.jeet.quiz_service.dto.QuestionWrapper;
import com.jeet.quiz_service.dto.Response;
import com.jeet.quiz_service.feign.QuizInterface;
import com.jeet.quiz_service.model.Quiz;
import com.jeet.quiz_service.repository.QuizRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    public QuizRepository quizRepository;

    @Autowired
    public QuizInterface quizInterface;



    public ResponseEntity<Quiz> createQuiz(String category, int numq, String title){

        List<Integer> questionIds = quizInterface.getQuestionForQuiz(category,numq).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);
        quizRepository.save(quiz);

//       if(!questions.isEmpty()) {
//           Quiz newQuiz = new Quiz();
//           newQuiz.setTitle(title);
//           newQuiz.setQuestions(questions);
//           Quiz savedQuiz =quizRepository.save(newQuiz);
//           return new ResponseEntity<>(savedQuiz,HttpStatus.OK);
//       }
//        else{
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//       }
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(int id){
        Optional<Quiz> quizOptional = quizRepository.findById(id);
        List<QuestionWrapper> response;
            Quiz quiz = quizOptional.get();
            ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromIds(quiz.getQuestionIds());
              response=questions.getBody();
            return new ResponseEntity<>(response,HttpStatus.OK);

    }

    public ResponseEntity<Integer> getCalculateResult(Integer id, List<Response> responses){
        return quizInterface.getScore(responses);
    }

}
