# vdc-automation

VDC Automation test framework support for the automation for web UI.

### Technical

This framework uses a number of open source projects to work properly:

* [Cucumber] - BDD style support for testing on Java!
* [JUnit 4&5] - Test runner tool of Java
* [Selenium] - Library use for interacting with Web Browser.
* [WebDriverManager] - Controlling the driver for each browser in the machine
* [Owner] - Handle application configuration via Java properties files
* [AssertJ] - Fluent assertions for java
* [Custom Page Factory] - Core custom functional and modified the library to control the test run
  and Page Object Initialization

### Environment

* Java & JDK 11
* Maven
* Docker

### Installation

Pull the code via git command to your local:

```sh
$ git clone https://github.com/luckhiem/vdc-automation.git
```

### Running

#### Local

**For Cucumber Test**

```sh
$ mvn clean -Dtest="TestRunner" test -Denv=[environment] "-Dcucumber.options=--tags \"[tagName]\"" 
```

Example:

```sh
$ mvn clean verify -Dtest="TestRunner" -Denv=local -Dcucumber.options="--tags @test" test
```

[tagName] = @ tag name e.g. @test...

| Variable Name | Meaning                                                        | Available Options               |
|---------------|----------------------------------------------------------------|--------------------------       |
| env           | The environment that test will be run                          | local, remote                   |

#### Multiple browser

The framework using [Selenium Grid] run inside docker, to running with multiple browser, please
follow steps below:

1. Start Selenium Grid in docker

```sh
$ docker compose -f ./docker-compose.yml up -d
```

2. Run test by input the browser that want to test (Available: `chrome`, `firefox`, `edge`)

```sh
$ mvn clean verify -Dtest="TestRunner" -Denv=remote -Dbrowser=firefox -Dcucumber.options="--tags @test" test
```

3. After finish run test, stop the Selenium Grid

```sh
$ docker compose -f ./docker-compose.yml down
```

### Structure

**MAIN**
`main.java.pageObject`: contains all **Page Object Classes** of the website
`main.java.utils`: contains all **Utilities Classes**, such as API Helper, Common utility, Database
Helper, Driver Factory, Driver Utils, Predefine Caps, Selenium Custom Element, Environment
Configuration...
`main.java.resources`: contains the **Environment Configuration** files (dev.properties)

**TEST**
`test.java.stepDefinitions`: where define **Methods of Cucumber Gherkin
steps** https://cucumber.io/docs/cucumber/step-definitions/. Define Cucumber hooks - `CustomHook` (
before, after methods or specific cucumber tags ) & Cucumber Running Class - `TestRunner` with
JUnit.
`test.java.resources:` The directory `features` stores all **Test Scenarios**.

**Pom.xml**: It is an XML file that contains information about the project and configuration details
used by Maven to build the project. It contains default values for most projects (plugins,
dependencies/libraries, properties, profile, environment variables...)

#### How to define a Web/Mobile Element in PageObject Class

Please use this pattern:

```
@FindBy(locator)
[Access Modifier] [?static] [ElementType] [ElementName];
```

Examples for Web Element: use **Element** type or specific elements as So **TextBox**, **Checkbox**
, **Table**, **CheckBox** and should be `public static`

```
@FindBy(id = "email")
public static Textbox Email_Textbox;
```

#### Utilities Classes

- **Helpers**: Declaring some helper functions
- **Factory**: Manage drivers (web) and predefine Capabilities of devices
- **Element**: Create specific types of interface **Element**
- **Env**: Handle application configuration via Java properties files

### Test Case Template

**Features** (stories) are explained using agile framework on user stories.

- Feature can contain one or more scenarios.
- Feature group contains locally related test scenarios.
- Feature represents small, discrete features of the system.
- Feature files start with keyword Feature, followed by feature name/terse description of feature.

Pattern:

 ```
As a [type of user], I want to [perform some action] so that I [can achieve some goal/result/value].”
 ```

Example:

```
As a user, 
    I want to be able to recover the password to my account 
    So that I will be able to access my account in case I forgot the password
```

**Scenario**: the name for the behavior that will be described with following keywords:
`Given` some precondition
`When` I do some action
`Then` I expect some result

**Note**: Each acceptance criteria written in this format has the following statements:
`Given` – the beginning state of the scenario
`When` – specific action that the user makes
`Then` – the outcome of the action in “When”
`And` – used to continue any of three previous statements

#### Scenario Step Conventions

- Subject: `I`
- Tense: `Simple Present`
- Common Actions: `click`on Desktop Web and `tap` on Mobile App
- Using `Page` for Desktop Web and `Screen` for Mobile App
- Mobile Steps: should have `on app` at the end of the sentence to avoid duplicating with same
  action on Web
- `Then` steps (Verifying Step):  using `I should` +  `do something` to describe the expected result

### Assertion

Functions of `AssertJ` library are used in verifying steps (`Then`). Static import:

```
import static org.assertj.core.api.Assertions.*;
```

Example:

```
assertThat(SearchPage.getAlertMessage).isEqualTo(AlertMessage);
```

In some concrete cases, using `Soft Assertions` to collects all assertion errors instead of stopping
at the first one. The instance of `Soft Assertion` always created in before step of each scenario (
Please check `test.java.stepDefinitions.config.CustomHook`) and stored
by `Scenario.currentContext()`. Just call it:

```
SoftAssertions softly = currentContext().get("Assertion", SoftAssertions.class);
softly.assertThat(...)
```

Reference links:
+ https://joel-costigliola.github.io/assertj/index.html.
+ https://joel-costigliola.github.io/assertj/assertj-core-features-highlight.html#soft-assertions.

### Test Report

The Cucumber test report will be generated by using report plugin and defined at @CucumberOptions
in `stepDefinitions.TestRunner`

**In Local:**
Use lifecycle `verify` instead of `test` to generate test report in
directory `target/cucumber-html-reports/js/`
The main report is file `overview-steps.html`
*Example:* Run this command to execute all scenarios which have tag "@regression" and generate the
test report:

```
clean verify -Denv=dev "-Dcucumber.options=--tags \"@test\""
```

### CI/CD

To running test in CI/CD process, we will use [Jenkins] and [Selenium Grid] with Selenium Grid
working as remote machine and every pipeline in [Jenkins] will call the remote machine to run test
in Selenium Grid

1. Start the Selenium Grid in a machine
2. Add the [Jenkins] pipeline with the config `REMOTE_URL` in `resources/env.dev.properties`
3. Run test

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

[Selenium]: <https://selenium.dev/r>

[Jenkins]:<https://www.jenkins.io/>

[Selenium Grid]:  <https://github.com/SeleniumHQ/docker-selenium>

[Appium]: <http://appium.io/>

[WebDriverManager]: <https://github.com/bonigarcia/webdrivermanager>

[Owner]: <http://owner.aeonbits.org/docs/welcome/>

[AssertJ]: <https://joel-costigliola.github.io/assertj/>

[Custom Page Factory]: <https://github.com/selenium34/custom-page-factory/blob/master/src/main/java/com/example/CustomFieldDecorator.java>

[Cucumber]: <https://cucumber.io/>

[JUnit 4&5]: <https://junit.org/>