package com.ajwebmotors.paradise.view.dnd;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.paradise.domain.Car;

@ManagedBean
@ViewScoped
public class ColumnManagerView implements Serializable {
    
    private final List<String> VALID_COLUMN_KEYS = Arrays.asList("id", "brand", "year", "color");
    
    private List<ColumnModel> columns = new ArrayList<ColumnModel>();
    
    private List<Car> cars;
    
    private TreeNode availableColumns;
    
    @ManagedProperty("#{carService}")
    private CarService service;
    
    @PostConstruct
    public void init() {
        cars = service.createCars(9);
        createAvailableColumns();
        createDynamicColumns();
    }
    
    private void createAvailableColumns() {
        availableColumns = new DefaultTreeNode("Root", null);
        TreeNode root = new DefaultTreeNode("Columns", availableColumns);
        root.setExpanded(true);
        TreeNode model = new DefaultTreeNode("column", new ColumnModel("Id", "id"), root);
        TreeNode year = new DefaultTreeNode("column", new ColumnModel("Year", "year"), root);
        TreeNode manufacturer = new DefaultTreeNode("column", new ColumnModel("Brand", "brand"), root);
        TreeNode color = new DefaultTreeNode("column", new ColumnModel("Color", "color"), root);
    }
      
    public void createDynamicColumns() {
        String[] columnKeys = new String[]{"id","year","brand"};
        columns.clear();  
         
        for(String columnKey : columnKeys) {
            String key = columnKey.trim();
             
            if(VALID_COLUMN_KEYS.contains(key)) {
                columns.add(new ColumnModel(columnKey.toUpperCase(), columnKey));
            }
        }
    }
        
    public void treeToTable() {
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String property = params.get("property");
        String droppedColumnId = params.get("droppedColumnId");
        String dropPos = params.get("dropPos");
         
        String[] droppedColumnTokens = droppedColumnId.split(":");
        int draggedColumnIndex = Integer.parseInt(droppedColumnTokens[droppedColumnTokens.length - 1]);
        int dropColumnIndex = draggedColumnIndex + Integer.parseInt(dropPos);
         
        //add to columns
        this.columns.add(dropColumnIndex, new ColumnModel(property.toUpperCase(), property));
         
        //remove from nodes
        TreeNode root = availableColumns.getChildren().get(0);
        for(TreeNode node : root.getChildren()) {
            ColumnModel model = (ColumnModel) node.getData();
            if(model.getProperty().equals(property)) {
                root.getChildren().remove(node);
                break;
            }
        }
    }
     
    public void tableToTree() {
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int colIndex = Integer.parseInt(params.get("colIndex"));
         
        //remove from table
        ColumnModel model = this.columns.remove(colIndex);
         
        //add to nodes
        TreeNode property = new DefaultTreeNode("column", model, availableColumns.getChildren().get(0));
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public TreeNode getAvailableColumns() {
        return availableColumns;
    }

    public void setService(CarService service) {
        this.service = service;
    }
    
    static public class ColumnModel implements Serializable {
 
        private String header;
        private String property;
 
        public ColumnModel(String header, String property) {
            this.header = header;
            this.property = property;
        }
 
        public String getHeader() {
            return header;
        }
 
        public String getProperty() {
            return property;
        }
    }

    private static class CarService {

        public CarService() {
        }

        private List<Car> createCars(int i) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
