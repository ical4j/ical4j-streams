package org.ical4j.streams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.concurrent.Flow;

/**
 * Support for subscribing to trigger publishers by invoking the associated handler.
 *
 * @param <T>
 */
public class RecordSubscriber<T extends Serializable> implements Flow.Subscriber<Record<T>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecordSubscriber.class);

    private final Trigger<T> trigger;

    private Flow.Subscription subscription;

    public RecordSubscriber(Trigger<T> trigger) {
        this.trigger = trigger;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        LOGGER.debug("Subscription initiated. {}", subscription);
        subscription.request(1);
    }

    @Override
    public void onNext(Record<T> item) {
        trigger.onRecord(item);
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
