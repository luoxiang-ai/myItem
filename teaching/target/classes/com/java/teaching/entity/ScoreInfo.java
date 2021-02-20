package com.java.teaching.entity;

import java.math.BigDecimal;

public class ScoreInfo extends ScoreInfoKey {
    private BigDecimal score;

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}