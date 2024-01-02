package org.com.business.spring.boot.fw.paypay;

import org.junit.Test;

import jp.ne.paypay.ApiException;

public class PayPayApiClientTest {

	private static final String merchantPaymentId = "DEVELOPER-PANEL-DEMO-c45d3843-1394-48c3-9da9-952125a00f15";

	//@Test
	public void testpayWebGetPaymentDetail() {
		PayPayApiClient payPayApiClient = new PayPayApiClient();
		try {
			payPayApiClient.payWebGetPaymentDetail(merchantPaymentId);
		} catch (ApiException e) {

			e.printStackTrace();
		}
	}

	//@Test
	public void testPayWebCreatQRCode() {

		PayPayApiClient payPayApiClient = new PayPayApiClient();
		try {
			payPayApiClient.payWebCreatQRCode(merchantPaymentId);
		} catch (ApiException e) {
			e.printStackTrace();
		}

	}
}
