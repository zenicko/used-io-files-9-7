# I'm a readme file and will tell about this project!
___
This project studies how to apply main file operations 
into tests: upload and download. Let's go!

## Acknowledgements
___
[**Dmitrii Tuchs**](https://github.com/dtuchs)

## About the hometask
___
Написать несколько тестов, используя другие сайты и файлы. 

Почитать про классы InputStream, OutputStream, Reader, Writer.

## Steps
___

1. Create files readme.md, build.gradle, .gitignore and 
a structure of directories of the project.
2. Create some tests for hometask
   1. Upload a file `class UpLoadFileTest`
   2. Download file `class DownLoadFileTest`
      1. void downLoadTxtFileTest()
      2. void downLoadPdfFileTest()
      3. void downLoadXlsFileTest()
      4. void downLoadCsvFileByFileReaderTest()
      5. void downLoadCsvByClassLoaderFileTest()
      6. String downLoadZipFileAndSave(). Note: used method `copyFile()` of 
      `class CopyFile` to copy file in resource directory.

## What's new?
___
### Java
1. Use files in a program.
   1. Upload file.
   
      `$(CSS selector or condition).uploadFromClasspath(__path to file or a list of paths to files__);`
  
   2. Get values of all fields an object. /Resources.7
```
  PDF pdfFile = new PDF(file);
  Field[] allFields = pdfFile.getClass().getDeclaredFields();

  for (Field field : allFields) {
      System.out.println("Name: " + field.getName() + 
                         "Value: " + field.get(pdfFile));
  }
```
   3. Encoding a string from `windows-1251` to `UTF-8`
      
      `String encodingField = new String(field.getBytes("windows-1251"), Charset.forName("UTF-8"));`
   4. Encoding a names of files into zip-archive 

      `Charset.forName("cp866"));`
   5. Encoding a file
      
      `Reader reader = new FileReader(file, Charset.forName("windows-1251"))`

### Gradle
### JUnit5
1. Exlude the test from the run list to use `@Disabled` before the test.

### Selenide
1. How use proxy for downloading file.
```
   Configuration.proxyEnabled = true;
   Configuration.fileDownload = FileDownloadMode.PROXY;
```
### Allure
### Miscellaneous
1. Use russian letters in text program
   1. Undefined symbols in the console IDEA
      solve: Help>Edit custom VM Option>click
   
      Add text:
      ```
      -Dconsole.encoding=UTF-8
      -Dfile.encoding=UTF-8
      ```
2. Проект Lombok — это плагин компилятора, который добавляет в Java новые 
«ключевые слова» и превращает аннотации в Java-код, уменьшая усилия 
на разработку и обеспечивая некоторую дополнительную функциональность.
   
### Resources
1. https://javarush.ru/quests/lectures/questcore.level08.lecture04
2. https://javarush.ru/quests/lectures/questcore.level09.lecture02
3. https://ru.selenide.org/2019/12/10/advent-calendar-download-files/
4. https://docs.oracle.com/javase/tutorial/essential/io/streams.html
5. Работа с таблицами и PDF-ом:
   1. https://github.com/codeborne/xls-test
   2. https://github.com/codeborne/pdf-test
6. https://github.com/codeborne/pdf-test
7. [Java 1.3](https://clck.ru/YsPEg)
8. https://github.com/codeborne/xls-test
9. [A simple library for reading and writing CSV in Java ](https://mvnrepository.com/artifact/com.opencsv/opencsv/5.5.2)
10. [How to read file and make its copy](https://clck.ru/YtduY)


## Checking
____



System.out.println("getAbsoluteFile() " + file.getAbsoluteFile());
System.out.println("getAbsolutePath() " + file.getAbsolutePath());
System.out.println("getPath() " + file.getPath());
System.out.println("getCanonicalPath() " + file.getCanonicalPath());
System.out.println("getCanonicalFile() " + file.getCanonicalFile());
System.out.println("getParentFile() " + file.getParentFile());
System.out.println("getName() " + file.getName());
System.out.println("file.canRead() " + (file.canRead()? "OK": "No"));
System.out.println("file.canWrite() " + (file.canWrite()? "OK": "No"));


int n = absolutePath.split("\\\\").length;
String
directoryZipFile = absolutePath.split("\\\\")[n - 2],
nameFile = absolutePath.split("\\\\")[n - 1],
pathZipFile = directoryZipFile + "\\" + nameFile;


        System.out.println("file.canRead() " + (absolutePath.canRead() ? "OK" : "No"));
        System.out.println("file.canWrite() " + (absolutePath.canWrite() ? "OK" : "No"));





