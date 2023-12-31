import request from '@/utils/request'


export default{
    getTeacherList(searchModel){
        return request({
            url: '/teacher/list',
            method: 'get',
            params:{
                pageNo: searchModel.pageNo,
                pageSize: searchModel.pageSize,
                // TODO 改
                username: searchModel.username,
                name: searchModel.name,
            }
        });
    },
    addTeacher(teacher){
        return request({
            url: '/teacher',
            method: 'post',
            data: teacher
        });
    },
    getOne(username){
        return request({
            // url: '/teacher',
            url: `/teacher/getOne`,
            method: 'get',
            params:{
                username: username
            }
        });
    },
    updateTeacher(teacher){
        return request({
            url: '/teacher',
            method: 'put',
            data: teacher
        });
    },
    saveTeacher(teacher,id){
        if(id == 0) return this.addTeacher(teacher);
        else if(id == 1) return this.updateTeacher(teacher);
    },
    deleteTeacher(id){
        return request({
            url: '/teacher/'+id,
            method: 'delete'
        });
    }
}