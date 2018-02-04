package com.kkhts.intra.form;

import lombok.Data;

/**
 * ログインチェック画面のFormModel
 */
@Data
public class LoginForm {

	/** ログインID */
	private String loginId;

	/** パスワード */
	private String password;

}
