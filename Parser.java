import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Parser{

	static String[] token = new String[0];
	static String[] token1 = new String[0];
	static String opcode;
	static String strLine="";
	static NumberFormat fb=new DecimalFormat("0000");
	
	public static void main(String args[]) throws IOException{

		try{
			// Open and read the file
			FileInputStream fstream = new FileInputStream("a.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			//Read file line by line and storing data in the form of tokens
			while((strLine = br.readLine()) != null){
				
				int middle=0;
				String location,value;
				if(strLine.startsWith("#")){
					for(int j=0;j<strLine.length();j++){
						if(strLine.charAt(j)=='='){
							middle=j;
							break;
						}
					}
					if(middle!=0){
						location=strLine.substring(1,middle);
						value=strLine.substring(middle+1);
						System.out.println(location+"   "+value);
					}
				}
				
				else{
					strLine = strLine.replaceAll("\\s?,\\s?",","); // replace space followed by comma or vice versa with a comma
					token = strLine.split(" |,");// split w.r.t spaces and , 
				
				
					int n = token.length;
					for(int i=1;i<n;i++){
						token[i]=token[i].replace("R",""); // remove R in the registries
					}
					
					// checking for appropriate operation
					if(token[0].equals("MOVE") || token[0].equals("NOT") || token[0].equals("AND")||token[0].equals("OR") || token[0].equals("ADD") || token[0].equals("SUB") 
					|| token[0].equals("ADDI") || token[0].equals("SUBI")|| token[0].equals("SET") || token[0].equals("SETH")|| token[0].equals("INCIZ") || token[0].equals("DECIN")
					|| token[0].equals("MOVEN") || token[0].equals("MOVEP")||token[0].equals("MOVEZ") || token[0].equals("MOVEX")){
					
						if(token[0].equals("MOVE")){
							opcode=move(strLine);
							System.out.println(opcode);
						}
			
						if(token[0].equals("NOT")){
							opcode=not(strLine);
							System.out.println(opcode);
						}
				
						if(token[0].equals("AND")){
							opcode=and(strLine);
							System.out.println(opcode);
						}
				
						if(token[0].equals("OR")){
							opcode=or(strLine);
							System.out.println(opcode);
						}
				
						if(token[0].equals("ADD")){
							opcode=add(strLine);
							System.out.println(opcode);
						}
				
						if(token[0].equals("SUB")){
							opcode=sub(strLine);
							System.out.println(opcode);
						}
				
						if(token[0].equals("ADDI")){
							opcode=addi(strLine);
							System.out.println(opcode);
						}
				
						if(token[0].equals("SUBI")){
							opcode=subi(strLine);
							System.out.println(opcode);
						}
				
						if(token[0].equals("SET")){
							opcode=set(strLine);
							System.out.println(opcode);
						}
				
						if(token[0].equals("SETH")){
							opcode=seth(strLine);
							System.out.println(opcode);
						}
				
						if(token[0].equals("INCIZ")){
							opcode=inciz(strLine);
							System.out.println(opcode);
						}
				
						if(token[0].equals("DECIN")){
							opcode=decin(strLine);
							System.out.println(opcode);
						}
				
						if(token[0].equals("MOVEZ")){
							opcode=movez(strLine);
							System.out.println(opcode);
						}
				
						if(token[0].equals("MOVEX")){
							opcode=movex(strLine);
							System.out.println(opcode);
						}
				
						if(token[0].equals("MOVEP")){
							opcode=movep(strLine);
							System.out.println(opcode);
						}
				
						if(token[0].equals("MOVEN")){
							opcode=moven(strLine);
							System.out.println(opcode);
						}
					}
					else{
						System.out.println("INVALID OPERATION");
					}
				}// else close
				
			} // while loop close
			in.close(); //Close the input stream
		} // try close
		
		catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
		}// main() close

	
	public static String move(String line){
	
		// MOVE Rd, Ra (leftover 4 bits)
		String opcode="0000",Rd,Ra;
		int dest,sour;
		Rd=token[1];
		Rd=Rd.trim();
		dest=Integer.parseInt(Rd);
		Rd=Integer.toBinaryString(dest);
		Rd=fb.format(Integer.parseInt(Rd));
	
		Ra=token[2];
		sour=Integer.parseInt(Ra);
		Ra=Integer.toBinaryString(sour);
		Ra=fb.format(Integer.parseInt(Ra));
				
		opcode=opcode+Rd+Ra+"0000";
		return opcode;
	}// move() close
	
	public static String not(String line){
	
		// NOT Rd, Ra (leftover 4 bits)
		String opcode="0001",Rd,Ra;
		int dest,sour;
		Rd=token[1];
		Rd=Rd.trim();
		dest=Integer.parseInt(Rd);
		Rd=Integer.toBinaryString(dest);
		Rd=fb.format(Integer.parseInt(Rd));
	
		Ra=token[2];
		sour=Integer.parseInt(Ra);
		Ra=Integer.toBinaryString(sour);
		Ra=fb.format(Integer.parseInt(Ra));
				
		opcode=opcode+Rd+Ra+"0000";
		return opcode;
	}// not() close
	
	public static String and(String line){
	
		// AND Rd, Ra, Rb
		String opcode="0010",Rd,Ra,Rb;
		int dest,sour, sour2;
		Rd=token[1];
		Rd=Rd.trim();
		dest=Integer.parseInt(Rd);
		Rd=Integer.toBinaryString(dest);
		Rd=fb.format(Integer.parseInt(Rd));
	
		Ra=token[2];
		sour=Integer.parseInt(Ra);
		Ra=Integer.toBinaryString(sour);
		Ra=fb.format(Integer.parseInt(Ra));
		
        Rb=token[3];
		sour2=Integer.parseInt(Rb);
		Rb=Integer.toBinaryString(sour2);
		Rb=fb.format(Integer.parseInt(Rb));
		
		opcode=opcode+Rd+Ra+Rb;
		return opcode;
	}// and() close
	
	public static String or(String line){
	
		// OR Rd, Ra, Rb
		String opcode="0011",Rd,Ra,Rb;
		int dest,sour, sour2;
		Rd=token[1];
		Rd=Rd.trim();
		dest=Integer.parseInt(Rd);
		Rd=Integer.toBinaryString(dest);
		Rd=fb.format(Integer.parseInt(Rd));
	
		Ra=token[2];
		sour=Integer.parseInt(Ra);
		Ra=Integer.toBinaryString(sour);
		Ra=fb.format(Integer.parseInt(Ra));
		
		Rb=token[3];
		sour2=Integer.parseInt(Rb);
		Rb=Integer.toBinaryString(sour2);
		Rb=fb.format(Integer.parseInt(Rb));
		
		opcode=opcode+Rd+Ra+Rb;
		return opcode;
	}// or() close
	
	public static String add(String line){
	
		// ADD Rd, Ra, Rb
		String opcode="0100",Rd,Ra,Rb;
		int dest,sour, sour2;
		Rd=token[1];
		Rd=Rd.trim();
		dest=Integer.parseInt(Rd);
		Rd=Integer.toBinaryString(dest);
		Rd=fb.format(Integer.parseInt(Rd));
	
		Ra=token[2];
		sour=Integer.parseInt(Ra);
		Ra=Integer.toBinaryString(sour);
		Ra=fb.format(Integer.parseInt(Ra));
		
		Rb=token[3];
		sour2=Integer.parseInt(Rb);
		Rb=Integer.toBinaryString(sour2);
		Rb=fb.format(Integer.parseInt(Rb));
		
		opcode=opcode+Rd+Ra+Rb;
		return opcode;
	}//close add()
		
	public static String sub(String line){
	
		// SUB Rd, Ra, Rb
		String opcode="0101",Rd,Ra,Rb;
		int dest,sour, sour2;
		Rd=token[1];
		Rd=Rd.trim();
		dest=Integer.parseInt(Rd);
		Rd=Integer.toBinaryString(dest);
		Rd=fb.format(Integer.parseInt(Rd));
	
		Ra=token[2];
		sour=Integer.parseInt(Ra);
		Ra=Integer.toBinaryString(sour);
		Ra=fb.format(Integer.parseInt(Ra));
		
		Rb=token[3];
		sour2=Integer.parseInt(Rb);
		Rb=Integer.toBinaryString(sour2);
		Rb=fb.format(Integer.parseInt(Rb));
		
		opcode=opcode+Rd+Ra+Rb;
		return opcode;
	}// sub() close
	
	public static String addi(String line){
	
		// ADDI Rd, Ra, Rb
		String opcode="0110",Rd,Ra,Rb;
		int dest,sour, sour2;
		Rd=token[1];
		Rd=Rd.trim();
		dest=Integer.parseInt(Rd);
		Rd=Integer.toBinaryString(dest);
		Rd=fb.format(Integer.parseInt(Rd));
	
		Ra=token[2];
		sour=Integer.parseInt(Ra);
		Ra=Integer.toBinaryString(sour);
		Ra=fb.format(Integer.parseInt(Ra));
		
		Rb=token[3];
		sour2=Integer.parseInt(Rb);
		Rb=Integer.toBinaryString(sour2);
		Rb=fb.format(Integer.parseInt(Rb));
		
		opcode=opcode+Rd+Ra+Rb;
		return opcode;
	}//close addi()
	
	public static String subi(String line){
	
		// SUBI Rd, Ra, Rb
		String opcode="0111",Rd,Ra,Rb;
		int dest,sour, sour2;
		Rd=token[1];
		Rd=Rd.trim();
		dest=Integer.parseInt(Rd);
		Rd=Integer.toBinaryString(dest);
		Rd=fb.format(Integer.parseInt(Rd));
	
		Ra=token[2];
		sour=Integer.parseInt(Ra);
		Ra=Integer.toBinaryString(sour);
		Ra=fb.format(Integer.parseInt(Ra));
		
		Rb=token[3];
		sour2=Integer.parseInt(Rb);
		Rb=Integer.toBinaryString(sour2);
		Rb=fb.format(Integer.parseInt(Rb));
		
		opcode=opcode+Rd+Ra+Rb;
		return opcode;
	}// subi() close
	
	public static String set(String line){
	
		// SET Rd, Ra, (leftover 4 bits)
		String opcode="1000",Rd,Ra;
		int dest,sour;
		Rd=token[1];
		Rd=Rd.trim();
		dest=Integer.parseInt(Rd);
		Rd=Integer.toBinaryString(dest);
		Rd=fb.format(Integer.parseInt(Rd));
	
		Ra=token[2];
		sour=Integer.parseInt(Ra);
		Ra=Integer.toBinaryString(sour);
		Ra=fb.format(Integer.parseInt(Ra));
				
		opcode=opcode+Rd+Ra+"0000";
		return opcode;
	}// set() close
	
	public static String seth(String line){
	
		// SETH Rd, Ra, (leftover 4 bits)
		String opcode="1001",Rd,Ra;
		int dest,sour;
		Rd=token[1];
		Rd=Rd.trim();
		dest=Integer.parseInt(Rd);
		Rd=Integer.toBinaryString(dest);
		Rd=fb.format(Integer.parseInt(Rd));
	
		Ra=token[2];
		sour=Integer.parseInt(Ra);
		Ra=Integer.toBinaryString(sour);
		Ra=fb.format(Integer.parseInt(Ra));
				
		opcode=opcode+Rd+Ra+"0000";
		return opcode;
	}// seth() close	
	
	public static String inciz(String line){
	
		// INCIZ Rd, Ra, Rb
		String opcode="1010",Rd,Ra,Rb;
		int dest,sour, sour2;
		Rd=token[1];
		Rd=Rd.trim();
		dest=Integer.parseInt(Rd);
		Rd=Integer.toBinaryString(dest);
		Rd=fb.format(Integer.parseInt(Rd));
	
		Ra=token[2];
		sour=Integer.parseInt(Ra);
		Ra=Integer.toBinaryString(sour);
		Ra=fb.format(Integer.parseInt(Ra));
		
		Rb=token[3];
		sour2=Integer.parseInt(Rb);
		Rb=Integer.toBinaryString(sour2);
		Rb=fb.format(Integer.parseInt(Rb));
		
		opcode=opcode+Rd+Ra+Rb;
		return opcode;
	}// inciz() close	
	
	public static String decin(String line){
	
		// DECIN Rd, Ra, Rb
		String opcode="1011",Rd,Ra,Rb;
		int dest,sour, sour2;
		Rd=token[1];
		Rd=Rd.trim();
		dest=Integer.parseInt(Rd);
		Rd=Integer.toBinaryString(dest);
		Rd=fb.format(Integer.parseInt(Rd));
	
		Ra=token[2];
		sour=Integer.parseInt(Ra);
		Ra=Integer.toBinaryString(sour);
		Ra=fb.format(Integer.parseInt(Ra));
		
		Rb=token[3];
		sour2=Integer.parseInt(Rb);
		Rb=Integer.toBinaryString(sour2);
		Rb=fb.format(Integer.parseInt(Rb));
		
		opcode=opcode+Rd+Ra+Rb;
		return opcode;
	}// decin() close

	public static String movez(String line){
	
		// MOVEZ Rd, Ra, Rb
		String opcode="1100",Rd,Ra,Rb;
		int dest,sour, sour2;
		Rd=token[1];
		Rd=Rd.trim();
		dest=Integer.parseInt(Rd);
		Rd=Integer.toBinaryString(dest);
		Rd=fb.format(Integer.parseInt(Rd));
	
		Ra=token[2];
		sour=Integer.parseInt(Ra);
		Ra=Integer.toBinaryString(sour);
		Ra=fb.format(Integer.parseInt(Ra));
		
		Rb=token[3];
		sour2=Integer.parseInt(Rb);
		Rb=Integer.toBinaryString(sour2);
		Rb=fb.format(Integer.parseInt(Rb));
		
		opcode=opcode+Rd+Ra+Rb;
		return opcode;
	}// movez() close
	
	public static String movex(String line){
	
		// MOVEX Rd, Ra, Rb
		String opcode="1101",Rd,Ra,Rb;
		int dest,sour, sour2;
		Rd=token[1];
		Rd=Rd.trim();
		dest=Integer.parseInt(Rd);
		Rd=Integer.toBinaryString(dest);
		Rd=fb.format(Integer.parseInt(Rd));
	
		Ra=token[2];
		sour=Integer.parseInt(Ra);
		Ra=Integer.toBinaryString(sour);
		Ra=fb.format(Integer.parseInt(Ra));
		
		Rb=token[3];
		sour2=Integer.parseInt(Rb);
		Rb=Integer.toBinaryString(sour2);
		Rb=fb.format(Integer.parseInt(Rb));
		
		opcode=opcode+Rd+Ra+Rb;
		return opcode;
	}// movex() close
	
	public static String movep(String line){
	
		// MOVEP Rd, Ra, Rb
		String opcode="1110",Rd,Ra,Rb;
		int dest,sour, sour2;
		Rd=token[1];
		Rd=Rd.trim();
		dest=Integer.parseInt(Rd);
		Rd=Integer.toBinaryString(dest);
		Rd=fb.format(Integer.parseInt(Rd));
	
		Ra=token[2];
		sour=Integer.parseInt(Ra);
		Ra=Integer.toBinaryString(sour);
		Ra=fb.format(Integer.parseInt(Ra));
		
		Rb=token[3];
		sour2=Integer.parseInt(Rb);
		Rb=Integer.toBinaryString(sour2);
		Rb=fb.format(Integer.parseInt(Rb));
		
		opcode=opcode+Rd+Ra+Rb;
		return opcode;
	}// movep() close
	
	public static String moven(String line){
	
		// MOVEN Rd, Ra, Rb
		String opcode="1111",Rd,Ra,Rb;
		int dest,sour, sour2;
		Rd=token[1];
		Rd=Rd.trim();
		dest=Integer.parseInt(Rd);
		Rd=Integer.toBinaryString(dest);
		Rd=fb.format(Integer.parseInt(Rd));
	
		Ra=token[2];
		sour=Integer.parseInt(Ra);
		Ra=Integer.toBinaryString(sour);
		Ra=fb.format(Integer.parseInt(Ra));
		
		Rb=token[3];
		sour2=Integer.parseInt(Rb);
		Rb=Integer.toBinaryString(sour2);
		Rb=fb.format(Integer.parseInt(Rb));
		
		opcode=opcode+Rd+Ra+Rb;
		return opcode;
	}// moven() close
	
}
