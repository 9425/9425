package util;

//下面的service指的是被传入的将要被代理的服务，然后创建处代理对象
public class ServiceFactory{
    public static Object getService(Object service){
        return new TransactionInvocationHandler(service).getProxy();
    }
}
