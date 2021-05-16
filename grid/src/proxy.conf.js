PROXY_CONFIG = {
  '/api/**': {
    target: 'https://knooppuntnet.nl',
    secure: false,
  },
};
module.exports = PROXY_CONFIG;
