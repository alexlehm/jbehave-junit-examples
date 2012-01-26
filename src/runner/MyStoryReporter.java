/*
 * please refer to the file LICENSE.txt
 */

 package runner;

import java.util.List;
import java.util.Map;

import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.model.GivenStories;
import org.jbehave.core.model.Meta;
import org.jbehave.core.model.Narrative;
import org.jbehave.core.model.OutcomesTable;
import org.jbehave.core.model.Scenario;
import org.jbehave.core.model.Story;
import org.jbehave.core.model.StoryDuration;
import org.jbehave.core.reporters.StoryReporter;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

public class MyStoryReporter implements StoryReporter {

  private static MyStoryReporter instance;
  
  private MyStoryReporter() {
    super();
  }
  
  public static MyStoryReporter getInstance() {
    if(instance==null) {
      instance=new MyStoryReporter();
    }
    return instance;
  }

  private RunNotifier notifier;

  private String inStory;
  private String inScenario;
  private boolean scenarioFailed;
  private String scenarioFailedDescription;
  private Throwable scenarioFailedTrace;

  private Class<?> testClass;
  
  private Description activeDescription;
  
  public static void registerRunNotifier(RunNotifier notifier) {
    getInstance().setNotifier(notifier);
  }
  
  private void setNotifier(RunNotifier notifier) {
    this.notifier=notifier;
  }

  public static void registerTestClass(Class<?> testClass) {
    getInstance().setTestClass(testClass);
  }

  private void setTestClass(Class<?> testClass) {
    this.testClass=testClass;
  }

  @Override
  public void storyNotAllowed(Story paramStory, String paramString) {
  }

  @Override
  public void storyCancelled(Story paramStory, StoryDuration paramStoryDuration) {
  }

  @Override
  public void beforeStory(Story paramStory, boolean paramBoolean) {
    log("beforeStory: "+paramStory.toString());
    inStory=paramStory.getPath();
  }

  @Override
  public void afterStory(boolean paramBoolean) {
    log("afterStory: ");
    inStory=null;
  }

  @Override
  public void narrative(Narrative paramNarrative) {
  }

  @Override
  public void scenarioNotAllowed(Scenario paramScenario, String paramString) {
  }

  @Override
  public void beforeScenario(String scenarioTitle) {
    log("beforeScenario: "+scenarioTitle);
    inScenario=scenarioTitle;
  }

  private void log(String string) {
//    System.out.println("***"+string);
  }

  @Override
  public void scenarioMeta(Meta paramMeta) {

  }

  @Override
  public void afterScenario() {
    log("afterScenario");
    inScenario=null;
  }

  @Override
  public void givenStories(GivenStories paramGivenStories) {
  }

  @Override
  public void givenStories(List<String> paramList) {
  }

  @Override
  public void beforeExamples(List<String> paramList,
      ExamplesTable paramExamplesTable) {
    log("beforeExamples: ");
  }

  @Override
  public void example(Map<String, String> paramMap) {
    log("example: "+paramMap.toString());
    finishPreviousScenario();
    Description desc=Description.createTestDescription(testClass!=null ? testClass : this.getClass(), (inScenario+": "+paramMap.toString()).replace('(', '[').replace(')', ']'));
    if(notifier!=null) {
      notifier.fireTestStarted(desc);
    }
    scenarioFailed=false;
    activeDescription=desc;
  }

  @Override
  public void afterExamples() {
    log("afterExamples: ");
    finishPreviousScenario();
  }

  /**
   * 
   */
  private void finishPreviousScenario() {
    log("finishPreviousScenario()");
    if(activeDescription!=null) {
      if(scenarioFailed) {
        log("scenarioFailed");
        // failureDescription doesn't work, apparently the description has to match for fireTestStarted and fireTestFailure
//        Description failureDescription= Description.createTestDescription(testClass, scenarioFailedDescription);
        if(notifier!=null) {
          notifier.fireTestFailure(new Failure(activeDescription, scenarioFailedTrace));
        }
      } else {
        log("scenarioSuccess");
        if(notifier!=null) {
          notifier.fireTestFinished(activeDescription);
        }
      }
      activeDescription=null;
    } else {
      log("activeDescription==null");
    }
  }

  @Override
  public void successful(String paramString) {
    log("successful()");
  }

  @Override
  public void ignorable(String paramString) {
    log("ignorable()");
  }

  @Override
  public void pending(String paramString) {
    log("pending()");
  }

  @Override
  public void notPerformed(String paramString) {
  }

  @Override
  public void failed(String paramString, Throwable paramThrowable) {
    log("failed()");
    scenarioFailed=true;
    scenarioFailedDescription=paramString;
    scenarioFailedTrace=paramThrowable;
  }

  @Override
  public void failedOutcomes(String paramString,
      OutcomesTable paramOutcomesTable) {

  }

  @Override
  public void restarted(String paramString, Throwable paramThrowable) {
  }

  @Override
  public void dryRun() {
  }

  @Override
  public void pendingMethods(List<String> paramList) {
  }

}
