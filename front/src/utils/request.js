import axios from "axios";
import router from "@/router";

const request = axios.create({
  baseURL: "http://localhost:8090", // 注意！！ 这里是全局统一加上了
  timeout: 5000,
});
// request.interceptors.response.use(
//   (response) => {
//     let res = response.data;
//     //状态码正常
//     if (res.code === 200) {
//       // 如果是返回的文件
//       if (response.config.responseType === "blob") {
//         return res;
//       }
//       // 兼容服务端返回的字符串数据
//       if (typeof res === "string") {
//         res = res ? JSON.parse(res) : res;
//       }
//     } else {
//       alert(!res.msg ? "系统异常" : res.msg);
//       return Promise.reject(response.data.msg);
//     }
//     return res;
//   },
//   (error) => {
//     console.log("err" + error); // for debug
//   }
// );
request.interceptors.response.use(
  (response) => {
    let res = response.data;
    if (typeof res === "string") {
      res = res ? JSON.parse(res) : res;
    }
    return res;
  },
  (error) => {
    console.log("err" + error); // for debug
  }
);
export default request;
