module ical4j.actions.main {
    requires ical4j.core;
    requires ical4j.template;
    requires jakarta.mail;
    requires org.slf4j;

    exports org.ical4j.streams;
    exports org.ical4j.streams.workflow;
}