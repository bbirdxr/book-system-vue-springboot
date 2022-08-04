<template>
  <div class="background">
    <div id="login-container">
      <div
        style="
          text-align: center;
          margin-bottom: 50px;
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

        <el-select
          v-model="userClass"
          placeholder="请选择用户组"
          style="margin-bottom: 20px; margin-left: 100px"
        >
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>

        <el-form-item>
          <el-button type="primary" v-on:click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入账户"));
      } else {
        if (this.ruleForm.pass !== "") {
          this.$refs.ruleForm.validateField("pass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        user: "",
        pass: "",
      },
      rules: {
        user: [{ validator: validatePass, trigger: "blur" }],
        pass: [{ validator: validatePass2, trigger: "blur" }],
      },
      options: [
        {
          value: "0",
          label: "管理员",
        },
        {
          value: "1",
          label: "普通用户",
        },
      ],
      userClass: "",
      isLogin: false,
    };
  },
  methods: {
    login: function (e) {
      this.axios({
        url: `/login?userName=${this.ruleForm.user}&pwd=${this.ruleForm.pass}&type=${this.userClass}`,
        method: "get",
      })
        .then((res) => {
          console.log(res.data);
          if (res.data.code != 200) {
            alert("登录成功");
            if (this.userClass == 0) this.$router.push("/adminmanagelist");
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
* {
  margin: 0;
  padding: 0;
}
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
