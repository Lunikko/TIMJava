package com.sevlow.sdk.tim.api.impl;

import com.sevlow.sdk.tim.api.TIMAccountService;
import com.sevlow.sdk.tim.api.TIMService;
import com.sevlow.sdk.tim.api.test.TestModule;
import com.sevlow.sdk.tim.bean.ImportAccountsResult;
import com.sevlow.sdk.tim.bean.account.TIMAccount;
import com.sevlow.sdk.tim.common.error.TIMException;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Arrays;

/**
 * @author Element
 * @Package com.sevlow.sdk.tim.api.impl
 * @date 2019-05-27 15:30
 * @Description:
 */
@Slf4j
@Guice(modules = {TestModule.class})
public class TIMAccountServiceImplTest {

	@Inject
	private TIMService timService;

	private TIMAccountService accountService;

	@BeforeTest
	public void before() {
		this.accountService = timService.getAccountService();
	}

	@Test
	public void testSingleImport() throws TIMException {

		TIMAccount account = new TIMAccount("71243920540958720");
		account.setNick("哈哈哈");
		account.setFaceUrl("https://res-test.7billion.cn/hope/users/71243920540958720/avatar/327f03fa-d5be-40f9-8c74-4e00c202b0dc");

		accountService.singleImport(account);

	}

	@Test
	public void testMultiImport() throws TIMException {

		ImportAccountsResult result = accountService.multiImport(Arrays.asList("69887072709640192", "71243920540958720", "test_4", "test_5"));
		Assert.assertEquals(0, result.getFailAccounts().size());

	}

	@Test
	public void testKick() throws TIMException {

		accountService.kick("test_2");

	}

}
