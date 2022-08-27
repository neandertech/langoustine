/**
 * @type {import('vite').UserConfig}
 */

import path from 'path'

export default {
  // root: path.resolve(__dirname, './../target/js-3'),
  server: {
    proxy: {
      // '/api/ws/events': {
      //   target: 'ws://localhost:9977/api/ws/events',
      //   ws: true,
      //   changeOrigin: true
      // },
      '/api': {
        target: 'http://localhost:9977',
        changeOrigin: true,
      }
    }
  }
}
