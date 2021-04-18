package io.kimmking.kmq.core;

import java.util.ArrayList;

/**
 * @author deguang
 * @date 2021/04/18
 */

public class MessageQueue {

    private ArrayList<KmqMessage> mqMessages;

    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
        this.mqMessages = new ArrayList<>(capacity);
    }

    public <T> void write(KmqMessage<T> mqMessage, int offset) {
        if (offset >= capacity) {
            throw new RuntimeException("offset out of range");
        }
        mqMessages.add(offset, mqMessage);
    }

    public <T> KmqMessage read(int offset) {
        if (offset < capacity) {
            return mqMessages.get(offset);
        }
        return null;
    }
}
