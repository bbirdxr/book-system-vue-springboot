<template>
  <el-menu
    router
    background-color="white"
    width="120px"
    height="calc(100vh - 55px)"
    default-active="path"
    class="el-menu"
  >
    <el-menu-item index="/adminchecklist">
      <el-icon><icon-menu /></el-icon>
      <span>查看书籍</span>
    </el-menu-item>
    <el-menu-item index="/adminmanagelist">
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
