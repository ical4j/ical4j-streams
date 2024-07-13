/*
 *  Copyright 2024 Node Logic
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ical4j.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.concurrent.Flow;

public interface TriggerHandler<T extends Serializable> {

    void onTrigger(Trigger<T> change);

    class TriggerSubscriber<T extends Serializable> implements Flow.Subscriber<Trigger<T>> {

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
}
