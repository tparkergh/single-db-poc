# cares-frontend-validation-integration
A small POC javascript application that demonstrates how to execute validation on the front end that is also checked on the backend

# The Requirement
There are multiple digital services: CANS, CALS, Intake, etc that need to validate data. We have been to validating on the front end as the user is entering information, and then validating on the back end before they save any changes. This means that validation is applied in multiple places with potential room for a lot of duplicated implementation of business rules. This duplication makes it difficult to add or modify existing buisness rules because we would need to make code changes in multiple locations simultaneously. We need to consolidate these rules so that they are contained in one place and ensure that minimal code is added each time a rule is added or changed.

# Proposed solution
Since buisness rules need to be evaluated on the front end and back end, we need an API that contains these buisness rules definitions that can be called from both the front end and the back end.

The front-end will use this API to retrieve the buisness rules it wants to evaluate. It will have its own mechanism written in Javascript to evaluate those buisness rules. It will also be responsible for the mapping of fields to buisness rules and displaying associated messages as the user fills out forms. When the user chooses to save his changes, then the responsibility of validating the data passes to the back end.

In a similar manner the back end will call the API to validate buisness rules. If it uses the API to retrieve the buisness rules and run them itself, or uses the API to evaluate the buisness rules and get a result is up to the authors of the API and back end systems. However, the back-end is required to use the same set of rules through the API because the API is at the least a central repository of rules.

It is recommended to have a composite buisness rule per unit being tested, so that a single rule can be used on the front end and back end to test that unit.

This POC will use BRE API to retrieve buisness rule definitions, which meets the requirements of the proposed solution and also has the ability to evaluate data against buisness rules.

# Benifits
* avoids making an api call every time the user changes data
* allows the rules to exist in one place externally so they can be shared
* low amount of duplication
* exposes the front end developers to buisness rules
# Drawbacks
* rules are run in two places. Therefore any new functions/operations on data need to be implemented in two places
* There are no industry standards for JSON-based buisness rule formats (but there are non-standardized solutions out there)

# Assumptions
* assumes that there is no divergence between front end and back end buisness rules
* assumes that the buisness rules will be self-descriptive with low amount of external dependencies (i believe the buisness rules can be built this way)

# Technology Stack
* [ReactJS](https://reactjs.org): core development library
* [Jest](https://jestjs.io): for unit testing
* [NeutrinoJS](https://neutrinojs.org): for easy setup
* [Yarn](https://yarnpkg.com/en): package manager for javascript
* [JsonLogic](http://jsonlogic.com) (potenially): a buisness rule format that can be executed on the front end in JavaScript and the back end in Java

# Getting Started
To setup the project:
```
yarn install
```

To run the server:
```
yarn start
```

To run unit tests:
```
MODE=development yarn test
```

To run linting:
```
MODE=development yarn lint
```
