package com.quantum.util.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.quantum.log.QuantumLog;
import com.quantum.util.Displayable;

/**
 * @author BC Holmes
 */
public class ProxyFactory {

    static abstract class InvocationHandlerImpl implements InvocationHandler {
        protected final Displayable displayable;
        private final String type;
        protected final Object target;
        
        public InvocationHandlerImpl(String type, Displayable displayable, Object target) {
            this.type = type;
            this.displayable = displayable;
            this.target = target;
        }
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                QuantumLog.getInstance().debug(
                        "Invoking " + this.type + "." + method.getName() + "() for \"" + 
                        this.displayable.getDisplayName() + "\"");
                Object object = method.invoke(this.target, args);
                return handleSpecial(object, proxy);
            } catch (InvocationTargetException e) {
                QuantumLog.getInstance().error(
                        "Exception in " + this.type + " for \"" + 
                        this.displayable.getDisplayName() + "\"", 
                        e.getTargetException());
                throw e.getTargetException();
            }
        }
        protected Object handleSpecial(Object object, Object proxy) {
            return object;
        }
    }
    static class ConnectionInvocationHandler extends InvocationHandlerImpl {
        public ConnectionInvocationHandler(Displayable displayable, Connection connection) {
            super("Connection", displayable, connection);
        }
        protected Object handleSpecial(Object object, Object proxy) {
            if (object instanceof DatabaseMetaData) {
                return createOtherProxy("DatabaseMetaData", DatabaseMetaData.class,
                        this.displayable, object, (Connection) proxy);
            } else if (object instanceof PreparedStatement) {
                return createOtherProxy("PreparedStatement", PreparedStatement.class,
                        this.displayable, object, (Connection) proxy);
            } else if (object instanceof Statement) {
                return createOtherProxy("Statement", Statement.class,
                        this.displayable, object, (Connection) proxy);
            } else {
                return super.handleSpecial(object, proxy);
            }
        }
    }
    static class DriverInvocationHandler extends InvocationHandlerImpl {
        public DriverInvocationHandler(Displayable displayable, Driver driver) {
            super("Driver", displayable, driver);
        }
        protected Object handleSpecial(Object object, Object proxy) {
            if (object instanceof Connection) {
                return createConnectionProxy(this.displayable, (Connection) object);
            } else {
                return super.handleSpecial(object, proxy);
            }
        }
    }

    static class OtherInvocationHandler extends InvocationHandlerImpl {
        private final Connection connection;
        public OtherInvocationHandler(String type, Displayable displayable, Object target, Connection connection) {
            super(type, displayable, target);
            this.connection = connection;
        }
        protected Object handleSpecial(Object object, Object proxy) {
            if (object instanceof Connection) {
                return this.connection;
            } else if (object instanceof ResultSet) {
                return createOtherProxy("ResultSet", ResultSet.class,
                        this.displayable, object, this.connection);
            } else {
                return super.handleSpecial(object, proxy);
            }
        }
    }

    public static Connection createConnectionProxy(Displayable displayable, Connection connection) {
        return (Connection) Proxy.newProxyInstance(
                null, new Class[] { Connection.class }, 
                new ConnectionInvocationHandler(displayable, connection));
    }

    public static Driver createDriverProxy(Displayable displayable, Driver driver) {
        return driver == null ? null : (Driver) Proxy.newProxyInstance(
                null, new Class[] { Driver.class }, 
                new DriverInvocationHandler(displayable, driver));
    }
    public static Object createOtherProxy(String type, Class clazz, Displayable displayable, Object target, Connection connection) {
        return target == null ? null : Proxy.newProxyInstance(
                null, new Class[] { clazz }, 
                new OtherInvocationHandler(type, displayable, target, connection));
    }
}
