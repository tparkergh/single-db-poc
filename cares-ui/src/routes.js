const caresApiRoute = (extension) => process.env.CARES_API_URL + extension

export const getAllReferralsRoute = () => caresApiRoute('/referrals')

export const getReferralByIdRoute = (id) => caresApiRoute(`/referrals/${id}`)

export const getClientByIdRoute = (id) => caresApiRoute(`/clients/${id}`)

export const getAllSystemMetasRoute = () => caresApiRoute('/system_metas')

export const getSystemCodesRoute = (meta) => caresApiRoute(`/system_codes/${meta}`)

export const getClientAddressesByClientIdRoute = (client_id) => caresApiRoute(`/clients/${client_id}/addresses`)
