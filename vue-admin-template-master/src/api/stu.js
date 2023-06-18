import request from '@/utils/request'


export default{
    getStuList(searchModel){
        return request({
            url: '/student/list',
            method: 'get',
            params:{
                pageNo: searchModel.pageNo,
                pageSize: searchModel.pageSize,
                username: searchModel.username,
                name: searchModel.name,
            }
        });
    },
    getOne(username){
        return request({
            // url: '/teacher',
            url: `/student/getOne`,
            method: 'get',
            params:{
                username: username
            }
        });
    },
    addStu(stu){
        return request({
            url: '/student/addStudent',
            method: 'post',
            data: stu
        });
    },
    updateStu(stu){
        return request({
            url: '/student',
            method: 'put',
            data: stu
        });
    },
    saveStu(stu,id){
        if(id == 0) return this.addStu(stu);
        else if(id == 1) return this.updateStu(stu);
    },
    deleteStu(username){
        return request({
            url: '/student/'+username,
            method: 'delete'
        });
    }
}