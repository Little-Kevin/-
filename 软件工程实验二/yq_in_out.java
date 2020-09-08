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
		File file = new File("C:\\Users\\盐\\Desktop\\软件工程\\实验二\\yq_in.txt");
		StringBuilder result = new StringBuilder();
		int index = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				out[index] = s;
				index++;
				result.append(System.lineSeparator() + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\盐\\Desktop\\out.txt"));
		PrintStream ps = new PrintStream(fos);
		String province = out[0].substring(0, 3);// 将省表明
//		System.out.println(province);
		ps.println(province);
		for (int i = 0; i < out.length; i++) {
			String[] str = out[i].split("\t");// 让一行按tab分割成三段
			if (str[0].equals(province)) {
				if (!str[2].equals("0")) {
//					System.out.println(str[1] + "\t" + str[2]);
					ps.println(str[1] + "\t" + str[2]);// 输出城市和数量
				}
			} else {
				province = str[0];// 将省更迭
//				System.out.println();
				ps.println();
//				System.out.println(province);
				ps.println(province);
			}
		}
		ps.close();
		System.out.println("文件已输出");
	}

}

//public static String txtIn(File file){
//StringBuilder result = new StringBuilder();
//try{
//  BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
//  String s = null;
//  while((s = br.readLine())!=null){//使用readLine方法，一次读一行
//      result.append(System.lineSeparator()+s);
//  }
//  br.close();
//}catch(Exception e){
//  e.printStackTrace();
//}
//return result.toString();
//}