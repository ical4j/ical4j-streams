package org.ical4j.actions.workflow;

import net.fortuna.ical4j.model.Period;
import net.fortuna.ical4j.model.component.VToDo;
import org.ical4j.actions.Record;
import org.ical4j.actions.Trigger;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class WorkflowProcessor<T extends Serializable> implements Trigger<T> {

    private final List<VToDo> actions = new CopyOnWriteArrayList<>();

    public void addAction(VToDo action) {
        this.actions.add(action);
    }

    public boolean removeAction(VToDo action) {
        return this.actions.remove(action);
    }

    @Override
    public void onRecord(Record<T> record) {
        List<VToDo> result;
        switch (record.getType()) {
            case Timer:
                result = actions.stream().map(action -> {
                    try {
                        return new RecurringTaskGenerator(action).generate((Record<Period<Temporal>>) record);
                    } catch (ParseException | IOException | URISyntaxException e) {
                        return new ArrayList<VToDo>();
                    }
                }).flatMap(Collection::stream).collect(Collectors.toList());
            default:
                result = actions.stream().map(action -> new ConditionalTaskGenerator<T>(action).generate(record))
                        .flatMap(Collection::stream).collect(Collectors.toList());
        }
        //xxx: process resulting tasks..
    }
}
