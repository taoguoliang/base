package cn.linz.base.config;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.util.ReflectUtil;
import cn.linz.base.exception.IdGeneratorException;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentityGenerator;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Objects;
import java.util.Properties;

/**
 * 自定义id生成器
 *
 * @author taogl
 * @version 1.0.0
 */
public class CustomIdentifierGenerator extends IdentityGenerator implements Configurable {

    private static final UUIDGenerator UUID_GENERATOR = new UUIDGenerator();

    /** {@inheritDoc} */
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        try {
            Object fieldValue = ReflectUtil.getFieldValue(object, "id");
            if (fieldValue != null) {
                return (Serializable) fieldValue;
            }
        } catch (SecurityException | UtilException e) {
            throw new IdGeneratorException("ID生成异常:" + e.getMessage(), e);
        }

        ParameterizedType superClass = (ParameterizedType) object.getClass().getGenericSuperclass();
        Class<?> type = (Class<?>) superClass.getActualTypeArguments()[0];
        if (Objects.equals(type, String.class)) {
            return UUID_GENERATOR.generate(session, object);
        } else if (Objects.equals(type, Integer.class) ||
                Objects.equals(type, Long.class)) {
            return super.generate(session, object);
        }
        throw new IdGeneratorException("ID生成异常,主键类型未配置生成器:" + type);
    }

    /** {@inheritDoc} */
    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        if (type instanceof StringType) {
            UUID_GENERATOR.configure(type, params, serviceRegistry);
        }
    }

}
