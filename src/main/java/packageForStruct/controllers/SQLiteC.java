package packageForStruct.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLiteC {

    private Connection connection;
    private String dataBaseUrl;

    public SQLiteC() { dataBaseUrl = "C:\\Users\\Progy\\IdeaProjects\\TableForSchool\\src\\main\\sqlite\\dbForSchool.db"; }
    public SQLiteC(String dbFileName){
        dataBaseUrl = dbFileName;
    }

    public void connect(){
        Connection connection = null;
        StringBuilder dbUrl = new StringBuilder("jdbc:sqlite:");
        dbUrl.append(dataBaseUrl);
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    dbUrl.toString());
        }
        catch(Exception connectionError){
            System.err.println(connectionError.getMessage());
        }
        this.connection = connection;
    }

    public List<String> queryTables(){
        String statement = "SELECT * " +
                "FROM sqlite_master " +
                "WHERE type='table' " +
                "ORDER BY name ";
        List<String> tableNames = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(statement)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                tableNames.add(resultSet.getString("name"));
            }
            resultSet.close();
        }
        catch(SQLException tableQueryException){
            System.err.println(tableQueryException.toString());
        }
        return tableNames;
    }

    public Map<String, String> queryColumns(String tableName){
        String statement = "PRAGMA table_info(" + tableName + ")";
        Map<String, String> columns = new HashMap<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(statement)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                columns.put(resultSet.getString("name"), resultSet.getString("type"));
            }
            resultSet.close();
        }
        catch(SQLException columnQueryException){
            System.err.println(columnQueryException.toString());
        }
        return columns;
    }

    public ObservableList<String> queryFirstRaw(String tableName) {
        ObservableList<ObservableList> rows = queryRows(tableName);
        return (ObservableList<String>) rows.get(0);
    }

    public ObservableList<ObservableList> queryRows(String tableName){
        String statement = "SELECT * " +
                "FROM " + tableName;
        ObservableList<ObservableList> rows = FXCollections.observableArrayList();
        try(PreparedStatement preparedStatement = connection.prepareStatement(statement)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getString(i));
                }
                rows.add(row);
            }
            resultSet.close();
        }
        catch(SQLException rowQueryException){
            System.err.println(rowQueryException.toString());
        }
        return rows;
    }

    public ObservableList<String> queryRow(String tableName, String condition) {
        String statement = "SELECT * " +
                "FROM " + tableName +
                " WHERE " + condition;
        ObservableList<String> row = FXCollections.observableArrayList();
        try(PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                row.add(resultSet.getString(i));
            }
            resultSet.close();
        }
        catch (SQLException exception) {
            System.err.println(exception.toString());
        }
        return row;
    }

    public void deleteRow(String tableName, String param, Object value){
        String statement = "DELETE FROM " + tableName +
                " WHERE "+ param +" = ? ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(statement)){
            preparedStatement.setObject(1, value);
            preparedStatement.executeUpdate();
        }
        catch(SQLException deleteException){
            System.err.println(deleteException.toString());
        }
    }

    public ObservableList queryColumnValue(String tableName, String columnName){
       ObservableList<String> columnValues = FXCollections.observableArrayList();;
        String statement = "SELECT " + columnName +
                        " FROM " + tableName +
                        " ORDER BY " + columnName;
        try(PreparedStatement preparedStatement = connection.prepareStatement(statement)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                columnValues.add(resultSet.getString(columnName));
            }
            resultSet.close();
        }
        catch(SQLException valueException){
            System.err.println(valueException.toString());
        }
        return columnValues;
    }

    public void addRow(String tableName, Object ... value){
        StringBuilder statementHelper = new StringBuilder();
        for (int i = 0; i < value.length; i++) {
            statementHelper.append((i != value.length - 1) ? "?, " : "?");
        }
        String statement = "INSERT INTO " +  tableName +
                " VALUES (" + statementHelper + ")";
        try(PreparedStatement preparedStatement = connection.prepareStatement(statement)){
            for (int i = 1; i <= value.length; i++) {
                preparedStatement.setObject(i,value[i-1]);
            }
            preparedStatement.executeUpdate();
        }
        catch(SQLException addException){
            System.err.println(addException.toString());
        }
    }

    public void updateRow(String tableName, String param, String condition, Object value){
        String statement = "UPDATE " + tableName +
                " SET " + param + " = ? "  +
                "WHERE " + condition;
        try(PreparedStatement preparedStatement = connection.prepareStatement(statement)){
            preparedStatement.setObject(1,value);
            preparedStatement.executeUpdate();
        }
        catch(SQLException updateException){
            System.err.println(updateException.toString());
        }
    }

    public void disconnect(){
        this.connection = null;
    }

}
