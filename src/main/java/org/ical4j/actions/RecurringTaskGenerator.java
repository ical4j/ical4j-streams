package org.ical4j.actions;

import net.fortuna.ical4j.model.Period;
import net.fortuna.ical4j.model.component.VToDo;
import net.fortuna.ical4j.model.property.LastModified;
import net.fortuna.ical4j.model.property.immutable.ImmutableStatus;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This generator produces occurrences of the specified recurring task within the
 * given period.
 */
public class RecurringTaskGenerator implements TaskGenerator<Period<Temporal>> {

    private final VToDo action;

    public RecurringTaskGenerator(VToDo action) {
        this.action = action;
    }

    public List<VToDo> generate(Trigger<Period<Temporal>> trigger) throws ParseException, IOException,
            URISyntaxException {

        return action.getOccurrences(trigger.getComponent()).stream()
                .peek(o -> o.add(ImmutableStatus.VTODO_NEEDS_ACTION)
                .add(new LastModified())).collect(Collectors.toList());
    }
}