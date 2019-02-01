export const tokenize = (selector) => selector.split('.')

export const tuplize = (selector) => {
  const tokens = tokenize(selector)
  return tokenize(selector).map((token, index) => [
    tokens.slice(0, index + 1).join('.'),
    tokens.slice(index + 1, tokens.length).join('.')
  ])
}
