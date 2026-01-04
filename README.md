# Project: Automated Testing of Online Store [Demo Web Shop](https://demowebshop.tricentis.com/)

## :open_book: Table of Contents

- [Technologies and Tools](#gear-technologies-and-tools)
- [Test Cases](#heavy_check_mark-test-cases)
- [Running Tests](#computer-running-tests-from-terminal)
- [Running Tests in Jenkins](#running-tests-in-jenkins)
- [Allure Report](#allure-report)
- [Telegram Notifications](#telegram-notifications)

## :gear: Technologies and Tools

<p align="left">
<a href="https://www.jetbrains.com/idea/"><img src="media/logo/Intelij_IDEA.svg" width="50" height="50" alt="IDEA" title="IntelliJ IDEA"/></a>
<a href="https://www.java.com/"><img src="media/logo/Java.svg" width="50" height="50" alt="Java" title="Java"/></a>
<a href="https://github.com/"><img src="media/logo/GitHub.svg" width="50" height="50" alt="GitHub" title="GitHub"/></a>
<a href="https://junit.org/junit5/"><img src="media/logo/JUnit5.svg" width="50" height="50" alt="JUnit 5" title="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="media/logo/Gradle.svg" width="50" height="50" alt="Gradle" title="Gradle"/></a>
<a href="https://selenide.org/"><img src="media/logo/Selenide.svg" width="50" height="50" alt="Selenide" title="Selenide"/></a>
<a href="https://rest-assured.io/"><img src="media/logo/RestAssured.svg" width="50" height="50" alt="RestAssured" title="RestAssured"/></a>
<a href="https://www.jenkins.io/"><img src="media/logo/Jenkins.svg" width="50" height="50" alt="Allure" title="Jenkins"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="media/logo/Allure_Report.svg" width="50" height="50" alt="Allure" title="Allure"/></a>
</p>

This project contains automated tests written in **Java** using **Selenide** for UI testing and **RestAssured** for API
testing.

**JUnit5** is used as the test framework, **Gradle** for build management, and **Allure** for reporting.  
Tests can be executed locally or remotely (e.g. via Selenoid).

## :heavy_check_mark: Test Cases

- Successful login with valid credentials 
- Unsuccessful login with invalid/empty credentials (parameterized test)
- Add product to cart and verify quantity
- Remove product from cart and verify empty state
- Add product to cart and verify price

## :computer: Running Tests from Terminal

### :house_with_garden: Local Test Execution

```bash
gradle clean test
```

### :earth_asia: Remote Test Execution

```bash
gradle clean test
-Dbrowser=${browser}
-DbrowserVersion=${browserVersion}
-DbrowserSize=${browserSize}
-DremoteDriverUrl={REMOTE_DRIVER_URL}
```

## Running Tests in Jenkins

* browser (default: chrome)
* browserVersion (default: 100.0)
* browserSize (default: 1920x1080)
* remoteDriverUrl (Selenoid URL)

## <img width="4%" title="Jenkins" src="media/logo/Jenkins.svg"> Running Tests in [Jenkins](https://jenkins.autotests.cloud/job/030_rudovich_jenkins_tests_modsen/)

To start a build, go to <code><strong>Build with Parameters</strong></code> and click <code><strong>Build</strong></code>.

<p align="center">
  <img src="media/screen/start_jenkins.png" alt="Jenkins" width="800">
</p>

## :allure: Report
## <img width="4%" title="Allure Report" src="media/logo/Allure_Report.svg"> Result of Testing in   [Allure Report](https://jenkins.autotests.cloud/job/015_aziyatdinov_final_ui/1/allure/)

<p align="center">
  <img src="media/screen/jenkins_overview.png" alt="allure-report" width="900">
</p>

<p align="center">
  <img src="media/screen/jenkins_behaviors.png" alt="allure-report_1" width="900">
</p>