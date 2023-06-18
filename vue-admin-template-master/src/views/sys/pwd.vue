<template>
  <div>
    <el-form :model="UserModal" ref='userFromRef' :rules="rules">
      <el-form-item label="原密码" prop="prePassword" :label-width="formLabelWidth">
        <el-input v-model="UserModal.prePassword" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword" :label-width="formLabelWidth">
        <el-input v-model="UserModal.newPassword" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="确认新密码" prop="newPassword2" :label-width="formLabelWidth">
        <el-input v-model="UserModal.newPassword2" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer" align="center">
      <el-button type="primary" @click="saveUser()">确 定</el-button>
      <el-button type="primary" @click="cancel()">取 消</el-button>
    </div>
  </div>
</template>

<script>
import userApi from '@/api/userOP'
import Vue from 'vue'
export default {
  data() {
    return {
      total: 0,
      searchModal: {
        pageNo: 1,
        pageSize: 10
      },
      UserModal: {
        username: "",
        prePassword: "",
        newPassword: "",
        newPassword2: ""
      },
      Userdata: {
        username: "",
        password: "",
        role: ""
      },
      formLabelWidth: "130px",
      formLabelWidth2: "300px",
      title: "",
      rules: {
        prePassword: [
          { required: true, message: '请输入原密码', trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
        ],
        newPassword: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
        ],
        newPassword2: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
        ],
      }
    }
  },
  methods: {
    saveUser() {
      this.Userdata = Vue.prototype.$user;
      this.UserModal.username = this.Userdata.username;
      // this.UserModal.username = Vue.$prototype.user.username;
      if (this.UserModal.newPassword != this.UserModal.newPassword2) {
        this.$message.error(
          "请将确认密码正确"
        );
        return;
      }
      this.$refs.userFromRef.validate((vaild) => {
        if(vaild){
          //提交验证请
          userApi.updatePassword(this.UserModal).then(response => {
            //成功提示  
            if (response.code == 20000) {
              this.$message({
                message: response.message,
                type: 'success'
              });
              //关闭对话框
              this.cancel();
            }
            else {
              this.$message.error(
                response.message
              );
            }
          });  
        }
        else{
          console.log("error submit");
          return false;
        }
        
      });
    },
    cancel() {
      this.clearForm();
      this.$router.push('/')
    },
    clearForm() {
      this.UserModal = {};
      this.$refs.userFromRef.clearValidate();
    }
  },
  // 钩子函数，加载的时候就执行
  created() {
  }
};
</script>

<style>
#search .el-input {
  width: 200px;
  margin-right: 10px;
}

/* .el-card{
  background-image: url(../../assets/5.png);
  background-position: center;
  background-size: 100%;

}
.el-table{
  opacity: 0.8;
} */
.el-dialog .el-input {
  width: 100%;
}

/* .el-input {
  width: 200px;
  margin-right: 10px;
} */
</style>
