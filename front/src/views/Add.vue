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
        <el-form-item label="图书名" prop="bookName" class="margin">
          <el-input v-model="ruleForm.bookName" style="width: 400px"></el-input>
        </el-form-item>

        <el-form-item label="图书作者" prop="author" class="margin">
          <el-input v-model="ruleForm.author" style="width: 400px"></el-input>
        </el-form-item>

        <el-form-item label="图书类别" prop="bookType" class="margin">
          <el-select v-model="ruleForm.bookType" placeholder="请选择">
            <el-option label="类别一" :value="0"></el-option>
            <el-option label="类别二" :value="1"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="语言" prop="type" class="margin">
          <el-select v-model="ruleForm.type" placeholder="请选择">
            <el-option label="中文" value="中文"></el-option>
            <el-option label="英文" value="英文"></el-option>
            <el-option label="其他" value="其他"></el-option>
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
            class="margin"
            >提交</el-button
          >
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Add",
  data() {
    return {
      ruleForm: {
        bookName: "",
        bookType: 0,
        author: "",
        type: "",
        price: "",
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
          this.$axios({
            url: "bookInf/add",
            method: "post",
            data: {
              bookName: this.ruleForm.bookName,
              type: this.ruleForm.bookType,
              author: this.ruleForm.author,
              price: this.ruleForm.price,
              language: this.ruleForm.type,
            },
          }).then((res) => {
            console.log(res.data);
            if (res.data.code == 200) {
              alert("添加成功");
              this.$router.push("/adminmanagelist");
            } else {
              alert("添加失败");
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
  background: #ffffffdd;
  width: 100%;
  height: 86%;
  position: relative;
  border-radius: 10px;
  padding-top: 60px;
}
.margin {
  margin-left: 30%;
}
</style>
