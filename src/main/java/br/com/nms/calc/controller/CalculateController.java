package br.com.nms.calc.controller;

import br.com.nms.calc.model.Operation;
import br.com.nms.calc.model.Result;

public interface CalculateController {

    Result add(Operation operation);

    Result subtract(Operation operation);

    Result multiply(Operation operation);

    Result divide(Operation operation);

}
