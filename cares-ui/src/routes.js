const caresApiRoute = (extension) => process.env.CARES_API_URL + extension

const caresBreApiRoute = (extension) => process.env.CARES_BRE_API_URL + extension

export const getAllReferralsRoute = () => caresApiRoute('/referrals')

export const getReferralByIdRoute = (id) => caresApiRoute(`/referrals/${id}`)

export const getClientByIdRoute = (id) => caresApiRoute(`/clients/${id}`)

export const getAllSystemMetasRoute = () => caresApiRoute('/system_metas')

export const getSystemCodesRoute = (meta) => caresApiRoute(`/system_codes/${meta}`)

export const getClientAddressesByClientIdRoute = (client_id) => caresApiRoute(`/clients/${client_id}/addresses`)

export const putAddressRoute = (address_id) => caresApiRoute(`/addresses/${address_id}`)

// search routes

export const searchRoute = () => caresApiRoute('/searches')

// reporter routes

export const createReporterRoute = () => caresApiRoute('/reporters')

export const stateSystemCodesRoute = () => caresApiRoute('/system_codes/STATE_C')

export const getReporterRoute = (id) => caresApiRoute(`/reporters/${id}`)

// business rules routes

export const getBreRuleSetRoute = (ruleSet) => caresBreApiRoute(`/bre/defs/${ruleSet}`)
