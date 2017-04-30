package cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		 plugin = {"pretty","jason:target/"},
		 features = {"src/Cucumber/"}
		)
public class CucumberRunner2 {
	
}