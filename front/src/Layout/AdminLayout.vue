<template>
  <div>
    <!--    头部-->
    <ListHeader />

    <!--    主体-->
    <div style="display: flex">
      <!--      侧边栏-->
      <AdminAside />

      <!--      内容区域-->
      <router-view style="flex: 1" />
    </div>
  </div>
</template>

<script>
import ListHeader from "@/components/ListHeader";
import AdminAside from "@/components/AdminAside.vue";
export default {
  name: "AdminLayout",
  components: {
    ListHeader,
    AdminAside,
  },
};
</script>

<style scoped></style>
