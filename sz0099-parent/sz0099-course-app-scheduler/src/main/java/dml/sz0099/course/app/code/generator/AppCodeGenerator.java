package dml.sz0099.course.app.code.generator;

import org.jit4j.app.code.template.CodeGenerator;
import org.jit4j.app.code.template.MethodGenerator;

/**
 * 
 * social模块代码生成工具
 * 可按规范生成标准的层次结构代码与方法生成
 * ----------------------------------------------------------------------------------------
 * Requirement		Author		Date		Function
 * init				bruceyang   2018-04-11  basic init
 * 
 * 
 */
public class AppCodeGenerator {

	public static void config() {
		
		CodeGenerator.DEMO_STORED_PROJECT="sz0099-course-app-template";//demo代码所在工程
		CodeGenerator.ENTITY_STORED_PROJECT="sz0099-course-domain";//实体类所在工程
		CodeGenerator.DELEGATE_STORED_PROJECT="sz0099-course-delegate";//delegate存放的工程
		CodeGenerator.WRAPPER_STORED_PROJECT="sz0099-course-web-wrapper";//client wrapper存放的工程
		CodeGenerator.DEMO_WRAPPER_STORED_PROJECT="sz0099-course-wrapper-template";//client demo wrapper存放的工程
		CodeGenerator.GENERATE_CODE_STORED_PROJECT_AUTH="sz0099-course-app-scheduler";//生成代码存放的工程
		CodeGenerator.ENTITY_PACKAGE_ROOT = "dml.sz0099.course.app.persist.entity.scheduler";
		CodeGenerator.PACKAGE_NAME_BASE = "dml.sz0099.course.app";
		
		CodeGenerator.PACKAGE_NAME_BASE_PERSIST_ENTITY="dml.sz0099.course.app.persist.entity";
		CodeGenerator.PACKAGE_NAME_PERSIST_DEMO_ENTITY="dml.sz0099.course.app.code.persist.entity.template";
		
	}
	
	
	public static void generateFile() {
		//1.先生成文件
		CodeGenerator.generateModuleLevelCode();
	}
	
	public static void generateMethod() {
		//3.生成方法，生成代码
		String rootPackageName = CodeGenerator.ENTITY_PACKAGE_ROOT;
		
		MethodGenerator.generateMethod(rootPackageName);
	}
	
	public static void main(String[] args) {
		//1.初始化配置
		config();
		//2.生成文件(要与第3步生成代码的方法分开执行)
		generateFile();
		
		//3.生成方法，生成代码（要与生成文件分开执行）
		//generateMethod();
		
	}
}
