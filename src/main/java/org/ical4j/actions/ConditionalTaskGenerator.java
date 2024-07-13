package org.ical4j.actions;

import net.fortuna.ical4j.model.component.VToDo;
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

    private final Predicate<T> predicate;

    private final AbstractTemplate<VToDo> template;

    public ConditionalTaskGenerator(Predicate<T> predicate, AbstractTemplate<VToDo> template) {
        this.predicate = predicate;
        this.template = template;
    }

    @Override
    public List<VToDo> generate(Trigger<T> trigger) {
        List<VToDo> tasks = new ArrayList<>();
        if (predicate.test(trigger.getComponent())) {
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
