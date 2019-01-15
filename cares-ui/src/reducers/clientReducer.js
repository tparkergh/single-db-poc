const clients = (state = [], action) => {
  switch (action.type) {
    case 'GET_CLIENTS':
      return action.payload
    default:
      return state
  }
}
export default clients
