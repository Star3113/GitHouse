package 项目管理;

import java.util.*;

public class passwordManagement {

	public static void main(String[] args) {
		passwordEncryption p=new passwordEncryption();
		Scanner scanner = new Scanner(System.in);
		System.out.println("请选择功能：\n1. 加密密码\n2. 解密密码\n3. 判断密码强度\n4. 生成密码\n5.退出");
		int choice = scanner.nextInt();

		switch (choice) {
			case 1:
				System.out.println("请输入要加密的密码：");
				scanner.nextLine(); // 消耗换行符
				String encryptPassword = p.encrypt(scanner.nextLine());
				System.out.println("加密后的密码：" + encryptPassword);
				break;
			case 2:
				System.out.println("请输入要解密的密码：");
				scanner.nextLine(); // 消耗换行符
				String decryptPassword = p.decrypt(scanner.nextLine());
				System.out.println("解密后的密码：" + decryptPassword);
				break;
			case 3:
				System.out.println("请输入密码：");
				scanner.nextLine(); // 消耗换行符
				String password = scanner.nextLine();
				int strength = p.calculatePasswordStrength(password);
				System.out.println("密码强度：" + p.getStrengthDescription(strength));
				break;
			case 4:
				int length = 0;
				while (length <= 0 || length > 16) {
					System.out.println("请输入密码长度（不超过16）：");
					length = scanner.nextInt();
				}
				String generatedPassword = p.generatePassword(length);
				System.out.println("生成的密码：" + generatedPassword);
				break;
			case 5:
				break;
		  default:
			  
		}
		
		scanner.close();
	}

}

class passwordEncryption {
	public  String encrypt(String password) {
		StringBuilder encrypted = new StringBuilder();
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			int ascii = (int) c + (i + 1) + 3;
			encrypted.append((char) ascii);
		}
		char array[] = encrypted.toString().toCharArray();
		char b = array[0];
		array[0] = array[array.length - 1];// 将字符串的第一位和最后一位调换顺序
		array[array.length - 1] = b;
		StringBuilder result = new StringBuilder();
		result.append(array);
		return result.reverse().toString();// 将字符串反转
	}

	// 解密密码
	public  String decrypt(String encryptedPassword) {
		String reversed = reverse(encryptedPassword);
		char array[] = reversed.toString().toCharArray();
		char b = array[0];
		array[0] = array[array.length - 1];
		array[array.length - 1] = b;
		StringBuilder decrypted = new StringBuilder();
		for (int i = 0; i < reversed.length(); i++) {
			int ascii = (int)( array[i] - (i + 1) - 3);
			decrypted.append((char) ascii);
		}
		return decrypted.toString();
	}

	// 判断密码强度
	public  int calculatePasswordStrength(String password) {
		int strength = 0;
		if (password.length() >= 8) {
			strength++;
		}
		if (password.matches(".*\\d.*")) {
			strength++;
		}
		if (password.matches(".*[a-z].*")) {
			strength++;
		}
		if (password.matches(".*[A-Z].*")) {
			strength++;
		}
		return strength;
	}

	// 获取密码强度描述
	public  String getStrengthDescription(int strength) {
		switch (strength) {
			case 0:
				return "弱";
			case 1:
				return "较弱";
			case 2:
				return "中等";
			case 3:
				return "较强";
			case 4:
				return "强";
			default:
				return "无效";
		}
	}

	// 生成密码
	public  String generatePassword(int length) {
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder password = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random() * characters.length());
			password.append(characters.charAt(index));
		}
		return password.toString();
	}

	// 反转字符串
	public static String reverse(String str) {
		return new StringBuilder(str).reverse().toString();
	}
}
