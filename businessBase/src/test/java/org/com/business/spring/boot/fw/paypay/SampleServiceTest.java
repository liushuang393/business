package org.com.business.spring.boot.fw.paypay;

import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.PaymentApi;
import jp.ne.paypay.model.MerchantOrderItem;
import jp.ne.paypay.model.MoneyAmount;
import jp.ne.paypay.model.Payment;
import jp.ne.paypay.model.PaymentDetails;
import jp.ne.paypay.model.PaymentState;
import jp.ne.paypay.model.QRCodeDetails;
import jp.ne.paypay.model.ResultInfo;

import org.com.business.spring.boot.fw.paypay.model.QrCodeRequest;
import org.com.business.spring.boot.fw.paypay.services.SampleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
class SampleServiceTest {

//	//@Mock
//	@Autowired
//	PaymentApi paymentApi;
//
//	//@InjectMocks
//	@Autowired
//	SampleService sampleService;
//
//	//@Mock
//	@Autowired
//	Environment env;
//
//	@BeforeEach
//	void setUp() {
//		//MockitoAnnotations.initMocks(this);
//	}
//
//	//@Test
//	@DisplayName("Create QR code")
//	void createQRCodeTest() throws Exception {
//		MoneyAmount amount = new MoneyAmount().amount(10).currency(MoneyAmount.CurrencyEnum.JPY);
//		MerchantOrderItem orderItem = new MerchantOrderItem();
//		orderItem.setCategory("Dessert");
//		orderItem.setName("Red Velvet Cake");
//		orderItem.setProductId("100");
//		orderItem.setQuantity(1);
//		orderItem.setUnitPrice(amount);
//		QrCodeRequest qrCodeRequest = new QrCodeRequest();
//		qrCodeRequest.setAmount(amount);
//		List<MerchantOrderItem> orderItems = new ArrayList<>();
//		orderItems.add(orderItem);
//		qrCodeRequest.setOrderItems(orderItems);
//
//		QRCodeDetails qrCodeDetails = new QRCodeDetails();
//		qrCodeDetails.setResultInfo(new ResultInfo().message("Success"));
//		Mockito.when(paymentApi.createQRCode(Mockito.any())).thenReturn(qrCodeDetails);
//		Mockito.when(env.getProperty("REDIRECT_PATH")).thenReturn("http://localhost:8080/orderpayment");
//		QRCodeDetails qrCode = sampleService.createQrCode(paymentApi, qrCodeRequest);
//		Assertions.assertEquals(qrCode.getResultInfo().getMessage(), "Success");
//
//	}
//
//	//@Test
//	@DisplayName("Get Payment Details")
//	void getPaymentDetailsTest() throws Exception {
//		String merchantId = UUID.randomUUID().toString();
//		PaymentDetails paymentDetails = new PaymentDetails();
//		Payment payment = new Payment();
//		payment.setStatus(PaymentState.StatusEnum.COMPLETED);
//		paymentDetails.setData(payment);
//		paymentDetails.setResultInfo(new ResultInfo().message("Success"));
//		Mockito.when(paymentApi.getCodesPaymentDetails(merchantId)).thenReturn(paymentDetails);
//		PaymentDetails result = sampleService.fetchPaymentDetails(paymentApi, merchantId);
//		Assertions.assertEquals(result.getResultInfo().getMessage(), "Success");
//		Assertions.assertEquals(result.getData().getStatus(), PaymentState.StatusEnum.COMPLETED);
//	}
//
//	@Test
//	@DisplayName("Update API Client details")
//	void updateApiClientTest() {
//		Mockito.when(env.getProperty(Mockito.anyString())).thenReturn("DummyKeys");
//		String result = null;
//		try {
//			result = sampleService.updateApiClient(new ApiClient());
//		} catch (ApiException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}
//		Assertions.assertEquals(result,"Success");
//	}
//
//	@Test
//	@DisplayName("No Api Client details provided")
//	void updateNoApiClientTest() {
//		String result = null;
//		try {
//			result = sampleService.updateApiClient(new ApiClient());
//		} catch (ApiException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}
//		Assertions.assertEquals(result,"No API Key/Secret set");
//	}
}
