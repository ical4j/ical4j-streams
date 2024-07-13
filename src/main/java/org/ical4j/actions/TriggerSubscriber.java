package org.ical4j.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.concurrent.Flow;

public class TriggerSubscriber<T extends Serializable> implements Flow.Subscriber<Trigger<T>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TriggerSubscriber.class);

    private final TriggerHandler<T> handler;

    private Flow.Subscription subscription;

    public TriggerSubscriber(TriggerHandler<T> handler) {
        this.handler = handler;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        LOGGER.debug("Subscription initiated. {}", subscription);
        subscription.request(1);
    }

    @Override
    public void onNext(Trigger<T> item) {
        handler.onTrigger(item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        LOGGER.error("Unexpected error", throwable);
    }

    @Override
    public void onComplete() {
        LOGGER.debug("Subscription complete. {}", subscription);
        this.notify();
    }
}
