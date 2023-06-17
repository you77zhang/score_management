import request from '@/utils/request'


export default{
    getUserList(searchModel){
        return request({
            url: '/user/list',
            method: 'get',
            params:{
                pageNo: searchModel.pageNo,
                pageSize: searchModel.pageSize,
                username: searchModel.username
            }
        });
    },
    addUser(user){
        return request({
            url: '/user/add',
            method: 'post',
            data: user
        });
    },
    updateUser(user){
        return request({
            url: '/user',
            method: 'put',
            data: user
        });
    },
    saveStu(stu,id){
        if(id == 0) return this.addStu(stu);
        else if(id == 1) return this.updateStu(stu);
    },
    deleteStu(user){
        return request({
            url: '/user/',
            method: 'post',
            data: user
        });
    }
}