<template>
  <div>
    <!-- 搜索栏 -->
    <el-card id="search">
      <el-row>
        <el-col :span="22">
          <el-input v-model="searchModal.username" placeholder="用户名" clearable></el-input>
          <el-input v-model="searchModal.name" placeholder="姓名" clearable></el-input>
          <el-button @click="getStuList" type="primary" icon="el-icon-search" round>查询</el-button>
        </el-col>
        <!-- 新增按钮 -->
        <el-col :span="2" align="right">
          <el-button @click="openForm('新增用户')" type="primary" icon="el-icon-plus" circle></el-button>
        </el-col>
      </el-row>
    </el-card>
    <!-- 结果列表 -->
    <el-card id="list">

      <el-table :data="stuList" stripe style="width: 100%">
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
        <el-table-column prop="name" label="姓名" align="center" width="180">
        </el-table-column>
        <el-table-column prop="classes" label="班级" align="center" width="180">
        </el-table-column>
        <el-table-column prop="score" label="分数" align="center" width="180">
        </el-table-column>
        <el-table-column prop="desc" label="描述" align="center">
        </el-table-column>
        <el-table-column fixed="right" prop="operator" label="操作" align="center" width="200">
          <template slot-scope="scope">
            <el-button type="primary" @click="editStu(scope.row,scope.$index)" plain>编辑</el-button>
            <el-button type="danger" @click="deleteStu(scope.row)" plain>删除</el-button>
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
      <el-form :model="stuModal" ref='stuFromRef' :rules="rules">
        <el-form-item label="用户名" prop="username" :label-width="formLabelWidth">
          <el-input v-if="title == '新增用户'" v-model="stuModal.username" autocomplete="off"></el-input>
          <el-input v-if="title == '修改用户'" v-model="stuModal.username" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name" :label-width="formLabelWidth">
          <el-input v-model="stuModal.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="班级" :label-width="formLabelWidth">
          <el-select v-model="stuModal.classes" placeholder="请选择班级">
            <el-option label="20计科1班" value="20计科1班"></el-option>
            <el-option label="20计科2班" value="20计科2班"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分数" prop="score" :label-width="formLabelWidth">
          <el-input v-model="stuModal.score" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述" :label-width="formLabelWidth">
          <el-input v-model="stuModal.desc" type="textarea" :rows="3" placeholder="请输入内容" maxlength="200"
            show-word-limit></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取 消</el-button>
        <el-button v-if="this.title == '新增用户'" type="primary" @click="saveStu('0')">确 定</el-button>
        <el-button v-if="this.title == '修改用户'" type="primary" @click="saveStu('1')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import stuApi from '@/api/stu'
// import { getList } from '@/api/table';

export default {
  data() {
    return {
      total: 0,
      searchModal: {
        pageNo: 1,
        pageSize: 10
      },
      stuModal: {
        username:"",
        name:"",
        classes:"",
        score:"",
        desc:"",
      },
      stuList: [],
      dialogAddVisible: false,
      formLabelWidth: "130px",
      formLabelWidth2: "300px",
      title: "",
      rules: {
        // sno: [
        //   { required: true, message: '请输入学号', trigger: 'blur' },
        //   { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
        // ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ],
      }
    }
  },
  methods: {
    editStu(stu,index) {
      this.stuModal = stu;
      this.stuList[index]=JSON.parse(JSON.stringify(stu))
      this.openForm('修改用户');
    },
    deleteStu(stu) {
      this.$confirm('此操作将永久删除 ' + stu.name + ' , 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        stuApi.deleteStu(stu.username).then(response => {
          this.$message({
            type: 'success',
            message: response.message
          });
          this.getStuList();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    saveStu(tempid) {
      this.$refs.stuFromRef.validate((valid) => {
        if (valid) {
          //提交验证请
          stuApi.saveStu(this.stuModal, tempid).then(response => {
            //成功提示  
            if (response.code == 20000) {
              this.$message({
                message: response.message,
                type: 'success'
              });
              //关闭对话框
              this.dialogAddVisible = false;
              //刷新表格
              this.getStuList();
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
      this.getStuList();
    },
    handleCurrentChange(pageNo) {
      this.searchModal.pageNo = pageNo;
      this.getStuList();
    },
    getStuList() {
      stuApi.getStuList(this.searchModal).then(response => {
        this.stuList = response.data.data;
        this.total = response.data.total;
      });
    },
    openForm(title) {
      this.dialogAddVisible = true;
      this.title = title;
    },
    clearForm() {
      this.stuModal = {};
      this.$refs.stuFromRef.clearValidate();
    }
  },
  // 钩子函数，加载的时候就执行
  created() {
    this.getStuList();
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
