<template>
  <div>
    <!-- 搜索栏 -->
    <el-card id="search">
      <el-row>
        <el-col :span="22">
          <el-input v-model="searchModal.username" placeholder="用户名" clearable></el-input>
          <el-button @click="getUserList" type="primary" icon="el-icon-search" round>查询</el-button>
        </el-col>
        <!-- 新增按钮 -->
        <el-col :span="2" align="right">
          <el-button @click="openForm('新增用户')" type="primary" icon="el-icon-plus" circle></el-button>
        </el-col>
      </el-row>
    </el-card>
    <!-- 结果列表 -->
    <el-card id="list">
      <el-table :data="userList" stripe style="width: 100%">
        <el-table-column type="selection" width="55">
          </el-table-column>
        <el-table-column prop="date" align="center" label="#" width="80">
          <!-- (pageNo-1)*pageSize+index+1 -->
          <template slot-scope="scope">
            {{ (searchModal.pageNo - 1) * searchModal.pageSize + scope.$index + 1 }}
          </template>
          
        </el-table-column>
        <el-table-column prop="username" label="用户名" align="center" width="180">
        </el-table-column>
        <el-table-column prop="password" label="密码" align="center" width="180">
        </el-table-column>
        <el-table-column prop="role" label="角色" align="center" width="180">
        </el-table-column>
        <el-table-column fixed="right" prop="operator" label="操作" align="center" width="200">
          <template slot-scope="scope">
            <el-button type="primary" @click="editUser(scope.row,scope.$index)" plain>编辑</el-button>
            <el-button type="danger" @click="deleteUser(scope.row)" plain>删除</el-button>
          </template>
        </el-table-column>

      </el-table>
    </el-card>
    <!-- 分页组件 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
      :current-page="searchModal.pageNo" :page-sizes="[5, 10, 50, 100]" :page-size="searchModal.pageSize"
      layout="total, sizes, prev, pager, next, jumper" :total="total">
    </el-pagination>

    <!-- 新增的对话框 -->
    <!-- 新增表单 -->
    <el-dialog @close='clearForm' id="add" :title="title" :visible.sync="dialogAddVisible">
      <el-form :model="UserModal" ref='userFromRef' :rules="rules">
        <el-form-item label="用户名" prop="username" :label-width="formLabelWidth">
          <el-input v-if="title == '新增用户'" v-model="UserModal.username" autocomplete="off"></el-input>
          <el-input v-if="title == '修改用户'" v-model="UserModal.username" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" :label-width="formLabelWidth">
          <el-input v-model="UserModal.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色" :label-width="formLabelWidth">
          <el-select v-model="UserModal.role" placeholder="请选择班级">
            <el-option label="超级管理员" value="admin"></el-option>
            <el-option label="老师" value="teacher"></el-option>
            <el-option label="学生" value="userdent"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取 消</el-button>
        <el-button v-if="this.title == '新增用户'" type="primary" @click="saveUser('0')">确 定</el-button>
        <el-button v-if="this.title == '修改用户'" type="primary" @click="saveUser('1')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import userApi from '@/api/userOP'

export default {
  data() {
    return {
      total: 0,
      searchModal: {
        pageNo: 1,
        pageSize: 10
      },
      UserModal: {
        username:"",
        password:"",
        role:"",
      },
      userList: [],
      dialogAddVisible: false,
      formLabelWidth: "130px",
      formLabelWidth2: "300px",
      title: "",
      rules: {
        // sno: [
        //   { required: true, message: '请输入学号', trigger: 'blur' },
        //   { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
        // ],
        // name: [
        //   { required: true, message: '请输入姓名', trigger: 'blur' },
        //   { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        // ],
      }
    }
  },
  methods: {
    editUser(user,index) {
      this.UserModal = user;
      this.userList[index]=JSON.parse(JSON.stringify(user))
      this.openForm('修改用户');
    },
    deleteUser(user) {
      this.$confirm('此操作将永久删除 ' + user.username + ' , 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        userApi.deleteUser(user).then(response => {
          this.$message({
            type: 'success',
            message: response.message
          });
          this.getUserList();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    saveUser(tempid) {
      this.$refs.userFromRef.validate((valid) => {
        if (valid) {
          //提交验证请
          userApi.saveUser(this.UserModal, tempid).then(response => {
            //成功提示  
            if (response.code == 20000) {
              this.$message({
                message: response.message,
                type: 'success'
              });
              //关闭对话框
              this.dialogAddVisible = false;
              //刷新表格
              this.getUserList();
            }
            else {
              this.$message.error(
                response.message
              );
            }
          });
        }
        else {
          console.log("error submit");
          return false;
        }
      });
    },
    handleSizeChange(pageSize) {
      this.searchModal.pageSize = pageSize;
      this.getUserList();
    },
    handleCurrentChange(pageNo) {
      this.searchModal.pageNo = pageNo;
      this.getUserList();
    },
    getUserList() {
      userApi.getUserList(this.searchModal).then(response => {
        this.userList = response.data.data;
        this.total = response.data.total;
      });
    },
    openForm(title) {
      this.dialogAddVisible = true;
      this.title = title;
    },
    clearForm() {
      this.UserModal = {};
      this.$refs.userFromRef.clearValidate();
    }
  },
  // 钩子函数，加载的时候就执行
  created() {
    this.getUserList();
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
