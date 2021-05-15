package Controllers;

import DatabaseConnection.UserQueries;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.*;
import java.awt.print.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BookingController implements Initializable {
    UserQueries UQ;
    @FXML
    JFXTextField firstname;
    @FXML
    JFXTextField lastname;

    @FXML
    JFXTextField email;

    @FXML
    JFXTextField address;

    @FXML
    JFXTextField phone;

    @FXML
    JFXTextField roomtype;

    @FXML
    JFXTextField roomcode;

    @FXML
    JFXDatePicker startdate;

    @FXML
    JFXDatePicker enddate;

    @FXML
    JFXTextField services;
    @FXML
    StackPane stackpane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startdate.setValue(LocalDate.now());
        UQ=new UserQueries();


    }





@FXML
    public void backbutton(javafx.event.ActionEvent ae) throws IOException {
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void close(ActionEvent ae) throws IOException{
        JFXDialogLayout dialogLayout=new JFXDialogLayout();
        dialogLayout.setHeading(new Text("Close"));
        dialogLayout.setBody(new Text("Do You want to exit !"));

        JFXButton ok=new JFXButton("Ok");
        JFXButton Cancel=new JFXButton("Cancel");

        JFXDialog dialog=new JFXDialog(stackpane, dialogLayout, JFXDialog.DialogTransition.CENTER);

        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                System.exit(0);
            }
        });

        Cancel.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });

        dialogLayout.setActions(ok,Cancel);
        dialog.show();
    }
    @FXML
    public void bookroom(ActionEvent ae) throws SQLException {
        if(firstname.getText().isEmpty() || lastname.getText().isEmpty() || email.getText().isEmpty() || address.getText().isEmpty() ||
        phone.getText().isEmpty() || roomtype.getText().isEmpty() || roomcode.getText().isEmpty() ||
        services.getText().isEmpty()  ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("sme fields are empty");
            alert.setContentText("please fill in all fields and try again");
            alert.show();

        }

        else {

                int result = 0;
                String sql = "INSERT INTO cusbooking (firstname,lastname,email,address,phone,roomtype,roomcode,startdate,enddate,services) VALUES (?,?,?,?,?,?,?,?,?,?)";




            PreparedStatement ps = UQ.connection.prepareStatement(sql);
                ps.setString(1, firstname.getText().toString());
                ps.setString(2, lastname.getText().toString());
                ps.setString(3, email.getText().toString());
                ps.setString(4, address.getText().toString());
                ps.setString(5, phone.getText().toString());
                ps.setString(6, roomtype.getText().toString());
                ps.setString(7, roomcode.getText().toString());
                ps.setString(8, startdate.getValue().toString());
                ps.setString(9, enddate.getValue().toString());
                ps.setString(10, services.getText().toString());

                result = ps.executeUpdate();


                if (result > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("succesful booking");
                    alert.setContentText("booking added succesfully");
                    alert.show();
                    updateroomstatus();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("unsuccesful booking");
                    alert.setContentText("booking not added succesfully");
                    alert.show();

                }
            }
        }

        private void updateroomstatus() {
            String text=roomcode.getText().toString().trim();
            String sql="UPDATE room SET roomStatus=? WHERE roomcode=?";

            try {
                PreparedStatement ps=(PreparedStatement)UQ.connection.prepareStatement(sql);
                ps.setString(1, "unavailable");
                ps.setString(2, text);

                ps.executeUpdate();


            } catch (SQLException ex) {

            }

        }

    @FXML
    private void print(ActionEvent ae) {
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
            pj.print();

        }
        catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }

    public class BillPrintable implements Printable {




        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
                throws PrinterException
        {

            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;

                double width = pageFormat.getImageableWidth();

                g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY());


                FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));

                int idLength=metrics.stringWidth("000");
                int amtLength=metrics.stringWidth("000000");
                int qtyLength=metrics.stringWidth("00000");
                int priceLength=metrics.stringWidth("000000");
                int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;



                int productPosition = 0;
                int discountPosition= prodLength+5;
                int pricePosition = discountPosition +idLength+10;
                int qtyPosition=pricePosition + priceLength + 4;
                int amtPosition=qtyPosition + qtyLength;



                try{
                    /*Draw Header*/
                    int y=20;
                    int yShift = 10;
                    int headerRectHeight=15;
                    int headerRectHeighta=40;

                    String  name=firstname.getText();
                    String phonenumber=phone.getText();
                    String address1=address.getText();
                    String roomtype1=roomtype.getText();

                    String status="paid";


                    g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
                    g2d.drawString("-------------------------------------",12,y);y+=yShift;
                    g2d.drawString("    Eka   Hotel Bill Receipt        ",12,y);y+=yShift;
                    g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;

                    g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    g2d.drawString("                                     ",10,y);y+=yShift;
                    g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
                    g2d.drawString("  Name                    " +name+"  ",10,y);y+=yShift;
                    g2d.drawString("  Address                 " +address1+"  ",10,y);y+=yShift;
                    g2d.drawString("  phonenumber       " +phonenumber+"  ",10,y);y+=yShift;
                    g2d.drawString("  roomtype       " +roomtype1+"  ",10,y);y+=yShift;
                    g2d.drawString("  Payment                 " +status+"  ",10,y);y+=yShift;


                    g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    g2d.drawString("          Hotel Phone Number         ",10,y);y+=yShift;
                    g2d.drawString("             03111111111             ",10,y);y+=yShift;
                    g2d.drawString("*************************************",10,y);y+=yShift;
                    g2d.drawString("    THANKS TO VISIT OUR HOTEL        ",10,y);y+=yShift;
                    g2d.drawString("*************************************",10,y);y+=yShift;



                }
                catch(Exception r){
                    r.printStackTrace();
                }

                result = PAGE_EXISTS;
            }
            return result;
        }
    }

    public PageFormat getPageFormat(PrinterJob pj)
    {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double middleHeight =8.0;
        double headerHeight = 2.0;
        double footerHeight = 2.0;
        double width = convert_CM_To_PPI(8);
        double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(
                0,
                10,
                width,
                height - convert_CM_To_PPI(1)
        );

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }

    protected static double convert_CM_To_PPI(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }

}










