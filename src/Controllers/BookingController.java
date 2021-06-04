package Controllers;

import DatabaseConnection.UserQueries;

import Model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.print.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class BookingController implements Initializable {
    UserQueries UQ;
    @FXML
    javafx.scene.control.TextField firstName;
    @FXML
    javafx.scene.control.TextField lastName;

    @FXML
    javafx.scene.control.TextField email;

    @FXML
    javafx.scene.control.TextField address;

    @FXML
    javafx.scene.control.TextField phone;

    @FXML
    javafx.scene.control.TextField roomType;

    @FXML
    javafx.scene.control.TextField roomCode;

    @FXML
     javafx.scene.control.DatePicker startDate;

    @FXML
    javafx.scene.control.DatePicker endDate;

    @FXML
    javafx.scene.control.TextField services;
    @FXML
    StackPane stackpane;

    private SceneSwitcher sceneSwitcher;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startDate.setValue(LocalDate.now());
        UQ=new UserQueries();
        sceneSwitcher = SceneSwitcher.getInstance();

    }





@FXML
    public void backButton(javafx.event.ActionEvent ae) throws IOException {
        sceneSwitcher.changeScene(ae,"../View/admin.fxml" );
    }

    @FXML
    private void close(ActionEvent ae) throws IOException {

            sceneSwitcher.changeScene(ae,"../View/login.fxml" );
            //System.exit(1);


    }
    @FXML
    public void bookRoom(ActionEvent ae) throws Exception {
        if(firstName.getText().isEmpty() || lastName.getText().isEmpty() || email.getText().isEmpty() || address.getText().isEmpty() ||
        phone.getText().isEmpty() || roomType.getText().isEmpty() || roomCode.getText().isEmpty() ||
        services.getText().isEmpty()  ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Some fields are empty");
            alert.setContentText("Please fill in all fields and try again");
            alert.show();

        }

        else {

                int result = 0;
                String sql = "INSERT INTO cusBooking (firstName,lastName,email,address,phone,roomType,roomCode,startDate,endDate,services) VALUES (?,?,?,?,?,?,?,?,?,?)";




            PreparedStatement ps = UQ.connection.prepareStatement(sql);
                ps.setString(1, firstName.getText().toString());
                ps.setString(2, lastName.getText().toString());
                ps.setString(3, email.getText().toString());
                ps.setString(4, address.getText().toString());
                ps.setString(5, phone.getText().toString());
                ps.setString(6, roomType.getText().toString());
                ps.setString(7, roomCode.getText().toString());
                ps.setString(8, startDate.getValue().toString());
                ps.setString(9, endDate.getValue().toString());
                ps.setString(10, services.getText().toString());

                result = ps.executeUpdate();


                if (result > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Booking Successful");
                    alert.setContentText("Booking added succesfully");
                    alert.show();
                    updateRoomStatus();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Booking unsuccessful");
                    alert.setContentText("Booking is not added succesfully");
                    alert.show();

                }
            }
        }

        private void updateRoomStatus() {
            String text=roomCode.getText().toString().trim();
            String sql="UPDATE room SET roomStatus=? WHERE roomCode=?";

            try {
                PreparedStatement ps=(PreparedStatement)UQ.connection.prepareStatement(sql);
                ps.setString(1, "Unavailable");
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

                    String  name=firstName.getText();
                    String phoneNumber=phone.getText();
                    String address1=address.getText();
                    String roomType1=roomType.getText();

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
                    g2d.drawString("  PhoneNumber       " +phoneNumber+"  ",10,y);y+=yShift;
                    g2d.drawString("  roomType       " +roomType1+"  ",10,y);y+=yShift;
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










