package org.ical4j.actions.workflow;

import net.fortuna.ical4j.model.component.VToDo;
import org.ical4j.actions.Record;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

/**
 * Implementors respond to input triggers by generating zero or more tasks.
 *
 * @param <T> a trigger instance that may be evaluated to determine generation of
 *           tasks.
 *
 * Example factors that may be considered in generating tasks include:
 *  - the current time, for recurring tasks
 *  - workflow triggers
 */
public interface TaskGenerator<T extends Serializable> {

    List<VToDo> generate(Record<T> record) throws ParseException, IOException, URISyntaxException;
}
