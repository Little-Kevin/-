package Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

public class yq_in_out {

	public static void main(String[] args) throws FileNotFoundException {
		String[] out = new String[129];
		File file = new File("C:\\Users\\��\\Desktop\\�������\\ʵ���\\yq_in.txt");
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

		FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\��\\Desktop\\out.txt"));
		PrintStream ps = new PrintStream(fos);
		String province = out[0].substring(0, 3);// ��ʡ����
//		System.out.println(province);
		ps.println(province);
		for (int i = 0; i < out.length; i++) {
			String[] str = out[i].split("\t");// ��һ�а�tab�ָ������
			if (str[0].equals(province)) {
				if (!str[2].equals("0")) {
//					System.out.println(str[1] + "\t" + str[2]);
					ps.println(str[1] + "\t" + str[2]);// ������к�����
				}
			} else {
				province = str[0];// ��ʡ����
//				System.out.println();
				ps.println();
//				System.out.println(province);
				ps.println(province);
			}
		}
		ps.close();
		System.out.println("�ļ������");
	}

}

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