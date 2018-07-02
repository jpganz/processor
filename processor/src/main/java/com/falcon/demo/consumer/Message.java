package com.falcon.demo.consumer;

import java.io.Serializable;
import java.time.Instant;

public class Message implements Serializable {


    private String message;

    private Instant created;

    public Message() {
        //requiere by jpa
    }

    public Message(final String message, final Instant created) {
        this.message = message;
        this.created = created;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(final Instant created) {
        this.created = created;
    }
}
