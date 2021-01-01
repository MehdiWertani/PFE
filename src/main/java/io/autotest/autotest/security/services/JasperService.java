package io.autotest.autotest.security.services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class JasperService {

    public File generatePdf(Map<String, String> data, String reportName) throws Exception {
        HashMap<String, Object> reportData = new HashMap<>(data);
        try {
            BufferedImage header = ImageIO.read(new ClassPathResource("static/itserv-cropped.jpg").getInputStream());
            reportData.put("headerImgUrl", header);
            reportData.put("creationDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));

            Path path = Paths.get(Objects.requireNonNull("D:\\"));
            Files.createDirectories(path);
            String filePath = "Campaign_report_" + new SimpleDateFormat("ddMMyyyy").format(new Date()) + ".pdf";
//            String filepath = new SimpleDateFormat("ddMMyyyy").format(new Date()) + System.currentTimeMillis() + ".pdf";
            File temp = new File(Paths.get(path.toFile().toString(), filePath).toUri());
            temp.createNewFile();
            InputStream reportAsStream = getClass().getClassLoader().getResourceAsStream("templates/autoTest.jrxml");
            JasperDesign load = JRXmlLoader.load(reportAsStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportData, new JREmptyDataSource());
//            File report = new File("classpath:/resources/temp");
            JasperExportManager.exportReportToPdfFile(jasperPrint, temp.getAbsolutePath());
//            byte[] reportAsBytes = JasperExportManager.exportReportToPdf(jasperPrint);
//            byte[] encode = Base64.getEncoder().encode(reportAsBytes);

//            return new String(encode);
            return temp;
        } catch (IOException | JRException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String generateBase64Pdf(Map<String, String> data) throws Exception {
        HashMap<String, Object> reportData = new HashMap<>(data);
        try {
            BufferedImage header = ImageIO.read(new ClassPathResource("static/itserv-cropped.jpg").getInputStream());
            reportData.put("headerImgUrl", header);
            reportData.put("creationDate", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));

            Path path = Paths.get(Objects.requireNonNull("D:\\"));
            Files.createDirectories(path);
//            String filePath = "Campaign_report_" + new SimpleDateFormat("ddMMyyyy").format(new Date()) + ".pdf";
////            String filepath = new SimpleDateFormat("ddMMyyyy").format(new Date()) + System.currentTimeMillis() + ".pdf";
//            File temp = new File(Paths.get(path.toFile().toString(), filePath).toUri());
//            temp.createNewFile();
            InputStream reportAsStream = getClass().getClassLoader().getResourceAsStream("templates/autoTest.jrxml");
            JasperDesign load = JRXmlLoader.load(reportAsStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportData, new JREmptyDataSource());
//            File report = new File("classpath:/resources/temp");
//            JasperExportManager.exportReportToPdfFile(jasperPrint, temp.getAbsolutePath());
            byte[] reportAsBytes = JasperExportManager.exportReportToPdf(jasperPrint);
            byte[] encode = Base64.getEncoder().encode(reportAsBytes);

            return new String(encode);
        } catch (IOException | JRException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
