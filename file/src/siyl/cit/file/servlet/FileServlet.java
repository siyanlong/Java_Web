package siyl.cit.file.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import siyl.cit.file.model.MultipartRequestWarpper;

/**
 * Servlet implementation class FileServlet
 */
public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		fileNew(request, response);
	}

	private void fileOld(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		InputStream is = null;
		FileOutputStream fos = null;
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
				ServletFileUpload upload = new ServletFileUpload();
				FileItemIterator iter = upload.getItemIterator(request);
				while (iter.hasNext()) {
					FileItemStream fis = iter.next();
					// 使用了multipart之后就表示通过字节数据进行传递，所以需要将其转化为输入流
					is = fis.openStream();
					// isFormField方法用来判断是否是普通的表单域
					if (fis.isFormField()) {
						// 如果是表单域可以获取表单域的名称
						System.out.println(fis.getFieldName());
						// 通过Streams中的asString方法可以把流中的数据转换为String
						System.out.println(Streams.asString(is));
					} else {
						// 如果不是表单域，表示是文件，此时可以获取文件的名称
						System.out.println(fis.getName());
						String path = request.getSession().getServletContext().getRealPath("/upload");
						path = path + "/" + fis.getName();
						System.out.println(path);
						fos = new FileOutputStream(path);
						byte[] buf = new byte[1024];
						int len = 0;
						while ((len = is.read(buf)) > 0) {
							fos.write(buf, 0, len);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				is.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}

	private void fileNew(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(request)) {
			request = new MultipartRequestWarpper(request);
		}

		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("photo"));
		String[] interest = request.getParameterValues("interest");
		for (String in : interest) {
			System.out.println(in);
		}
	}
}
