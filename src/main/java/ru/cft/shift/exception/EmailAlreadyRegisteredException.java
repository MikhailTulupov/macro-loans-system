package ru.cft.shift.exception;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "email already registered")
public class EmailAlreadyRegisteredException extends Exception{
}
