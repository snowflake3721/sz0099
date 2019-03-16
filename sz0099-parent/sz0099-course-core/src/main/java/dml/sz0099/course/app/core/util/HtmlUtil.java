/**
 * Jit8j framework support by bruce yang 
 * contact email: 275060435@qq.com
 */
package dml.sz0099.course.app.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.HtmlUtils;

/**
 * <pre>
 * @formatter:off
 *
 * description: 功能说明
 * @author bruce yang at 2018-12-09 09:51:36
 * 
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang	2018-12-09	basic init
 * 
 * @formatter:on
 * </pre>
 */

public class HtmlUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HtmlUtil.class);

	// 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
   public static String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";

    // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
   public static String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";

    // 定义HTML标签的正则表达式
   public static String regEx_html = "<[^>]+>";

    // 定义空格回车换行符
   public static String regEx_space = "\\s*|\t|\r|\n";

    // 定义转义字符
   public static String regEx_escape = "&.{2,6}?;";
    
   public static Pattern p_script=Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);;
   
   public static Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
	
   public static Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
   public static Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
   
   public static Pattern p_escape = Pattern.compile(regEx_escape, Pattern.CASE_INSENSITIVE);
   /**
     * 过滤HTML标签输出文本
     *
     * @param inputString 原字符串
     * @return 过滤后字符串
     */
	public static String html2Text(String inputString) {
		if (StringUtils.isEmpty(inputString)) {
			return "";
		}

		// 含html标签的字符串
		String htmlStr = inputString.trim();
		String textStr = "";
		// 过滤script标签

		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll("");

		// 过滤style标签

		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll("");

		// 过滤html标签

		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll("");

		// 过滤空格回车标签

		Matcher m_space = p_space.matcher(htmlStr);
		htmlStr = m_space.replaceAll("");

		// 过滤转义字符

		Matcher m_escape = p_escape.matcher(htmlStr);
		htmlStr = m_escape.replaceAll("");

		textStr = htmlStr;

		// 返回文本字符串
		return textStr;
	}
	
	public static Integer countTextLength(String inputString) {
		String text = html2Text(inputString);
		return text.length();
	}
	
	public static String substring(String content, int begin, int maxLength) {
		if(StringUtils.isNotBlank(content)) {
			String text = html2Text(content);
			int length = text.length();
			if(length>maxLength && begin<maxLength) {
				text = text.substring(begin, maxLength-3)+"...";
			}
			return text;
		}
		return content;
	}
	
	
	/**
     * Html转换为TextArea文本
     * @return
     */
	public static String html2Textarea(String input) {
		if (input == null) {
			return "";
		} else if (input.length() == 0) {
			return "";
		}
		input = input.replaceAll("<br/>", "\r\n");
		input = input.replaceAll("&nbsp;", " ");
		return input;
	}
	
	public static String htmlUnescape2Textarea(String input) {
		input=textarea2UnEscapeForHtml(input);
		input = html2Textarea(input);
		return input;
	}

     /**
     * TextArea文本转换为Html:写入数据库时使用
     */
	public static String textarea2Html(String input) {
		if (input == null) {
			return "";
		} else if (input.length() == 0) {
			return "";
		}
		input = input.replaceAll("\r\n", "<br/>");
		input = input.replaceAll("\n", "<br/>");
		input = input.replaceAll("\r", "<br/>");
		input = input.replaceAll("\\s", "&nbsp;");
		//input=HtmlUtils.htmlEscape(input, "utf-8");
		return input;
	}
	
	public static String textarea2HtmlThenEscape(String input) {
		input=textarea2Html(input);
		input=HtmlUtils.htmlEscape(input, "utf-8");
		return input;
	}
	
	public static String textarea2Escape(String input) {
		input=textarea2Html(input);
		//input=StringEscapeUtils.escapeHtml(input);
		input=HtmlUtils.htmlEscape(input, "utf-8");
		input=StringEscapeUtils.escapeJava(input);
		return input;
	}
	
	public static String textarea2UnEscape(String input) {
		input=StringEscapeUtils.unescapeJava(input);
		input=HtmlUtils.htmlUnescape(input);
		input=StringEscapeUtils.unescapeHtml(input);
		input=html2Textarea(input);
		return input;
	}
	public static String textarea2UnEscapeForHtml(String input) {
		input=StringEscapeUtils.unescapeJava(input);
		input=StringEscapeUtils.unescapeHtml(input);
		return input;
	}
	
	public static void main(String[] args) {
		String str="  <stong>中文名称</strong>   七娘山\r\n" + 
				"地理位置   中国广东省深圳市龙岗区大鹏半岛\r\n" + 
				"气候类型   亚热带季风气候\r\n" + 
				"占地面积   20万平方米\r\n" + 
				"主峰海拔   867.4米";
		String text=textarea2Html(str);
		System.out.println(text);
		String html=StringEscapeUtils.escapeHtml(text);
		System.out.println(html);
		String textarea=html2Textarea(text);
		System.out.println(textarea);
	}

}
