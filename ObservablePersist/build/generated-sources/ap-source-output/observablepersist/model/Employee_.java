package observablepersist.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Employee.class)
public abstract class Employee_ {

	public static volatile SingularAttribute<Employee, String> lastName;
	public static volatile SingularAttribute<Employee, String> firstName;
	public static volatile SingularAttribute<Employee, LocalDate> hireDate;
	public static volatile SingularAttribute<Employee, Integer> id;
	public static volatile SingularAttribute<Employee, String> mi;
	public static volatile SingularAttribute<Employee, String> title;

}
