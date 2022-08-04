<template>
  <el-menu
    style="justify-content: center"
    router
    background-color="white"
    width="120px"
    height="calc(100vh - 55px)"
    default-active="path"
    class="el-menu"
  >
    <el-menu-item style="font-size: 17px" index="/adminmanagelist">
      <el-icon><document /></el-icon>
      <span>管理书籍</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "AdminAside",
  data() {
    return {
      path: this.$route.path,
    };
  },
};
</script>

<style scoped></style>
