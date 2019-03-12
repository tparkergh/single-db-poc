import { Survey } from "survey-react";
import { Component, Fragment } from "react";
import { Alert } from "@cwds/components";

export default class JsonForm extends Component {
  componentDidMount() {
    this.props.model.update(this.props)
    this.props.model.loadJsonRules()
  }

  componentDidUpdate() {
    this.props.model.update(this.props)
  }

  render () {
    if (this.props.active) {
      const errorMsg = this.props.error
      return (
        <Fragment>
          {errorMsg && 
            <Alert className="errorMessage-customizable" color="danger">
              {errorMsg}
            </Alert>
          }
          <Survey model={this.props.model} />
       </Fragment>)
    }
    return null
  }
}
