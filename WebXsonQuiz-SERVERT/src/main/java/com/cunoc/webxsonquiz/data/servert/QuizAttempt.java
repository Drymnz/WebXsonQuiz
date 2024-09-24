package com.cunoc.webxsonquiz.data.servert;

import java.io.Serializable;

public class QuizAttempt implements Serializable {

    private static final long serialVersionUID = 4L;

    private String user;
    private String quizId;
    private int responseTime;
    private int score;

    public QuizAttempt(String user, String quizId, int responseTime, int score) {
        this.user = user;
        this.quizId = quizId;
        this.responseTime = responseTime;
        this.score = score;
    }

    public String getUser() {
        return user;
    }

    public String getQuizId() {
        return quizId;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public int getScore() {
        return score;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "QuizAttempt{" +
               "user='" + user + '\'' +
               ", quizId='" + quizId + '\'' +
               ", responseTime=" + responseTime +
               ", score=" + score +
               '}';
    }
}
