package org.ical4j.actions.workflow


import org.ical4j.template.groupware.Action
import spock.lang.Specification

class ConditionalTaskGeneratorTest extends Specification {

    def 'test meeting generator'() {
        given: 'an action template'
        Action template = []

        when: 'conditional task generator is invoked'
        def tasks = new ConditionalTaskGenerator((bool) -> bool, template).generate(true)

        then: 'tasks are generated as expected'
        tasks.size() == 1
    }
}
