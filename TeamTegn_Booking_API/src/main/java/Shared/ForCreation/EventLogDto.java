package Shared.ForCreation;



public class EventLogDto {
    public EventLogDto(String exceptionMessage, String stackTrace) {
        this.exceptionMessage = exceptionMessage;
        this.stackTrace = stackTrace;
    }

    private String exceptionMessage;
    private String stackTrace;

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
