package org.ical4j.streams.workflow

import net.fortuna.ical4j.model.ContentBuilder
import net.fortuna.ical4j.model.Period
import net.fortuna.ical4j.model.component.VToDo
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDate

class RecurringTaskGeneratorTest extends Specification {

    @Shared
    VToDo action

    def setup() {
        action = new ContentBuilder().vtodo {
            dtstart '20240101T085000'
            summary 'Turn on the lights'
//            duration 'PT1H'
            rrule 'FREQ=WEEKLY;BYDAY=MO,TU,WE,TH,FR'
        }
    }

    def 'generate tasks for 2 weeks'() {
        given: 'a period of 2 weeks'
        Period<LocalDate> period = Period.parse('20240711/P2W')

        expect: '10 tasks are generated'
        def tasks = new RecurringTaskGenerator(action).generate(period)
        tasks.size() == 10
    }
}
