package cn.linz.base.demo1;

import cn.linz.base.common.model.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import java.util.Objects;

/**
 * test
 *
 * @author taogl
 * @date 2021/12/12 01:04
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@GenericGenerator(name = BaseEntity.ID_GENERATOR_NAME, strategy = "guid")
public class Test1 extends BaseEntity<String> {

    private static final long serialVersionUID = -8869179051522090862L;

    private String name;

    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Test1 test1 = (Test1) o;
        return getId() != null && Objects.equals(getId(), test1.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
