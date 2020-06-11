package packageForStruct.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import packageForStruct.Main;
import packageForStruct.workClasses.Subject;
import packageForStruct.workClasses.Group;
import packageForStruct.workClasses.Teacher;
import packageForStruct.workClasses.Variables;

import java.net.URL;
import java.util.ResourceBundle;

public class DBFillingC implements Initializable {

    private ToggleGroup[] groupOfToggle = new ToggleGroup[7];
    private SQLiteC SQLiteC = new SQLiteC();
    private Variables variables = new Variables();

    public void initialize(URL location, ResourceBundle resources){
        listUpdate();
        toggleRadio();
        selectRadio(variables.groups);
    }

    /**
     * Метод, обновляющий списки
     */
    public void listUpdate(){
        variables.update();
        this.groupList.setItems(variables.listOfGroups);
        this.subjectList.setItems(variables.listOfSubjects);
    }

    /**
     * Метод, который на основе базы данных выбирает RadioButton
     * @param groups
     */
    public void selectRadio(ObservableList<Group> groups){
        for (Group group:groups) {
            switch(group.getNumber()){
                case 5:
                    switch(group.getCount()){
                        case 1:
                            Radio5A.setSelected(true);
                            break;
                        case 2:
                            Radio5AB.setSelected(true);
                            break;
                        case 3:
                            Radio5ABV.setSelected(true);
                            break;
                        case 4:
                            Radio5ABVG.setSelected(true);
                            break;
                        case 5:
                            Radio5ABVGD.setSelected(true);
                            break;
                    }
                case 6:
                    switch(group.getCount()){
                        case 1:
                            Radio6A.setSelected(true);
                            break;
                        case 2:
                            Radio6AB.setSelected(true);
                            break;
                        case 3:
                            Radio6ABV.setSelected(true);
                            break;
                        case 4:
                            Radio6ABVG.setSelected(true);
                            break;
                        case 5:
                            Radio6ABVGD.setSelected(true);
                            break;
                    }
                case 7:
                    switch(group.getCount()){
                        case 1:
                            Radio7A.setSelected(true);
                            break;
                        case 2:
                            Radio7AB.setSelected(true);
                            break;
                        case 3:
                            Radio7ABV.setSelected(true);
                            break;
                        case 4:
                            Radio7ABVG.setSelected(true);
                            break;
                        case 5:
                            Radio7ABVGD.setSelected(true);
                            break;
                    }
                case 8:
                    switch(group.getCount()){
                        case 1:
                            Radio8A.setSelected(true);
                            break;
                        case 2:
                            Radio8AB.setSelected(true);
                            break;
                        case 3:
                            Radio8ABV.setSelected(true);
                            break;
                        case 4:
                            Radio8ABVG.setSelected(true);
                            break;
                        case 5:
                            Radio8ABVGD.setSelected(true);
                            break;
                    }
                case 9:
                    switch(group.getCount()){
                        case 1:
                            Radio9A.setSelected(true);
                            break;
                        case 2:
                            Radio9AB.setSelected(true);
                            break;
                        case 3:
                            Radio9ABV.setSelected(true);
                            break;
                        case 4:
                            Radio9ABVG.setSelected(true);
                            break;
                        case 5:
                            Radio9ABVGD.setSelected(true);
                            break;
                    }
                case 10:
                    switch(group.getCount()){
                        case 1:
                            Radio10A.setSelected(true);
                            break;
                        case 2:
                            Radio10AB.setSelected(true);
                            break;
                        case 3:
                            Radio10ABV.setSelected(true);
                            break;
                        case 4:
                            Radio10ABVG.setSelected(true);
                            break;
                        case 5:
                            Radio10ABVGD.setSelected(true);
                            break;
                    }
                case 11:
                    switch(group.getCount()){
                        case 1:
                            Radio11A.setSelected(true);
                            break;
                        case 2:
                            Radio11AB.setSelected(true);
                            break;
                        case 3:
                            Radio11ABV.setSelected(true);
                            break;
                        case 4:
                            Radio11ABVG.setSelected(true);
                            break;
                        case 5:
                            Radio11ABVGD.setSelected(true);
                            break;
                    }
            }
        }
    }

    /**
     * Метод, который связывает RadioButton в одну группу
     *
     * @param objects
     * @return
     */
    public ToggleGroup setToggleGroup(RadioButton ... objects){
        ToggleGroup group = new ToggleGroup();
        for (RadioButton object:objects) {
            object.setToggleGroup(group);
        }
        return group;
    }

