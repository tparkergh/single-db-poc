# JsonBRE

a buisness rules engine for front end applications that evaluates data using a JSON based rule format. It contains a repository of rules and functions for finding rules and evaluating them against data. It is a powerful tool for mapping rules to UI elements based on their context as defined in the rule.

## Setup

To lint

```
yarn lint
```

to run tests
```
yarn test
```

to build the library
```
yarn build
```

## Maintenance

When an update is made to the engine and its included locally in package.json via `"JsonBRE": "file:./engine"`, to make sure the updated engine is used in your application:

```
cd /engine
yarn build
cd ..
rm -rf ./node_modules
yarn
```

## Usage

import the engine:
```
import JsonBRE from 'JsonBRE'
```

to create a new instance of the engine with an entirely clean state:
```
const engine = new JsonBRE()
```

To register rules in the engines repository:
```
const definition = {
  "if": [
    { "===": [{"length": {"var": "address.zip"}}, 5]},
    true,
    "An address zip code must be 5 digits"
  ]
}

engine.define({
  identifier: 'zip-code-5-digits',
  definition
})
```

To find a rule in the engine:
```
const rules = engine.find((rule) => rule.identifier === 'zip-code-5-digits')
```
or
```
const rules = engine.find((rule) => rule.applies('address.zip'))
```

To evaluate a rule against some data:
```
engine.evaluate(rule, data)
```

## Examples

The power of engine is really shown with regard to finding rules and applying them to form data. Lets look at an example where you have a form for creating employees in a database at a fictitious company called Form Builder.

fields:
* first name
* last name
* dob
* email

The following rules are coming from the buisness:
* dob must be > 18 because the person is employable
* email must be their new company email with an extension of ".formbuilder.com"

The rules can be written in JsonLogic with their full context as:
```
const employableRule = {
  identifier: 'age-18-employability',
  definition: {
    "if": [
    { ">=": [{ "var": "create.employee.dob" }, 18]},
    true,
    "When creating an employee their dob must be at least 18"
    ]
  }
}

const emailRule = {
  identifier: 'company-email-rule',
  definition: {
    "if": [
    { "in": [".formbuilder.com", { "var": "create.employee.email" }]},
    true,
    "When creating an employee their email must be from .formbuilder.com"
    ]
  }
}
```

Lets say we are using ReactJS to implement our form, and we have a FormBuilder.Form component that takes in a validation context and wraps the default form element. We also have a FormBuilder.Input component that wraps the default html input:
```
<FormBuilder.Form validationContext="create.employee">
  <FormBuilder.Input type="date" name="dob" />
  <FormBuilder.Input type="date" name="dob" />
</FormBuilder.Form>
```
rough implementation of FormBuilder.Form component where we pass the validation context to the children and render them:
```
const FormContext = React.createContext()
render() {
  const props = this.props
  return (
    <Form { ...props }>
      <MyContext.Provider value = { this.props.validationContext }>
        { this.props.children }
      </MyContext.Provider>
    </Form>
  )
}
```

rough implementation of FormBuilder.Input component Which renders a validation component and an Input:
```
render() {
  const { ...inputProps, validationContext } = this.props
  return (
    <Input {...inputProps} />
    <FormBuilder.Validate context={validationContext} name={this.props.name} />
  )
}
```

rough implementation of FormBuilder.Validate component:
```
```
