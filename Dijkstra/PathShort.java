import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PathShort {
	int cities,roads,k,src,dest;
	String  paths[],kroads[];

	//File Input
	public void fileInput(String filename){

	    try {
            FileReader fileReader=new FileReader(filename);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            this.cities=Integer.parseInt(bufferedReader.readLine());
            this.roads=Integer.parseInt(bufferedReader.readLine());
						this.paths=new String[roads];
						for(int i=0;i<this.roads;i++){
							this.paths[i]=bufferedReader.readLine();
						}
            this.k=Integer.parseInt(bufferedReader.readLine());
						this.kroads=new String[k];
        		for(int i=0;i<k;i++){
							kroads[i]=bufferedReader.readLine();
						}
        		src=Integer.parseInt(bufferedReader.readLine());
        		dest=Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();
	  	}
	    catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" +filename + "'");
      }
      catch(IOException ex) {
            System.out.println( "Error reading file '" + filename + "'");
      }
	}

	//console Input
	public void consoleInput(){
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		System.out.println("Define your input");
		cities=sc.nextInt();
		roads=sc.nextInt();
		paths=new String[roads];
		for(int i=0;i<roads;i++){
			paths[i]=sc.next();
		}
		k=sc.nextInt();
		kroads=new String[k];
		for(int i=0;i<k;i++){
			kroads[i]=sc.next();
		}
		src=sc.nextInt();
		dest=sc.nextInt();

	}
}
