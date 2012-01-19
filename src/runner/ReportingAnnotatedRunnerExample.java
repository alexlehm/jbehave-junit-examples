package runner;

import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.UsingPaths;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.reporters.StoryReporterBuilder;
import runner.ReportingAnnotatedRunnerExample.MyReportBuilder;
import runner.ReportingAnnotatedRunnerExample.MyStoryControls;
import runner.ReportingAnnotatedRunnerExample.MyStoryLoader;
import steps.ExampleSteps;

import org.junit.runner.RunWith;

import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;

@RunWith(ReportingAnnotatedPathRunner.class)
@Configure( storyControls = MyStoryControls.class, storyLoader = MyStoryLoader.class, storyReporterBuilder = MyReportBuilder.class )
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true, ignoreFailureInStories = true, ignoreFailureInView = true,
                storyTimeoutInSecs = 100, threads = 1, metaFilters = "-skip" )
@UsingSteps(instances = { ExampleSteps.class })
@UsingPaths(searchIn = "src", includes = { "**/*.story" }, excludes = { "**/examples_table*.story" })
public class ReportingAnnotatedRunnerExample {

    public static class MyStoryControls extends StoryControls {
        public MyStoryControls() {
            doDryRun(false);
            doSkipScenariosAfterFailure(false);
        }
    }

    public static class MyStoryLoader extends LoadFromClasspath {
        public MyStoryLoader() {
            super(ReportingAnnotatedRunnerExample.class.getClassLoader());
        }
    }

    public static class MyReportBuilder extends StoryReporterBuilder {
        public MyReportBuilder() {
            this.withFormats(CONSOLE, TXT, HTML, XML).withDefaultFormats().withReporters(MyStoryReporter.getInstance());
        }
    }

}
