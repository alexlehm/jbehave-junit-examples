Very preliminary implementation of a junit runner that reports each
example as an individual test in the eclipse junit display.

This extends the AnnotatedPathRunner and implements a StoryReporter
that reports the success/failure to the Junit notifier, it should work
with the other AnnotatedPathRunners as well (guice, spring etc), I have
mostly tested it with the GuiceAnnotatedPathRunner.

Currently all tests show up as unrooted tests and it only works for
scenarios that actually use an example table, maybe I can fix this
later.

The StoryReporter implementation doesn't catch all possibilities, this
could be extended, but as a better solution I would prefer a reporter
or monitor that is triggered from inside the actual tests, not by
tracking the individual steps. An EmbedderMonitor doesn't work for
this either since it doesn't report the necessary events, maybe
something else could be implemented.



