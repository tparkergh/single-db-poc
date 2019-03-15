import { Component } from "react";
import Search from "./Search"
import SearchResults from "./SearchResults"
import Reporter from "./Reporter"

export class ReportWizard extends Component {
  render () {
    return (
      <div>
        <Search />
        <SearchResults />
        <Reporter />
      </div>
    )
  }
}
