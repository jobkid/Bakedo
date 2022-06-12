package test;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;


public class FileMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Member member = new Member();
		member.memberMenu();
			
	}

}

class Member
{
	
	File filename = new File("src/test/reservation.txt");
	FileReader frreader = new FileReader(filename);
	BufferedReader brreader = new BufferedReader(frreader);
	ArrayList<String> reservation=new ArrayList<String>();
	String data=null;
	Scanner sc=new Scanner(System.in);
	
	Member() throws IOException
	{
		File filename = new File("src/test/reservation.txt");
		FileReader frreader = new FileReader(filename);
		BufferedReader brreader = new BufferedReader(frreader);
	}
	
	void memberMenu() throws IOException
	{
		System.out.println("�޴��� �������ּ���. [1] ��ȭ���� [2] ���� ��Ȳ [3] ��ȭ ���� [4]�޴��� ���ư��� [0] ��ü����");
		int selectnum=sc.nextInt();
		switch(selectnum)
		{
		case 1:
		{
			getReservation();
			break;
		}
		case 2:{
			seeReservation();
			break;
		}
		case 3:
		{
			delReservation();
			break;
		}
		case 4:
		{
			memberMenu();
		}
		case 0:
			break;
		}
		
	}
	
	private void setReservation()
	{
		try
		{
			File movie = new File("src/test/movie.txt");
			FileReader frmovie = new FileReader(movie);
			BufferedReader brmovie = new BufferedReader(frmovie);
			String str=null;
			
			ArrayList<String> moviearrays=new ArrayList<String>();
			String data=null;
			while((data=brmovie.readLine())!=null)
			{
				moviearrays.add(data);
			}
			
			File reserve = new File("src/test/reservation.txt");
			FileWriter fwreserve=new FileWriter(reserve);
			BufferedWriter bwreserve=new BufferedWriter(fwreserve);
			Scanner sc=new Scanner(System.in);
			int j=0;
			int i=0;
			do
			{
				System.out.println("��ȭ�� �����Ͻðڽ��ϱ�? [1] ���� [2] ���");
				System.out.println("=================================");
				
				int selection=sc.nextInt();
				if(selection==1)
				{
					System.out.println("��Ҹ� �������ּ���.");
					String place=sc.next();
					
					for(i=0; i<moviearrays.size(); i++)
					{
						System.out.println("["+(i+1)+"��] "+moviearrays.get(i));
					}
					int purchase=sc.nextInt();
					String movienum=moviearrays.get(purchase-1);
					System.out.println("["+purchase+"��] ��ȭ�� �����߽��ϴ�.\n=================================");
					
					File seats = new File("src/test/seats.txt");
					FileReader frreader = new FileReader(seats);
					BufferedReader brreader = new BufferedReader(frreader);
					ArrayList<String> seatarr = new ArrayList<String>();
					
					while((str=brreader.readLine())!=null)
					{
						seatarr.add(str);
						System.out.println(str);
					}
					
					System.out.println("======================");
					System.out.println("��ȭ �¼��� �������ּ���");
					System.out.println("���ϴ� �¼� ���� �������ּ���. A-Z ������ ��ȣ�� �Է����ּ���. A�� 1, B�� 2");
					
					int row=sc.nextInt();
					String row2=seatarr.get(row-1);
					char alphabet=row2.charAt(0);
					System.out.println(alphabet+"���� �����ϼ̽��ϴ�.");
					System.out.println("���Ͻô� ���� �������ּ���.");
					
					int column=sc.nextInt();
					char column2=row2.charAt(3*column-1);
					String seatnumber=alphabet+"-"+column;
					
					bwreserve.write(place+", "+System.currentTimeMillis()+", "+moviearrays.get(purchase-1)+", "+seatnumber);
					bwreserve.newLine();
					System.out.println("��ȭ���Ÿ� �����մϴ�.");
					
				}
				else if(selection==2)
				{
					System.out.println("��ȭ ���Ÿ� ����߽��ϴ�.");
					break;
				}
				
			}
			while((j++)<moviearrays.size());
			bwreserve.close();
			fwreserve.close();
			System.out.println("��ȭ���Ÿ� �����մϴ�.");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("������ ã�� ���� �����ϴ�.");
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println("������ �ʰ��߽��ϴ�. �ٽ� �������ּ���.");
		}
		catch(IOException e)
		{}
	}
	
	void getReservation() throws IOException
	{
		setReservation();
		memberMenu();
	}
	
	void delReservation() throws IOException
	{
		int i=0;
		System.out.println("������ ��ȭ�� �������ּ���.\n===============================");
		
		while((data=brreader.readLine())!=null)
		{	
			System.out.println("["+(++i)+"]"+data);
			reservation.add(data);
			//System.out.println("["+(i++)+"]"+data);
		}
		/*
		for(i=0; i<reservation.size(); i++)
		{
			System.out.println("["+(i+1)+"]"+reservation.get(i));
		}
		*/
		int delnumber=sc.nextInt();
		reservation.remove(delnumber-1);
		System.out.println("��ȭ�� �����մϴ�.");
		
		File newfile=new File("src/test/reservation.txt");
		FileWriter fw=new FileWriter(newfile);
		BufferedWriter bw = new BufferedWriter(fw);		
		
		while(newfile.canWrite())
		{
			for(i=0; i<reservation.size(); i++)
			{
				bw.write(reservation.get(i));
				bw.newLine();
			}
			break;
		}
		bw.close();
		fw.close();
		
		System.out.println("���� �� ���ο� ��ȭ ���� �ϼ�.");
		memberMenu();
	}
	
	void seeReservation() throws IOException
	{
		while((data=brreader.readLine())!=null)
		{
			System.out.println(data);
		}
		System.out.println("=====================================");
		memberMenu();
	}
}