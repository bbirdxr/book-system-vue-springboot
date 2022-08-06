<template>
  <div class="common-layout">
    <!--搜索区域-->
    <div id="searchArea">
      <span>
        图书编号
        <input
          type="string"
          style="text-align: left; width: 200px; height: 20px"
          v-model="inputBookId"
          @change="handleBookIdChange"
        />
      </span>
      <span>
        图书名称
        <input
          size="large"
          type="string"
          style="text-align: left; width: 200px; height: 20px"
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
      <span
        ><el-button
          @click="query"
          size="large"
          style="background-color: rgba(22, 127, 231, 0.515)"
          >查询</el-button
        ></span
      >
      <span
        ><el-button
          size="large"
          @click="addBook"
          style="background-color: rgba(231, 154, 22, 0.464)"
          >添加</el-button
        ></span
      >
    </div>
    <!--搜索区域-->
    <!--表格-->
    <el-table
      id="el-table"
      :data="bookData"
      border="border"
      stripe
      style="width: auto; margin: 10px"
    >
      <el-table-column prop="bookId" label="图书编号" />
      <el-table-column prop="bookName" label="名称" />
      <el-table-column prop="type" label="类型">
        <template #default="scope">
          <span v-if="scope.row.type == 1">文学类</span>
          <span v-if="scope.row.type == 2">历史类</span>
          <span v-if="scope.row.type == 3">工学类</span>
          <span v-if="scope.row.type == 4">法学类</span>
          <span v-if="scope.row.type == 5">经济学类</span>
          <span v-if="scope.row.type == 6">统计学类</span></template
        >
      </el-table-column>
      <el-table-column prop="author" label="作者" />
      <el-table-column prop="price" label="价格" />
      <el-table-column prop="language" label="语言" />
      <el-table-column fixed="right" label="操作">
        <template #default="scope">
          <el-button
            round
            link
            type="primary"
            size="large"
            @click="handleModifyBook(scope.row)"
            >修改</el-button
          >
          <el-popconfirm
            title="确认删除？"
            confirm-button-text="是"
            cancel-button-text="否"
            icon="el-icon-delete"
            icon-color="red"
            @confirm="handleDeleteConfirm(scope.row.bookId)"
            @cancel="handleCancel"
          >
            <template #reference
              ><el-button round link type="primary" size="large"
                >删除</el-button
              >
              >
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
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
  name: "ManageList",
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
          bookId: "1001",
          bookName: "Tom",
          type: "1",
          author: "Ming",
          price: "30",
          language: "英文",
        },
        {
          bookId: "1002",
          bookName: "Paly",
          type: "2",
          author: "Lise",
          price: "70",
          language: "英文",
        },
        {
          bookId: "1003",
          bookName: "The old man",
          type: "3",
          author: "Rosel",
          price: "50",
          language: "中文",
        },
        {
          bookId: "1004",
          bookName: "Alice and dog",
          type: "4",
          author: "Euke",
          price: "25",
          language: "英文",
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
        .post(
          "bookinfo/search?currentPage=" +
            this.currentPage +
            "&pageSize=" +
            this.pageSize,
          this.search
        )
        .then((res) => {
          console.log(res);
          this.bookData = res.data.records;
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
    handleModifyBook(row) {
      this.$router.push({
        name: "adminmodifybook",
        params: {
          bookName: row.bookName,
          bookId: row.bookId,
          bookType: row.type,
          author: row.author,
          price: row.price,
          language: row.language,
        },
      });
    },
    handleDeleteConfirm(bookId) {
      console.log(" delete   " + bookId);
      request.delete("/bookinfo/delete?bookId=" + bookId).then((res) => {
        if (res.code == 200) {
          this.$message({
            type: "success",
            message: "删除成功！",
          });
          this.load(); //添加成功后重新加载表格
        } else {
          this.$message({
            type: "error",
            message: "删除失败！",
          });
        }
      });
      this.load();
    },
    addBook() {
      this.$router.push("/adminaddbook");
    },
  },
  created() {
    this.load();
  },
};
</script>

<style scoped>
#searchArea {
  /* background-color: rgba(231, 154, 22, 0.464);  */
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: medium;
  padding-top: 10px;
  padding-left: 20px;
  padding-right: 20px;
}
</style>
