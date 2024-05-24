package dev.manish.productservicemanish.controllers.advices;

import dev.manish.productservicemanish.dto.ExceptionDto;
import dev.manish.productservicemanish.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
//@ControllerAdvice(assignableTypes = {ProductControllerAdvice.class})// for specific controller
public class ProductControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    private ExceptionDto handleProductNotFoundException(ProductNotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setStatus("NOT_FOUND");

        return exceptionDto;
    }
}

/**
 * Above is Not mapped with any perticular controller so it is a Global advice. So any controller if it gets ProductNotFoundException
 * this will get trigger.
 *
 * We can specify controller name as well @ControllerAdvice("Name_Of_Controller") for the specific controller we want to run the advice
 * @ControllerAdvice(assignableTypes = {ProductControllerAdvice.class})
 *
 *
 *
 * AOP -> Aspect oriented programing
 */
