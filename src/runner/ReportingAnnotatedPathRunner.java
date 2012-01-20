/*
 * please refer to the file LICENSE.txt
 */

 package runner;

import org.jbehave.core.junit.AnnotatedPathRunner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

public class ReportingAnnotatedPathRunner extends AnnotatedPathRunner {

  private Class<?> testClass;
  
  public ReportingAnnotatedPathRunner(Class<?> testClass)
      throws InitializationError {
    super(testClass);
    this.testClass=testClass;
  }

  @Override
  public void run(RunNotifier notifier) {
    MyStoryReporter.registerRunNotifier(notifier);
    MyStoryReporter.registerTestClass(testClass);
    super.run(notifier);
  }
  
}
