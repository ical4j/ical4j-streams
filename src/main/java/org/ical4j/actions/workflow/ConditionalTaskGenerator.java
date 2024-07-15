package org.ical4j.actions.workflow;

import net.fortuna.ical4j.model.component.VToDo;
import org.ical4j.actions.Record;
import org.ical4j.template.AbstractTemplate;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * This generator produces an instance of the given task template when the specified
 * condition for the trigger is true.
 *
 * @param <T> the trigger type evaluated
 */
public class ConditionalTaskGenerator<T extends Serializable> implements TaskGenerator<T> {

    private Predicate<T> predicate;

    private AbstractTemplate<VToDo> template;

    public ConditionalTaskGenerator(Predicate<T> predicate, AbstractTemplate<VToDo> template) {
        this.predicate = predicate;
        this.template = template;
    }

    public ConditionalTaskGenerator(VToDo action) {

    }

    @Override
    public List<VToDo> generate(Record<T> record) {
        List<VToDo> tasks = new ArrayList<>();
        if (predicate.test(record.getSource())) {
            try {
                tasks.add(template.apply());
            } catch (NoSuchMethodException | InvocationTargetException |
                     InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return tasks;
    }
}
