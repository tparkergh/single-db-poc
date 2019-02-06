import FirstNameRequiredRule from './first-name-required-rule'
import LastNameRequiredRule from './last-name-required-rule'
import AddressZipBusinessRule from './address-zip-business-rule'

export const Rules = [
  { identifier: 'first-name-required-rule', definition: FirstNameRequiredRule },
  { identifier: 'last-name-required-rule', definition: LastNameRequiredRule },
  { identifier: 'address-zip-business-rule', definition: AddressZipBusinessRule }
]
