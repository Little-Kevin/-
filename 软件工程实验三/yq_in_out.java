package Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class yq_in_out {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		String[] out = new String[129];
		String name = in.nextLine(); // �ڿ���̨��һ��
		String[] n = name.split(" "); // ���ո�ֿ������浽n����
		String name1 = n[0]; // �����ļ���
		String name2 = n[1]; // ����ļ���
//		File file = new File("C:\\Users\\��\\Desktop\\�������\\ʵ���\\yq_in.txt");
		File file = new File(name1);
		StringBuilder result = new StringBuilder();
		int index = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// ����һ��BufferedReader������ȡ�ļ�
			String s = null;
			while ((s = br.readLine()) != null) {// ʹ��readLine������һ�ζ�һ��
				out[index] = s;
				index++;
				result.append(System.lineSeparator() + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

//		FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\��\\Desktop\\out.txt"));
		FileOutputStream fos = new FileOutputStream(new File(name2));
		PrintStream ps = new PrintStream(fos);

		if (n.length == 2) { // δ��ʡ�����
			String province = out[0].substring(0, 3);// ��ʡ����
			ps.println(province);
			for (int i = 0; i < out.length; i++) {
				String[] str = out[i].split("\t");// ��һ�а�tab�ָ������
				if (str[0].equals(province)) {
					if (!str[2].equals("0"))
						ps.println(str[1] + "\t" + str[2]);// ������к�����

				} else {
					province = str[0];// ��ʡ����
					ps.println();
					ps.println(province);
				}
			}
		} else { // ��ʡ�����
			String name3 = n[2]; // ʡ����
			ps.println(name3);
			for (int i = 0; i < out.length; i++) {
				String[] str = out[i].split("\t");// ��һ�а�tab�ָ������
				if (str[0].equals(name3)) {
					if (!str[2].equals("0"))
						ps.println(str[1] + "\t" + str[2]);// ������к�����
				}
			}
		}
		ps.close();
		in.close();
		System.out.println("�ļ������");
	}

}

//C:\Users\��\Desktop\�������\ʵ���\yq_in.txt C:\\Users\\��\\Desktop\\out.txt ����ʡ
//C:\Users\��\Desktop\�������\ʵ���\yq_in.txt C:\\Users\\��\\Desktop\\out.txt

//public static String txtIn(File file){
//StringBuilder result = new StringBuilder();
//try{
//  BufferedReader br = new BufferedReader(new FileReader(file));//����һ��BufferedReader������ȡ�ļ�
//  String s = null;
//  while((s = br.readLine())!=null){//ʹ��readLine������һ�ζ�һ��
//      result.append(System.lineSeparator()+s);
//  }
//  br.close();
//}catch(Exception e){
//  e.printStackTrace();
//}
//return result.toString();
//}