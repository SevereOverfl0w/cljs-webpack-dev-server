const path = require('path');

module.exports = {
    mode: 'development',
    devServer: {
        port: 9777,
        allowedHosts: 'all',
        hot: 'only',
        headers: {
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, PATCH, OPTIONS",
            "Access-Control-Allow-Headers": "X-Requested-With, content-type, Authorization",
        },
    },
    // entry: './target/public/cljs-out/app/main.js',
    entry: './hot.js',
    output: {
        filename: '[name]_bundle.js',
        path: path.resolve(__dirname, 'target/public/cljs-out/app'),
    }
}
