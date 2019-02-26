import { Component } from "react";
import Search from "./Search"
import SearchResults from "./SearchResults"

export class ReportWizard extends Component {
  render () {
    return (
      <div>
        <Search />
        <SearchResults />
      </div>
    )
  }
}
