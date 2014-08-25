import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

	public static String readFile(String path) throws IOException {
		File f = new File(path);
		if (f.exists()) {
			BufferedReader bf = new BufferedReader(new FileReader(f));
			String temp = null;
			StringBuffer sb = new StringBuffer();
			temp = bf.readLine();
			while (temp != null) {
				sb.append(temp + "\r\n");
				temp = bf.readLine();
			}
			return sb.toString();
		} else {
			System.out.println(f.getAbsolutePath().concat("无此路径"));
			return null;
		}
	}

	public static void writeFile(String path, String data) throws IOException {
		File f = new File(path);
		if (!f.exists())
			f.createNewFile();
		FileOutputStream out = new FileOutputStream(f);
		out.write(data.getBytes());
		out.close();
		System.out.println("输出："+path);
	}
}
