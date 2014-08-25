import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonToClasses {

	private static String header = "//\r\n" + "//  {MessageFileName}\r\n"
			+ "//\r\n" + "//  Created by JsonTool \r\n" + "//  {CREATE_TIME}\r\n"
			+ "//  Copyright (c) 2014 YudiMo. All rights reserved.\r\n"
			+ "//\r\n" + "#import <Foundation/Foundation.h>\r\n"
			+ "#import \"{MESSAGE_HEADER_FILE_NAME}\"";

	public static String MSG_NAME_PROP_MARK = "msgName";

	public static void main(String[] args) throws IOException {
		String jsonStr = FileUtil.readFile(args[0]);
		String templateM = FileUtil.readFile(args[1]);
		String templateH = FileUtil.readFile(args[2]);
		Pattern pattern = Pattern
				.compile("message\\s+(\\w+)\\{(\\s+(.*?))+\\s*\\}");
		Matcher matcher = pattern.matcher(jsonStr);

		String outHName = args[3].split("/")[args[3].split("/").length - 1];
		String outMName = args[4].split("/")[args[4].split("/").length - 1];
		String time = new Date().toGMTString();
		String outHStr = header.replace("{MESSAGE_HEADER_FILE_NAME}", outHName)
				+ "\r\n";
		outHStr = outHStr.replace("{CREATE_TIME}", time) + "\r\n";
		String outMStr = header.replace("{MESSAGE_HEADER_FILE_NAME}", outHName)
				+ "\r\n";
		outMStr = outMStr.replace("{CREATE_TIME}", time) + "\r\n";
		while (matcher.find()) {
			String message = matcher.group();
			String msgName = matcher.group(1);
			System.out.println("MESSAGE ［ " + msgName + " ］ GENERATING...");
			outHStr += templateH + "\r\n";
			outHStr = outHStr.replace("{MessageFileName}", msgName + ".h");
			outHStr = outHStr.replace("{MESSAGE_NAME}", msgName);
			outMStr += templateM + "\r\n";
			outMStr = outMStr.replace("{MessageFileName}", msgName + ".m");
			outMStr = outMStr.replace("{MESSAGE_NAME}", msgName);

			pattern = Pattern.compile("(\\w+)\\s(\\w+);");
			Matcher mt = pattern.matcher(message);
			// h
			String prop_declaration = "";
			String prop_property = "";
			// m
			String prop_example = "";
			String prop_assignment = "";
			String prop_synthesize = "";
			String prop_dic="";
			while (mt.find()) {

				String propType = mt.group(1).toLowerCase();
				String propName = mt.group(2);
				System.out.println("  propType:" + propType + "  propName:"
						+ propName);
				
				prop_example += propName + ":32131,\r\n *";
				prop_synthesize += "@synthesize " + propName + " = _"
						+ propName + ";\r\n";
				prop_dic+="\t_"+propName+","+"@\""+propName+"\",\r\n";
				
				// 整数
				if (propType.equals("int") || propType.equals("integer")) {
					prop_declaration += "int " + "_" + propName + ";\r\n\t";
					prop_property += "@property int " + propName + ";\r\n";
					prop_assignment += "self." + propName
							+ " = [[json objectForKey:@\"" + propName
							+ "\"] intValue];\r\n\t";
				}
				// 浮点数
				else if (propType.equals("number")) {
					prop_declaration += "NSNumber* " + "_" + propName
							+ ";\r\n\t";
					prop_property += "@property (copy)NSNumber* " + propName
							+ ";\r\n";
					prop_assignment += "self."
							+ propName
							+ " = [[[NSNumberFormatter alloc] init] numberFromString:[json objectForKey:@\""
							+ propName + "\"]];\r\n\t";
				}
				// 字符串
				else if (propType.equals("string")) {
					prop_declaration += "NSString* " + "_" + propName
							+ ";\r\n\t";
					prop_property += "@property (copy)NSString* " + propName
							+ ";\r\n";
					prop_assignment += "self." + propName
							+ " = [json objectForKey:@\"" + propName
							+ "\"];\r\n\t";
				}
			}
			outHStr = outHStr.replace("{PROP_DECLARATION}", prop_declaration);
			outHStr = outHStr.replace("{PROP_PROPERTY}", prop_property);

			outMStr = outMStr.replace("{PROP_EXAMPLE}", prop_example);
			outMStr = outMStr.replace("{PROP_ASSIGNMENT}", prop_assignment);
			outMStr = outMStr.replace("{PROP_SYNTHESIZE}", prop_synthesize);
			outMStr = outMStr.replace("{PROP_DIC}", prop_dic);
		}
		FileUtil.writeFile(args[3], outHStr);
		FileUtil.writeFile(args[4], outMStr);
		// System.out.println(key+","+json.get(key));
		// }
	}
}
