package org.ical4j.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

/**
 * Support for processing triggers to generate new streams.
 * @param <T>
 * @param <P>
 */
public class RecordProcessor<T, P> extends SubmissionPublisher<P> implements Flow.Processor<T, P> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecordProcessor.class);

    private final Function<T, List<P>> function;

    private Flow.Subscription subscription;

    public RecordProcessor(Function<T, List<P>> function) {
        this.function = function;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        function.apply(item).forEach(this::submit);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        LOGGER.error("Unexpected error", throwable);
    }

    @Override
    public void onComplete() {
        close();
    }
}
