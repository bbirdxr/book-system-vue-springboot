<template>
  <div>
    <!--    头部-->
    <ListHeader />

    <!--    主体-->
    <div style="display: flex">
      <!--      侧边栏-->
      <UserAside />

      <!--      内容区域-->
      <router-view style="flex: 1" />
    </div>
  </div>
</template>

<script>
import ListHeader from "@/components/ListHeader";
import UserAside from "@/components/UserAside.vue";
export default {
  name: "UserLayout",
  components: {
    ListHeader,
    UserAside,
  },
};
</script>

<style scoped></style>
