package centjoin;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by azhar on 11/6/14.
 */


public class CJoiner {

    private String filePathA;
    private String filePathB;
    private String filePathOut;

    private HashMap<String, ArrayList<String>> hm;


    public CJoiner(String fpA, String fpB, String fpOut){
        filePathA=fpA;
        filePathB=fpB;
        filePathOut=fpOut;

    }

    public void CJoin(){
        File fileA = new File(filePathA);
        File fileB = new File(filePathB);
        File fileOut=null;
        PrintWriter out=null;

        hm= new HashMap<String, ArrayList<String>>();
        BufferedReader br1=null;
        BufferedReader br2=null;
        String line;
        String key;
        String value;
        ArrayList<String> str_list;
        int first_col_index;

        try{
            br1 = new BufferedReader(new FileReader(filePathA));
            line = br1.readLine();

            while(line!=null){
                first_col_index=line.indexOf(";");
                key= line.substring(0,first_col_index);
                str_list=hm.get(key);
                if(str_list==null){
                    str_list= new ArrayList<String>();
                    hm.put(key,str_list);
                }

                str_list.add(line.substring(first_col_index+1));
                //System.out.println("List size:"+str_list.size()+" :  "+line);
                line=br1.readLine();
            }

        }catch(Exception ex){
            System.out.println("Exception while reading  file "+filePathA+ ex.toString());

        }
        try{

            //fileOut = File.createTempFile(filePathOut,"txt");
            fileOut = new File(filePathOut);
            out = new PrintWriter(fileOut);

            br2 = new BufferedReader(new FileReader(filePathB));
            line = br2.readLine();

            while(line!=null){
                first_col_index=line.indexOf(";");
                key= line.substring(0,first_col_index);
                str_list=hm.get(key);
                if(str_list!=null){
                    writeRecord(key,line.substring(first_col_index+1),str_list,out);
                }else{
                    //System.out.println("Got one:"+ line);
                }
                line=br2.readLine();

            }

        }catch(Exception ex){
            System.out.println("Exception while reading file"+ filePathB+ex.toString());

        }



        finally {
            try{
                br1.close();
            }catch(Exception e1){

            }
            try{
                br2.close();
            }catch(Exception e2){

            }

            try{
                out.close();
            }catch(Exception e3){

            }
        }
        //BufferedReader br1= new BufferedReader();

    }

    public void writeRecord(String key, String toupleB, ArrayList<String> toupleListA, PrintWriter out)throws IOException{

        StringBuilder sb= new StringBuilder();

        //System.out.println("touple list size:"+toupleListA.size());
        for(String toupleA : toupleListA){
            sb.append(key).append(";").append(toupleA).append(";").append(toupleB).append("\r\n");
        }
        out.print(sb.toString());
        //System.out.println(sb.toString());
    }

}
