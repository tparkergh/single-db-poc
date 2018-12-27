module.exports = {
  use: [
    '@neutrinojs/standardjs',
    [
      '@neutrinojs/library',
      {
        name: 'JsonBRE'
      }
    ],
    '@neutrinojs/jest'
  ]
};
