PROXY_CONFIG = {
  '/resource-server/**': {
    target: 'http://127.0.0.1:8081',
    secure: false,
  },
};

module.exports = PROXY_CONFIG;
