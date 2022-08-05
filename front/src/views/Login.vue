<template>
  <div class="background">
    <div id="login-container">
      <div
        style="
          text-align: center;
          margin-bottom: 20px;
          font-weight: bold;
          font-size: xx-large;
        "
      >
        图书管理
      </div>
      <el-form
        :model="ruleForm"
        status-icon
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="账号" prop="user">
          <el-input
            type="text"
            v-model="ruleForm.user"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="pass">
          <el-input
            type="password"
            v-model="ruleForm.pass"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item label="用户组" prop="userClass">
          <el-select v-model="ruleForm.userClass" placeholder="请选择">
            <el-option label="管理员" :value="0"></el-option>
            <el-option label="用户" :value="1"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" v-on:click="login" style="width: 70px"
            >登录</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Login",
  data() {
    return {
      ruleForm: {
        user: "",
        pass: "",
        userClass: 0,
      },
      rules: {
        user: [{ required: true, message: "请输入账号", trigger: "blur" }],
        pass: [{ required: true, message: "请输入密码", trigger: "blur" }],
        userClass: [{ required: true, message: "请选择", trigger: "change" }],
      },
      isLogin: false,
    };
  },
  methods: {
    login: function (e) {
      request
        .post("/login", {
          params: {
            userName: this.ruleForm.user,
            password: this.ruleForm.pass,
            type: this.ruleForm.userClass,
          },
        })
        .then((res) => {
          console.log(res.data);
          if (res.data.code == 200) {
            alert("登录成功");
            if (this.ruleForm.userClass == 0)
              this.$router.push("/adminmanagelist");
            else this.$router.push("/userchecklist");
            //登录成功
          } else {
            alert("登录失败");
          }
        })
        .catch((err) => {
          console.log(err);
          alert("登录失败");
        });
    },
  },
};
</script>

<style scoped>
#login-container {
  width: 380px;
  height: 360px;
  background: #ffffff99;
  position: absolute;
  left: 40%;
  top: 20%;
  border-radius: 40px;
  padding-top: 40px;
  padding-right: 40px;
}
</style>
