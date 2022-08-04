<template>
  <div>
    <div class="table">
      <el-form
        :model="ruleForm"
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
        size="large"
      >
        <el-form-item label="id" prop="bookId" class="margin">
          <el-input
            v-model="ruleForm.bookId"
            style="width: 400px"
            :disabled="true"
          ></el-input>
        </el-form-item>

        <el-form-item label="图书名" prop="bookName" class="margin">
          <el-input v-model="ruleForm.bookName" style="width: 400px"></el-input>
        </el-form-item>

        <el-form-item label="图书作者" prop="author" class="margin">
          <el-input v-model="ruleForm.author" style="width: 400px"></el-input>
        </el-form-item>

        <el-form-item label="图书类别" prop="bookType" class="margin">
          <span>
            <el-select v-model="ruleForm.bookType" placeholder="请选择">
              <el-option label="文学类" :value="1"></el-option>
              <el-option label="历史类" :value="2"></el-option>
              <el-option label="工学类" :value="3"></el-option>
              <el-option label="法学类" :value="4"></el-option>
              <el-option label="经济学类" :value="5"></el-option>
              <el-option label="统计学类" :value="6"></el-option>
            </el-select>
          </span>
        </el-form-item>

        <el-form-item label="语言" prop="Type" class="margin">
          <el-select v-model="ruleForm.type" placeholder="请选择">
            <el-option label="中文" :value="0"></el-option>
            <el-option label="英文" :value="1"></el-option>
            <el-option label="其他" :value="2"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="价格" prop="price" class="margin">
          <el-input
            type="price"
            v-model.number="ruleForm.price"
            autocomplete="off"
            style="width: 400px"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button
            type="primary"
            @click="submitForm('ruleForm')"
            class="button1"
            >提交</el-button
          >
          <el-button @click="resetForm('ruleForm')" class="button2">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "ModifyBook",
  data() {
    return {
      ruleForm: {
        bookName: this.$route.params.bookName,
        bookType: this.$route.params.type,
        author: this.$route.params.author,
        type: this.$route.params.language,
        price: this.$route.params.price,
        bookId: this.$route.params.bookId,
      },
      rules: {
        bookName: [{ required: true, message: "请输入书名", trigger: "blur" }],
        author: [{ required: true, message: "请输入作者", trigger: "blur" }],
        bookType: [
          { required: true, message: "请选择图书类别", trigger: "change" },
        ],
        type: [
          {
            required: true,
            message: "请选择",
            trigger: "change",
          },
        ],
        price: [
          { required: true, message: "价格不能为空", trigger: "blur" },
          { type: "number", message: "价格必须为数字值", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.axios({
            url: "bookInf/modify",
            method: "post",
            data: {
              bookId: this.ruleForm.id,
              bookName: this.ruleForm.bookName,
              type: this.ruleForm.bookType,
              author: this.ruleForm.author,
              price: this.ruleForm.price,
              language: this.ruleForm.type,
            },
          }).then((res) => {
            console.log(res.data);
            if (res.data.code == 200) {
              alert("修改成功");
              this.$router.push("/adminmanagelist");
            } else {
              alert("修改失败");
            }
          });
        } else {
          alert("请提交有效的信息");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style scoped>
.table {
  background: #c7eef4dd;
  width: 100%;
  height: 100%;
  position: relative;
  border-radius: 10px;
  padding-top: 60px;
}
.margin {
  margin-left: 30%;
}
.button1{
  width:60px;
  margin-left: 400px;
}
.button2{
  width:60px;
  margin-left: 100px;
}
</style>
