package siyl.cit.shopping.model;

import java.io.ByteArrayOutputStream;
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

public class MultipartWrapper extends HttpServletRequestWrapper {
	private Map<String, String[]> params = null;

	public MultipartWrapper(HttpServletRequest request) {
		super(request);
		params = new HashMap<String, String[]>();
		setParams(request);
	}

	@SuppressWarnings("unchecked")
	private void setParams(HttpServletRequest request) {
		// 1、判断是否是multipart类型
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			ServletFileUpload upload = new ServletFileUpload();
			InputStream is = null;
			ByteArrayOutputStream baos = null;
			try {
				FileItemIterator iter = upload.getItemIterator(request);
				while (iter.hasNext()) {
					FileItemStream fis = iter.next();
					is = fis.openStream();
					if (fis.isFormField()) {
						// params.put(fis.getFieldName(), new String[] { Streams.asString(is) });
						setFormParams(fis.getFieldName(), is);
					} else {
						/**
						 * 将一个文件输入流转换为字节数组需要通过ByteArrayOutputStream
						 */
						baos = new ByteArrayOutputStream();
						int len = 0;
						byte[] buf = new byte[1024];
						while ((len = is.read(buf)) > 0) {
							// 这里就可以把输入流输出到一个直接数组流中
							baos.write(buf, 0, len);
						}
						byte[] fs = baos.toByteArray();
						request.setAttribute("fs", fs);
						params.put(fis.getFieldName(), new String[] { fis.getName() });
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (baos != null)
						baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					if (is != null)
						is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
	public Map<String, String[]> getParameterMap() {
		return params;
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
