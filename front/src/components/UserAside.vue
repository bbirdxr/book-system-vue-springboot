<template>
  <el-menu
    background-color="white"
    width="120px"
    default-active="/userchecklist"
    class="el-menu"
    @open="handleOpen"
    @close="handleClose"
  >
    <el-menu-item style="font-size: 17px" index="/userchecklist">
      <el-icon><icon-menu /></el-icon>
      <span>查看书籍</span>
    </el-menu-item>
  </el-menu>
</template>
<script>
export default {
  name: "UserAside",
  data() {
    return {
      path: this.$route.path,
    };
  },
};
</script>

<style scoped></style>