    /**
     * Метод, заполняющий все группы RadioButton
     */
    public void toggleRadio(){
        groupOfToggle[0] = setToggleGroup(Radio5A, Radio5AB, Radio5ABV, Radio5ABVG, Radio5ABVGD);
        groupOfToggle[1] = setToggleGroup(Radio6A, Radio6AB, Radio6ABV, Radio6ABVG, Radio6ABVGD);
        groupOfToggle[2] = setToggleGroup(Radio7A, Radio7AB, Radio7ABV, Radio7ABVG, Radio7ABVGD);
        groupOfToggle[3] = setToggleGroup(Radio8A, Radio8AB, Radio8ABV, Radio8ABVG, Radio8ABVGD);
        groupOfToggle[4] = setToggleGroup(Radio9A, Radio9AB, Radio9ABV, Radio9ABVG, Radio9ABVGD);
        groupOfToggle[5] = setToggleGroup(Radio10A, Radio10AB, Radio10ABV, Radio10ABVG, Radio10ABVGD);
        groupOfToggle[6] = setToggleGroup(Radio11A, Radio11AB, Radio11ABV, Radio11ABVG, Radio11ABVGD);
    }

    /**
     * Метод, возвращающий число выбранных пользователем классов
     * @param toggle
     * @return
     */
    public int getCountOfGroups(Toggle toggle){
        String str = toggle.toString();
        int indexOfId = str.indexOf("id=");
        int indexOfA = str.indexOf("A", indexOfId);
        int indexOfNext = str.indexOf(",",indexOfId);
        int count = indexOfNext - indexOfA;
        return count;
    }

    /**
     * Метод, возвращающий предметы для заданной группы
     * @param group
     * @return
     */
    public ObservableList<Subject> takeSubjectsOfGroup(Object group){
        SQLiteC.connect();
        ObservableList<ObservableList> subjects = SQLiteC.queryRows("Предметы");
        ObservableList<ObservableList> subjectsForGroup = FXCollections.observableArrayList();
        subjects.forEach(subject -> {
            if (subject.get(0).equals(group)) subjectsForGroup.add(subject);
        });
        SQLiteC.disconnect();
        ObservableList<Subject> result = FXCollections.observableArrayList();
        subjectsForGroup.forEach(subject ->{
            if(subject.get(1)!= null && subject.get(2)!= null)result.add(new Subject(subject.get(1).toString(), subject.get(2).toString()));
            else result.add(new Subject());
        });
        return result;
    }

    /**
     * Метод, возвращающий преподавателей для предмета
     * @param subject
     * @return
     */
    public ObservableList<Teacher> takeTeachersOfSubject(Object subject){
        SQLiteC.connect();
        ObservableList<ObservableList> teachers = SQLiteC.queryRows("Преподаватели");
        ObservableList<ObservableList> teachersForSubject = FXCollections.observableArrayList();
        teachers.forEach(teacher ->{
            if(teacher.get(2).equals(subject)) teachersForSubject.add(teacher);
        });
        SQLiteC.disconnect();
        ObservableList<Teacher> result = FXCollections.observableArrayList();
        teachersForSubject.forEach(teacher ->{
            if(teacher.get(1) != null && teacher.get(3) != null && teacher.get(4) != null)
                result.add(new Teacher(teacher.get(1).toString(),teacher.get(4).toString(), teacher.get(3).toString()));
            else result.add(new Teacher());
        });
        return result;
    }

    /***********************************************************************************|
     *                                                                                  |
     *                                                                                  |
     *                                  События                                         |
     *                                                                                  |
     *                                                                                  |
     ************************************************************************************/

