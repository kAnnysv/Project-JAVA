package ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class FileManager {
    private Path rootPath = Paths.get(System.getProperty("user.dir")).resolve(Paths.get("src")).resolve(Paths.get("ex02")).resolve(Paths.get("MAIN"));


    public void fileUtil() throws IOException {


        while (true) {

            System.out.println(rootPath);
            System.out.println("->");

            Scanner scanner1 = new Scanner(System.in);
            String[] command = scanner1.nextLine().split(" ");


            if (command[0].equals("ls")) {
                ls(rootPath);
            } else if (command[0].equals("cd") && !command[1].contains("../")) {
                if (Files.isDirectory(rootPath)) {
                    rootPath = rootPath.resolve(command[1]);
                } else {
                    System.out.println("is not directory");
                    System.exit(1);
                }

            } else if(command[0].equals("mv") && command[2].contains("../")){
                String foldName = command[2].substring(3);
                try {
                    move(command[1], foldName );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else if(command[0].equals("mv") && !command[2].contains("../")){
                renameFile(command[1], command[2]);
            }else{
                System.exit(0);
            }
        }


    }

    private void ls(Path path) {


        try (DirectoryStream<Path> paths = Files.newDirectoryStream(rootPath)) {
            for (Path p : paths) {
                if(Files.isDirectory(p)){
                    System.out.println(p.getFileName() + " " + sum(p) + " KB");
                }else {
                    System.out.println(p.getFileName() + " " + Files.size(p) / 1024 + " KB");
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Integer sum(Path path){
        int sum = 0;
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(path)) {
            for (Path p : paths) {
               sum += (int) (Files.size(p) / 1024);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sum;
    }

    private void move( String fileName, String directName) throws IOException {

        Path fileMove = rootPath.resolve(Paths.get(fileName));
        Path folderNameFile = rootPath.getParent().resolve(Paths.get(directName).resolve(Paths.get(fileName)));
       Files.move(fileMove, folderNameFile , StandardCopyOption.REPLACE_EXISTING);


    }

    public void renameFile(String fileName1, String fileName2){

        File file = new File(String.valueOf(rootPath.resolve(Paths.get(fileName1))));
        File file1 = new File(String.valueOf(rootPath.resolve(Paths.get(fileName2))));

        System.out.println(file.renameTo(file1));


    }








}
