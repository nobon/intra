package com.kkhts.intra.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

	@Id
	/** ID(社員番号) */
	private Long id;

	/** 氏名(姓) */
	private String familyName;

	/** 氏名(名) */
	private String lastName;

	/** メールアドレス */
	private String mailAddress;

	/** グループID */
	private String groupId;

	/** パスワード */
	private String password;

	@Override
	/** 氏名 */
	public String getUsername() {
		return familyName + " " + lastName;
	}

	@Override
	/** 権限一覧 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	/** アカウント有効期限判定 */
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	/** アカウント停止判定 */
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	/** アカウント資格判定 */
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	/** アカウント有効性判定 */
	public boolean isEnabled() {
		return true;
	}

}