package gov.ca.cwds.bre.services.aop;

import java.time.Duration;
import java.time.Instant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import gov.ca.cwds.bre.interfaces.model.BreResponse;

/**
 * @author CWDS Team J
 */

@Aspect
@Component
public class ExecutionTimerAspect {

  private final static Logger LOGGER = LoggerFactory.getLogger(ExecutionTimerAspect.class);
  
  @Around("@annotation(ExecutionTimer)")
  public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    Instant starTime = Instant.now();
    Object obj = joinPoint.proceed();
    Instant endTime = Instant.now();
    long durationMillis = Duration.between(starTime, endTime).toMillis();
    
    if (obj instanceof BreResponse) {
      ((BreResponse) obj).setExecutionTimeMillis(durationMillis);
    }
    
    LOGGER.info("Took {} milli seconds to: {} ", durationMillis, joinPoint.toLongString());
    
    return obj;
  }
}
