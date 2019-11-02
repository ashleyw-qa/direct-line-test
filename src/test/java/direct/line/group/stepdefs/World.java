package direct.line.group.stepdefs;

import cucumber.runtime.java.guice.ScenarioScoped;

/*
A class used to store values used across Cucumber steps
 */
@ScenarioScoped
class World {
    String knownReg;
    String unKnownReg;
}