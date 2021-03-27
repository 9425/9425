package util;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//这个类的作用是创建处jdk代理对象，并且进行功能增强
public class TransactionInvocationHandler implements InvocationHandler {
    private Object target;

    public TransactionInvocationHandler(Object target) {
        this.target = target;
    }

    //上面的target是传入的被服务，将要被代理，下面将会对其进行功能增强

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SqlSession session=null;
        Object obj=null;
        try {
            session=SqlSessionUtil.getSession();
            //处理业务逻辑，对其进行功能增强
            obj=method.invoke(target,args);

            //处理结束业务逻辑，提交事务
            session.commit();
        }catch (Exception e){
            session.rollback();
            e.printStackTrace();
        }finally {
            SqlSessionUtil.myClose(session);
        }
        return obj;
    }

    //创建处代理对象
    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
}
