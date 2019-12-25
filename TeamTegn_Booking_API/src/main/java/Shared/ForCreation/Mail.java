package Shared.ForCreation;

import org.springframework.beans.factory.annotation.Value;

public class Mail {
    private String[] to;
    private int senderId;
    private String senderType;
    private String subject;
    private String content;
    @Value("${mail.username}")
    private String from;

    public Mail() {
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }


    public Mail(String[] to, String subject, String content, int senderId, String senderType) {
        this.to = to;
        this.senderId = senderId;
        this.senderType = senderType;
        this.subject = subject;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }


    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
