package com.project.ticketreservation.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.project.ticketreservation.Models.FlightTicket;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class TicketQRcodeGenerator {
    public static String generateQRcode(FlightTicket t) throws WriterException , IOException {
        String qrCodePath = "ticketreservation\\src\\main\\resources\\static\\images\\TicketsQRcode\\";
        String qrCodeName = qrCodePath+t.getFirstName()+t.getNationalID()+"-TicketQRCODE.png";
        var qrWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrWriter.encode("National id :" +t.getNationalID() +
                "\n" + "first name : " + t.getFirstName()+
                "\n" + "last name : " + t.getLastName() +"\n" + "seat number : "
                + t.getSeat(), BarcodeFormat.QR_CODE , 400 ,400);
        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);


        return path.toString();

    }

}
