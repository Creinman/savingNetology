import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        GameProgress progress0 = new GameProgress(100, 10, 1, 100);
        GameProgress progress1 = new GameProgress(80, 8, 3, 300);
        GameProgress progress2 = new GameProgress(10, 1, 5, 500);
        File progress00 = new File("D://Games//savegames//progress0.dat");
        File progress01 = new File("D://Games//savegames//progress1.dat");
        File progress02 = new File("D://Games//savegames//progress2.dat");
        progress00.mkdir();
        progress01.mkdir();
        progress02.mkdir();

        saveGame("D://Games//savegames//progress0.dat", progress0);
        saveGame("D://Games//savegames//progress1.dat", progress1);
        saveGame("D://Games//savegames//progress2.dat", progress2);

        ArrayList<String> progres = new ArrayList<String>();
        progres.add("D://Games//savegames//progress0.dat");
        progres.add("D://Games//savegames//progress1.dat");
        progres.add("D://Games//savegames//progress2.dat");

        zipGame("D://Games//savegames//saving.zip", progres);

        try (BufferedReader br = new BufferedReader(new FileReader("D://Games//savegames"))) {
            String s;
            String template = "saving.zip";
            while ((s = br.readLine()) != null) {
                boolean compare = s.equals("saving.zip");
                if (compare == false) {
                    File extra = new File(s);
                    extra.delete();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void saveGame(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            // запишем экземпляр класса в файл
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void zipGame(String path, List<String> list) {
        try (ZipOutputStream zout = new ZipOutputStream(new
                FileOutputStream(path));
             for(String progres :list) {
            FileInputStream fis = new FileInputStream(progres.get())){
                ZipEntry entry = new ZipEntry("packed_progres" + progres + ".txt");
                zout.putNextEntry(entry);
                // считываем содержимое файла в массив byte
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                // добавляем содержимое к архиву
                zout.write(buffer);
            }
            // закрываем текущую запись для новой записи
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

