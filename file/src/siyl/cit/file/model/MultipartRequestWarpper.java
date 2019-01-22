package siyl.cit.file.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;

public class MultipartRequestWarpper extends HttpServletRequestWrapper {
	Map<String, String[]> params = new HashMap<String, String[]>();

	public final static String PATH = "E:\\MyProject\\Java_Web\\trunk\\file\\WebContent\\upload";

	public MultipartRequestWarpper(HttpServletRequest request) {
		super(request);
		setParams(request);
	}

	private void setParams(HttpServletRequest request) {
		// 1、判断是否是multipart类型
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			ServletFileUpload upload = new ServletFileUpload();
			InputStream is = null;
			try {
				FileItemIterator iter = upload.getItemIterator(request);
				while (iter.hasNext()) {
					FileItemStream fis = iter.next();
					is = fis.openStream();
					if (fis.isFormField()) {
						// params.put(fis.getFieldName(), new String[] { Streams.asString(is) });
						setFormParams(fis.getFieldName(), is);
					} else {
						// 要输入的流中有数据才进行上传
						if (is.available() > 0) {
							// 对于IE而言，上传的文件会获取完整的绝对路径，此时就需要仅仅获取绝对路径中的文件名
							String fileName = FilenameUtils.getName(fis.getName());
							// 完成文件上传，并且自动关闭流
							Streams.copy(is, new FileOutputStream(PATH + "/" + fileName), true);
							// fis.getFieldName表示获取表单域的名称，fis.getName表示获取文件的名称
							params.put(fis.getFieldName(), new String[] { fileName });
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 如果不是multipart，直接通过请求获取值
			params = request.getParameterMap();
		}
	}

	private void setFormParams(String fieldName, InputStream is) throws IOException {
		if (params.containsKey(fieldName)) {
			// 说明表单域中已经存在了值，这个时候就需要往params的String的数组中添加
			String[] values = params.get(fieldName);
			// 让数组长度 + 1
			values = Arrays.copyOf(values, values.length + 1);
			// 添加到数组中
			values[values.length - 1] = Streams.asString(is);
			params.put(fieldName, values);
		} else {
			params.put(fieldName, new String[] { Streams.asString(is) });
		}
	}

	@Override
	public String getParameter(String name) {
		String[] values = params.get(name);
		if (values != null) {
			return values[0];
		}
		return null;
	}

	@Override
	public Map getParameterMap() {
		return super.getParameterMap();
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = params.get(name);
		if (values != null) {
			return values;
		}
		return null;
	}

}
