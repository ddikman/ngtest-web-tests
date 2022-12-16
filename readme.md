# Web testing with TestNG and Selenium

This project is a small example to show how web testing can be automated with Selenium and TestNG.


## Run

To avoid problems with environment setup, this project comes with a Dockerfile ready to use

```shell
docker build -t testng-example .
docker run -v $(pwd):/app testng-example
```

If you are on windows you will need to adjust the volume mount. This will output the test results into `build/test-results/test/TEST-AppTest.xml`


## Headless tests

Tests are only useful when they are being run continously. This repository relies on Github Actions to run tests on commit. Read more [on how to run selenium headless](https://www.linkedin.com/pulse/running-selenium-web-tests-github-actions-moataz-nabil/) (without UI) in a continous integration environment like Github Actions.