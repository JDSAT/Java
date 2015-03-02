package observablepersist;

import java.time.LocalDate;
import java.time.Month;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import observablepersist.data.DataManager;
import observablepersist.model.Employee;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {        
        DataManager<Employee> dbe=new DataManager("pu","Employee",Employee.class);
        
        for(int i=0;i<250000;i++){
            Employee e1=new Employee();        
            e1.setLastName("garrick");
            e1.setFirstName("ronald");
            e1.setMi("a");
            e1.setHireDate(LocalDate.of(2014, Month.MARCH, 13));
            e1.setTitle("ops research");
            dbe.insertEntity(e1);
        }
        
//        dbe.removeEntity(1127);
        
        dbe.removeAllEntities();
        
//        for(Employee p:dbe.retrieveAllEntities()){
//            System.out.println(p.getId()+""+'\t'+p.getLastName()+'\t'+p.getFirstName()+'\t'+p.getMi()+
//                    '\t'+p.getHireDate()+'\t'+p.getTitle());
//        }
        dbe.shutDown();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
