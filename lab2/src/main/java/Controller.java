import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    public String home = System.getProperty("user.home");
    public File file = new File(home+"/Desktop/testy");

    public String home2 = System.getProperty("user.home");
    public File file2 = new File(home2+"/Dysk Google/UTP/Semestr 4/WF");

    public List<String> showFiles(File file) {
        String[] pathnames;
        pathnames = file.list();
        for(String pathname : pathnames)
            System.out.println(pathname);

        List<String> myList = new ArrayList<String>(Arrays.asList(pathnames));
        System.out.println(myList);
        return myList;
    }

    public String getPath(File file) {
        return file.getPath();
    }

    public void CreateDirectory(File file, String directory) {
        Path dir = Paths.get(file + "/" + directory);
        if(!Files.exists(dir)) {
            try{
                Files.createDirectories(dir);
                System.out.println("Stworzono nowy katalog");
            }catch (Exception e) {
                System.err.println(e);
            }

        }else System.out.println("Katalog juz istnieje");
    }

    public void DeleteDirectory(Path dir) {
        String[] pathnames;
        if(Files.exists(dir)) {
            if(Files.isDirectory(dir)) {
                try{
                    pathnames = dir.toFile().list();
                    for(String pathname : pathnames) { //usuwamy pliki w srodku bo inaczej nie bedziemy mogli usunac folderu
                        System.out.println(pathname);
                        Files.delete(Paths.get(dir+"/"+pathname));
                    }
                    Files.delete(dir);
                    System.out.println("Katalog Usuniety");
                }catch (Exception e) {
                    System.err.println(e);
                }

            }else try{
                Files.delete(dir);
                System.out.println("Plik Usuniety");
            }catch (Exception e) {
                System.err.println(e);
            }

        }else System.out.println("Brak podanego Pliku lub Folderu");

    }

    public int OpenFile(File file, Path dir) {
        if(!Files.isDirectory(dir)) {
            try {
                if(!Desktop.isDesktopSupported()) {
                    System.out.println("Nie wspierane");
                }
                Desktop desktop = Desktop.getDesktop();
                if(file.exists()) {
                    File fileToOpen = new File(dir.toString());
                    desktop.open(fileToOpen);   //otwarcie pliku
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            return 1;
        } else return 0;
    }

    public void CreateFile(File file, String fileName) {
        //create file
        Path fileToCreate = Paths.get(file + "/" + fileName);
        if(!Files.exists(fileToCreate)) {
            try{
                Files.createFile(fileToCreate);
                System.out.println("Stworzono nowy plik");
            }catch (Exception e) {
                System.err.println(e);
            }

        }else System.out.println("Plik juz istnieje");
    }

    public static void CopyDirectory(String source, String destination) {
        String[] pathnames;
        Path src = Paths.get(source);
        Path dst = Paths.get(destination);
        if (Files.exists(src)) {
            try {
                Files.copy(src, dst); //stworzenie folderu
                pathnames = src.toFile().list();
                for (String pathname : pathnames) { //skopiowanie plików
                    System.out.println(pathname);
                    Files.copy(Paths.get(src + "/" + pathname), Paths.get(dst + "/" + pathname), StandardCopyOption.REPLACE_EXISTING);
                }
                System.out.println("Kopiowanie");
            } catch (Exception e) {
                System.err.println(e);
            }

        }
    }

    public static void MoveDirectory(String source, String destination) {
        String[] pathnames;
        Path src = Paths.get(source);
        Path dst = Paths.get(destination);
        if (Files.exists(src)) {
            try {
                Files.move(src, dst); //stworzenie folderu
                pathnames = src.toFile().list();
                for (String pathname : pathnames) { //skopiowanie plików
                    System.out.println(pathname);
                    Files.move(Paths.get(src + "/" + pathname), Paths.get(dst + "/" + pathname), StandardCopyOption.REPLACE_EXISTING);
                }
                System.out.println("Wycinanie");
            } catch (Exception e) {
                System.err.println(e);
            }

        }
    }

    public String getSpace(File file) {
        return file.getFreeSpace()/1024/1024/1024 + "/" + file.getTotalSpace()/1024/1024/1024 + "GB";
    }

    public static void main(String[] args) {

        /* TESTOWANIE FUNKCJI
        String home = System.getProperty("user.home");
        try
        {
        //constructor of file class having file as argument
            File file = new File(home+"/Desktop/testy/ahsoka.jpg");
            if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not
            {
                System.out.println("not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if(file.exists())         //checks file exists or not
                desktop.open(file);              //opens the specified file
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        System.out.println(home);
        String[] pathnames;
        File file = new File(home+"/Desktop/testy");
        pathnames = file.list();
        for(String pathname : pathnames)
            System.out.println(pathname);

        List<String> myList = new ArrayList<String>(Arrays.asList(pathnames));
        System.out.println(myList);

        System.out.println("\nRozmiar: " + file.getTotalSpace()/1024/1024/1024 + "GB"); //getfreespace
        System.out.println("\nSciezka: "+ file.getPath());

        System.out.println(file.getParent());


        File myFile = new File(home+"Desktop/testy/README.txt");
        //create dir \ przy path trzeba wybrac nio
        Path dir = Paths.get(file+"/x1");
        if(!Files.exists(dir)) {
            try{
              Files.createDirectories(dir);
                System.out.println("Stworzono nowy katalog");
            }catch (Exception e) {
                System.err.println(e);
            }

        }else System.out.println("Katalog juz istnieje");

        //create file
        Path fileCreate = Paths.get(dir+"/x2.txt");
        if(!Files.exists(fileCreate)) {
            try{
                Files.createFile(fileCreate);
                System.out.println("Stworzono nowy plik");
            }catch (Exception e) {
                System.err.println(e);
            }

        }else System.out.println("Plik juz istnieje");



        //delete dir
        if(Files.exists(dir)) {
            try{
                pathnames = dir.toFile().list();
                for(String pathname : pathnames) { //usuwamy pliki w srodku bo inaczej nie bedziemy mogli usunac folderu
                    System.out.println(pathname);
                    Files.delete(Paths.get(dir+"/"+pathname));
                }
                Files.delete(dir);
                System.out.println("Katalog Usuniety");
            }catch (Exception e) {
                System.err.println(e);
            }

        }else System.out.println("Brak podanego katalogu");*/
    }
}
