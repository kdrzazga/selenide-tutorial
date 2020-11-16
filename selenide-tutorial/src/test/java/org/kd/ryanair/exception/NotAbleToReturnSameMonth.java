package org.kd.ryanair.exception;

public class NotAbleToReturnSameMonth extends RuntimeException {

    public NotAbleToReturnSameMonth(){
        super("Cannot end test. Return flight not available same month as departure flight.");
    }

}
