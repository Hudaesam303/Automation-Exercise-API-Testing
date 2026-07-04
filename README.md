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
| **Total** | **24** | **✅ 100% Pass Rate** |

## 📮 Postman Collection
A Postman collection and environment file are also included in this repo (`API Store(Automation Test Exercise Website).postman_collection.json` and `API Store Environment.postman_environment.json`) — useful for manual/exploratory testing or as a quick reference for the same API flows covered by the automated suite.

## 🚀 How to Run
```bash
# Run all tests
mvn clean test

# Generate Allure Report
mvn allure:serve
```

## 👩‍💻 Author
**Huda Esam** — QA Engineer | ISTQB CTFL Certified
[LinkedIn](www.linkedin.com/in/huda-esam-b85b6a22a) · [GitHub](https://github.com/Hudaesam303)