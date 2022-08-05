const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,

  devServer: {
    port: 8888,
    proxy: {
      //设置代理，必须填
      "/api": {
        //设置拦截器  拦截器格式   斜杠+拦截器名字，名字可以自己定

        target: "http://localhost:8090", //代理的目标地址
        changeOrigin: true, //是否设置同源，输入是的
        secure: true,
        pathRewrite: {
          //路径重写
          "^/api": "", //选择忽略拦截器里面的内容
        },
        headers: {
          referer: "http://localhost:8090", //这里后端做了拒绝策略限制，请求头必须携带referer，否则无法访问后台
        },
      },
    },
  },
});
