# Project: Automated Testing of Online Store [Demo Web Shop](https://demowebshop.tricentis.com/)

## :open_book: Table of Contents

- [Technologies and Tools](#gear-technologies-and-tools)
- [Project Structure](#file_folder-project-structure)
- [Test Cases](#heavy_check_mark-test-cases)
- [Running Tests](#computer-running-tests)
- [Allure Report](#allure-report)

## :gear: Technologies and Tools

<p align="left">
<a href="https://www.jetbrains.com/idea/"><img src="media/logo/Intelij_IDEA.svg" width="50" height="50" alt="IDEA" title="IntelliJ IDEA"/></a>
<a href="https://www.java.com/"><img src="media/logo/Java.svg" width="50" height="50" alt="Java" title="Java"/></a>
<a href="https://github.com/"><img src="media/logo/GitHub.svg" width="50" height="50" alt="GitHub" title="GitHub"/></a>
<a href="https://junit.org/junit5/"><img src="media/logo/JUnit5.svg" width="50" height="50" alt="JUnit 5" title="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="media/logo/Gradle.svg" width="50" height="50" alt="Gradle" title="Gradle"/></a>
<a href="https://selenide.org/"><img src="media/logo/Selenide.svg" width="50" height="50" alt="Selenide" title="Selenide"/></a>
<a href="https://rest-assured.io/"><img src="media/logo/RestAssured.svg" width="50" height="50" alt="RestAssured" title="RestAssured"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="media/logo/Allure_Report.svg" width="50" height="50" alt="Allure" title="Allure"/></a>
</p>

This project contains automated tests written in **Java** using **Selenide** for UI testing and **RestAssured** for API
testing.

**JUnit5** is used as the test framework, **Gradle** for build management, and **Allure** for reporting.  
Tests can be executed locally or remotely (e.g. via Selenoid).

## :file_folder: Project Structure

!!!!!!!!!!!!!!!!

- **tests** — UI + API сценарии
- **models** — генерация тестовых данных
- **specs** — спецификации для RestAssured
- **TestBase** — базовый класс с настройкой окружения
- **TestData** — константы и дефолтные пользователи

---

## :heavy_check_mark: Test Cases

| Test Case                                         | Type     | Result     |
|---------------------------------------------------|----------|------------|
| Successful login with valid credentials           | API + UI | ✅ Positive |
| Unsuccessful login with invalid/empty credentials | API      | ❌ Negative |
| Add product to cart and verify quantity           | API + UI | ✅ Positive |
| Remove product from cart and verify empty state   | UI       | ✅ Positive |
| Add product to cart and verify price              | API + UI | ✅ Positive |

---

## :computer: Running Tests

### Local Execution

```bash
gradle clean test

- **tests** — UI + API сценарии  
- **models** — генерация тестовых данных  
- **specs** — спецификации для RestAssured  
- **TestBase** — базовый класс с настройкой окружения  
- **TestData** — константы и дефолтные пользователи  

```

## :heavy_check_mark: Test Cases

| Test Case                                         | Type     | Result     |
|---------------------------------------------------|----------|------------|
| Successful login with valid credentials           | API + UI | ✅ Positive |
| Unsuccessful login with invalid/empty credentials | API      | ❌ Negative |
| Add product to cart and verify quantity           | API + UI | ✅ Positive |
| Remove product from cart and verify empty state   | UI       | ✅ Positive |
| Add product to cart and verify price              | API + UI | ✅ Positive |

---

## :computer: Running Tests

### Local Execution

```bash
gradle clean test
-Dbrowser=${browser} \
-DbrowserVersion=${browserVersion} \
-DbrowserSize=${browserSize} \
-DremoteDriverUrl=${REMOTE_DRIVER_URL}
```

## :allure: Report
After execution, results are available in Allure Report with detailed steps, screenshots, and logs.

<p align="center">
<img src="media/screen/allure_overview.png" alt="allure-report" width="900">
</p>