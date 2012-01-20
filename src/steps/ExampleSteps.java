/*
 * please refer to the file LICENSE.txt
 */

package steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;

import static org.junit.Assert.*;

public class ExampleSteps {

  @Given("a string starting with a: <string>")
  public void givenAStringStartingWithAstring(@Named("string") String string) {
    assertTrue(string.startsWith("a"));
  }

}
