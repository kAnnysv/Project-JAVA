package ex00;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CheckFile {
    private Map<String, String> fileExtension = new HashMap<>();
    private String fileNameSignatures = "\\src\\ex00\\signatures.txt";
    private String fileNameResult = "\\src\\ex00\\result.txt";
    public  static String fileNameAnalyses;


    public void processed(){
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Path file --> or 42 exit");
            String line = scanner.nextLine();
            if(line.equals("42")){
                System.exit(0);
            }

            fileNameAnalyses = line;


            readPutMap(fileNameSignatures);
            resultRead( fileNameResult);

        }


    }

    private void readPutMap(String fileName){
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null){
                String[] tmp = line.split(",");
                fileExtension.put(tmp[1], tmp[0]);
                line = reader.readLine();
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    private String fileAnalysis(String fileName){
        try(FileInputStream stream = new FileInputStream(fileName)) {
            int i = 0;
            StringBuilder hex = new StringBuilder();

            while (i < 8) {

                hex.append(String.format("%02X ", stream.read()));

                i++;
            }
            return hex.toString();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void resultRead( String fileNameResult){
        String res = " " + fileAnalysis(fileNameAnalyses);


        for(Map.Entry entry : fileExtension.entrySet()){
            String s = (String) entry.getKey();
            String tmp = res.substring(0, ((String) entry.getKey()).length());


            if(tmp.equals(s)){
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileNameResult, true))){
                    writer.append((String) entry.getValue()).append("\n");
                    System.out.println("PROCESSED");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }



        }


    }








}
