export const type = (obj) => {
  if (Array.isArray(obj)) {
    return 'array'
  }
  return (typeof obj)
}
