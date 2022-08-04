<template>
  <div id="MyHeader">
    <div style="margin-left:20px;font-size:40px"><i><b>图书管理系统~</b></i></div>
    <div><el-button @click="logout" size="large" style="margin-right:20px">退出</el-button></div>
  </div> 
</template>
<script>
// import request from "@/utils/request";

export default {
  name: "ListHeader",
  data() {
    return {};
  },
  methods: {
    logout() {
      alert("已退出系统");
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
#MyHeader {
  background-color: #252626;
  display: flex;
  justify-content: space-between;
  padding-top: 0;
  padding-left: 10px;
  padding-right: 10px;
  height: 80px;
  align-items: center;
  font-size: medium;
  color: aliceblue;
}
</style>
