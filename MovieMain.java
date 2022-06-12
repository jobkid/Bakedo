package project;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MovieMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Manager admin = new Manager();
		//admin.getMovie();
		admin.setSeat();
		System.out.println("====================");
		int [] test= {3,5,3};
		
		File i=admin.seatsfile;
		
		FileInputStream fis = new FileInputStream(i);
		DataInputStream dti=new DataInputStream(fis);
		
		dti.read();
		System.out.println();
	}

}

class Manager{
	
	File seatsfile=new File("src/project/seats.txt");;
	FileWriter fwseats=new FileWriter(seatsfile);;
	BufferedWriter bwseats=new BufferedWriter(fwseats);;
	
	
	Manager() throws IOException
	{
		File seatsfile=new File("src/project/seats.txt");;
		FileWriter fwseats=new FileWriter(seatsfile);;
		BufferedWriter bwseats=new BufferedWriter(fwseats);;
	}
	
	public void setMovie() throws IOException{
		
		FileWriter movie = new FileWriter("src/project/movie.txt");
		BufferedWriter bw = new BufferedWriter(movie);
		
		//System.out.println("영화 등록은 '1' 종료는 '0'");
		//System.out.println("영화 제목, 장르, 연령대를 5번 작성해주세요. 작성은 '1' 종료는 '0'");
		//Scanner sc = new Scanner(System.in);
		//int tmp=sc.nextInt();
		
		int i=5;
		while (i>0){
			System.out.println("영화 제목, 장르, 연령대를 5번 작성해주세요. 현재 "+i+"번 남았습니다. 작성은 '1' 종료는 '0'");
			
			Scanner sc = new Scanner(System.in);
			int tmp=sc.nextInt();
			if(tmp==1)
			{
				
				
				Scanner moviesc = new Scanner(System.in);
				
				System.out.print("영화를 등록합니다.");
				String title=moviesc.nextLine();
				
				System.out.print("장르를 입력해주세요.");
				String janre=moviesc.nextLine();
				
				System.out.print("연령대를 입력해주세요.");
				String age=moviesc.nextLine();
			
				System.out.println("========================================");
				
				
				bw.write(System.currentTimeMillis()+", "+title+", "+janre+", "+age);
				bw.newLine();
			}
			else if(tmp==0)
			{
				System.out.println("영화등록이 취소되었습니다.");
			}
			i--;
		}
		bw.close();
		movie.close();
		
		System.out.println("영화 등록 종료");

	}
	
	void getMovie() throws IOException
	{
		setMovie();
//		FileReader movies=new FileReader("src/project/movie.txt");
//		BufferedReader br=new BufferedReader(movies);
//		
//		br.close();
//		movies.close();
	}
	
	void setDelete() throws IOException
	{
		
	}
	
	void setSeat() throws IOException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("좌석 행을 지정해주세요.");
		int seatrow=sc.nextInt();
		
		System.out.println("좌석 열을 지정해주세요.");
		int seatculumn=sc.nextInt();
		char []row=new char [seatrow];
		int [][] seats = new int[seatrow][seatculumn];
		int sum=1;
		for(int i=0; i<seats.length; i++)
		{
			int j=0;
			for(j=0;j<seats[i].length; j++)
			{
				seats[i][j]=sum+j;
			}
			char seatAlphabet=(char)(i+65);
			row[i]=seatAlphabet;
			
			System.out.println("===========================");
			System.out.println(row[i]+Arrays.toString(seats[i]));
			String str=row[i]+Arrays.toString(seats[i]);
			bwseats.write(row[i]+Arrays.toString(seats[i]));
			bwseats.newLine();
	
		}
		bwseats.close();
		fwseats.close();		
	}
	
	void reservation()
	{
		
		
	}
}	