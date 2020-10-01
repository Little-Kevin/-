package Week5;

import java.io.*;

import java.util.Scanner;

import Week4.City;

public class Demo {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		if (s.equals("")) {
			f1();
		} else {
			String[] str = s.split(" ");
			switch (str.length) {
			case 2:
			f2(str[0],str[1]);
				break;
			case 3:
			f3(str[0],str[1],str[2]);
				break;
			default:
				break;
			}
		}
		System.out.println("\n文件已输出");
		in.close();
	}

	private static void f1() throws FileNotFoundException {
		String read = "C:\\Users\\盐\\Desktop\\软件工程\\实验五\\yq_in.txt";
		String write = "C:\\Users\\盐\\Desktop\\软件工程\\实验五\\out05.txt";
		City[] city = fileIn(read);
		FileOutputStream fos = new FileOutputStream(new File(write));
		PrintStream out = new PrintStream(fos);
		String cur_province = city[0].getProvince();
		System.out.println(cur_province);
		out.println(cur_province);
		for (int i = 0; i < city.length; i++) {
			if (city[i].getProvince().equals(cur_province)) {
				System.out.println(city[i].getName() + "\t" + city[i].getNum());
				out.println(city[i].getName() + "\t" + city[i].getNum());
			} else {
				cur_province = city[i].getProvince();
				System.out.println("\n"+cur_province);
				out.println("\n"+cur_province);
			}
		}
		out.close();
	}
	private static void f2(String read, String write) throws FileNotFoundException {
		
		City[] city = fileIn(read);
		Province[] ps = ProvinceSort(city);
		City[] cs = CitySort(city);
		FileOutputStream fos = new FileOutputStream(new File(write));
		PrintStream out = new PrintStream(fos);
		String op = new String();
		for (int i = 0; i < ps.length; i++) {
			op = ps[i].getName();
			System.out.println(ps[i].getName() + "\t" + ps[i].getSum());
			out.println(ps[i].getName() + "\t" + ps[i].getSum());
			for (int j = 0; j < cs.length; j++) {
				if (cs[j].getProvince().equals(op)) {
					System.out.println(cs[j].getName() + "\t" + cs[j].getNum());
					out.println(city[j].getName() + "\t" + city[j].getNum());
				}
			}
			System.out.println();
			out.println();
		}
		out.close();
	}
//	C:\Users\盐\Desktop\软件工程\实验五\yq_in.txt C:\Users\盐\Desktop\软件工程\实验五\out05.txt
	
	private static void f3(String read, String write, String province) throws FileNotFoundException {
		City[] city = fileIn(read);
		City[] cs = CitySort(city);
		FileOutputStream fos = new FileOutputStream(new File(write));
		PrintStream out = new PrintStream(fos);
		System.out.println(province);
		out.println(province);
		for(int i=0; i<cs.length; i++) {
			if(cs[i].getProvince().equals(province)) {
				System.out.println(cs[i].getName()+"\t"+cs[i].getNum());
				out.println(cs[i].getName()+"\t"+cs[i].getNum());
			}
		}
		out.close();
	}
	
	
	public static City[] fileIn(String path) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		City[] city = new City[129];
		File file = new File(path);
		int index1 = 0;
		String[] str = new String[3];
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				str = s.split("\t");
				city[index1] = new City();
				city[index1].setProvince(str[0]);
				city[index1].setName(str[1]);
				city[index1].setNum(Integer.parseInt(str[2]));
				index1++;
			}
			br.close();
			return city;
		} catch (Exception e) {
			e.printStackTrace();
		}
		in.close();
		return null;
	}


	public static Province[] ProvinceSort(City[] city) { // 对省份的统计和排序
		// 获取有几个省份
		String province = city[0].getProvince(); // 输出第一行数据的省份
		int n = 1;
		for (int i = 0; i < city.length; i++) {
			if (!city[i].getProvince().equals(province)) {
				province = city[i].getProvince();
				n++;
			}
		}
		Province[] pn = new Province[n]; // 存放省份和其总数据
		int p = 0;
		province = city[0].getProvince();
		pn[p] = new Province();
		pn[p].setName(province);
		for (int i = 0; i < city.length; i++) {
			if (city[i].getProvince().equals(province)) {
				int a = pn[p].getSum();
				int b = a + city[i].getNum();
				pn[p].setSum(b);
			} else {
				province = city[i].getProvince();
				p++;
				pn[p] = new Province();
				int a = pn[p].getSum();
				int b = a + city[i].getNum();
				pn[p].setSum(b);
				pn[p].setName(province);
			}
		}
		String Ptmp = new String(); // 对省份和数据从大到小排序
		int Ntmp = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (pn[i].getSum() < pn[j].getSum()) {
					Ptmp = pn[i].getName();
					Ntmp = pn[i].getSum();
					pn[i].setName(pn[j].getName());
					pn[i].setSum(pn[j].getSum());
					pn[j].setName(Ptmp);
					pn[j].setSum(Ntmp);
				}
			}
		}
		return pn;

	}

	public static City[] CitySort(City[] city) {
		for (int i = 0; i < city.length; i++) {
			for (int j = i + 1; j < city.length; j++) {
				if (city[i].getNum() < city[j].getNum()) {
					String temp1 = city[i].getName();
					String temp2 = city[i].getProvince();
					int temp3 = city[i].getNum();
					city[i].setName(city[j].getName());
					city[i].setProvince(city[j].getProvince());
					city[i].setNum(city[j].getNum());
					city[j].setName(temp1);
					city[j].setProvince(temp2);
					city[j].setNum(temp3);
				}
				if (city[i].getNum() == city[j].getNum()) {
					if (city[i].getName().charAt(0) > city[j].getName().charAt(0)) {
						String temp1 = city[i].getName();
						String temp2 = city[i].getProvince();
						int temp3 = city[i].getNum();
						city[i].setName(city[j].getName());
						city[i].setProvince(city[j].getProvince());
						city[i].setNum(city[j].getNum());
						city[j].setName(temp1);
						city[j].setProvince(temp2);
						city[j].setNum(temp3);
					}
				}
			}
		}

		return city;

	}

}
