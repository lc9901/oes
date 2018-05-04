package com.asher.oes.model;

import java.util.Date;

import com.asher.oes.enums.QuestionEnum;

public class Question {
    private int id;
    private QuestionEnum questionEnum;
    private int userId;
    private String description;
    private String answer;
    private Date createTime;
    private Date updateTime;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuestionEnum getQuestionEnum() {
        return questionEnum;
    }

    public void setQuestionEnum(QuestionEnum questionEnum) {
        this.questionEnum = questionEnum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", questionEnum=" + questionEnum + ", userId=" + userId + ", description="
                + description + ", answer=" + answer + ", createTime=" + createTime + ", updateTime=" + updateTime
                + ", optionA=" + optionA + ", optionB=" + optionB + ", optionC=" + optionC + ", optionD=" + optionD
                + "]";
    }
}