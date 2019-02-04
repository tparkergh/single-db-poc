import { Input } from '@cwds/reactstrap'
export const DictionaryOptions = ({name, options, code}) => (
  <Input type='select' name={name} id={name}>
    <option
      key={0}
      selected={ 0 === code }
      disabled={ 0 !== code }
    />
    { options.map(({key, option}) =>
      <option
        key={key}
        selected={ key === code }
        disabled={ key !== code }
      >{option}</option>) 
    }
  </Input>
)
