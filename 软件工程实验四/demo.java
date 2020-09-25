package Week4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class demo {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		City[] city = new City[129];
		String fileName = in.nextLine(); // 在控制台读一行
		String[] fn = fileName.split(" "); // 按空格分开，保存到n数组
		String name1 = fn[0]; // 输入文件名
		String name2 = fn[1]; // 输出文件名
		File file = new File(name1);
		int index1 = 0;
		String[] str = new String[3];
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				str = s.split("\t");
				city[index1] = new City();
				city[index1].province = str[0];
				city[index1].name = str[1];
				city[index1].num = Integer.parseInt(str[2]);
				index1++;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		文件输出
		FileOutputStream fos = new FileOutputStream(new File(name2));
		PrintStream ps = new PrintStream(fos);
//		对省统计
		Province[] province = new Province[9];
		int index2 = 0;
		String cur_province = city[0].province;
		province[index2] = new Province();
		province[index2].name = cur_province;
		for (int i = 0; i < city.length; i++) {
			if (city[i].province.equals(cur_province)) {
//				System.out.println(city[i].name + "\t" + city[i].num);
				province[index2].sum += city[i].num;
			} else {
				index2++;
				cur_province = city[i].province;
				province[index2] = new Province();
				province[index2].sum += city[i].num;
				province[index2].name = cur_province;
//				System.out.println("\n" + cur_province);
			}
		}
//		对省的对象数组排序
		for (int i = 0; i < province.length; i++) {
			for (int j = i + 1; j < province.length; j++) {
				if (province[i].sum < province[j].sum) {
					String temp1 = province[i].name;
					int temp2 = province[i].sum;
					province[i].name = province[j].name;
					province[i].sum = province[j].sum;
					province[j].name = temp1;
					province[j].sum = temp2;
				}
			}
		}
//		对城市对象数组排序
		for (int i = 0; i < city.length; i++) {
			for (int j = i + 1; j < city.length; j++) {
				if (city[i].num < city[j].num) {
					String temp1 = city[i].name;
					String temp2 = city[i].province;
					int temp3 = city[i].num;
					city[i].name = city[j].name;
					city[i].province = city[j].province;
					city[i].num = city[j].num;
					city[j].name = temp1;
					city[j].province = temp2;
					city[j].num = temp3;
				}
				if (city[i].num == city[j].num) {
					if (city[i].name.charAt(0) > city[j].name.charAt(0)) {
						String temp1 = city[i].name;
						String temp2 = city[i].province;
						int temp3 = city[i].num;
						city[i].name = city[j].name;
						city[i].province = city[j].province;
						city[i].num = city[j].num;
						city[j].name = temp1;
						city[j].province = temp2;
						city[j].num = temp3;
					}
				}
			}
		}
		String op = new String();
		for (int i = 0; i < province.length; i++) {
			op = province[i].name;
//			System.out.println(province[i].name + "\t" + province[i].sum);
			ps.println(province[i].name + "\t" + province[i].sum);
			for (int j = 0; j < city.length; j++) {
				if (city[j].province.equals(op)) {
//					System.out.println(city[j].name + "\t" + city[j].num);
					ps.println(city[j].name + "\t" + city[j].num);
				}
			}
//			System.out.println();
			ps.println();
		}
		System.out.println("文件已输出");
		in.close();
		ps.close();
	}

}
/*
 * C:\Users\盐\Desktop\软件工程\实验四\yq_in.txt C:\\Users\\盐\\Desktop\\out04.txt
 */
