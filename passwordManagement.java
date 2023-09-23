package 项目管理;

import java.util.*;

public class passwordManagement {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请选择功能：\n1. 加密\n2. 解密\n3. 判断密码强度\n4. 密码生成\n5.退出");
		int choice = sc.nextInt();
		switch (choice) {
			case 1:
				passwordEncryption p = new passwordEncryption();// demodemo
				System.out.println("请输入要加密的数字密码：");
				System.out.println("加密后的密码：" + p.Encryption(sc.next()));
				break;

		}

	}

}

class passwordEncryption {
	public String Encryption(String password) {
		StringBuilder encrypted = new StringBuilder();
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			char num = (char) (c + (i + 1) + 3);
			encrypted.append(num);
		} // 将每个字符的ASCII码加上它在字符串中的位置(1开始)和偏移值3
		char array[] = encrypted.toString().toCharArray();
		char b = array[0];
		array[0] = array[array.length - 1];// 将字符串的第一位和最后一位调换顺序
		array[array.length - 1] = b;
		StringBuilder result = new StringBuilder();
		result.append(array);
		return result.reverse().toString();// 将字符串反转
	}
}
