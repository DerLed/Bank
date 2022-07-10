package ru.lebedev.bank.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.lebedev.bank.domain.account.AccountService;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {

    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @After("execution(* ru.lebedev.bank.domain.account.AccountServiceImpl.transferMoneyBy*(..))")
    public void logCountTransfer(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature()
                .getName();



        logger.log(Level.INFO, "FFFFFFFFFF: " + methodName);
    }

}
