import { createRouter, createWebHistory } from "vue-router";
// import message from "@element-plus/icons-vue/dist/es/message.mjs";

const routes = [
  //重定向
  {
    path: "/",
    redirect: "/login",
  },
  //登录界面
  { path: "/login", name: "Login", component: () => import("@/views/Login") },
  {
    //管理员页面布局路由
    path: "/adminlayout",
    name: "AdminLayout",
    meta: {
      requiredAuth: true,
    },
    component: () => import("@/Layout/AdminLayout"),
    //内嵌子路由
    children: [
      {
        //书库管理路由
        path: "/adminmanagelist",
        name: "adminmanageList",
        component: () => import("@/views/ManageList"),
      },
      //查看书库路由
      {
        path: "/adminchecklist",
        name: "admincheckList",
        component: () => import("@/views/CheckList"),
      },
      //添加书籍路由
      {
        path: "/adminaddbook",
        name: "adminaddbook",
        component: () => import("@/views/Add"),
      },
      {
        path: "/adminmodifybook",
        name: "adminmodifybook",
        component: () => import("@/views/ModifyBook"),
      },
    ],
  },
  {
    //用户页面布局路由
    path: "/userlayout",
    name: "UserLayout",
    meta: {
      requiredAuth: true,
    },
    component: () => import("@/Layout/UserLayout"),
    //内嵌子路由
    children: [
      {
        //查看书库路由
        path: "/userchecklist",
        name: "UserCheckList",
        component: () => import("@/views/CheckList"),
      },
    ],
  },
];
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});
export default router;
