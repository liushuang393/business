package org.com.business.spring.boot.fw.core.Interceptor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
/**
 *
 * @author 79027
 * 插件签名，告诉mybatis插件用来拦截那个对象的那个方法
 */
//@Intercepts({
//	@Signature(type=ResultSetHandler.class,method="handleResultSets",args=Statement.class)
//})
//@Intercepts( {
//    @Signature(method = "query", type = Executor.class, args = {
//           MappedStatement.class, Object.class, RowBounds.class,
//           ResultHandler.class }),
//
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Intercepts({
        //    @Signature(method = "update", type = Executor.class, args = { MappedStatement.class, Object.class }),
        @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class, Integer.class })
})
@Slf4j
public class MybatisInterceptor implements Interceptor {

    private long maxSize = 0L;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();

        MetaObject metaObject = MetaObject.forObject(statementHandler,
                SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                new DefaultReflectorFactory());

        MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");

        String id = mappedStatement.getId();

        if (id.matches(".select*")) {
            BoundSql boundSql = statementHandler.getBoundSql();

            String sql = boundSql.getSql();

            String countSql = "select count(*) from (" + sql + ") a";

            Connection connection = (Connection)invocation.getArgs()[0];
            PreparedStatement countStatement = connection.prepareStatement(countSql);

            ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
            parameterHandler.setParameters(countStatement);
            ResultSet rs = countStatement.executeQuery();
            Map<?,?> parameter = (Map<?,?>)boundSql.getParameterObject();

            if(rs.next()) {
                long cont= rs.getLong(1);
            }

        } else if (id.matches(".+updateBy*")) {

        }


        Object o = invocation.proceed();
        return o;
    }

    @Override
    public Object plugin(Object target) {

        Object o = Plugin.wrap(target, this);
        return o;
    }

    @Override
    public void setProperties(Properties properties) {
        String value = (String) properties.get("maxSize");
        if (!StringUtils.isEmpty(value)) {
            this.maxSize = Long.parseLong(value);
        }

    }

    /**
     * 根据原Sql语句获取对应的查询总记录数的Sql语句
     * @param sql
     * @return
     */
    private String getCountSql(String sql) {
        int index = sql.indexOf("from");
        return "select count(*) " + sql.substring(index);
    }
}

/**
 * 利用反射进行操作的一个工具类
 *
 */
class ReflectUtil {
    /**
    * 利用反射获取指定对象的指定属性
    * @param obj 目标对象
    * @param fieldName 目标属性
    * @return 目标属性的值
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
    */
    public static Object getFieldValue(Object obj, String fieldName)
            throws IllegalArgumentException, IllegalAccessException {
        Object result = null;
        Field field = ReflectUtil.getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            result = field.get(obj);
        }
        return result;
    }

    /**
    * 利用反射获取指定对象里面的指定属性
    * @param obj 目标对象
    * @param fieldName 目标属性
    * @return 目标字段
    */
    private static Field getField(Object obj, String fieldName) {
        Field field = null;
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                return null;
            }
        }
        return field;
    }

    /**
    * 利用反射设置指定对象的指定属性为指定的值
    * @param obj 目标对象
    * @param fieldName 目标属性
     * @param fieldValue 目标值
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
    */
    public static void setFieldValue(Object obj, String fieldName,
            String fieldValue) throws IllegalArgumentException, IllegalAccessException {
        Field field = ReflectUtil.getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            field.set(obj, fieldValue);
        }
    }
}