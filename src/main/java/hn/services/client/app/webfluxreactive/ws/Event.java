package hn.services.client.app.webfluxreactive.ws;

import lombok.Data;

import java.time.Instant;

 @Data
public class Event{
    private Instant instant;
    private double value;
    private String societeID;
}
