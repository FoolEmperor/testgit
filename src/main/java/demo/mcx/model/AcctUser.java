package demo.mcx.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "acct_user", catalog = "work")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AcctUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6980093847795726310L;
	private String id;
	private String nickName;
	private String telephone;
	private Date registerTime;
	private Set<AcctRole> acctRoles = new HashSet<AcctRole>(0);

	public AcctUser() {

	}

	public AcctUser(String id, String nickName) {
		this.id = id;
		this.nickName = nickName;
	}

	public AcctUser(String id, String nickName, String telephone,
			Date registerTime, Set<AcctRole> acctRoles) {
		this.id = id;
		this.nickName = nickName;
		this.telephone = telephone;
		this.registerTime = registerTime;
		this.acctRoles = acctRoles;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "nick_name", nullable = false)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "telephone")
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "register_time", length = 19)
	public Date getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	/**
	 * 1.CacheConcurrencyStrategy.NONE 　
	 * 不适用，默认 
	 * 2.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE   
	 * 更新不频繁几个小时或更长 
	 * 3.CacheConcurrencyStrategy.READ_ONLY 　
	 * 对于不发生改变的数据使用 [size=large][/size] 
	 * 4.CacheConcurrencyStrategy.READ_WRITE  　
	 * 基于时间戳判定机制，，对于数据同步要求严格的情况，使用频繁 
	 * 5.CacheConcurrencyStrategy.TRANSACTIONAL 
	 * 运行在jta环境种，基于事务
	 */
	
	@JsonIgnoreProperties(value={"acctUsers", "acctAuthorities"})
	@ManyToMany(fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinTable(name = "acct_user_role", catalog = "work", 
	joinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) }, 
	inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
	public Set<AcctRole> getAcctRoles() {
		return this.acctRoles;
	}

	public void setAcctRoles(Set<AcctRole> acctRoles) {
		this.acctRoles = acctRoles;
	}

}
