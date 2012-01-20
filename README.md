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

Please note that I currently use Ivy for Eclipse to build my
projects, this will work with maven or anything else as well, you
just need the two classes MyStoryReporter and
ReportingAnnotatedPathRunner in the same directory as your
StoriesRunner. If you are using a DI framework, you have to change
the parent class for ReportingAnnotatedPathRunner to e.g.
GuiceAnnotatedPathRunner, everything else in the class should be the
same for all frameworks (have only tested that with Guice yet). To
connect the reporter to the StoryReporterBuilder only
.withReporters(MyStoryReporter.getInstance()) is necesary (where
this shows up depends on the DI framework again).

This project currently lives at
https://github.com/alexlehm/jbehave-junit-examples

Please refer to the file LICENSE.txt (files authored by me) or to
LICENSE-jbehave.txt (files changed by me) both licenses are identical
except for the author name.

If you have any questions, please send me a mail to alexlehm(at)gmail.com

