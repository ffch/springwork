package com.cff.springwork.websocket.endpoint;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.socket.BinaryMessage;

import com.cff.springwork.websocket.handler.WebsocketHandler;

@Controller("webSocketController")
@RequestMapping("/webSocket")
public class WebSocketController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="/fileUpload")
	public void fileUpload(@RequestParam("userId") String userId, @RequestParam MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException{
		//可以在上传文件的同时接收其它参数
		System.out.println("收到发往用户[" + userId + "]的文件上传请求");
		
		//如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
		//这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建
		String realPath = request.getSession().getServletContext().getRealPath("/upload");
		System.out.println("路径[" + realPath + "]的文件上传请求");
		System.out.println("文件数量："+myfiles.length);
		realPath = "D:/doc/";
		//设置响应给前台内容的数据格式
		response.setContentType("text/plain; charset=UTF-8");
		//上传文件的原名(即上传前的文件名字)
		String originalFilename = null;
		//如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解
		//如果想上传多个文件,那么这里就要用MultipartFile[]类型来接收文件,并且要指定@RequestParam注解
		//上传多个文件时,前台表单中的所有<input type="file"/>的name都应该是myfiles,否则参数里的myfiles无法获取到所有上传的文件
		for(MultipartFile myfile : myfiles){
			if(myfile.isEmpty()){
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("102");
				return;
			}else{
				originalFilename = myfile.getOriginalFilename();
				System.out.println("文件原名: " + originalFilename);
				System.out.println("文件名称: " + myfile.getName());
				System.out.println("文件长度: " + myfile.getSize());
				System.out.println("文件类型: " + myfile.getContentType());
				System.out.println("========================================");
				try {
					//这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
					//此处也可以使用Spring提供的MultipartFile.transferTo(File dest)方法实现文件的上传
					//FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, originalFilename));
					
					ByteArrayOutputStream swapStream = new ByteArrayOutputStream(); 
					byte[] buff = new byte[100]; //buff用于存放循环读取的临时数据 
					int rc = 0; 
					InputStream is = myfile.getInputStream();
					while ((rc = is.read(buff, 0, 100)) > 0) { 
					swapStream.write(buff, 0, rc); 
					} 
					byte[] in_b = swapStream.toByteArray(); //in_b为转换之后的结果
					System.out.println("正在发送文件: ");

					WebsocketHandler.sendBinaryMessageToUser(userId,new BinaryMessage(in_b));
				} catch (IOException e) {
					System.out.println("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");
					e.printStackTrace();
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write("101");
					return ;
				}
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("200");
		return;
	}
	
}
