package com.bangtaoche.messagepush.client.exception;

public class MessagePushException extends RuntimeException {
    MessagePushException(String message){
        super(message);
    }

    MessagePushException(String message,Throwable throwable){
        super(message,throwable);
    }

    MessagePushException(Throwable throwable){
        super(throwable);
    }
}
