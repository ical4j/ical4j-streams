# ical4j-scheduler
A service for automating schedule management and creation

## Summary
Managing schedules across multiple CUAs can be complex and time-consuming, even when using sophisticated software such as Microsoft Outlook or on of the many capable CalDAV implementations. The ical4j-scheduler aims to provide functions that can automate some of the time-consuming manual aspects of schedule management.

## Features
* Automatic resource allocation - a common use-case for scheduling software is allocation of resources, such as meeting rooms, for an event. Instead of manually searching for an available resource, just include the ical4j-scheduler as an attendee and it will provide a counter-invitation that includes an appropriate resource. Resources may be allocated intelligently using event event properties such as number of attendees, location, etc. to allocate the most appropriate available resource.
* Automated conflict resolution - often resources and people may have conflicts in their schedule that prevent them from being available for simultaneous events. The ical4j-scheduler can offer to resolve such conflicts whilst avoiding new conflicts based on the free/busy availability of the resources and people in question. One or more counter-invitations will be provided to the events managed (attended) by the ical4j-scheduler that offer a solution to the conflict. This can also be useful for constructing recurring timetables for many people and resources.
