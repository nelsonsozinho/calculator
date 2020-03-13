package br.com.nms.calc.model;

import java.math.BigDecimal;

public class Result {

    private BigDecimal result;

    public Result(BigDecimal result) {
        this.result = result;
    }

    public BigDecimal getResult() {
        return result;
    }

}
