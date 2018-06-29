package com.falcon.demo.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "message")
@Repository
public class Message {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Id
    @Column(name = "message", nullable = false)
    private String message;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }


}
