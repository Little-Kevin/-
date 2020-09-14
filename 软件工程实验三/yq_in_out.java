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
		String name = in.nextLine(); // 在控制台读一行
		String[] n = name.split(" "); // 按空格分开，保存到n数组
		String name1 = n[0]; // 输入文件名
		String name2 = n[1]; // 输出文件名
//		File file = new File("C:\\Users\\盐\\Desktop\\软件工程\\实验二\\yq_in.txt");
		File file = new File(name1);
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

//		FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\盐\\Desktop\\out.txt"));
		FileOutputStream fos = new FileOutputStream(new File(name2));
		PrintStream ps = new PrintStream(fos);

		if (n.length == 2) { // 未加省份情况
			String province = out[0].substring(0, 3);// 将省表明
			ps.println(province);
			for (int i = 0; i < out.length; i++) {
				String[] str = out[i].split("\t");// 让一行按tab分割成三段
				if (str[0].equals(province)) {
					if (!str[2].equals("0"))
						ps.println(str[1] + "\t" + str[2]);// 输出城市和数量

				} else {
					province = str[0];// 将省更迭
					ps.println();
					ps.println(province);
				}
			}
		} else { // 加省份情况
			String name3 = n[2]; // 省份名
			ps.println(name3);
			for (int i = 0; i < out.length; i++) {
				String[] str = out[i].split("\t");// 让一行按tab分割成三段
				if (str[0].equals(name3)) {
					if (!str[2].equals("0"))
						ps.println(str[1] + "\t" + str[2]);// 输出城市和数量
				}
			}
		}
		ps.close();
		in.close();
		System.out.println("文件已输出");
	}

}

//C:\Users\盐\Desktop\软件工程\实验二\yq_in.txt C:\\Users\\盐\\Desktop\\out.txt 河南省
//C:\Users\盐\Desktop\软件工程\实验二\yq_in.txt C:\\Users\\盐\\Desktop\\out.txt

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