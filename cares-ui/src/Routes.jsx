const caresApiRoute = (extension) => process.env.CARES_API_URL + extension

export const getAllReferralsRoute = () => caresApiRoute('/referrals')

export const getReferralByIdRoute = (id) => caresApiRoute(`/referrals/${id}`)

export const getClientByIdRoute = (id) => caresApiRoute(`/clients/${id}`)

export const getAllSystemCodesRoute = (id) => caresApiRoute('/system_metas')
