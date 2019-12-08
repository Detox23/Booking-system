package API.Configurations.Threads;

import Shared.ForCreation.EventLogDto;

import API.Services.LogsService.EventLogService;
import API.Services.LogsService.IEventLogService;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

public class CustomAsyncExceptionHandler
        implements AsyncUncaughtExceptionHandler {

    private EventLogService logService;

    @Autowired
    public void setLogService(EventLogService logService) {
        this.logService = logService;
    }

    @Override
    public void handleUncaughtException(
            Throwable throwable, Method method, Object... obj) {

        System.out.println("Exception message - " + throwable.getMessage());
        System.out.println("Method name - " + method.getName());
        logService.addLog(new EventLogDto(throwable.getMessage(), method.getName()));
        for (Object param : obj) {
            System.out.println("Parameter value - " + param);
        }
    }

}