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

import java.io.Serializable;
import java.net.URI;
import java.time.Instant;

/**
 * A trigger encapsulates contextual data that may be relevant when evaluating
 * defined actions. A trigger includes the source of the event that lead to the
 * trigger being generated, the evaluation target, the type of event and a timestamp
 * indicating when the event occurred.
 *
 * @param <T> the evaluation target type
 */
public class Record<T extends Serializable> implements Serializable {

    private final URI context;

    private final T source;

    private final RecordType type;

    private final Instant timestamp;

    public Record(T source, RecordType type) {
        this(null, source, type);
    }

    public Record(URI context, T source, RecordType type) {
        this.context = context;
        this.source = source;
        this.type = type;
        this.timestamp = Instant.now();
    }

    public URI getContext() {
        return context;
    }

    public T getSource() {
        return source;
    }

    public RecordType getType() {
        return type;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
