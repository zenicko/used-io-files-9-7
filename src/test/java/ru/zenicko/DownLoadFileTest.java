package ru.zenicko;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class DownLoadFileTest {


    @Test
    void downLoadTxtFileTest() throws IOException {

        open("https://github.com/javadev/file-download-utility/blob/master/README.md");
        File file = $("#raw-url").download();
        System.out.println(file.getAbsoluteFile());
        Reader reader = new FileReader(file);

        String stringFile = IOUtils.toString(reader);
        Assertions.assertTrue(stringFile.contains("file-download-utility"));
    }

    @Test
    void downLoadPdfFileTest() throws IOException, IllegalAccessException {
        open("https://tmb.gks.ru/folder/36745");
        File file = $$(".document-list__item a").find(Condition.text("PDF")).download();
        System.out.println(file.getAbsoluteFile());

        PDF pdfFile = new PDF(file);
        Field[] allFields = pdfFile.getClass().getDeclaredFields();

        for (Field field : allFields) {
            System.out.println("Name: " + field.getName() + "Value: " + field.get(pdfFile));
        }

        Assertions.assertEquals(8, pdfFile.numberOfPages);
        Assertions.assertEquals("О некоторых итогах Всероссийской переписи населения 2002 года", pdfFile.title);

    }

    @Test
    void downLoadXlsFileTest() throws IOException, IllegalAccessException {
        open("https://tmb.gks.ru/folder/35202");
        File file = $$(".document-list__item a").find(Condition.text("XLS")).download();
        System.out.println(file.getAbsoluteFile());

        XLS xlsFile = new XLS(file);
        Field[] allFields = xlsFile.getClass().getDeclaredFields();

        Assertions.assertEquals("nom_2021.xls", new File(xlsFile.name).getName());
        Assertions.assertEquals("Номенклатура продукции и услуг по Общероссийскому классификатору " +
                        "продукции по видам экономической деятельности (ОКПД2) для разработки статистической " +
                        "информации в оперативном (ежемесячном) режиме в 2021 году и по итогам за 2021 год ",
                xlsFile.excel.getSheetAt(0).getRow(1).getCell(0).toString());
        System.out.println(xlsFile.excel.getSheetAt(0).getRow(1).getCell(0).toString());
        System.out.println();

    }

    @Test
    void downLoadCsvFileByFileReaderTest() throws IOException {
        open("https://rosstat.gov.ru/opendata/7708234640-ca1502076");
        File file = $$(".document-list__item a").filter(Condition.text("CSV")).get(2).download();
        System.out.println(file.getAbsoluteFile());

        try (Reader reader = new FileReader(file, Charset.forName("windows-1251"))) {
            CSVReader csvFile = new CSVReader(reader);
            for (Iterator<String[]> it = csvFile.iterator(); it.hasNext(); ) {
                String[] line = it.next();
                for (String field : line) {
                    System.out.print(field + " ");
                }
                System.out.println();
            }
            Field[] allFields = csvFile.getClass().getDeclaredFields();
            Assertions.assertEquals("data-20210401T000000-structure-20150401T000000.csv", file.getName());
        }
        System.out.println();
    }

    @Test
    void downLoadCsvByClassLoaderFileTest() throws IOException, CsvException {

        ClassLoader loader = this.getClass().getClassLoader();

        try (InputStream is = loader.getResourceAsStream("data-20210401T000000-structure-20150401T000000.csv");
             Reader reader = new InputStreamReader(is, Charset.forName("windows-1251"))) {
            CSVReader csvFile = new CSVReader(reader);

            List<String[]> strings = csvFile.readAll();

            for (Iterator<String[]> it = strings.iterator(); it.hasNext(); ) {
                String[] line = it.next();
                for (String field : line) {
                    System.out.print(field + " ");
                }
                System.out.println();
            }

            String actualString = strings.get(0)[0].split(";")[0];
            Assertions.assertEquals("Наименование должности", actualString);
        }
    }


    String downLoadZipFileAndSave() throws IOException {
        Configuration.downloadsFolder = "src/test/resources";
        open("https://tmb.gks.ru/folder/36745");
        File fileSourceFile = $$(".document-list__item a").filter(Condition.text("ZIP")).get(0).download();
        File fileDestFile = new File(Configuration.downloadsFolder + "\\" + fileSourceFile.getName());
        CopyFile.copyFile(fileSourceFile, fileDestFile);

        //return Configuration.downloadsFolder + "/" + fileSourceFile.getName();
        return fileSourceFile.getName();
    }

    @Test
    void downLoadZipFileByFileReaderTest() throws IOException {
        String absolutePath = downLoadZipFileAndSave();
        ClassLoader classLoader = this.getClass().getClassLoader();

        InputStream is = ClassLoader.getSystemResourceAsStream("tom7.zip");
        ZipInputStream zipFile = new ZipInputStream(is, Charset.forName("cp866"));

        ZipEntry zipEntity = zipFile.getNextEntry();
        Assertions.assertEquals("Том 7. Экономически активное и экономически неактивное население/",
                zipEntity.getName());

    }
}


