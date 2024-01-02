package org.com.business.spring.boot.common.utils.itextpdf;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class PdfReportView extends AbstractView {

	private static final String CONTENT_TYPE = "application/pdf";

	private String templatePath;
	private String exportFileName;

	public PdfReportView(String templatePath, String exportFileName) {
		this.templatePath = templatePath;
		if (exportFileName != null) {
			try {
				exportFileName = URLEncoder.encode(exportFileName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		this.exportFileName = exportFileName;
		setContentType(CONTENT_TYPE);
	}

	protected Map<String, Object> getParamsMap(Map<String, Object> map)
			throws Exception {
		Map<String, Object> params = new HashMap<>();
		for (String key : map.keySet()) {
			Object val = map.get(key);
			if (val instanceof JRDataSource) {
				continue;
			} else {
				params.put(key, val);
			}
		}
		return params;
	}

	protected JRDataSource getDataSource(Map<String, Object> map)
			throws Exception {
		for (String key : map.keySet()) {
			Object val = map.get(key);
			if (val instanceof JRDataSource) {
				return (JRDataSource) map.get(key);
			}
		}
		return new JREmptyDataSource();
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		response.setCharacterEncoding("utf-8");
		response.setContentType(getContentType());
		// response.setContentType("application/octet-stream");
		response.setHeader("content-disposition",
				"attachment;filename=" + exportFileName + ".pdf");

		try (InputStream inputStream = Thread.currentThread()
				.getContextClassLoader().getResourceAsStream(templatePath)) {

			try (OutputStream ouputStream = response.getOutputStream()) {


				JasperPrint jasperPrint = JasperFillManager.fillReport(
						inputStream, getParamsMap(map), getDataSource(map));

				// 帳票レイアウト ファイル (.jrxml) を実行時に動的にコンパイルし、帳票を作成します。
//	            JasperPrint jasperPrint = JasperFillManager.fillReport(
//	                this.createLazyCompiledReport(), this.createParameters(), this.createDataSource());

	            // コンパイル済み帳票レイアウト ファイル (.jasper) から、帳票を作成します。
	            // JasperPrint jasperPrint = JasperFillManager.fillReport(
	            //     this.createPreCompiledReport(), this.createParameters(), this.createDataSource());

				JasperExportManager.exportReportToPdfStream(jasperPrint,
						ouputStream);


//				            JRPdfExporter exporter = new JRPdfExporter();
//				            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//
//				 exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
//				            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
//				            exporter.setConfiguration(configuration);
//				            exporter.exportReport();

				//ByteArrayOutputStream byteOutStm = new ByteArrayOutputStream();

				//byte[] waterBytes = addWatermark(new ByteArrayInputStream(byteOutStm.toByteArray()),"SAMPLE");

				//ouputStream.write(waterBytes);

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 透かし
	 *
	 * @param inputStream
	 * @param watermark
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public byte[] addWatermark(InputStream inputStream, String watermark)
			throws IOException, DocumentException {

		PdfReader reader = new PdfReader(inputStream);
		try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
			PdfStamper stamper = new PdfStamper(reader, os);
			int total = reader.getNumberOfPages() + 1;
			PdfContentByte content;
			// フォント設定
			BaseFont baseFont = BaseFont.createFont("msgothic.ttc",
					BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			// 循环对每页插入水印
			for (int i = 1; i < total; i++) {
				// 水印的起始
				content = stamper.getUnderContent(i);
				// 开始
				content.beginText();
				// 设置颜色
				content.setColorFill(new BaseColor(244, 244, 244));
				// 设置字体及字号
				content.setFontAndSize(baseFont, 50);
				// 设置起始位置
				content.setTextMatrix(400, 780);
				for (int x = 0; x < 5; x++) {
					for (int y = 0; y < 5; y++) {
						content.showTextAlignedKerned(Element.ALIGN_CENTER,
								watermark, (100f + x * 350), (40.0f + y * 150),
								30);
					}
				}
				content.endText();
			}
			stamper.close();
			return os.toByteArray();
		} finally {
			reader.close();
		}

	}
}