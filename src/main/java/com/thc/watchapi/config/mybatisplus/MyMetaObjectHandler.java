package com.thc.watchapi.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.thc.watchapi.model.Admin;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.config.mybatisplus
 * @Description: mybatisplus自动注入属性
 * @date 2020/11/12 4:49 下午
 */

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    private HttpServletRequest request;

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            // create_user 处理
            System.out.println("insert");
            System.out.println("request:");
            System.out.println(request);
            System.out.println(request.toString());
            Object object = request.getAttribute("currentUser");
            System.out.println("object");
            System.out.println(object);
//        Admin admin = (Admin) object;
            if(object!=null) {
                Admin admin = (Admin) object;
                this.setFieldValByName("createUser", admin.getUsername(), metaObject);
            }
//        this.setFieldValByName("createUser", admin.getUsername(), metaObject);
            this.setFieldValByName("gmtCreate", new Date(), metaObject);
            this.setFieldValByName("gmtModified", new Date(), metaObject);
            this.setFieldValByName("deleted", false, metaObject);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }
}
