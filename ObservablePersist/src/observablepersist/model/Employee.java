package observablepersist.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import observablepersist.utils.LocalDateAdapter;

@Entity
@Access(AccessType.PROPERTY)
@Table(name="EMPLOYEE")
public class Employee implements Externalizable {
    
    private IntegerProperty id;
    private int _id;
 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "EMPLOYEE_ID")
    public final int getId() {
        if(id == null) return _id;
        else return id.get();
    }
 
    public final void setId(int id) {
        if(this.id==null) _id=id;
        else this.id.set(id);
    }
     
    public IntegerProperty idProperty() {
        if(id==null) id=new SimpleIntegerProperty(this, "id", _id);
        return id;
    }
    
    private StringProperty lastName;
    private String _lastName ;

    @Column(name="LAST_NAME")
    public String getLastName(){
        if(this.lastName==null) return _lastName;
        else return lastName.get();
    }
    
    public final void setLastName(String lastName) {
        if(this.lastName==null) _lastName=lastName;
        else this.lastName.set(lastName);
    }
    public StringProperty lastNameProperty() {
        if(lastName==null) lastName = new SimpleStringProperty(this, "lastName", _lastName);
        return lastName ;
    }
    
    private StringProperty firstName ;
    private String _firstName ;

    @Column(name="FIRST_NAME")
    public final String getFirstName() {
        if(this.firstName==null) return _firstName;
        else return firstName.get();
    }
    public final void setFirstName(String firstName){
        if(this.firstName==null) _firstName=firstName;
        else this.firstName.set(firstName);
    }
    
    public StringProperty firstNameProperty() {
        if (firstName == null) firstName = new SimpleStringProperty(this, "firstName", _firstName);
        return firstName ;
    }
    
    private StringProperty mi;
    private String _mi;
    
    @Column(name="MI")
    public final String getMi() {
        if(this.mi==null) return _mi;
        else return mi.get();
    }
    public final void setMi(String mi) {
        if(this.mi==null) _mi=mi;
        else this.mi.set(mi);
    }
    
    public StringProperty miProperty() {
        if (mi == null) mi = new SimpleStringProperty(this, "mi", _mi);
        return mi ;
    }
    
    private ObjectProperty<LocalDate> hireDate;
    private LocalDate _hireDate;
    
    @Column(name="HIRE_DATE")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getHireDate(){
        if(this.hireDate==null) return _hireDate;
        else return hireDate.get();
    }
    
    public void setHireDate(LocalDate hireDate){
        if(this.hireDate==null) _hireDate=hireDate;
        else this.hireDate.set(hireDate);
    }
    
    public ObjectProperty<LocalDate> hireDateProperty(){
        if(this.hireDate==null) return new SimpleObjectProperty<LocalDate>(this,"hireDate",_hireDate);
        return hireDate;
    }
    
    private StringProperty title;
    private String _title;
    
    @Column(name="TITLE")
    public String getTitle(){
        if(this.title==null) return _title;
        else return this.title.get();
    }
    
    public void setTitle(String title){
        if(this.title==null) _title=title;
        else this.title.set(title);
    }
    
    public StringProperty titleProperty(){
        if(this.title==null) return new SimpleStringProperty(this,"title",_title);
        return title;
    }
    
    @Override
    public int hashCode(){
        return 0;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Employee) {
            Employee other=(Employee) obj;
            return other.getId()==this.getId();
        } else {
            return false;
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(getId());
        out.writeObject(getLastName());
        out.writeObject(getFirstName());
        out.writeObject(getMi());
        out.writeObject(getHireDate());
        out.writeObject(getTitle());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId(in.readInt());
        setLastName((String)in.readObject());
        setFirstName((String)in.readObject());
        setMi((String) in.readObject());
        setHireDate((LocalDate)in.readObject());
        setTitle((String)in.readObject());
    }
}

