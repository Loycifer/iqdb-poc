const path = require('path');
const TerserPlugin = require("terser-webpack-plugin");

module.exports = (env, argv) => ({
    entry: './js/iqdb.js',
    output: {
        path: path.resolve(__dirname, './target/classes/static'),
        filename: 'iqdb.js'
    },
    devtool: argv.mode === 'production' ? false : 'eval-source-map',
    performance: {
        maxEntrypointSize: 488000,
        maxAssetSize: 488000
    },
    optimization: {
        minimize: true,
        minimizer: [
           new TerserPlugin(),
          //  new CssMinimizerPlugin()
        ]
    },
    plugins: [
      //  new WarningsToErrorsPlugin()
    ],
    module: {
        rules: [
            {
                test: /\.js$'/,
                include: path.resolve(__dirname, './src/main/resources/js'),
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env'],
                    },
                },
            },
        ]
    },
    resolve: {
        modules: [
            path.resolve(__dirname, './src/main/resources'),
            'node_modules'
        ],
    }
});