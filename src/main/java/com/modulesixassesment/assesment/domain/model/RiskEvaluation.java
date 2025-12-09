package com.modulesixassesment.assesment.domain.model;

import com.modulesixassesment.assesment.domain.enums.RiskLevel;

public class RiskEvaluation {
    private int score;
    private RiskLevel level;
    private String detail;
    private String motive;

    public RiskEvaluation (){}

    public RiskEvaluation(int score, RiskLevel level, String detail, String motive) {
        this.score = score;
        this.level = level;
        this.detail = detail;
        this.motive = motive;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public RiskLevel getLevel() {
        return level;
    }

    public void setLevel(RiskLevel level) {
        this.level = level;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }
}
