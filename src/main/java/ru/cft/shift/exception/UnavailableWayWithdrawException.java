package ru.cft.shift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "unavailable way to withdraw money")
public class UnavailableWayWithdrawException extends Exception{
}
