package com.jeet.quiz_service.dto;

import lombok.Data;
@Data
public class QuestionWrapper {

        private int id;

        private String category;

        private String difficultyLevel;

        private String option1;

        private String option2;

        private String option3;

        private String option4;

        private String questionTitle;

        private String rightAnswer;
}
