/**
 * @type {import('vite').UserConfig}
 */

import path from 'path'

export default () => {
  const port = process.env.LANGOUSTINE_PORT;
  const str = `http://localhost:${port}`;
  return {
    server: {
      proxy: {
        '/api': {
          target: str,
          changeOrigin: true
        }
      }
    }
  }
}
