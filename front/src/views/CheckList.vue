<template>
  <div class="common-layout">
    <!--搜索区域-->
    <div id="searchArea">
      <span>
        图书编号
        <input
          type="string"
          style="text-align: right; width: 200px; height: 20px"
          v-model="inputBookId"
          @change="handleBookIdChange"
        />
      </span>
      <span>
        图书名称
        <input
          size="large"
          type="string"
          style="text-align: right; width: 200px; height: 20px"
          v-model="inputBookName"
          @change="handleBookNameChange"
        />
      </span>
      <!--类别筛选-->
      <span>
        图书类别
        <el-select
          v-model="search.bookType"
          placeholder="选择图书类别"
          @change="handleselectChanged"
        >
          <el-option
            v-for="item in bookTypeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
            <span style="float: left">{{ item.label }}</span>
          </el-option>
        </el-select>
      </span>
      <!--类别筛选-->
      <span><el-button @click="query" size="large">查询</el-button></span>
    </div>
    <!--搜索区域-->
    <!--表格-->
    <el-table
      id="el-table"
      :data="bookData"
      border="border"
      stripe
      style="width: 1300px; margin: 10px"
    >
      <el-table-column prop="bookId" label="图书编号" />
      <el-table-column prop="bookName" label="名称" />
      <el-table-column prop="type" label="类型" />
      <el-table-column prop="author" label="作者" />
      <el-table-column prop="price" label="价格" />
      <el-table-column prop="language" label="语言" />
    </el-table>
    <!--表格-->
    <!--分页-->
    <div>
      <el-pagination
        layout="sizes,prev, pager,
              next,jumper"
        background
        :page-sizes="[8, 10, 12]"
        :pager-count="15"
        :page-size="pageSize"
        :total="total"
        :current-Page="currentPage"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
    <!--分页-->
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "CheckList",
  data() {
    return {
      search: {
        bookId: "",
        bookName: "",
        bookType: "",
      },
      bookTypeOptions: [
        { value: "1", label: "文学类" },
        { value: "2", label: "历史类" },
        { value: "3", label: "工学类" },
        { value: "4", label: "法学类" },
        { value: "5", label: "经济学类" },
        { value: "6", label: "统计学类" },
      ],
      total: 10000,
      pageSize: 10,
      currentPage: 1,
      bookData: [
        {
          bookId: "001",
          bookName: "Tom",
          type: "小说",
          author: "Ming",
          price: "30",
          language: "English",
        },
        {
          bookId: "002",
          bookName: "Paly",
          type: "小说",
          author: "Lise",
          price: "70",
          language: "English",
        },
        {
          bookId: "003",
          bookName: "The old man",
          type: "童话",
          author: "Rosel",
          price: "50",
          language: "Chinese",
        },
        {
          bookId: "004",
          bookName: "Alice and dog",
          type: "教材",
          author: "Euke",
          price: "25",
          language: "English",
        },
      ],
    };
  },
  methods: {
    load() {
      console.log(this.currentPage);
      console.log(this.pageSize);
      console.log(this.search);
      request
        .get("http://localhost:8090/bookinfo/search", {
          params: {
            currentPage: this.currentPage,
            pageSize: this.pageSize,
            search: this.search,
          },
        })
        .then((res) => {
          console.log(res);
          this.bookData = res.data.books;
          this.total = res.data.total;
        });
    },
    handleBookIdChange() {
      this.search.bookId = this.inputBookId;
    },
    handleBookNameChange() {
      this.search.bookName = this.inputBookName;
    },
    handleselectChanged() {
      // 强制刷新选择框
      this.$forceUpdate();
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.load();
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.load();
    },
    query() {
      this.currentPage = 1;
      this.load();
    },
  },
  created() {
    this.load();
  },
};
</script>

<style scoped>
.el-menu {
  background-color: white;
}
#searchArea {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: medium;
  padding-top: 10px;
  padding-left: 20px;
  padding-right: 20px;
}
</style>
