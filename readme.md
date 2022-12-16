# Web testing with TestNG and Selenium

This project is a small example to show how web testing can be automated with Selenium and TestNG.


## Run

To avoid problems with environment setup, this project comes with a Dockerfile ready to use

```shell
docker build -t testng-example .
docker run -v $(pwd):/app testng-example
open build/reports/tests/test/index.html
```

If running on windows the volume mount and paths will need to be updated.

Before tearing down each test, a screenshot is saved with the test name in `build/test-results/test/` which can help troubleshooting.

## Project structure

The test "steps" are defined in [src/test/java/AppTest.java](src/test/java/AppTest.java) together with setup and teardown for Selenium. To define which order to run these are to be executed , refer to the test suite with two tests defined together with parameters in [suites/features.xml](suites/features.xml).

## Headless tests

Tests are only useful when they are being run continously. This repository relies on Github Actions to run tests on commit. Read more [on how to run selenium headless](https://www.linkedin.com/pulse/running-selenium-web-tests-github-actions-moataz-nabil/) (without UI) in a continous integration environment like Github Actions.