    @FXML
    public void updateTVFromSubject(){
        Object item = subjectList.getSelectionModel().getSelectedItem();
        TableColumn<Teacher,String> nameColumn = new TableColumn<>("ФИО преподавателя");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Teacher,String> loadColumn = new TableColumn<>("Часы в неделю");
        loadColumn.setCellValueFactory(new PropertyValueFactory<>("load"));

        TableColumn<Teacher,String> classroomColumn = new TableColumn<>("Аудитория");
        classroomColumn.setCellValueFactory(new PropertyValueFactory<>("classroom"));

        tableViewTeachers.setItems(takeTeachersOfSubject(item));
        tableViewTeachers.getColumns().setAll(nameColumn, loadColumn, classroomColumn);

    }
    @FXML
    public void updateTVFromGroup(){
        Object item = groupList.getSelectionModel().getSelectedItem();
        TableColumn<Subject, String> nameColumn = new TableColumn<>("Предметы");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Subject, String> loadColumn = new TableColumn<>("Часы в неделю");
        loadColumn.setCellValueFactory(new PropertyValueFactory<>("load"));

        takeSubjectsOfGroup(item).forEach(System.out::println);

        this.tableViewSubjects.setItems(takeSubjectsOfGroup(item));
        this.tableViewSubjects.getColumns().setAll(nameColumn, loadColumn);
    }
    @FXML
    public void saveCountOfGroups(){
        SQLiteC.connect();
        ObservableList<Group> groups = FXCollections.observableArrayList();
        for (int i = 0; i < groupOfToggle.length; i++) {
            int count = getCountOfGroups(groupOfToggle[i].getSelectedToggle());
            groups.add(new Group(i+5,count));
        }
        groups.forEach(group ->
                SQLiteC.updateRow("Классы", "КолКлассов", "Класс = "+group.getNumber(), group.getCount())
        );
        SQLiteC.disconnect();
    }
    @FXML
    public void addSubject(){
        Main.showAddSubjectWindow(groupList.getSelectionModel().getSelectedItem(),this);
    }
    @FXML
    public  void deleteSubject(){
        SQLiteC.connect();
        ObservableList<Integer> id = FXCollections.observableArrayList();
        Subject subject = tableViewSubjects.getSelectionModel().getSelectedItem();
        ObservableList<ObservableList> rows = SQLiteC.queryRows("Предметы");
        rows.forEach(row ->{
            if (row.get(0).equals(groupList.getSelectionModel().getSelectedItem())){
                int res = (row.get(3) != null)?Integer.parseInt(row.get(3).toString()):0;
                String name = (row.get(1) != null)?row.get(1).toString():"";
                String load = (row.get(2) != null)?row.get(2).toString():"";
                if(name.equals(subject.getName()) && load.equals(subject.getLoad()))
                   id.add(res);
            }
        });
        SQLiteC.disconnect();
        if(id.size() == 1)
        Main.showDeleteSubjectWindow(this, id.get(0));
        else System.err.println("Ошибка поиска");
    }
    @FXML
    public void addTeacher(){ Main.showAddTeacherWindow(subjectList.getSelectionModel().getSelectedItem(),this); }
    @FXML
    public void deleteTeacher(){
        SQLiteC.connect();
        ObservableList<Integer> id = FXCollections.observableArrayList();
        Teacher teacher = tableViewTeachers.getSelectionModel().getSelectedItem();
        ObservableList<ObservableList> rows = SQLiteC.queryRows("Преподаватели");
        rows.forEach(row ->{
            if (row.get(2).equals(subjectList.getSelectionModel().getSelectedItem())){
                int res = (row.get(0) != null)?Integer.parseInt(row.get(0).toString()):0;
                String name = (row.get(1) != null)?row.get(1).toString():"";
                String classroom = (row.get(3) != null)?row.get(3).toString():"";
                String load = (row.get(4) != null)?row.get(4).toString():"";
                if(name.equals(teacher.getName()) && load.equals(teacher.getLoad()) && classroom.equals(teacher.getClassroom()))
                    id.add(res);
            }
        });
        SQLiteC.disconnect();
        if(id.size() == 1)
            Main.showDeleteTeacherWindow(this, id.get(0));
        else System.err.println("Ошибка поиска");
    }

    /***********************************************************************************|
     *                                                                                  |
     *                                                                                  |
     *                            Объявляем объекты FXML                                |
     *                                                                                  |
     *                                                                                  |
     ************************************************************************************/

    @FXML
    private RadioButton Radio5A;
    @FXML
    private RadioButton Radio5AB;
    @FXML
    private RadioButton Radio5ABV;
    @FXML
    private RadioButton Radio5ABVG;
    @FXML
    private RadioButton Radio5ABVGD;


    @FXML
    private RadioButton Radio6A;
    @FXML
    private RadioButton Radio6AB;
    @FXML
    private RadioButton Radio6ABV;
    @FXML
    private RadioButton Radio6ABVG;
    @FXML
    private RadioButton Radio6ABVGD;

    @FXML
    private RadioButton Radio7A;
    @FXML
    private RadioButton Radio7AB;
    @FXML
    private RadioButton Radio7ABV;
    @FXML
    private RadioButton Radio7ABVG;
    @FXML
    private RadioButton Radio7ABVGD;

    @FXML
    private RadioButton Radio8A;
    @FXML
    private RadioButton Radio8AB;
    @FXML
    private RadioButton Radio8ABV;
    @FXML
    private RadioButton Radio8ABVG;
    @FXML
    private RadioButton Radio8ABVGD;

    @FXML
    private RadioButton Radio9A;
    @FXML
    private RadioButton Radio9AB;
    @FXML
    private RadioButton Radio9ABV;
    @FXML
    private RadioButton Radio9ABVG;
    @FXML
    private RadioButton Radio9ABVGD;

    @FXML
    private RadioButton Radio10A;
    @FXML
    private RadioButton Radio10AB;
    @FXML
    private RadioButton Radio10ABV;
    @FXML
    private RadioButton Radio10ABVG;
    @FXML
    private RadioButton Radio10ABVGD;

    @FXML
    private RadioButton Radio11A;
    @FXML
    private RadioButton Radio11AB;
    @FXML
    private RadioButton Radio11ABV;
    @FXML
    private RadioButton Radio11ABVG;
    @FXML
    private RadioButton Radio11ABVGD;

    @FXML
    private ListView groupList;
    @FXML
    private ListView subjectList;
    @FXML
    private TableView<Subject> tableViewSubjects;
    @FXML
    private TableView<Teacher> tableViewTeachers;
}
