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

import java.net.URI;

/**
 * Indicate the type of event that lead to a trigger being generated. For example
 * adding a new component to a collection, receiving a new calendar from an
 * external source, and modifying an existing component are all events that
 * can generate a trigger.
 */
public enum TriggerType {
    Timer("semcal:trigger:timer", "Timer trigger", ""),

    // created, deleted, updated, published,
    Created("coucal:change:published", "", ""),

    Updated("coucal:change:published", "", ""),

    Deleted("coucal:change:published", "", ""),

    Published("coucal:change:published", "", ""),

    EventCreated("coucal:message:event:created", "Event Created", ""),

    RequestCreated("coucal:message:request:created", "Request Created", ""),

    EventUpdated("coucal:message:event:updated", "Event Updated", ""),

    EventDeleted("coucal:message:event:deleted", "Event Deleted", "");

    private final URI uri;

    private final String name;

    private final String description;

    TriggerType(String uri, String name, String description) {
        this.uri = URI.create(uri);
        this.name = name;
        this.description = description;
    }

    public URI getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
