package br.com.nms.calc.controller.rest;

import br.com.nms.calc.controller.CalculateController;
import br.com.nms.calc.model.Operation;
import br.com.nms.calc.model.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/calc")
public class CalculateControllerRest implements CalculateController {

    @Override
    @PostMapping("/add")
    public Result add(@RequestBody Operation operation) {
        return new Result(operation.getNum1().add(operation.getNum2()));
    }

    @Override
    @PostMapping("/subtract")
    public Result subtract(@RequestBody Operation operation) {
        return new Result(operation.getNum1().subtract(operation.getNum2()));
    }

    @Override
    @PostMapping("/multiply")
    public Result multiply(@RequestBody Operation operation) {
        return new Result(operation.getNum1().multiply(operation.getNum2()));
    }

    @Override
    @PostMapping("/divide")
    public Result divide(@RequestBody Operation operation) {
        try {
            return new Result(operation.getNum1().divide(operation.getNum2()));
        } catch(ArithmeticException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Impossibile divide by 0", ex);
        }
    }
}
