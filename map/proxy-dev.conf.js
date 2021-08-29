PROXY_CONFIG = {
  "/tiles/**": {
    "target": "https://knooppuntnet.nl",
    "changeOrigin": true,
    "secure": false
  }
};

module.exports = PROXY_CONFIG;
