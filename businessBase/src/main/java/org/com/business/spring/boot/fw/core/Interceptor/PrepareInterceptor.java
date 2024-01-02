package org.com.business.spring.boot.fw.core.Interceptor;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class PrepareInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		String methodName = invocation.getMethod().getName();
		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

		if ("update".equals(methodName)) {
			Object object = invocation.getArgs()[1];
			Date currentDate = new Date(System.currentTimeMillis());

			if (SqlCommandType.INSERT.equals(sqlCommandType)) {
				Field fieldCreateTime = object.getClass().getDeclaredField("createtime");
				fieldCreateTime.setAccessible(true);
				fieldCreateTime.set(object, currentDate);

				Field fieldModifyTime = object.getClass().getDeclaredField("updatetime");
				fieldModifyTime.setAccessible(true);
				fieldModifyTime.set(object, currentDate);

			} else if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
				Field fieldModifyTime = object.getClass().getDeclaredField("updatetime");
				fieldModifyTime.setAccessible(true);
				fieldModifyTime.set(object, currentDate);
			}
		} else if ("insert".equals(methodName)) {

		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}
}