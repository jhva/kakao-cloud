package java_12_23;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesMain {
    public static void main(String[] args) {
        //현재 디렉토리 위치
//        File f= new File ("./");
//        System.out.println(f.getAbsolutePath());
        File file = new File("C:\\Users\\user\\Documents\\kakaoCloudSchool\\java_study\\JAVA_Practice\\javapractice\\config.properties");
        try (FileInputStream fis = new FileInputStream(file)) {
            Properties properties = new Properties();
            properties.load(fis);
            System.out.println(
                    properties.getProperty("id")
            );

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
