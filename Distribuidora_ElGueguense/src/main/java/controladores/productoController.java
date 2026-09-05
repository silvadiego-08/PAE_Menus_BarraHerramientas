package controladores;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modelos.Producto;

public class productoController {

    // controles
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox<String> cmbCategoria;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtExistencia;
    @FXML
    private Label lblEstado;

    // controles tabla
    @FXML
    private TableView<Producto> tblProductos;
    @FXML
    private TableColumn<Producto,String> colCodigo;
    @FXML
    private TableColumn<Producto,String> colNombre;
    @FXML
    private TableColumn<Producto,String> colCategoria;
    @FXML
    private TableColumn<Producto,Double> colPrecio;
    @FXML
    private TableColumn<Producto,Integer> colExistencia;

    private ObservableList<Producto> productos;

    @FXML
    public void initialize(){
        // inicializar la lista de productos y agregar las categorias al combo box
        productos = FXCollections.observableArrayList();
        cmbCategoria.getItems().addAll("Electrónica", "Ropa", "Joyeria", "Hogar", "Ferreteria");

        // inicializar la tabla
        colCodigo.setCellValueFactory(new PropertyValueFactory<Producto, String>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<Producto, String>("categoria"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<Producto,Double>("precio"));
        colExistencia.setCellValueFactory(new PropertyValueFactory<Producto,Integer>("existencia"));

        // agregar listener para seleccionar un producto de la tabla y mostrarlo en los controles
        tblProductos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtCodigo.setText(newValue.getCodigo());
                txtNombre.setText(newValue.getNombre());
                cmbCategoria.setValue(newValue.getCategoria());
                txtPrecio.setText(String.valueOf(newValue.getPrecio()));
                txtExistencia.setText(String.valueOf(newValue.getExistencia()));
            }
        });
    }

    @FXML
    protected void accionGuardar(){

        try {
            // validacion campos vacios
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            String categoria = cmbCategoria.getValue();

            if (codigo.isBlank() || nombre.isBlank() || categoria == null) {
                mostrarEstado("Error: Código, nombre y categoría son obligatorios.");
                return;
            }

            double precio = Double.parseDouble(txtPrecio.getText());
            int existencia = Integer.parseInt(txtExistencia.getText());

            Producto producto = new Producto(codigo, nombre, categoria, precio, existencia);

            agregarProductoTabla(producto);
            limpiarControles();
            mostrarEstado("Producto guardado correctamente.");

        } catch (NumberFormatException e) {
            mostrarEstado("Error: Ingrese valores numéricos válidos en precio y existencia.");
        }
    }
    //acciones del menu superior
    @FXML
    protected void accionAcercaDe() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Acerca de");
        alerta.setHeaderText(null);
        alerta.setContentText("Sistema de Gestión de Productos Distribuidora el Güegüense\nDesarrollado por: Ing. Diego Silva");

        alerta.showAndWait();
    }

    @FXML
    protected void accionSalir() {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Salir");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Está seguro que desea salir del sistema?");

        if (alerta.showAndWait().get() == ButtonType.OK) {
            javafx.application.Platform.exit();
        }
    }
    @FXML
    protected void accionEliminar() {
        // producto que el usuario seleccionó en la tabla
        Producto productoSeleccionado = tblProductos.getSelectionModel().getSelectedItem();

        //.Validar que realmente haya seleccionado algo
        if (productoSeleccionado == null) {
            mostrarEstado("Error: seleccione un producto de la tabla para eliminarlo.");
            return;
        }

        // alerta de confirmación
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmar eliminacion");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Esta seguro de eliminar el producto: " + productoSeleccionado.getNombre() + "?");

        // validacion que el usuario este de acuerdo en eliminar
        if (alerta.showAndWait().get() == ButtonType.OK) {
            productos.remove(productoSeleccionado); // se retira el objeto de la lita
            mostrarEstado("Producto eliminado correctamente.");
            limpiarControles(); //
        } else {
            mostrarEstado("Eliminación cancelada.");
        }
    }

    @FXML
    protected void accionVerDetalle() {
        Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarEstado("Atención: Seleccione un producto en la tabla para ver sus detalles.");
            return;
        }

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Detalle del Producto");
        alerta.setHeaderText("Ficha de Inventario: " + seleccionado.getCodigo());

        String detalle = "Nombre: " + seleccionado.getNombre() + "\n"
                + "Categoría: " + seleccionado.getCategoria() + "\n"
                + "Precio: C$ " + seleccionado.getPrecio() + "\n"
                + "Existencia: " + seleccionado.getExistencia() + " unidades";

        alerta.setContentText(detalle);
        alerta.showAndWait();

        mostrarEstado("Mostrando detalles del producto: " + seleccionado.getNombre());
    }
    @FXML
    protected void accionEditar() {
        Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarEstado("Error: Seleccione un producto de la tabla para editarlo.");
            return;
        }

        try {
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            String categoria = cmbCategoria.getValue();

            if (codigo.isBlank() || nombre.isBlank() || categoria == null) {
                mostrarEstado("Error: No pueden haber campos vacios al editar.");
                return;
            }

            double precio = Double.parseDouble(txtPrecio.getText());
            int existencia = Integer.parseInt(txtExistencia.getText());

            // Actualizar el memorio existente
            seleccionado.setCodigo(codigo);
            seleccionado.setNombre(nombre);
            seleccionado.setCategoria(categoria);
            seleccionado.setPrecio(precio);
            seleccionado.setExistencia(existencia);

            // para que muestre valores nuevos
            tblProductos.refresh();

            limpiarControles();
            mostrarEstado("Producto editado correctamente.");

        } catch (NumberFormatException e) {
            mostrarEstado("Error: Ingrese valores numericos validos en precio y existencia.");
        }
    }
    @FXML
    protected void accionLimpiar() {
        limpiarControles();
        mostrarEstado("Campos del formulario limpiados.");
    }

    @FXML
    protected void accionNuevo() {
        limpiarControles();
        // importante quitar la selección de la tabla para que el usuario no sobreescriba un producto por accidente
        tblProductos.getSelectionModel().clearSelection();
        mostrarEstado("Listo para ingresar un nuevo producto.");
    }

    //metodos de apoyo
    public void limpiarControles(){
        txtCodigo.clear();
        txtNombre.clear();
        cmbCategoria.getSelectionModel().clearSelection();
        txtPrecio.clear();
        txtExistencia.clear();
    }
    public void agregarProductoTabla(Producto producto){
        productos.add(producto);
        tblProductos.setItems(productos);
    }
    // agregar texto cuando se guarde, se edite o elimine
    public void mostrarEstado(String mensaje) {
        lblEstado.setText(mensaje);
    }

}
