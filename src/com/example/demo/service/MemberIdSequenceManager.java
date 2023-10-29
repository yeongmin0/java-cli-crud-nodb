package com.example.demo.service;

public final class MemberIdSequenceManager {
    private long sequence;

    public MemberIdSequenceManager() {}

    public MemberIdSequenceManager(long startValue) {
        this.sequence = startValue;
    }

    public long increaseAndGetId() {
        return ++sequence;
    }
}
