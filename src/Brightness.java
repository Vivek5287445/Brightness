import java.io.*;
public class Brightness {
    public static FileReader in = null;



    public static void main(String args[]) throws IOException {
        FileWriter out = null;
        int percentage = 0;

        try {
            if(args.length == 2){
                percentage = Integer.parseInt(args[1]);
                out = new FileWriter("/sys/class/backlight/intel_backlight/brightness");
            }


            if(args[0].equals("-help")) help();

            else if(args[0].equals("-set")){
                if (percentage > 0 && percentage <= 100) setBrightness(percentage*20, out);
                else System.out.println("Invalid percentage value. Use between 0 and 100.");
            }

            else if(args[0].equals("-get")) getBrightness();

            else{
                System.out.println("Invalid Argument!!! Please type -help for help.");
            }


        }catch (IOException e){
            System.out.println(e);
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Error!!! Pass -help as argument for help.");
        }
        finally {
            if (out != null) {
                out.close();
            }
        }
    }

    private static void setBrightness(int percentage, FileWriter outStream) {
        try {
            outStream.write(String.valueOf(percentage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void help() {
        System.out.println("usage:java Brightness [options]\n" +
                "where options are:\n" +
                "-help\n" +
                "-set <percentage>\n" +
                "-get\n");
    }


    public static void getBrightness() throws IOException {
        try {
            in = new FileReader("/sys/class/backlight/intel_backlight/brightness");
            BufferedReader fin = new BufferedReader(in);

            String str = null;

            while ((str = fin.readLine()) != null) {
                System.out.println(str);
            }

            fin.close();


        }catch(IOException e){
            System.out.println(e);
        }finally {
            if (in != null) {
                in.close();
            }
        }
    }




}