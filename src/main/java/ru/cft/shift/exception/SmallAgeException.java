package ru.cft.shift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "user age below 18")
public class SmallAgeException extends Exception{
}
