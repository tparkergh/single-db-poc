import { Survey } from "survey-react";
import { Component, Fragment } from "react";

export default class JsonForm extends Component {
  componentDidMount() {
    console.log(this.props.model)
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
