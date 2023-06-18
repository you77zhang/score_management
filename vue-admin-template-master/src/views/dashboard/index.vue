<template>
  <!-- <div>欢迎~ {{ userModal.username }}</div> -->
  <div id='app'>
    <div>欢迎 {{ userModal.username }}</div>
    <el-table v-if="userModal.roles == 'teacher'" :data="userList" stripe style="width: 100%">
      <el-table-column prop="username" label="用户名" align="center">
      </el-table-column>
      <el-table-column prop="name" label="姓名" align="center">
      </el-table-column>
      <el-table-column prop="desc" label="描述" align="center">
      </el-table-column>
    </el-table>
    <el-table v-if="userModal.roles == 'student'" :data="userList" stripe style="width: 100%">
      <el-table-column prop="username" label="用户名" align="center">
      </el-table-column>
      <el-table-column prop="name" label="姓名" align="center">
      </el-table-column>
      <el-table-column prop="classes" label="班级" align="center">
      </el-table-column>
      <el-table-column prop="score" label="成绩" align="center">
      </el-table-column>
      <el-table-column prop="desc" label="描述" align="center">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>

import { mapGetters } from 'vuex'
import Vue from 'vue'
import teacherApi from '@/api/teacher'
import studentApi from '@/api/stu'
export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  data(){
    return{
      userModal:{
        username:"",
        password:"",
        roles:""
      },
      userdata:{
        username:"",
        name:"",
        desc:"",
        classes:"",
        score:""
      },
      userList:[]
    }
  },
  methods:{
    getInfo(){
      this.userModal = Vue.prototype.$user;
      console.log(this.userModal);
    },
    getList(){
      if(this.userModal.roles =='teacher'){
        teacherApi.getOne(this.userModal.username).then(response => {
          debugger
          this.userList = response.data.data;
          console.log(this.userdata);
        });
      }
      else if(this.userModal.roles == 'student'){
        studentApi.getOne(this.userModal.username).then(response => {
          this.userList = response.data.data;
        });
      }
    }
  },
  created(){
    this.getInfo();
    this.getList();
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }

  &-text {
    font-size: 30px;
    line-height: 46px;
  }

}
</style>
