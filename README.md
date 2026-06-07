# Automation Exercise API Testing Framework

![Java](https://img.shields.io/badge/Java-11-orange)
![RestAssured](https://img.shields.io/badge/RestAssured-6.0.0-green)
![TestNG](https://img.shields.io/badge/TestNG-7.12.0-red)
![Allure](https://img.shields.io/badge/Allure-2.25.0-blue)

## 📌 About
API Test Automation Framework for [Automation Exercise](https://automationexercise.com/api_list) built with Java + RestAssured + TestNG.

## 🛠️ Tech Stack
| Tool | Purpose |
|------|---------|
| Java 11 | Programming Language |
| RestAssured 6.0 | API Testing Library |
| TestNG 7.12 | Test Runner |
| Allure 2.25 | Test Reporting |
| Maven | Build Tool |

## 📁 Test Suites
| Suite | Tests | Description |
|-------|-------|-------------|
| Full User Lifecycle | 8 | Create → Login → Update → Delete |
| Negative User Cycle | 6 | Operations on deleted account |
| Products & Search | 4 | Products list, search, brands |
| Negative Login | 6 | Invalid credentials scenarios |
| **Total** | **24** | |

## 🚀 How to Run
```bash
# Run all tests
mvn clean test

# Generate Allure Report
mvn allure:serve
```

## 👩‍💻 Author
**Huda Esam** — QA Engineer | ISTQB CTFL Certified