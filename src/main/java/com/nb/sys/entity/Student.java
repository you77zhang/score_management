package com.nb.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zp
 * @since 2023-06-13
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 班级
     */
    private String classes;

    /**
     * 名字
     */
    private String name;

    /**
     * 成绩
     */
    private Integer score;

    /**
     * 描述
     */
    private String desc;
    
    public Student(){
        id = null;
        username="";
        classes = "";
        name = "";
        score = 0;
        desc = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + id +
            ", username=" + username +
            ", classes=" + classes +
            ", name=" + name +
            ", score=" + score +
            ", desc=" + desc +
        "}";
    }
}
