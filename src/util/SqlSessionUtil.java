package util;

import jdk.internal.dynalink.beans.StaticClass;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

//这个类的作用是创建出session以供后面使用
public class SqlSessionUtil {
    private static SqlSessionFactory sqlSessionFactory;
    private static ThreadLocal<SqlSession> t=new ThreadLocal<SqlSession>();
    public SqlSessionUtil() {
    }
    //此处是将数据库连接的.xml文件进行注册
    static {
        String resource="mybatis-config.xml";
        InputStream inputStream=null;
        try {
            inputStream= Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
    }
    //取得SqlSession对象
    public static SqlSession getSession(){
        SqlSession session=sqlSessionFactory.openSession();
        if (session==null){
            session=sqlSessionFactory.openSession();
            //如果少了这句，session就不会加入到线程池中，无法进行操作
            t.set(session);
        }
        return session;
    }

    //关闭SqlSession对象
    public static void myClose(SqlSession session){
        if (session!=null){
            session.close();

            //下面这句代码是必不可少的
            t.remove();
        }
    }
}
